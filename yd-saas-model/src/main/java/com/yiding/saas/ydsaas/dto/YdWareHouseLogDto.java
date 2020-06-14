package com.yiding.saas.ydsaas.dto;

import java.io.Serializable;

public class YdWareHouseLogDto extends BaseDto implements Serializable {

	private static final long serialVersionUID = 71011244584568L;
	
	    private int transportId;
	    private int goodState;//物品状态
	    private String rfid;
	    private String tobaccoName;//烟叶名称
	    private String tobaccoLevel;//烟叶等级
	    private String unit;//出入库单位
	    private int count;//出入库数量
	    private String tobaccoStation;//发货单位
		public int getTransportId() {
			return transportId;
		}
		public void setTransportId(int transportId) {
			this.transportId = transportId;
		}
		public String getRfid() {
			return rfid;
		}
		public void setRfid(String rfid) {
			this.rfid = rfid;
		}
		public String getTobaccoName() {
			return tobaccoName;
		}
		public void setTobaccoName(String tobaccoName) {
			this.tobaccoName = tobaccoName;
		}
		public String getTobaccoLevel() {
			return tobaccoLevel;
		}
		public void setTobaccoLevel(String tobaccoLevel) {
			this.tobaccoLevel = tobaccoLevel;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public String getTobaccoStation() {
			return tobaccoStation;
		}
		public void setTobaccoStation(String tobaccoStation) {
			this.tobaccoStation = tobaccoStation;
		}
		public int getGoodState() {
			return goodState;
		}
		public void setGoodState(int goodState) {
			this.goodState = goodState;
		}
	    
}
