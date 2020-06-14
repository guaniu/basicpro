package com.yiding.saas.ydsaas.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共枚举
 */
public enum CommonEnum {


    smokeframe("1", "烟框"),
    cigarettePacket("0", "烟包"),

    toBeLoaded("1", "待装车"),
    waitingToLeave("2", "待出发"),
    inTransit("3", "在途中"),
    pendingReceipt("4", "待收货"),
    received("5", "已收货"),

    /**
     * 告警/消息
     */
    stopTimeOut("1", "停车超时"),
    lowPower("2", "低电量"),
    lost("3", "丢失"),
    removal("4", "拆卸"),
	depart("5","车辆已出发"),
	waitReceiving("6","已到达待收货"),
	already("7","已确认收货"),

    /**
     * 处理状态
     */
    unProcess("0", "未处理"),
    process("1", "已处理"),
	
	goodExist("1","物品在车"),
	goodError("2","物品异常"),
	goodLose("3","物品丢失");





    /**
     * 状态码
     */
    private String code;
    /**
     * 名称或描述
     */
    private String name;

    CommonEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    /**
     * 打包方式
     */
    private static List<CommonEnum> packageTypeList = new ArrayList<>();
    /**
     * 运输状态
     */
    private static List<CommonEnum> transportStateList = new ArrayList<>();

    private static Map<String, String> packageMap = new HashMap<>();

    private static Map<String, String> transportStateMap = new HashMap<>();
    /**
     * 告警类型
     */
    private static List<CommonEnum> warnTypeList = new ArrayList<>();
    private static Map<String, String> warnTypeMap = new HashMap<>();


    private static List<CommonEnum> processList = new ArrayList<>();

    private static Map<String, String> processMap = new HashMap<>();
    
    /**
     * 消息类型装换
     */
    private static List<CommonEnum> infoTypeList = new ArrayList<>();
    private static Map<String,String> infoTypeMap = new HashMap<>();
    
    /**
     * 物品状态类型转换
     */
    private static List<CommonEnum> goodStateList = new ArrayList<>();
    private static Map<String,String> goodStateMap = new HashMap<>();


    /**
     * 打包类型转化
     *
     * @param code
     * @return
     */
    public static String getPackageType(String code) {
        if (packageTypeList.size() == 0) {
            packageTypeList.add(smokeframe);
            packageTypeList.add(cigarettePacket);
            for (CommonEnum commonEnum : packageTypeList) {
                packageMap.put(commonEnum.getCode(), commonEnum.getName());
            }
        }
        return packageMap.get(code);
    }

    /**
     * 运输状态转化
     *
     * @param code
     * @return
     */
    public static String getTransportState(String code) {
        if (transportStateList.size() == 0) {
            transportStateList.add(toBeLoaded);
            transportStateList.add(waitingToLeave);
            transportStateList.add(inTransit);
            transportStateList.add(pendingReceipt);
            transportStateList.add(received);
            for (CommonEnum commonEnum : transportStateList) {
                transportStateMap.put(commonEnum.getCode(), commonEnum.getName());
            }
        }
        return transportStateMap.get(code);
    }


    /**
     * 告警类型转换
     *
     * @param code
     * @return
     */
    public static String getWarnType(String code) {
        if (warnTypeList.size() == 0) {
            warnTypeList.add(stopTimeOut);
            warnTypeList.add(lowPower);
            warnTypeList.add(removal);
            warnTypeList.add(lost);
        }
        for (CommonEnum commonEnum : warnTypeList) {
            warnTypeMap.put(commonEnum.getCode(), commonEnum.getName());
        }
        return warnTypeMap.get(code);
    }


    /**
     * 处理状态转化
     *
     * @param code
     * @return
     */
    public static String getProcessState(String  code) {
        if (processList.size() == 0) {
            processList.add(unProcess);
            processList.add(process);
        }
        for (CommonEnum commonEnum : processList) {
            processMap.put(commonEnum.getCode(), commonEnum.getName());
        }
        return processMap.get(code);
    }

    /**
     * 获取消息类型转换
     * @param code
     * @return
     */
    public static String getInfoType(String code) {
    	if(infoTypeList.size()==0) {
    		infoTypeList.add(stopTimeOut);
    		infoTypeList.add(lowPower);
    		infoTypeList.add(removal);
    		infoTypeList.add(lost);
    		infoTypeList.add(depart);
    		infoTypeList.add(waitReceiving);
    		infoTypeList.add(already);
    	}
    	for (CommonEnum commonEnum : infoTypeList) {
			infoTypeMap.put(commonEnum.getCode(), commonEnum.getName());
		}
    	return infoTypeMap.get(code);
    }
    
    /**
     * 获取物品状态转换
     * @param code
     * @return
     */
    public static String getGoodState(String code) {
    	if(goodStateList.size()==0) {
    		goodStateList.add(goodExist);
    		goodStateList.add(goodError);
    		goodStateList.add(goodLose);
    	}
    	for (CommonEnum commonEnum : goodStateList) {
			goodStateMap.put(commonEnum.getCode(), commonEnum.getName());
		}
    	return goodStateMap.get(code);
    }
}
