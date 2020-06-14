package com.yiding.saas.ydsaas.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yiding.saas.ydsaas.common.utils.DateTimeUtils;
import com.yiding.saas.ydsaas.dao.SysDictMapper;
import com.yiding.saas.ydsaas.dao.YdWarehouseMapper;
import com.yiding.saas.ydsaas.dto.YdWareHouseLogDto;
import com.yiding.saas.ydsaas.model.*;
import com.yiding.saas.ydsaas.service.YdAcquisitionPlanService;
import com.yiding.saas.ydsaas.service.YdOrganizationService;
import com.yiding.saas.ydsaas.service.YdWarehouseService;
import com.yiding.saas.ydsaas.service.util.DateUtils;
import com.yiding.saas.ydsaas.service.util.DictUtils;
import com.yiding.saas.ydsaas.service.util.PageUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author BruceLee
 * @Date 2020/5/25
 */
@Service
public class YdWarehouseImpl implements YdWarehouseService {

    private double totalWeight = 0;

    @Autowired
    private YdWarehouseMapper ydWarehouseMapper;
    @Autowired
    private YdOrganizationService ydOrganizationService;
    @Autowired
    private YdAcquisitionPlanService ydAcquisitionPlanService;


    @Override
    public YdPageDO findPageList(YdWarehousePageDO ydWarehouseDO) {
        int pageNums = (ydWarehouseDO.getPageNum() - 1) * ydWarehouseDO.getPageSize();
        int pageSize = ydWarehouseDO.getPageSize();
        ydWarehouseDO.setPageNum(pageNums);
        //如果前端未传时间条件，则默认查询一年内的数据
        if (ydWarehouseDO.getStartDate() == null && ydWarehouseDO.getEndDate() == null) {
            SimpleDateFormat s = new SimpleDateFormat("yyyy");
            ydWarehouseDO.setStartDate(s.format(new Date()) + "-01-01");
            ydWarehouseDO.setEndDate(s.format(new Date()) + "-12-31");
        }
        int totalSize = ydWarehouseMapper.getCounts(ydWarehouseDO);
        List<YdWarehouseLogDO> list2 = ydWarehouseMapper.findPageList(ydWarehouseDO);
        double totalWeight = 0;//总出入库重量 担
        double inOutFrameWeight = 0;//烟框出入库总重量
        double inOutFrameCount = 0;//烟框出入库总数量
        double inOutPageWeight = 0;//烟包出入库总重量
        double inOutPageCount = 0;//烟包出入库总数量
        for (YdWarehouseLogDO y : list2) {
            totalWeight = totalWeight + Double.valueOf(y.getWeightLog());
            if (y.getPackageType().equals("0")) {
                inOutPageWeight = inOutPageWeight + Double.valueOf(y.getWeightLog());
                inOutPageCount = inOutPageWeight / 0.8;
                y.setPackageType("烟包");
            } else if (y.getPackageType().equals("1")) {
                y.setPackageType("烟框");
                inOutFrameWeight = inOutFrameWeight + Double.valueOf(y.getWeightLog());
                inOutFrameCount = inOutFrameWeight / 12;
            }
        }
        YdPageDO ydPageDO = PageUtils.getLogPage(list2, pageNums, pageSize, totalSize);
        ydPageDO.setTotalWeight(totalWeight);
        ydPageDO.setInOutFrameCount(inOutFrameCount);
        ydPageDO.setInOutPageCount(inOutPageCount);
        return ydPageDO;
    }

