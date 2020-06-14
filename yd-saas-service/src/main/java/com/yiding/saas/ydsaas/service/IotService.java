package com.yiding.saas.ydsaas.service;

import com.alibaba.fastjson.JSONObject;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 调用iot
 */
@Service
public class IotService {
    /**
     * 日志
     */
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 开始装车
     */
    @Value("${iot.start.loding}")
    private String startLoading;
    /**
     * 结束装车
     */
    @Value("${iot.ending.loading}")
    private String endingLoading;
    /**
     * 开始发车
     */
    @Value("${iot.startDeparture}")
    private String startDeparture;

    /**
     * 开始装车
     *
     * @param tranId   运单Id
     * @param deviceId 设备Id
     * @return
     */
    public boolean startLoading(Integer tranId, Integer deviceId) {
        Map<String, Integer> map = null;
        try {
            map = new HashMap<>();
            map.put("wayBillId", tranId);
            map.put("deviceId", deviceId);
            ResponseEntity<JSONObject> resp = restTemplate.getForEntity(startLoading, JSONObject.class, map);
            JSONObject body = resp.getBody();
            log.info("startLoading.resp:{}", body);
            if (body.getBoolean("data")) {
                return true;
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return false;
    }


    /**
     * 结束装车
     *
     * @param tranId 运单Id
     * @return
     */
    public Pair<Boolean, JSONObject> endingLoading(Integer tranId) {
        try {
            JSONObject rtnJson = new JSONObject();
            Map<String, Integer> map = new HashMap<>();
            map.put("wayBillId", tranId);
            ResponseEntity<JSONObject> resp = restTemplate.getForEntity(endingLoading, JSONObject.class, map);
            JSONObject body = resp.getBody();
            log.info("endingLoading.resp:{}", body);
            if (body.getBoolean("normal")) {
                //总数量
                Integer total = body.getInteger("total");
                String[] rfidArr = body.getObject("data", String[].class);
                rtnJson.put("total", total);
                rtnJson.put("rfidArr", rfidArr);
                return new Pair<>(true, rtnJson);//烟框数量
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return new Pair<>(false, new JSONObject());
    }

    /**
     * 开始发车
     *
     * @param tranId 运单Id
     * @return
     */
    public boolean startDeparture(Integer tranId) {
        Map<String, Integer> map = null;
        try {
            map = new HashMap();
            map.put("wayBillId", tranId);
            ResponseEntity<JSONObject> resp = restTemplate.getForEntity(startDeparture, JSONObject.class, map);
            JSONObject body = resp.getBody();
            log.info("startDeparture.resp:{}", body);
            if (body.getBoolean("normal")) {
                return true;
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return false;
    }
}
