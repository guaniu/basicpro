package com.yiding.saas.ydsaas.service.util;

import com.yiding.saas.ydsaas.dao.business.SysSequenceMapper;
import com.yiding.saas.ydsaas.dao.domain.SysSequenceDO;
import com.yiding.saas.ydsaas.dao.domain.SysSequenceDOExample;
import com.yiding.saas.ydsaas.model.UniqIdModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class SysSequenceUtils {

    public static final String BIZ_SEQUENCE_CODE = "yt_saas";

    private static final ConcurrentHashMap<String, UniqIdModel> cache = new ConcurrentHashMap<String, UniqIdModel>();
    private ReentrantLock lock = new ReentrantLock();

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    private SysSequenceMapper sequenceMapper;



    /**
     * 获取序列code
     *
     * @param busiType 业务类型
     * @return
     */
    public String getBillNo(Integer busiType) {
        String uniqName = BIZ_SEQUENCE_CODE;

        if (UniqBusiType.valueOfType(busiType) != UniqBusiType.OTHER) {
            uniqName += "_" + UniqBusiType.valueOfType(busiType).toString();
        }

        String traceId = "";
        try {
            lock.lock();
            StringBuffer sb = new StringBuffer();

            if (StringUtils.isNotBlank(UniqBusiType.valueOfType(busiType).toString())) {
                sb.append(UniqBusiType.valueOfType(busiType).toString());
                String prefix = new SimpleDateFormat("yyyyMMdd").format(new Date()).substring(2);
                sb.append(prefix);
            }

            traceId = UniqIdUtil.getInstance().getUniqID();

            if (cache.get(uniqName) == null) {
                cache(traceId, uniqName);
            }
            UniqIdModel model = null;
            model = cache.get(uniqName);
            if (model != null) {
                Long value = model.getCurrentValue().incrementAndGet();
                if (value % model.getIncreaseStep() == 0) {
                    cache.remove(uniqName);
                }
                sb.append(value);
                String uniqId = sb.toString();
                if (log.isDebugEnabled()) {
                    log.debug("getUniqCode, traceId:{}, uniqName:{}, uniqId:{}"+traceId+":"+uniqName+":"+uniqId);
                }
                log.debug(busiType+"的uniqId："+uniqId);
                System.out.println("----------"+busiType+"的uniqId："+uniqId);
                return uniqId;
            }
        } catch (Exception e) {
            log.error("getUniqCode error, traceId:{}, uniqName:{}"+traceId+":"+uniqName);
            log.error("getUniqCode error ", e);
        } finally {
            lock.unlock();
        }
        return null;
    }


    /**
     * @param traceId
     * @param uniqName
     */
    private void cache(final String traceId, final String uniqName) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        UniqIdTask uniqIdTask = new UniqIdTask(traceId, uniqName);
        Future<SysSequenceDO> future = executor.submit(uniqIdTask);
        executor.shutdown();
        SysSequenceDO modelResult = null;
        try {
            //超时时间为5秒
            modelResult = future.get(10L, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("future get error", e);
            return;
        }

        if (modelResult != null) {
            UniqIdModel model = new UniqIdModel();
            convertDoToModel(modelResult, model);
            cache.put(uniqName, model);
        } else {
            log.info("queryYzUniqStepModel failed, traceId:{}, uniqName:{}"+traceId+":" +uniqName);
        }
    }

    /**
     * 数据转换
     * @param doObj UniqidDO
     * @param model UniqIdModel
     */
    private void convertDoToModel(SysSequenceDO doObj, UniqIdModel model) {
        model.setId(doObj.getId());
        model.setCurrentValue(new AtomicLong(doObj.getMinValue()));
        model.setIncreaseStep(doObj.getIncreaseStep());
        model.setOriginValue(doObj.getMinValue());
        model.setUniqName(doObj.getBusinessName());
        model.setUpdateTime(doObj.getUpdateTime());
    }
    /**
     * 序列生成-业务类型
     */
    public enum UniqBusiType {
        OTHER(0, "其他"),
        QT(1, "隔离申请单"),
        DG(2, "分舍分栏申请单"),
        MV(3 ,"转舍转栏申请单"),
        BUY(4, "购入牛只"),
        YF(5, "转育肥牛申请单"),
        FodIn(6, "饲料入库申请单"),
        DruIn(7, "药品入库申请单"),
        FroIn(8, "冻精入库申请单"),
        OUT(9,"淘汰申请单"),
        MY(10,"免疫申请单"),
        FodOut(11, "饲料出库申请单"),
        DruOut(12, "药品出库申请单"),
        FroOut(13, "冻精出库申请单"),
        FQ(14, "发情申请单"),
        PZ(15, "配种申请单"),
        DI(16, "疾病申请单"),
        PRE(17, "处方申请单"),

        ;

        UniqBusiType(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        private static Map<Integer, SysSequenceUtils.UniqBusiType> typeMap = new HashMap<>(11);

        static {
            for (SysSequenceUtils.UniqBusiType t : values()) {
                typeMap.put(t.getCode(), t);
            }
        }

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        /**
         * 获取枚举
         *
         * @param code
         * @return
         */
        public static SysSequenceUtils.UniqBusiType valueOfType(int code) {
            SysSequenceUtils.UniqBusiType busiType = typeMap.get(code);
            if (busiType == null) {
                return OTHER;
            }
            return busiType;
        }
    }


    /**
     * 内部类,操作数据库，避免由于事务嵌套导致生成主键失败， 只能由外部类访问
     */
    private class UniqIdTask implements Callable<SysSequenceDO> {

        private String traceId;
        private String uniqName;

        private UniqIdTask(String traceId, String uniqName) {
            this.traceId = traceId;
            this.uniqName = uniqName;
        }

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public SysSequenceDO call() throws Exception {


            SysSequenceDO doObj = new SysSequenceDO();

            doObj.setHashRoute(0);
            doObj.setUpdateTime(System.currentTimeMillis());
            doObj.setLocknum(traceId);
            SysSequenceDOExample example = new SysSequenceDOExample();
            SysSequenceDOExample.Criteria criteria = example.createCriteria();
            criteria.andBusinessNameEqualTo(uniqName);

            Integer updateNums =  sequenceMapper.updateByExampleSelective(doObj,example);


            SysSequenceDOExample example1 = new SysSequenceDOExample();
            SysSequenceDOExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andBusinessNameEqualTo(uniqName).andHashRouteEqualTo(0).andLocknumEqualTo(traceId);

            doObj =sequenceMapper.selectOneByExample(example1);



            if(doObj == null){
                return null;
            }
            log.info("queryUniqDOByCondition params:{}, result:{}"+doObj.toString()+","+doObj);

//            if (doObj.getMinValue() + doObj.getIncreaseStep() * 2 > doObj.getMaxValue()) {
                //加一个步长
                doObj.setMinValue(doObj.getMinValue() + doObj.getIncreaseStep());

                Integer overMaxUp = sequenceMapper.updateByExampleSelective(doObj,example);;
                log.info("autoupdateUniqDOByCondition params:{}, result:{}"+doObj.toString());

                doObj = sequenceMapper.selectOneByExample(example1);
                log.info("queryUniqDOByCondition twice params:{}, result:{}"+doObj.toString());

//            }
//            doObj.setMinValue(doObj.getMinValue() - doObj.getIncreaseStep());

            return doObj;

        }
    }
}