    @Override
    public YdWarehouseLogDO warehouseDetails(Integer logId) {
        YdWarehouseLogDO ydWarehouseLogDO = ydWarehouseMapper.warehouseDetails(logId);
        //字典转换
        String variety = "";
        variety = new DictUtils().getDictLabels("tobacco_type", ydWarehouseLogDO.getVariety());
        ydWarehouseLogDO.setVariety(variety);

        if (Integer.valueOf(ydWarehouseLogDO.getPackageType()) == 0) {
            ydWarehouseLogDO.setPackageType("烟包");
        } else {
            ydWarehouseLogDO.setPackageType("烟框");
        }
        List<YdProductInfoDO> list = ydWarehouseMapper.getProductInfos(logId);
        for (YdProductInfoDO y : list) {
            //烟包产地信息处理方式不同于烟框
            if (y.getDistrictIds() != null) {
                String[] ids = y.getDistrictIds().split("-");
                List<String> list1 = ydWarehouseMapper.getDistictName(ids);
                y.setCityName(list1.get(0) + list1.get(1));
                y.setTownName(list1.get(2));
            }
        }

        ydWarehouseLogDO.setList(list);
        return ydWarehouseLogDO;
    }

    @Override
    @Transactional
    public String saveWarehouse(YdWarehouseLogDO ydWarehouseLogDO) {
        double weight = Double.valueOf(ydWarehouseLogDO.getWeightLog()) * 50;//获取担数，一担50公斤/kg，转换为kg，目前没用但是产品要求存
        ydWarehouseLogDO.setCount(1);//每次设备只能扫描一包/框。此处定值1
        ydWarehouseLogDO.setWeight(String.valueOf(weight));
        //如果是复烤厂，则默认创建一个仓库 设置仓库id为0，产品说复烤厂可能有仓库也可能没仓库，所以设计有仓库，但是复烤厂都默认存0，根据复烤厂和烟种类判断
        if (ydWarehouseLogDO.getStatus() == 1) {
            ydWarehouseLogDO.setRepertoryId(0);
        }
        //查询仓库内是否已经创建烟站仓库/复烤厂
        YdWarehouseLogDO ydWarehouseLogDO1 = ydWarehouseMapper.getWarehouseInfo(ydWarehouseLogDO.getTobaccoStation(), ydWarehouseLogDO.getTobaccoId(), ydWarehouseLogDO.getRepertoryId());
        if (ydWarehouseLogDO.getInOutType().equals("0")) {
            //TODO 仓库剩余容量小于入库容量要返回错误
            //如果查询到，则在原有库存数上加上新入库的
            if (ydWarehouseLogDO1 != null) {
                BigDecimal b1 = BigDecimal.valueOf(Double.valueOf(ydWarehouseLogDO.getWeightLog()) * 50);
                BigDecimal b2 = BigDecimal.valueOf(Double.valueOf(ydWarehouseLogDO1.getWeightOne()));
                BigDecimal b3 = BigDecimal.valueOf(Double.valueOf(ydWarehouseLogDO.getWeightLog()));
                BigDecimal b4 = BigDecimal.valueOf(Double.valueOf(ydWarehouseLogDO1.getWeightTwo()));
                ydWarehouseLogDO.setWeightOne(String.valueOf(b1.add(b2)));
                ydWarehouseLogDO.setWeightTwo(String.valueOf(b3.add(b4)));
                ydWarehouseLogDO.setId(ydWarehouseLogDO1.getId());
            } else {//查询不到数据则新增仓库
                ydWarehouseLogDO.setWeightOne(String.valueOf(Double.valueOf(ydWarehouseLogDO.getWeightLog()) * 50));
                ydWarehouseLogDO.setWeightTwo(ydWarehouseLogDO.getWeightLog());
                ydWarehouseLogDO.setUnitOne("kg");
                ydWarehouseLogDO.setUnitTwo("担");
                ydWarehouseLogDO.setId(0);
            }
        } else {
            if (Double.valueOf(ydWarehouseLogDO1.getWeightTwo()) - Double.valueOf(ydWarehouseLogDO.getWeightLog()) < 0) {
                return "出库数大于库存数";
            }
            BigDecimal b1 = BigDecimal.valueOf(Double.valueOf(ydWarehouseLogDO.getWeightLog()) * 50);
            BigDecimal b2 = BigDecimal.valueOf(Double.valueOf(ydWarehouseLogDO1.getWeightOne()));
            BigDecimal b3 = BigDecimal.valueOf(Double.valueOf(ydWarehouseLogDO.getWeightLog()));
            BigDecimal b4 = BigDecimal.valueOf(Double.valueOf(ydWarehouseLogDO1.getWeightTwo()));
            ydWarehouseLogDO.setWeightOne(String.valueOf(b2.subtract(b1)));
            ydWarehouseLogDO.setWeightTwo(String.valueOf(b4.subtract(b3)));
            ydWarehouseLogDO.setId(ydWarehouseLogDO1.getId());
        }
        ydWarehouseLogDO.setInOutDate(new Date());
        ydWarehouseMapper.saveWarehouse(ydWarehouseLogDO);//库存，执行顺序不能变，log的id会影响库存表的sql执行
        ydWarehouseMapper.saveLogInfo(ydWarehouseLogDO);//保存到出入库明细表
        ydWarehouseMapper.saveProductInfo(ydWarehouseLogDO);//保存产地信息和烟农
        return "操作完程";
    }

