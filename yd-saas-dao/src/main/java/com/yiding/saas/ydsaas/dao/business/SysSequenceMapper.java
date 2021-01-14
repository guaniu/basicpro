package com.yiding.saas.ydsaas.dao.business;

import com.yiding.saas.ydsaas.dao.domain.SysSequenceDO;
import com.yiding.saas.ydsaas.dao.domain.SysSequenceDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSequenceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated 2019-03-18 16:56:05
     */
    long countByExample(SysSequenceDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated 2019-03-18 16:56:05
     */
    int deleteByExample(SysSequenceDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated 2019-03-18 16:56:05
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated 2019-03-18 16:56:05
     */
    int insert(SysSequenceDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated 2019-03-18 16:56:05
     */
    int insertSelective(SysSequenceDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated 2019-03-18 16:56:05
     */
    List<SysSequenceDO> selectByExample(SysSequenceDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<SysSequenceDO> selectByExampleSelective(@Param("example") SysSequenceDOExample example, @Param("selective") SysSequenceDO.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated 2019-03-18 16:56:05
     */
    SysSequenceDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    SysSequenceDO selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") SysSequenceDO.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated 2019-03-18 16:56:05
     */
    int updateByExampleSelective(@Param("record") SysSequenceDO record, @Param("example") SysSequenceDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated 2019-03-18 16:56:05
     */
    int updateByExample(@Param("record") SysSequenceDO record, @Param("example") SysSequenceDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated 2019-03-18 16:56:05
     */
    int updateByPrimaryKeySelective(SysSequenceDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated 2019-03-18 16:56:05
     */
    int updateByPrimaryKey(SysSequenceDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    SysSequenceDO selectOneByExample(SysSequenceDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    SysSequenceDO selectOneByExampleSelective(@Param("example") SysSequenceDOExample example, @Param("selective") SysSequenceDO.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsert(@Param("list") List<SysSequenceDO> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsertSelective(@Param("list") List<SysSequenceDO> list, @Param("selective") SysSequenceDO.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsert(SysSequenceDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_sequence
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsertSelective(SysSequenceDO record);
}