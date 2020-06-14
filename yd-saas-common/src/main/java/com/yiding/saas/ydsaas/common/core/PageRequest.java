package com.yiding.saas.ydsaas.common.core;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页请求
 * @author Louis
 * @date Aug 19, 2018
 */
public class PageRequest {
	/**
	 * 当前页码
	 */
	private int pageNum = 1;
	/**
	 * 每页数量
	 */
	private int pageSize = 10;

	private String jobNo;

	/**
	 * 每页数量
	 */
	private Map<String, Map<String,Object>> columnFilters = new HashMap<String, Map<String,Object>>();

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Map<String, Map<String,Object>> getColumnFilters() {
		return columnFilters;
	}
	public void setColumnFilters(Map<String, Map<String,Object>> columnFilters) {
		this.columnFilters = columnFilters;
	}
	public Map<String,Object> getColumnFilter(String name) {
		return columnFilters.get(name);
	}
}