    @Override
    public YdPageDO findPageWarehouseList(YdWarehousePageDO ydWarehousePageDO) {
        int pageNums = (ydWarehousePageDO.getPageNum() - 1) * ydWarehousePageDO.getPageSize();
        int pageSize = ydWarehousePageDO.getPageSize();
        int totalSize = 0;
        double count = 0;//库存数量
        ydWarehousePageDO.setPageNum(pageNums);
        List<YdWarehouseDO> list = new ArrayList<>();
        List<YdWarehouseDO> lists = new ArrayList<>();
        List<YdWarehousePercentDO> list3 = new ArrayList();
        //下拉框条件检索，用户不选择则默认为0
        //三种检索条件，第一种选择州，选择县，选择镇
        if (ydWarehousePageDO.getStateId() != 0 && ydWarehousePageDO.getCityId() != 0 && ydWarehousePageDO.getTownId() != 0) {
            List list1 = new ArrayList<>();
            list1.add(ydWarehousePageDO.getTownId());
            ydWarehousePageDO.setList2(list1);
        }
        //第二种选择州，选择县，不选镇
        if (ydWarehousePageDO.getStateId() != 0 && ydWarehousePageDO.getCityId() != 0 && ydWarehousePageDO.getTownId() == 0) {
            List list1 = ydWarehouseMapper.getSonIdLists(ydWarehousePageDO.getCityId());
            ydWarehousePageDO.setList2(list1);

        }
        //第三种，选择州，不选择县，不选择镇 用户什么都不选，默认选择当前最大数据权限
        if (ydWarehousePageDO.getStateId() != 0 && ydWarehousePageDO.getCityId() == 0 && ydWarehousePageDO.getTownId() == 0) {
            List list1 = ydWarehouseMapper.getSonIdLists(ydWarehousePageDO.getStateId());//获取县id列表
            List list2 = ydWarehouseMapper.getDistrictId(list1);//获取镇id列表
            ydWarehousePageDO.setList2(list2);
        }

        /**
         * 扇形图数据
         * */
        lists = ydWarehouseMapper.findPageWarehouseList2(ydWarehousePageDO);//符合条件的全部数据,不带分页
        list3 = ydWarehouseMapper.findWarehouseList(ydWarehousePageDO);//符合条件的全部仓库
        List<YdWarehousePercentDO> list6 = this.getPercent(lists, list3);//扇形图等级占比和站点+仓库名称组合以及仓库总重量
        /**
         * 列表数据
         * */
        totalSize = ydWarehouseMapper.getCounts2(ydWarehousePageDO);
        list = ydWarehouseMapper.findPageWarehouseList(ydWarehousePageDO);//符合条件的全部数据,带分页
        List<YdWarehouseDO> list5 = this.getCountAndPackageType(list, count);//烟包烟框类型转换和当前仓库下的包或框数量计算
        return PageUtils.getRepertoryPage(list5, list6, pageNums, pageSize, totalSize);
    }

