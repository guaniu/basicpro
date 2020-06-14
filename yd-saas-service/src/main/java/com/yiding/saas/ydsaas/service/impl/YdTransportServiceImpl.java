package com.yiding.saas.ydsaas.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.common.enums.CommonEnum;
import com.yiding.saas.ydsaas.common.utils.DateTimeUtils;
import com.yiding.saas.ydsaas.dao.YdOrganizationMapper;
import com.yiding.saas.ydsaas.dao.YdTransportGoodsMapper;
import com.yiding.saas.ydsaas.dao.YdTransportMapper;
import com.yiding.saas.ydsaas.dto.TransportTobaccoDto;
import com.yiding.saas.ydsaas.dto.WarnDto;
import com.yiding.saas.ydsaas.dto.YdTransportDto;
import com.yiding.saas.ydsaas.model.YdOrganization;
import com.yiding.saas.ydsaas.model.YdTransport;
import com.yiding.saas.ydsaas.model.YdTransportGoods;
import com.yiding.saas.ydsaas.model.YdWarehouseLogDO;
import com.yiding.saas.ydsaas.model.YdWarehousePageDO;
import com.yiding.saas.ydsaas.model.YdWarn;
import com.yiding.saas.ydsaas.service.IotService;
import com.yiding.saas.ydsaas.service.YdTransportService;
import com.yiding.saas.ydsaas.service.YdWarehouseService;
import com.yiding.saas.ydsaas.service.YdWarnService;
import com.yiding.saas.ydsaas.service.util.DateUtils;
import com.yiding.saas.ydsaas.service.util.DictUtils;
import com.yiding.saas.ydsaas.vo.TransportTobaccoVo;
import com.yiding.saas.ydsaas.vo.WarnVo;

import cn.jiguang.common.utils.StringUtils;
import javafx.util.Pair;

@Service
public class YdTransportServiceImpl implements YdTransportService {

    @Autowired
    private YdTransportMapper ydTransportMapper;
    @Autowired
    private YdTransportGoodsMapper ydTransportGoodsMapper;
    @Autowired
    private YdOrganizationMapper ydOrganizationMapper;
    @Autowired
    private YdWarnService ydWarnService;
    @Autowired
    private YdWarehouseService ydWarehouseService;
    @Autowired
    private IotService iotService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return ydTransportMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(YdTransport record) {
        return ydTransportMapper.insert(record);
    }

