package com.yiding.saas.ydsaas;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.dao.YdOrganizationMapper;
import com.yiding.saas.ydsaas.dao.YdTransportMapper;
import com.yiding.saas.ydsaas.dto.TransportTobaccoDto;
import com.yiding.saas.ydsaas.dto.WarnDto;
import com.yiding.saas.ydsaas.dto.YdUserDto;
import com.yiding.saas.ydsaas.model.*;
import com.yiding.saas.ydsaas.service.*;
import com.yiding.saas.ydsaas.vo.OrganizationVo;
import com.yiding.saas.ydsaas.vo.TransportTobaccoVo;
import com.yiding.saas.ydsaas.vo.UserVo;
import com.yiding.saas.ydsaas.vo.WarnVo;
import com.yiding.saas.ydsaas.web.biz.*;
import javafx.util.Pair;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTestApplication {

    @Autowired
    private UserBizService userBizService;

    @Autowired
    private OrgBizService orgBizService;

    @Test
    public void saveUser() {
        for (int i = 0; i < 100; i++) {
            YdUser ydUser = new YdUser();
            ydUser.setRoleId(Long.valueOf(i));
            ydUser.setUserName("金");
            ydUser.setUserComments("备注:" + UUID.randomUUID().toString().replace("-", ""));
            ydUser.setUserMobile("18322286703");
            ydUser.setUserOrgId(3);
            ydUser.setUserOrgName("山东公司");
            userBizService.saveUser(ydUser);
        }

    }


    @Test
    public void saveOrg() {
        for (int i = 0; i < 100; i++) {
            YdOrganization ydOrganization = new YdOrganization();
            ydOrganization.setOrgStatus(1);
            ydOrganization.setOrgName("双汇");
            ydOrganization.setOrgFullName("双汇集团");
            ydOrganization.setCreateTime(new Date());
            ydOrganization.setOfficalUserId(Long.valueOf(i));
            ydOrganization.setOrgComments("测试：" + UUID.randomUUID().toString().replace("-", ""));
            orgBizService.save(ydOrganization);
        }
    }


    @Autowired
    private TobaccoBizService tobaccoBizService;

    @Test
    public void saveTobacco() {
        YdTobacco ydTobacco = new YdTobacco();
        ydTobacco.setCreateTime(new Date());
        ydTobacco.setTobaccoName("中桔二");
        ydTobacco.setTobaccoLevel("C2F");
        tobaccoBizService.save(ydTobacco);
    }


    @Test
    public void listUser() {
        YdUserDto ydUserDto = new YdUserDto();
        PageInfo<UserVo> ss = userBizService.ydUserList(ydUserDto);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(ss);
        System.out.println(jsonObject.toJSONString());

    }

    @Autowired
    private YdOrganizationService ydOrganizationService;

    @Autowired
    private YdUserOrgRefService ydUserOrgRefService;


    @Test
    public void testOrgIds() {
        List<Long> list = new ArrayList<>();
        String ss = orgBizService.getParentIdsByOrdId(9L, list);
        System.out.println(ss);

    }

    @Autowired
    private YdOrganizationMapper organizationMapper;

    @Test
    public void testOrgList() {
        List<OrganizationVo> list = organizationMapper.getOrgList(new YdOrganization());
        for (OrganizationVo ydOrganization : list) {
            System.out.println(ydOrganization.getOrgStatusName() + ":" + ydOrganization.getOrgTypeName());

        }
    }


    @Autowired
    private DataAuthBizService dataAuthBizService;

    @Test
    public void testDataAuth() {
        List<Long> s = dataAuthBizService.getAuthData(106L);
        for (Long aLong : s) {
            System.out.println(aLong);
        }
    }


    @Test
    public void testStringDataOrg() {
        List<Long> list = new ArrayList<>();
        //吧自己ID放进list
        list.add(1L);
        String s = orgBizService.getParentIdsByOrdId(1L, list);
        System.out.println(s);
    }

    /**
     * 返回父组织id字符串
     */
    @Test
    public void testOg() {
        //吧自己ID放进list
        String s = orgBizService.getOrgIdsByOrgId(3L);
        System.out.println(s);
    }


    @Autowired
    private YdTransportMapper ydTransportMapper;

    @Test
    public void testTranTobacco() {
        List<String> ll = new ArrayList<>();
        ll.add("-5-");
        ll.add("-2-");
        List<TransportTobaccoVo> ss = ydTransportMapper.sumTobaccoList(5, "1", 5, ll);
        for (TransportTobaccoVo s : ss) {
            System.out.println(s.getWeight() + ":" + s.getTobaccoLevel());
        }
    }

    @Autowired
    private YdTransportService ydTransportService;


    /**
     * 测试数据权限
     */
    @Test
    public void testTraList() {
        YdTransport ydTransport = new YdTransport();
        ydTransport.setOrgIds(dataAuthBizService.getAuthData(2L));
        PageInfo<YdTransport> list = ydTransportService.selectAll(ydTransport);
        List<YdTransport> s = list.getList();
        for (YdTransport transport : s) {
            System.out.println(transport.getTransportNo());
        }

    }


    /**
     * 测试导入 村庄信息
     *
     * @throws Exception
     */


    @Test
    public void testUpload() throws Exception {
        /*byte[] b = Files.readAllBytes(Paths.get("C:\\Users\\HUAWEI\\Desktop\\work\\烟草\\abc.xlsx"));
        InputStream inputStream=new ByteInputStream(b,b.length);
        boolean flag = orgBizService.uploadVillageGroupByOrgId(9L, inputStream);
        System.out.println(flag);*/

        // String s = orgBizService.getOrgIdsByOrgId(47L);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        /*ydOrganizationService.queryById(36L);
        ydOrganizationService.queryById(36L);
        ydOrganizationService.queryById(36L);*/
        //ydTransportService.insert(new YdTransport());
        ydOrganizationService.insert(new YdOrganization());
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }


    @Test
    public void batchInsert() {
        YdOrganization ydOrganization = new YdOrganization();
        ydOrganization.setId(23L);
        List<YdOrgPurchaseRegion> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            YdOrgPurchaseRegion ydOrgPurchaseRegion = new YdOrgPurchaseRegion();
            ydOrgPurchaseRegion.setVillageName("测试：" + i);
            ydOrgPurchaseRegion.setGroupName("小分队:" + i);
            list.add(ydOrgPurchaseRegion);
        }

        ydOrganization.setOrgPurchaseRegions(list);
        orgBizService.saveOrgPurchaseRegion(ydOrganization);
        System.out.println("写入完成");
    }

    @Test
    public void batchUpdate() {
        List<YdOrganization> list = new ArrayList<>();
        for (int i = 48; i <= 50; i++) {
            YdOrganization ydOrganization = new YdOrganization();
            ydOrganization.setId(Long.valueOf(i));
            ydOrganization.setOrgName("测试:" + i);
            ydOrganization.setStorehouseVolume("" + i);
            list.add(ydOrganization);

        }
        orgBizService.batchUpdateStoreHouse(list);
    }

    @Test
    public void batchOrgInsert() {
        List<YdOrganization> list1 = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            YdOrganization ydOrganization = new YdOrganization();
            ydOrganization.setParentId(47L);
            ydOrganization.setOrgName("测试:" + i);
            ydOrganization.setStorehouseVolume("" + i);
            list1.add(ydOrganization);

        }
        orgBizService.batchInsertStoreHouse(list1);
    }


    @Test
    public void testOrgType() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        List<YdOrganization> list1 = ydOrganizationService.getOrgListByOrgType(Arrays.asList("1", "2", "3"));
        for (YdOrganization ydOrganization : list1) {
            System.out.println(ydOrganization.getOrgType());

        }
    }

    @Test
    public void testOrgStr() {
        String ss = orgBizService.getOrgIdsByOrgId(3L);
        System.out.println(ss);
    }


    @Autowired
    private WarnBizService warnBizService;

    @Test
    public void testSaveWarn() {
        YdWarn ydWarn = new YdWarn();
        ydWarn.setTransportId(5);
        ydWarn.setCreateTime(new Date());
        ydWarn.setCarLatitude("21.444");
        ydWarn.setCarLongitude("63.4232");
        ydWarn.setWarnType("4");
        ydWarn.setWarnContent("设备被拆卸");
        warnBizService.save(ydWarn);
    }


    @Autowired
    private IotService iotService;

    @Test
    public void testIot() {
        System.out.println("发车：");
        boolean flag = iotService.startDeparture(2);
        System.out.println(flag);
        System.out.println("开始装车：");
        boolean flag1 = iotService.startLoading(1, 3);
        System.out.println(flag1);
        System.out.println("结束装车：");
        Pair<Boolean, JSONObject> flag2 = iotService.endingLoading(1);
        System.out.println(flag2.toString());
    }

    @Autowired
    private YdWarehouseService ydWarehouseService;


    @Test
    public void testa(){
        /*TransportTobaccoDto ss = new TransportTobaccoDto();
        ss.setId(5);
        Pair<Boolean, JSONObject> result = ydTransportService.stopLoading(ss);
        System.out.println(result.toString());*/
        List<String> list=new ArrayList<>();
        list.add("123456");
        List<YdWarehouseLogDO> warehouseLogs = ydWarehouseService.getListByRfid(list);
        double a=0;
        for (YdWarehouseLogDO warehouseLog : warehouseLogs) {
            System.out.println(warehouseLog.getWeight());
            a+=Double.valueOf(warehouseLog.getWeight());
        }
        System.out.println(a);
    }



    @Test
    public void  testIsAdmin(){
        boolean flag = userBizService.isAdmin(1L);
        System.out.println(flag);
        boolean result = userBizService.isAdmin(2L);
        System.out.println(result);
    }


    @Autowired
    private YdWarnService ydWarnService;

    @Test
    public void testWarn(){
        WarnDto warnDto=new WarnDto();
        warnDto.setTransportId(5);
        List<WarnVo> list = ydWarnService.getListByParms(warnDto);
        for (WarnVo warnVo : list) {
            System.out.println(warnVo.toString());
        }
    }


}