    public List<YdWarehousePercentDO> getPercent(List<YdWarehouseDO> list, List<YdWarehousePercentDO> list3) {
        int repertoryId = 0;//仓库id
        double totalWeight = 0;//仓库烟总重量
        Map map = new HashMap();
        List<YdWarehousePercentDO> list6 = new ArrayList<>();
        for (int i = 0; i < list3.size(); i++) {
            List<Map<String, Double>> listPercent = new ArrayList();
            YdWarehousePercentDO ydWarehousePercentDO = new YdWarehousePercentDO();
            String s = list3.get(i).getTobaccoStation() + list3.get(i).getRepertoryName();//拼接站点+仓库号码组合
            repertoryId = list.get(i).getOrgId();
            //下边算法是获取当前查询条件下，仓库内各个等级的‘库存占比’（不是库容占比，但是现在产品把库容当库存来思考，原型已截图有证据）和仓库的总重量（担）
            for (int j = 0; j < list.size(); j++) {
                if (repertoryId == list.get(j).getOrgId()) {
                    //累加获取当前条件下的仓库的总担数
                    totalWeight = totalWeight + Double.valueOf(list.get(j).getWeightTwo());
                    //用仓库id当key，存储该仓库id下的总重量
                    map.put(list3.get(i).getRepertoryId(), totalWeight);
                }
            }
            this.totalWeight = totalWeight;//赋予全局变量，供列表计算库存比使用
            for (int k = 0; k < list.size(); k++) {
                if (repertoryId == list.get(k).getOrgId()) {
                    double persent = Double.valueOf(list.get(k).getWeightTwo()) / (double) map.get(repertoryId);
                    list.get(k).setWarehousePercent(persent);//列表数据，更改列表内的库存占比
                    Map map2 = new HashMap();
                    map2.put(list.get(k).getTobaccoLevel(), persent);//扇形图的等级库存占比
                    listPercent.add(map2);
                }
            }
            ydWarehousePercentDO.setList(listPercent);//存等级比
            ydWarehousePercentDO.setTotalWeight(totalWeight);//存仓库总重量
            ydWarehousePercentDO.setRepertoryName(s);//存站点+仓库号码组合
            list6.add(ydWarehousePercentDO);
        }
        return list6;
    }

