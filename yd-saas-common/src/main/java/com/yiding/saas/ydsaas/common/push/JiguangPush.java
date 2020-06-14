package com.yiding.saas.ydsaas.common.push;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 *  极光SDK推送
 * @author wangcb
 * @version 2019-02-19
 */
@SuppressWarnings("all")
public class JiguangPush {
	private static final Logger log = LoggerFactory.getLogger(JiguangPush.class);
    private static String masterSecret = "6e371317b60e6384815aaaad";
    private static String appKey = "52183fc654ec99070ce78a5d";
    private static Long timeToLive = 86400L;//推送消息在极光推送平台保留的时长（秒）
    
    /**
       * 极光推送
     * @param alert 通知内容
     * @param alias 通知用户的userCode
     */
    public void jiguangPush(String alert,String alias,boolean apnsProduction){
    	if(StringUtils.isNotBlank(alias) && StringUtils.isNotBlank(alert)) {
    		 log.info("对别名<" + alias + ">的用户推送信息");
    	     log.info("对通知<" + alert + ">的推送");
    	     PushResult result = push(String.valueOf(alias),alert,apnsProduction);
    	}
    }
    
    /**
      * 生成极光推送对象PushPayload（采用java SDK）
     * @param alias
     * @param alert
     * @return PushPayload
     */
    public static PushPayload buildPushObject_android_ios_alias_alert(String alias,String alert,boolean apnsProduction){
    	//默认构建json作为附加字段做业务区分处理
    	JSONObject json = new JSONObject();
    	JSONObject dataJson = new JSONObject();
    	json.put("JPushType", 1);
    	json.put("data", dataJson);
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .addExtra("model", json.toJSONString())
                                .setAlert(alert)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .addExtra("model", json.toJSONString())
                                .setAlert(alert)
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(apnsProduction)//true-推送生产环境 false-推送开发环境（测试使用参数）
                        .setTimeToLive(timeToLive)//消息在JPush服务器的失效时间（测试使用参数）
                        .build())
                .build();
    }
    
    /**
      * 极光推送方法(采用java SDK)
     * @param alias
     * @param alert
     * @return PushResult
     */
    public static PushResult push(String alias,String alert,boolean apnsProduction){
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
        PushPayload payload = buildPushObject_android_ios_alias_alert(alias,alert,apnsProduction);
        try {
            return jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
            return null;
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
            log.info("Msg ID: " + e.getMsgId());
            return null;
        }    
    }
    
    /*****************************************************************V2*********************************************************************/
       /**
       * 极光推送
	   * @param alert 通知内容
	   * @param alias 通知用户的userCode
	   */
	  public void jiguangPush_v2(String alert,String alias,String json,boolean apnsProduction){
	  	if(StringUtils.isNotBlank(alias) && StringUtils.isNotBlank(alert)) {
	  		 log.info("对别名<" + alias + ">的用户推送信息");
	  	     log.info("对通知<" + alert + ">的推送");
	  	     PushResult result = push_v2(String.valueOf(alias),alert,json,apnsProduction);
	  	}
	  }
	  
	  /**
	    * 生成极光推送对象PushPayload（采用java SDK）
	   * @param alias
	   * @param alert
	   * @return PushPayload
	   */
	  public static PushPayload buildPushObject_android_ios_alias_alert_v2(String alias,String alert,String json,boolean apnsProduction){
	      return PushPayload.newBuilder()
	              .setPlatform(Platform.android_ios())
	              .setAudience(Audience.alias(alias))
	              .setNotification(Notification.newBuilder()
	                      .addPlatformNotification(AndroidNotification.newBuilder()
	                              .addExtra("model", json)
	                              .setAlert(alert)
	                              .build())
	                      .addPlatformNotification(IosNotification.newBuilder()
	                              .addExtra("model", json)
	                              .setAlert(alert)
	                              .build())
	                      .build())
	              .setOptions(Options.newBuilder()
	                      .setApnsProduction(apnsProduction)//true-推送生产环境 false-推送开发环境（测试使用参数）
	                      .setTimeToLive(timeToLive)//消息在JPush服务器的失效时间（测试使用参数）
	                      .build())
	              .build();
	  }
	  
	  /**
	    * 极光推送方法(采用java SDK)
	   * @param alias
	   * @param alert
	   * @return PushResult
	   */
	  public static PushResult push_v2(String alias,String alert,String json,boolean apnsProduction){
	      ClientConfig clientConfig = ClientConfig.getInstance();
	      JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
	      PushPayload payload = buildPushObject_android_ios_alias_alert_v2(alias,alert,json,apnsProduction);
	      try {
	          return jpushClient.sendPush(payload);
	      } catch (APIConnectionException e) {
	          log.error("Connection error. Should retry later. ", e);
	          return null;
	      } catch (APIRequestException e) {
	          log.error("Error response from JPush server. Should review and fix it. ", e);
	          log.info("HTTP Status: " + e.getStatus());
	          log.info("Error Code: " + e.getErrorCode());
	          log.info("Error Message: " + e.getErrorMessage());
	          log.info("Msg ID: " + e.getMsgId());
	          return null;
	      }    
	  }
    
    public static void main(String[] args) {
		JiguangPush jpush = new JiguangPush();
//		jpush.push("998", "test");
		jpush.jiguangPush("test234567","0_222005_nkup",false);
	}
}
