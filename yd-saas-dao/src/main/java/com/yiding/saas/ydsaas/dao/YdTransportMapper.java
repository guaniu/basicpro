package com.yiding.saas.ydsaas.dao;

import java.util.List;

import com.yiding.saas.ydsaas.vo.TransportTobaccoVo;
import org.apache.ibatis.annotations.Param;

import com.yiding.saas.ydsaas.dto.YdTransportDto;
import com.yiding.saas.ydsaas.model.YdTransport;

public interface YdTransportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YdTransport record);

    int insertSelective(YdTransport record);

    YdTransport selectByPrimaryKey(Integer id);

    List<YdTransport> selectAll(YdTransport record);

    int updateByPrimaryKeySelective(YdTransport record);

    int updateByPrimaryKey(YdTransport record);

    YdTransportDto selectTransport(Integer id);

    List<Object> selectStat(@Param("parentIds") String parentIds);
    
    Integer selectStatByOrgIds(@Param("createTime") String createTime, @Param("travelState") Integer travelState,@Param("orgIds") List<String> orgIds);

    /**
     * 运单详情-烟叶列表
     *
     * @param rfid
     * @param travelState
     * @return
     */
    List<TransportTobaccoVo> getTobaccoList(@Param("id") Integer id, @Param("rfid") String rfid, @Param("travelState") Integer travelState,@Param("orgIds") List<String> orgIds);

    /**
     * 运单详情-烟叶列表汇总统计
     * @param id
     * @param rfid
     * @param travelState
     * @return
     */
    List<TransportTobaccoVo> sumTobaccoList(@Param("id") Integer id, @Param("rfid") String rfid, @Param("travelState") Integer travelState,@Param("orgIds") List<String> orgIds);



}