    public List<YdWarehouseDO> getCountAndPackageType(List<YdWarehouseDO> list, double count) {

        //获取打包状态，当前站点的某一仓库下每种等级数量
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setWarehousePercent(Double.valueOf(list.get(i).getWeightTwo()) / this.totalWeight);
            //下面是打包类型转换和仓库各个等级的烟叶重量转换为数量
            if (list.get(i).getPackageType().equals("0")) {
                list.get(i).setPackageType("烟包");
                count = Double.valueOf(list.get(i).getWeightTwo()) / 0.8;
                list.get(i).setCount(count);
            } else {
                list.get(i).setPackageType("烟框");
                count = Double.valueOf(list.get(i).getWeightTwo()) / 12;
                list.get(i).setCount(count);
            }
        }
        return list;
    }

    @Override
    public Map getRepertory(YdWarehouseLogDO ydWarehouseLogDO) {
        //如果前端未传时间条件，则默认查询一年内的数据
        if (ydWarehouseLogDO.getStartDate() == null && ydWarehouseLogDO.getEndDate() == null) {
            SimpleDateFormat s = new SimpleDateFormat("yyyy");
            ydWarehouseLogDO.setStartDate(s.format(new Date()) + "-01-01");
            ydWarehouseLogDO.setEndDate(s.format(new Date()) + "-12-31");
        }
        //获取当前判断条件下的出入库的去重复的全部的仓库名称在web端并排显示，2020-06-05 下午 产品如此设计，建议过未同意
        List list2 = ydWarehouseMapper.getRepertoryNames(ydWarehouseLogDO);
        List<YdWarehouseLogDO> list = ydWarehouseMapper.getRepertory(ydWarehouseLogDO);
        List<YdTobacco> list1 = ydWarehouseMapper.getTobaccoLevels();
        Map map = this.calculateLogPercent(list,list1);
        map.put("tobaccoStations", list2);//存仓库集合
        return map;
    }

    @Override
    public List<YdWarehouseLogDO> getListByRfid(List<String> rfids) {
        return ydWarehouseMapper.getListByRfid(rfids);
    }

    @Override
    public List<YdWarehouseLogDO> findList(Integer transportId) {
        List<YdWarehouseLogDO> ydWarehouseLogDOList = new ArrayList<YdWarehouseLogDO>();
        if (transportId != null && transportId > 0) {
            ydWarehouseLogDOList = ydWarehouseMapper.findList(transportId);
        }
        return ydWarehouseLogDOList;
    }

    @Override
    @Transactional
    public Map findByRfidList(YdWarehousePageDO ydWarehousePageDO) {
        List<YdWarehouseLogDO> list1=new ArrayList<>();
//        if (ydWarehousePageDO.getPageNum()==0&&ydWarehousePageDO.getPageSize()==0){
//           list1= ydWarehouseMapper.findByRfidList(ydWarehousePageDO);//供发车使用获取全部数据，不需要分页
//        }else {
//            list1 = ydWarehouseMapper.findPageByRfidList(ydWarehousePageDO);//分页数据
//        }
        list1= ydWarehouseMapper.findByRfidList(ydWarehousePageDO);//供发车使用获取全部数据，不需要分页
        int totalSize=ydWarehouseMapper.getCounts3(ydWarehousePageDO);
        List<YdWarehouseLogDO> list3 = ydWarehouseMapper.findByRfidList(ydWarehousePageDO);//全部数据用来计算等级占比
        List<YdTobacco> list2 = ydWarehouseMapper.getTobaccoLevels();
        for (YdWarehouseLogDO y:list1){
            List<YdProductInfoDO> ydProductInfoDO= ydWarehouseMapper.getProductInfos(y.getId());
            y.setList(ydProductInfoDO);
            if ("0".equals(y.getPackageType())){
                y.setPackageType("烟包");
            }else if ("1".equals(y.getPackageType())){
                y.setPackageType("烟框");
            }
        }
        Map map= this.calculateLogPercent(list3,list2);

        return PageUtils.getRepertoryPage2(list1,map,ydWarehousePageDO.getPageNum(),ydWarehousePageDO.getPageSize(),totalSize);
    }
    public Map calculateLogPercent(List<YdWarehouseLogDO> list1,List<YdTobacco> list2){
        Map map=new HashMap();
        if (list1.size() != 0) {
            double total = 0;
            Map map1 = new HashMap();
            for (YdTobacco yt : list2) {
                double weight = 0;
                for (YdWarehouseLogDO y : list1) {
                    if (y.getWeightLog()==null){
                        map.put(500,"净重担为空");
                        return map;
                    }
                    if (y.getTobaccoLevel().equals(yt.getTobaccoLevel())) {
                        weight = weight + Double.valueOf(y.getWeightLog());
                    }
                }
                map1.put(yt.getTobaccoLevel(), weight);
                total = total + weight;
            }
            for (YdTobacco yt : list2) {
                BigDecimal b1 = BigDecimal.valueOf((double) map1.get(yt.getTobaccoLevel()));
//                BigDecimal b2 = BigDecimal.valueOf(total);
                map.put(yt.getTobaccoLevel(), b1);//存扇形图烟叶等级占比
            }
           map.put("total",total);
        }
        return map;
    }
    
    @Override
    public void findWarehouseScreenData(YdWarehousePageDO ydWarehousePageDO,JSONObject json){
    	//当前库存(担)
    	BigDecimal weightTwo = new BigDecimal(0);
    	int i=0;
    	//打包类型的数量 XX包/框
    	BigDecimal packageTypeNum=new BigDecimal(0);
    	//打包类型
    	String packageType="-1";
    	//获取仓库当前库存数据
    	List<YdWarehouseDO> ydWarehouseDOList = new ArrayList<YdWarehouseDO>();
    	ydWarehouseDOList = ydWarehouseMapper.findTobaccoWarehouseList(ydWarehousePageDO);
    	if(ydWarehouseDOList!=null && ydWarehouseDOList.size()>0){
    		for(YdWarehouseDO ydWarehouseDO :ydWarehouseDOList){
    			if(i==0){
    				packageType = ydWarehouseDO.getPackageType();
    			}
    			if(StringUtils.isNotBlank(ydWarehouseDO.getWeightTwo())){
    				weightTwo= weightTwo.add(new BigDecimal(ydWarehouseDO.getWeightTwo()));
    				i++;
    			}
    		}
    	}
    	if("0".equals(packageType)){//烟包
    		packageTypeNum = weightTwo.multiply(new BigDecimal(1.25));
    	}else{
    		packageTypeNum = weightTwo.divide(new BigDecimal(12));
    	}
    	//当前库存(担)
    	json.put("weightTwo", weightTwo+"");
    	//库存packageType
    	json.put("packageType", packageType+"");
    	//库存packageType数量
    	json.put("packageTypeNum", packageTypeNum+"");
    	//仓库当前库存数据list
    	json.put("ydWarehouseDOList", ydWarehouseDOList);
    	//获取仓库容积
    	YdOrganization org = ydOrganizationService.queryById((long)ydWarehousePageDO.getRepertoryId());
    	if(org!=null){
    		json.put("storehouseVolume", new BigDecimal(org.getStorehouseVolume()).divide(new BigDecimal(50))+"");
    	}else{
    		json.put("storehouseVolume", new BigDecimal(0)+"");
    	}
    	//今日出库入库数量
    	BigDecimal inWeightLog = new BigDecimal(0);
    	BigDecimal outWeightLog = new BigDecimal(0);
    	YdWarehouseDO ydWHDo = new YdWarehouseDO();
    	//当天
    	ydWHDo.setIstoday(1);
    	//仓库id
    	ydWHDo.setOrgId(ydWarehousePageDO.getRepertoryId());
    	List<YdWarehouseLogDO> ydWarehouseLogDOList = ydWarehouseMapper.findTodayList(ydWHDo);
    	if(ydWarehouseLogDOList!=null && ydWarehouseLogDOList.size()>0){
    		for(YdWarehouseLogDO ydWarehouseLogDO :ydWarehouseLogDOList){
    			if(StringUtils.isNotBlank(ydWarehouseLogDO.getInOutType()) && "0".equals(ydWarehouseLogDO.getInOutType())){
    				inWeightLog = inWeightLog.add(new BigDecimal(ydWarehouseLogDO.getWeightLog()));
    			}else if(StringUtils.isNotBlank(ydWarehouseLogDO.getInOutType()) && "1".equals(ydWarehouseLogDO.getInOutType())){
    				outWeightLog = outWeightLog.add(new BigDecimal(ydWarehouseLogDO.getWeightLog()));
    			}
    		}
    	}
    	json.put("inWeightLog", inWeightLog);
    	json.put("outWeightLog", outWeightLog);
    	//累计收购数量(担)
    	BigDecimal cumulativeWeight = new BigDecimal(0);
    	YdWarehouseDO ydWHDo1 = new YdWarehouseDO();
    	//当年
    	ydWHDo1.setIstoday(2);
    	//仓库id
    	ydWHDo1.setOrgId(ydWarehousePageDO.getRepertoryId());
    	List<YdWarehouseLogDO> ydWarehouseLogDOList1 = ydWarehouseMapper.findTodayList(ydWHDo1);
    	if(ydWarehouseLogDOList1!=null && ydWarehouseLogDOList1.size()>0){
    		for(YdWarehouseLogDO ydWarehouseLogDO :ydWarehouseLogDOList1){
    			if(StringUtils.isNotBlank(ydWarehouseLogDO.getInOutType()) && "0".equals(ydWarehouseLogDO.getInOutType())){
    				cumulativeWeight = cumulativeWeight.add(new BigDecimal(ydWarehouseLogDO.getWeightLog()));
    			}
    		}
    	}
    	
    	//获取收购计划
    	//计划收购数量(担)
        BigDecimal acquisitionWeight =new BigDecimal(0);
        //日收购进度
        BigDecimal todayProgress=new BigDecimal(0);
        //累计收购进度
        BigDecimal cumulativeProgress=new BigDecimal(0);
        //需要天数
        int needDays = 0;
        //还剩天数
        double remaining = 0;
    	YdAcquisitionPlan ydAcquisitionPlan = new YdAcquisitionPlan();
        ydAcquisitionPlan.setStorehouseId((long)ydWarehousePageDO.getRepertoryId());
        ydAcquisitionPlan.setStartTime(DateTimeUtils.getCurrYearFirst());
        ydAcquisitionPlan.setStopTime(DateTimeUtils.getCurrYearLast());
        List<YdAcquisitionPlan> list = ydAcquisitionPlanService.list(ydAcquisitionPlan);
        if(list!=null && list.size()>0){
        	ydAcquisitionPlan = list.get(0);
        	acquisitionWeight = (ydAcquisitionPlan!=null &&(StringUtils.isNotBlank(ydAcquisitionPlan.getAcquisitionWeight())))
        			?new BigDecimal(ydAcquisitionPlan.getAcquisitionWeight()):new BigDecimal(0);
        	todayProgress = inWeightLog.divide(acquisitionWeight.divide(new BigDecimal(50)))
        			.multiply(new BigDecimal(100));
        	if(todayProgress.compareTo(new BigDecimal(0))>0){//当日收购数量不为0时计算需要天数
        		needDays = new BigDecimal(1).multiply(new BigDecimal(100)).divide(todayProgress)
        				.setScale( 0, BigDecimal.ROUND_UP).intValue();
        	}
        	remaining = DateUtils.getDistanceOfTwoDate(new Date(), ydAcquisitionPlan.getStopTime());
        	cumulativeProgress = cumulativeWeight.divide(acquisitionWeight.divide(new BigDecimal(50)))
        			.multiply(new BigDecimal(100));
        }
        json.put("acquisitionWeight", acquisitionWeight.divide(new BigDecimal(50))+"");//计划收购数量(担)
        json.put("todayProgress", todayProgress+"%");//日计收购进度
        json.put("needDays", needDays);//需要天数
        json.put("remaining", remaining);//还剩天数
        json.put("cumulativeWeight", cumulativeWeight);//累计收购数量(担)
        json.put("cumulativeProgress", cumulativeProgress+"%");//累计收购进度
    }

    /**
     * 烟站出库操作
     * */
    @Override
    @Transactional
    public String saveRfids(List list) {
        YdWarehouseLogDO y=new YdWarehouseLogDO();
        y.setIsValid(0);
        y.setStatus(0);
        y.setInOutType("0");
        y.setList2(list);
       List<YdWarehouseLogDO> list1= ydWarehouseMapper.findByRfids(y);
       for (YdWarehouseLogDO ydWarehouseLogDO:list1){
           //查询仓库内是否已经创建烟站仓库/复烤厂
           YdWarehouseLogDO ydWarehouseLogDO1 = ydWarehouseMapper.getWarehouseInfo(ydWarehouseLogDO.getTobaccoStation(), ydWarehouseLogDO.getTobaccoId(), ydWarehouseLogDO.getRepertoryId());
           List<YdProductInfoDO> list2=ydWarehouseMapper.getProductInfos(ydWarehouseLogDO.getId());
           ydWarehouseLogDO.setInOutDate(new Date());
           ydWarehouseLogDO.setInOutType("1");
           ydWarehouseLogDO.setList(list2);
           if (ydWarehouseLogDO1==null){
               return "出库数据与记录数据不匹配";
           }
           if (Double.valueOf(ydWarehouseLogDO1.getWeightTwo()) - Double.valueOf(ydWarehouseLogDO.getWeightLog()) < 0) {
               return "出库数大于库存数";
           }
           BigDecimal b1 = BigDecimal.valueOf(Double.valueOf(ydWarehouseLogDO.getWeightLog()) * 50);
           BigDecimal b2 = BigDecimal.valueOf(Double.valueOf(ydWarehouseLogDO1.getWeightOne()));
           BigDecimal b3 = BigDecimal.valueOf(Double.valueOf(ydWarehouseLogDO.getWeightLog()));
           BigDecimal b4 = BigDecimal.valueOf(Double.valueOf(ydWarehouseLogDO1.getWeightTwo()));
           ydWarehouseLogDO.setWeightOne(String.valueOf(b2.subtract(b1)));
           ydWarehouseLogDO.setWeightTwo(String.valueOf(b4.subtract(b3)));
           ydWarehouseLogDO.setId(ydWarehouseLogDO1.getId());
           ydWarehouseLogDO.setDistrictIds(ydWarehouseLogDO.getList().get(0).getDistrictIds());//同一logId下的产地信息权限字段orgIds都是相同的，所以此处获取第一个即可
           ydWarehouseMapper.saveWarehouse(ydWarehouseLogDO);//库存，执行顺序不能变，log的id会影响库存表的sql执行
           ydWarehouseMapper.saveLogInfo(ydWarehouseLogDO);//保存到出入库明细表
           ydWarehouseMapper.saveProductInfo(ydWarehouseLogDO);//保存产地信息和烟农
       }
       return "操作完成";
    }
    /**
     * 编辑
     * */
    @Override
    @Transactional
    public Map updateWarehouse(YdWarehouseLogDO ydWarehouseLogDO) {
      Map map=new HashMap();
      YdWarehouseLogDO ydWarehouseLogDO2=ydWarehouseMapper.findLogInfoById(ydWarehouseLogDO.getId());
      YdWarehouseLogDO ydWarehouseLogDO3=ydWarehouseMapper.getWarehouseInfo(ydWarehouseLogDO.getTobaccoStation(),ydWarehouseLogDO.getTobaccoId(),ydWarehouseLogDO.getRepertoryId());
      if (Double.valueOf(ydWarehouseLogDO3.getWeightTwo())<Double.valueOf(ydWarehouseLogDO2.getWeightLog())){
          map.put(500,"编辑失败，编辑净重担大于库存数");
          return map;
      }
        ydWarehouseLogDO.setWeightOne(String.valueOf((Double.valueOf(ydWarehouseLogDO3.getWeightTwo())-Double.valueOf(ydWarehouseLogDO2.getWeightLog()))*50));//库存 kg
        ydWarehouseLogDO.setWeightTwo(String.valueOf(Double.valueOf(ydWarehouseLogDO3.getWeightTwo())-Double.valueOf(ydWarehouseLogDO2.getWeightLog())));//库存 担
        ydWarehouseMapper.updateWarehouse(ydWarehouseLogDO);//编辑库存表
        ydWarehouseMapper.updateLogInfo(ydWarehouseLogDO);//编辑出入库明细表
        ydWarehouseMapper.updateProductInfo(ydWarehouseLogDO);//编辑产地信息的address字段
        map.put(200,"操作完成");
        return map;
    }
}
