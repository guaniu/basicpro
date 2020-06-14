package com.yiding.saas.ydsaas.web.config;

import com.alibaba.fastjson.JSONObject;
import com.yiding.saas.ydsaas.web.anno.ReqRespLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.HashMap;


/**
 * 切面 自定义注解打印日志
 */
@Aspect
@Component
public class AnnoLogAop {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 环绕通知 打印日志
     *
     * @param proceedingJoinPoint
     * @param reqRespLog
     * @return
     */
    @Around(value = "@annotation(reqRespLog)")
    public Object aroundLog(ProceedingJoinPoint proceedingJoinPoint, ReqRespLog reqRespLog) {
        Object resp = null;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            MethodSignature sinature = (MethodSignature) proceedingJoinPoint.getSignature();
            String className = sinature.getDeclaringTypeName();
            String methodName = sinature.getName();
            String[] parms = sinature.getParameterNames();
            Object[] arrObjs = proceedingJoinPoint.getArgs();
            HashMap<String, String> map = new HashMap<>();
            for (int i = 0; i < parms.length; i++) {
                map.put(parms[i], String.valueOf(arrObjs[i]));
            }
            String parmsStr = JSONObject.toJSONString(map);
            JSONObject jsonObject = JSONObject.parseObject(parmsStr);
            StringBuffer stringBuffer = new StringBuffer(className);
            stringBuffer.append(".");
            stringBuffer.append(methodName);
            String parmsName = Arrays.toString(parms).replace("[", "").replace("]", "");
            stringBuffer.append("(");
            stringBuffer.append(parmsName);
            stringBuffer.append(")");
            stringBuffer.append(" req:  ");
            stringBuffer.append(jsonObject.toJSONString());
            resp = proceedingJoinPoint.proceed();
            String respJson = JSONObject.toJSONString(resp);
            stringBuffer.append(",resp:");
            stringBuffer.append(respJson);
            stopWatch.stop();
            stringBuffer.append(": 总共耗时:");
            stringBuffer.append(stopWatch.getTotalTimeSeconds() + "秒");
            logger.info(stringBuffer.toString());
        } catch (Exception e) {
            logger.error("", e);
        } catch (Throwable throwable) {
            logger.error("", throwable);
        }
        return resp;
    }
}