    @Override
    public YdTransport selectByPrimaryKey(Integer id) {
        return ydTransportMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<YdTransport> selectAll(YdTransport record) {
        //分页
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        //处理数据权限
        List<String> orgIds_str = new ArrayList<>();
        for (Long orgid : record.getOrgIds()) {
            orgIds_str.add("-" + orgid + "-");
        }
        //数据权限封装
        record.setOrgStrIds(orgIds_str);
        List<YdTransport> list = ydTransportMapper.selectAll(record);
        if (list != null && list.size() > 0) {
            for (YdTransport ydTransport : list) {
                if (ydTransport.getTransportState() == 5) {//确认收货
                    long diff = (ydTransport.getFinshTime().getTime() / 1000) - (ydTransport.getDepartTime().getTime() / 1000);
                    ydTransport.setUseTime(DateTimeUtils.secondToTime(diff));
                }
                if (ydTransport.getDepartTime() != null) {
                    long dt = ydTransport.getDepartTime().getTime() / 1000;
                    String dtLabel = DateTimeUtils.secondToDate(dt + ydTransport.getEstime(), DateTimeUtils.DATE_FORMAT);
                    ydTransport.setExpectedTime(dtLabel);//预计到达时间
                }
            }
        }
        PageInfo<YdTransport> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void selectAllList(YdTransport record, JSONObject json) {
        //处理数据权限
        List<String> orgIds_str = new ArrayList<>();
        for (Long orgid : record.getOrgIds()) {
            orgIds_str.add("-" + orgid + "-");
        }
        //数据权限封装
        record.setOrgStrIds(orgIds_str);
        List<YdTransport> list = ydTransportMapper.selectAll(record);
        if (list != null && list.size() > 0) {
            for (YdTransport ydTransport : list) {
                if (ydTransport.getTransportState() == 5) {//确认收货
                    long diff = (ydTransport.getFinshTime().getTime() / 1000) - (ydTransport.getDepartTime().getTime() / 1000);
                    ydTransport.setUseTime(DateTimeUtils.secondToTime(diff));
                }
                if (ydTransport.getDepartTime() != null) {
                    long dt = ydTransport.getDepartTime().getTime() / 1000;
                    String dtLabel = DateTimeUtils.secondToDate(dt + ydTransport.getEstime(), DateTimeUtils.DATE_FORMAT);
                    ydTransport.setExpectedTime(dtLabel);//预计到达时间
                }
            }
        }
        //在途运单数据
        json.put("ydTransportList", list);
        //获取待出发运单/在途中运单/已到达运单数量
        String createTime = DateUtils.date3String(new Date());
        //待出发
        int transportTodayNum1 = ydTransportMapper.selectStatByOrgIds(createTime, 1, orgIds_str);
        //在途
        int transportTodayNum2 = ydTransportMapper.selectStatByOrgIds(createTime, 2, orgIds_str);
        //待收货
        int transportTodayNum3 = ydTransportMapper.selectStatByOrgIds(createTime, 3, orgIds_str);
        //收到货
        int transportTodayNum4 = ydTransportMapper.selectStatByOrgIds(createTime, 4, orgIds_str);
        json.put("transportTodayNum1", transportTodayNum1);
        json.put("transportTodayNum2", transportTodayNum2);
        json.put("transportTodayNum3", transportTodayNum3 + transportTodayNum4);
        //告警列表
        WarnDto warnDto = new WarnDto();
        warnDto.setStartTime(DateUtils.date3String(new Date()) + " 00:00:01");
        warnDto.setEndTime(DateUtils.date3String(new Date()) + " 23:59:59");
        PageHelper.startPage(warnDto.getPageNum(), warnDto.getPageSize());
        List<WarnVo> ydWarnList = ydWarnService.getListByParms(warnDto);
        if (ydWarnList != null && ydWarnList.size() > 0) {
            for (WarnVo warnVo : ydWarnList) {
                warnVo.setWarnTypeName(CommonEnum.getWarnType(warnVo.getWarnType()));
                warnVo.setProcessStateName(CommonEnum.getProcessState(warnVo.getProcessState() + ""));
            }
        }
        PageInfo<WarnVo> ydWarnPageInfo = new PageInfo<>(ydWarnList);
        json.put("ydWarnPageInfo", ydWarnPageInfo);
    }

    @Override
    public void selectLeftScreenDetails(Integer id, JSONObject json) {
        //运单信息
        YdTransport ydTransport = this.selectByPrimaryKey(id);
        if (ydTransport != null) {
            json.put("ydTransport", ydTransport);
            //获取运单告警信息
            YdWarn ydWarn = new YdWarn();
            ydWarn.setTransportId(id);
            List<YdWarn> ydWarnList = ydWarnService.queryAll(ydWarn);
            if (ydWarnList != null && ydWarnList.size() > 0) {
                for (YdWarn ydW : ydWarnList) {
                    ydW.setWarnTypeName(CommonEnum.getWarnType(ydW.getWarnType()));
                    ydW.setProcessStateName(CommonEnum.getProcessState(ydW.getProcessState() + ""));
                }
                json.put("ydWarnList", ydWarnList);
            }
            //节点追踪数据，地图定位，剩余公里数，剩余时间，预计到达时间
            long dt = ydTransport.getDepartTime().getTime() / 1000;//出发时间
            long nt = (new Date()).getTime() / 1000;//当前时间
            String dtLabel = DateTimeUtils.secondToDate(dt + ydTransport.getEstime(), DateTimeUtils.DATE_FORMAT_ONE);
            json.put("remainingTime", (ydTransport.getEstime() - (nt - dt)) / 60000);//剩余时间（分钟）
            json.put("expectedTime", dtLabel);//预计到达时间
            //文山扣数据列表
            List<YdWarehouseLogDO> list = ydWarehouseService.findList(id);
            JSONArray jsonArr = new JSONArray();
            String levelStr = ",";
            if (list != null && list.size() > 0) {
                for (YdWarehouseLogDO ydWarehouseLogDO : list) {
                    String variety = "";
                    variety = new DictUtils().getDictLabels("tobacco_type", ydWarehouseLogDO.getVariety());
                    ydWarehouseLogDO.setVariety(variety);

                    if (Integer.valueOf(ydWarehouseLogDO.getPackageType()) == 0) {
                        ydWarehouseLogDO.setPackageType("烟包");
                    } else {
                        ydWarehouseLogDO.setPackageType("烟框");
                    }
                    //物品状态
                    ydWarehouseLogDO.setGoodsStateLabel(CommonEnum.getGoodState(ydWarehouseLogDO.getGoodsState() + ""));
                    //等级种类
                    if (StringUtils.isNotEmpty(ydWarehouseLogDO.getTobaccoLevel())) {
                        String tobaccoLevel = "," + ydWarehouseLogDO.getTobaccoLevel() + ",";
                        if (!levelStr.contains(tobaccoLevel)) {
                            levelStr += ydWarehouseLogDO.getTobaccoLevel() + ",";
                        }
                    }
                }
            }
            json.put("ydWarehouseLogDOList", list);
            //等级数量(占比)
            if (levelStr.length() > 2) {
                levelStr = levelStr.substring(1, levelStr.length() - 1);
                String[] levelStrs = levelStr.split(",");
                for (int i = 0; i < levelStrs.length; i++) {
                    JSONObject jsonb = new JSONObject();
                    jsonb.put(levelStrs[i] + "", Collections.frequency(list, levelStrs[i] + ""));
                    jsonArr.add(jsonb);
                }
            }
            json.put("levelNum", jsonArr);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateByPrimaryKey(YdTransport record) {
        YdTransport ydTransport = this.selectByPrimaryKey(record.getId());
        Date now = new Date();
        ydTransport.setUpdateTime(now);
        ydTransport.setDepartTime(now);
        ydTransport.setWeight(record.getWeight());
        ydTransport.setLoaderId(record.getLoaderId());
        ydTransport.setGoodsNum(record.getGoodsNum());
        ydTransport.setGoodsType(record.getGoodsType());
        int ret = ydTransportMapper.updateByPrimaryKey(ydTransport);
        if (record.getRfidList() != null && record.getRfidList().size() > 0) {
            for (String obj : record.getRfidList()) {
                YdTransportGoods goods = new YdTransportGoods();
                goods.setTransportId(record.getId());
                goods.setRfid(obj);
                goods.setCreateTime(now);
                goods.setGoodsState(1);
                ydTransportGoodsMapper.insert(goods);
            }
        }
        return ret;
    }

    @Override
    public int updateTransport(YdTransport record) {
        YdTransport ydTransport = this.selectByPrimaryKey(record.getId());
        Date now = new Date();
        ydTransport.setUpdateTime(now);
        ydTransport.setFinshTime(now);
        ydTransport.setTransportState(5);//已收获
        int ret = ydTransportMapper.updateByPrimaryKey(ydTransport);
        return ret;
    }

    @Override
    public YdTransportDto selectTransport(Integer id) {
        YdTransport ydTransport = ydTransportMapper.selectByPrimaryKey(id);
        YdOrganization send = ydOrganizationMapper.queryById(Long.parseLong(ydTransport.getSendId().toString()));
        YdOrganization receive = ydOrganizationMapper.queryById(Long.parseLong(ydTransport.getReceiveId().toString()));
        YdTransportDto dto = new YdTransportDto();
        dto.setId(ydTransport.getId());
        if (send != null) {
            dto.setSendName(send.getOrgName());
            dto.setSendTele(send.getOrgTele());
            dto.setSendAddress(send.getOrgAddress());
            if (send.getOrgLongitude() != null && send.getOrgLatitude() != null) {
                dto.setSendLoc_lon(send.getOrgLongitude());
                dto.setSendLoc_lat(send.getOrgLatitude());
            }
        }
        if (receive != null) {
            dto.setReceiveName(receive.getOrgName());
            dto.setReceiveTele(receive.getOrgTele());
            dto.setReceiveAddress(receive.getOrgAddress());
            if (receive.getOrgLongitude() != null && receive.getOrgLatitude() != null) {
                dto.setReceiveLoc_lon(receive.getOrgLongitude());
                dto.setReceiveLoc_lat(receive.getOrgLatitude());
            }
        }
        dto.setDeviceNo(ydTransport.getDeviceNo());
        dto.setCarNo(ydTransport.getCarNo());
        dto.setDispatchNo(ydTransport.getDispatchNo());
        dto.setTransportNo(ydTransport.getTransportNo());
        dto.setDriverName(ydTransport.getDriverName());
        dto.setDriverMobile(ydTransport.getDriverMobile());
        dto.setTransportState(ydTransport.getTransportState());
        dto.setDepartTime(ydTransport.getDepartTime());
        dto.setWeight(ydTransport.getWeight());
        dto.setGoodsNum(ydTransport.getGoodsNum());
        dto.setGoodsType(ydTransport.getGoodsType());
        dto.setEstime(ydTransport.getEstime());
        dto.setCreateTime(ydTransport.getCreateTime());
        String str = DateTimeUtils.secondToTime(ydTransport.getEstime());
        dto.setEstimeLabel(str);
        long dt = ydTransport.getDepartTime().getTime() / 1000;
        String dtLabel = DateTimeUtils.secondToDate(dt + ydTransport.getEstime(), DateTimeUtils.DATE_FORMAT);
        dto.setExpectedTime(dtLabel);//预计到达时间
        WarnDto warnDto = new WarnDto();
        warnDto.setTransportId(id);
        List<WarnVo> list = ydWarnService.getListByParms(warnDto);
        dto.setWarnCount(list.size());
        return dto;
    }

    @Override
    public List selectStat(String parentIds) {
        StringBuilder sb = new StringBuilder("-");
        sb = sb.append(parentIds).append("-");
        return ydTransportMapper.selectStat(sb.toString());
    }

    @Override
    public JSONObject selectStatByOrgIds(List<Long> orgIds) {
        JSONObject rtnJson = new JSONObject();
        //处理数据权限
        List<String> orgIds_str = new ArrayList<>();
        for (Long orgid : orgIds) {
            orgIds_str.add("-" + orgid + "-");
        }
        //获取当天计划运单/待出发运单/在途中运单/待收货/收到货运单数量

        String createTime = DateUtils.date3String(new Date());
        //待出发
        int transportTodayNum1 = ydTransportMapper.selectStatByOrgIds(createTime, 1, orgIds_str);
        //在途
        int transportTodayNum2 = ydTransportMapper.selectStatByOrgIds(createTime, 2, orgIds_str);
        //待收货
        int transportTodayNum3 = ydTransportMapper.selectStatByOrgIds(createTime, 3, orgIds_str);
        //收到货
        int transportTodayNum4 = ydTransportMapper.selectStatByOrgIds(createTime, 4, orgIds_str);
        //计划总量
        int transportTodayNum = transportTodayNum1 + transportTodayNum2 + transportTodayNum3 + transportTodayNum4;
        rtnJson.put("transportTodayNum", transportTodayNum);
        rtnJson.put("transportTodayNum1", transportTodayNum1);
        rtnJson.put("transportTodayNum2", transportTodayNum2);
        rtnJson.put("transportTodayNum3", transportTodayNum3 + transportTodayNum4);
        //查询前一周每天的运单量
        String historyTime = "";
        JSONArray jsonArr = new JSONArray();
        JSONObject jsonb = new JSONObject();
        jsonb.put(createTime, transportTodayNum);
        jsonArr.add(jsonb);
        for (int i = -1; i > -7; i--) {
            JSONObject json = new JSONObject();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DATE, i);
            Date start = c.getTime();
            historyTime = format.format(start);
            json.put(historyTime, ydTransportMapper.selectStatByOrgIds(historyTime, null, orgIds_str));
            jsonArr.add(json);
        }
        rtnJson.put("transportNumH", jsonArr);
        return rtnJson;
    }

    /**
     * 运单详情-烟叶列表
     *
     * @return
     */
    @Override
    public PageInfo<TransportTobaccoVo> getTobaccoList(TransportTobaccoDto transportTobaccoDto) {
        PageHelper.startPage(transportTobaccoDto.getPageNum(), transportTobaccoDto.getPageSize());
        List<Long> orgids = transportTobaccoDto.getOrgIds();
        //处理数据权限
        List<String> orgIds_str = new ArrayList<>();
        for (Long orgid : orgids) {
            orgIds_str.add("-" + orgid + "-");
        }
        Integer id = transportTobaccoDto.getId();
        String rfid = transportTobaccoDto.getRfid();
        Integer travelState = transportTobaccoDto.getTravelState();
        List<TransportTobaccoVo> list = ydTransportMapper.getTobaccoList(id, rfid, travelState, orgIds_str);
        for (TransportTobaccoVo transportTobaccoVo : list) {
            String packageType = transportTobaccoVo.getPackageType();
            transportTobaccoVo.setPackageType(CommonEnum.getPackageType(packageType));
            String tranportState = transportTobaccoVo.getTransportState();
            transportTobaccoVo.setTransportState(CommonEnum.getTransportState(tranportState));
        }
        PageInfo<TransportTobaccoVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 单详情-烟叶列表汇总统计
     *
     * @param transportTobaccoDto
     * @return json list&rate
     */
    @Override
    public JSONObject sumTransportTobacco(TransportTobaccoDto transportTobaccoDto) {
        JSONObject rtnJson = new JSONObject();
        List<Long> orgids = transportTobaccoDto.getOrgIds();
        //处理数据权限
        List<String> orgIds_str = new ArrayList<>();
        for (Long orgid : orgids) {
            orgIds_str.add("-" + orgid + "-");
        }
        Integer id = transportTobaccoDto.getId();
        String rfid = transportTobaccoDto.getRfid();
        Integer travelState = transportTobaccoDto.getTravelState();
        List<TransportTobaccoVo> list = ydTransportMapper.sumTobaccoList(id, rfid, travelState, orgIds_str);
        rtnJson.put("list", list);
        JSONObject rateJson = new JSONObject();
        //总担数
        BigDecimal sum = new BigDecimal("0");
        for (TransportTobaccoVo transportTobaccoVo : list) {
            String weight = transportTobaccoVo.getWeight();
            sum.add(new BigDecimal(weight == null ? "0" : weight));
        }
        for (TransportTobaccoVo transportTobaccoVo : list) {
            BigDecimal rateBigDecimal = new BigDecimal("0");
            if (sum.longValue() != 0) {
                rateBigDecimal = new BigDecimal(transportTobaccoVo.getWeight()).divide(sum, 6, BigDecimal.ROUND_HALF_UP);
            }
            rateJson.put(transportTobaccoVo.getTobaccoLevel(), rateBigDecimal);
        }
        rtnJson.put("rate", rateJson);
        return rtnJson;
    }

    /**
     * 改为待收货
     *
     * @param id
     * @return
     */
    @Override
    public boolean pendingReceipt(Integer id) {
        YdTransport tran = ydTransportMapper.selectByPrimaryKey(id);
        if (tran == null) {
            return false;
        }
        //待收货
        tran.setTransportState(4);
        tran.setUpdateTime(new Date());
        ydTransportMapper.updateByPrimaryKey(tran);
        return true;
    }

    /**
     * 开始装车
     *
     * @param transportTobaccoDto
     */
    @Override
    public boolean startLoading(TransportTobaccoDto transportTobaccoDto) {
        boolean result = iotService.startLoading(transportTobaccoDto.getId(), transportTobaccoDto.getDeviceId());
        return result;
    }

    /**
     * 结束装车
     *
     * @param transportTobaccoDto
     * @return
     */
    @Override
    @Transactional
    public Pair<Boolean, JSONObject> stopLoading(TransportTobaccoDto transportTobaccoDto) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            Pair<Boolean, JSONObject> result = iotService.endingLoading(transportTobaccoDto.getId());
            if (!result.getKey()) {
                return result;
            }
            JSONObject body = result.getValue();
            String goodsType = "";
            //总重量
            Double weight = 0d;
            String[] arrs = body.getObject("rfidArr", String[].class);
            if (arrs == null || arrs.length == 0) {
                return new Pair<>(false, jsonObject);
            }
            List<String> list = Arrays.asList(arrs);
            List<YdWarehouseLogDO> warehouseLogs = ydWarehouseService.getListByRfid(list);
            if (warehouseLogs != null && warehouseLogs.size() > 0) {
                YdWarehouseLogDO warehouse = warehouseLogs.get(0);
                String type = warehouse.getPackageType();
                goodsType = CommonEnum.getPackageType(type);
            }
            for (YdWarehouseLogDO warehouseLog : warehouseLogs) {
                weight += Double.valueOf(warehouseLog.getWeightLog() == null ? "0" : warehouseLog.getWeightLog());
            }
            jsonObject.put("goodsType", goodsType);
            jsonObject.put("weight", weight);
            jsonObject.put("goodsNum", body.getInteger("total"));
            jsonObject.put("rfidList", list);
            YdTransport tran = ydTransportMapper.selectByPrimaryKey(transportTobaccoDto.getId());
            tran.setTransportState(2);//已装车
            ydTransportMapper.updateByPrimaryKey(tran);
            return new Pair<>(true, jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            return new Pair<>(false, jsonObject);
        }
    }


    /**
     * 确认发车
     *
     * @param record
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deaprt(YdTransport record) {
        boolean flag = iotService.startDeparture(record.getId());
        if (!flag) {
            return false;
        }
        YdTransport ydTransport = this.selectByPrimaryKey(record.getId());
        Date now = new Date();
        //发车
        ydTransport.setTransportState(3);
        ydTransport.setUpdateTime(now);
        ydTransport.setDepartTime(now);
        ydTransport.setWeight(record.getWeight());
        ydTransport.setLoaderId(record.getLoaderId());
        ydTransport.setGoodsNum(record.getGoodsNum());
        ydTransport.setGoodsType(record.getGoodsType());
        int ret = ydTransportMapper.updateByPrimaryKey(ydTransport);
        if (record.getRfidList() != null && record.getRfidList().size() > 0) {
            List<String> list = new ArrayList<>();
            for (String obj : record.getRfidList()) {
                YdTransportGoods goods = new YdTransportGoods();
                goods.setTransportId(record.getId());
                goods.setRfid(obj);
                goods.setCreateTime(now);
                goods.setGoodsState(1);
                ydTransportGoodsMapper.insert(goods);
                list.add(obj.toString());
            }
            //调用仓库功能接口
            ydWarehouseService.saveRfids(list);
/*            YdWarehousePageDO ydWarehousePageDO = new YdWarehousePageDO();
            ydWarehousePageDO.setPageNum(0);
            ydWarehousePageDO.setPageSize(0);
            ydWarehousePageDO.setInOutType(0+"");
            ydWarehousePageDO.setStatus(0);
            ydWarehousePageDO.setIsValid(0);
            ydWarehousePageDO.setPackageType(record.getGoodsType().equals("烟框")?"0":"1");
            ydWarehousePageDO.setList(list);
            Map map = ydWarehouseService.findByRfidList(ydWarehousePageDO);
            if(!map.isEmpty()) {
            	List<YdWarehouseLogDO> logList = (List<YdWarehouseLogDO>)map.get("logList");
            	if(logList!=null && logList.size()>0) {
            		for (YdWarehouseLogDO ydWarehouseLogDO : logList) {
            			ydWarehouseLogDO.setInOutType("1");
            			ydWarehouseService.saveWarehouse(ydWarehouseLogDO);
					}
            	}
            }*/
        }
        //TODO 出库待完善
        return true;
    }

    
    @Override
    public void selectTodayCarList(YdTransport record, JSONObject json) {
        List<YdTransport> list = ydTransportMapper.selectAll(record);
        int carNum1=0;//预约
        int carNum2=0;//在途
        int carNum3=0;//已报到
        int carNum4=0;//已收货
        if (list != null && list.size() > 0) {
            for (YdTransport ydTransport : list) {
                if(ydTransport.getTransportState()!=null&& (ydTransport.getTransportState()==1
                		||ydTransport.getTransportState()==2)){
                	carNum1++;
                }else if(ydTransport.getTransportState()!=null&& (ydTransport.getTransportState()==3)){
                	carNum2++;
                }else if(ydTransport.getTransportState()!=null&& (ydTransport.getTransportState()==4)){
                	carNum3++;
                }else if(ydTransport.getTransportState()!=null&& (ydTransport.getTransportState()==5)){
                	carNum4++;
                }
            }
        }
        json.put("carNum1", carNum1);
        json.put("carNum2", carNum2);
        json.put("carNum3", carNum3);
        json.put("carNum4", carNum4);
        //在途运单数据
        json.put("todayCarList", list);
    }
}
