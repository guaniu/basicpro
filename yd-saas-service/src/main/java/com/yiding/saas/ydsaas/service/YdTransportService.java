package com.yiding.saas.ydsaas.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.dto.TransportTobaccoDto;
import com.yiding.saas.ydsaas.dto.YdTransportDto;
import com.yiding.saas.ydsaas.model.YdTransport;
import com.yiding.saas.ydsaas.vo.TransportTobaccoVo;
import javafx.util.Pair;

public interface YdTransportService {

    int deleteByPrimaryKey(Integer id);

    int insert(YdTransport record);

    YdTransport selectByPrimaryKey(Integer id);

    PageInfo<YdTransport> selectAll(YdTransport record);
    
    void selectAllList(YdTransport record,JSONObject json);
    
    void selectTodayCarList(YdTransport record,JSONObject json);
    
    void selectLeftScreenDetails(Integer id,JSONObject json);

    int updateByPrimaryKey(YdTransport record);

    /**
     * 运单待确认/已签收详情
     *
     * @param id
     * @return
     */
    YdTransportDto selectTransport(Integer id);

    int updateTransport(YdTransport record);

    /**
     * 运单统计汇总
     *
     * @param parentIds
     * @return
     */
    List<YdTransport> selectStat(String parentIds);
    /**
     * 首页运单统计汇总
     *
     * @param parentIds
     * @return
     */
    JSONObject selectStatByOrgIds(List<Long> orgIds);

    /**
     * 运单详情-烟叶列表
     *
     * @return
     */
    PageInfo<TransportTobaccoVo> getTobaccoList(TransportTobaccoDto transportTobaccoDto);

    /**
     * 运单详情-烟叶列表汇总统计
     *
     * @param transportTobaccoDto
     * @return
     */
    JSONObject sumTransportTobacco(TransportTobaccoDto transportTobaccoDto);

    /**
     * 根据运单id修改状态为待收货
     * @param id
     * @return
     */
    boolean pendingReceipt(Integer id);

    /**
     * 开始装车
     * @param transportTobaccoDto
     */
    boolean startLoading(TransportTobaccoDto transportTobaccoDto);

    /**
     * 结束装车
     * @param transportTobaccoDto
     * @return
     */
    Pair<Boolean,JSONObject> stopLoading(TransportTobaccoDto transportTobaccoDto);

    /**
     * 确认发车
     * @param ydTransport
     * @return
     */

    boolean deaprt(YdTransport ydTransport);
}
