package com.yiding.saas.ydsaas;

import com.alibaba.fastjson.JSONObject;
import com.yiding.saas.ydsaas.common.utils.PasswordUtils;
import com.yiding.saas.ydsaas.model.YdOrganization;
import com.yiding.saas.ydsaas.web.util.JedisUtil;
import com.yiding.saas.ydsaas.web.util.RedisKeyUtil;

import java.util.Set;

public class TestApp {
    public static void main(String[] args) {
        String pwd= PasswordUtils.encode("admin","");
        System.out.println("密码："+pwd);
        /*

        boolean flag = PasswordUtils.matches("", "111111", "96e79218965eb72c92a549dd5a330112");
        System.out.println(flag);


        JSONObject jsonObject= (JSONObject) JSONObject.toJSON(new YdUser());
        System.out.println(jsonObject.toJSONString());
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        //JWTCreator.Builder builder = JWT.create();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());


        System.out.println("-----------------------------------------");
        long total=4781506560l;
        long ff = total / 1024 / 1024 / 1024;
        System.out.println(ff);

        String org_key = RedisKeyUtil.org_key + 18;
        boolean s = JedisUtil.delKey(org_key);
        System.out.println(s);
        YdOrganization ydOrganization1=new YdOrganization();
        ydOrganization1.setOrgName("sfdfdf");
        JSONObject jsonObject1=(JSONObject) JSONObject.toJSON(ydOrganization1);
        stopWatch.start();
        *//*JSONObject object = (JSONObject) JSONObject.parse(jsonObject1.toJSONString());
        YdOrganization ydOrganization = JSONObject.toJavaObject(object, YdOrganization.class);*//*
        YdOrganization ydOrganization=JSONObject.parseObject(jsonObject1.toJSONString(),YdOrganization.class);
        System.out.println(ydOrganization.getOrgName());
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());*/
//        System.out.println(3%6);
//
//        System.out.println(DateTimeUtils.getCurrYearFirst());
//        System.out.println(DateTimeUtils.getCurrYearLast());




        Set<String> set = JedisUtil.getKeys(RedisKeyUtil.org_key + "*");
        long start = System.currentTimeMillis();
        for (String s : set) {
            String value = JedisUtil.getValue(s);
            //YdOrganization org = JSONObject.parseObject(value, YdOrganization.class);
           /* if(org!=null){
                System.out.println(org.getOrgName());
            }*/
           //JedisUtil.delKey(s);
            System.out.println(value);

        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);


    }
}
