package com.yiding.saas.ydsaas.dao.business;

import com.yiding.saas.ydsaas.dao.domain.YdSysDictDO;
import com.yiding.saas.ydsaas.dao.domain.YdSysDictDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YdSysDictMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated 2019-06-25 16:05:15
     */
    long countByExample(YdSysDictDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated 2019-06-25 16:05:15
     */
    int deleteByExample(YdSysDictDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated 2019-06-25 16:05:15
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated 2019-06-25 16:05:15
     */
    int insert(YdSysDictDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated 2019-06-25 16:05:15
     */
    int insertSelective(YdSysDictDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated 2019-06-25 16:05:15
     */
    List<YdSysDictDO> selectByExample(YdSysDictDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<YdSysDictDO> selectByExampleSelective(@Param("example") YdSysDictDOExample example, @Param("selective") YdSysDictDO.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated 2019-06-25 16:05:15
     */
    YdSysDictDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    YdSysDictDO selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") YdSysDictDO.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated 2019-06-25 16:05:15
     */
    int updateByExampleSelective(@Param("record") YdSysDictDO record, @Param("example") YdSysDictDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated 2019-06-25 16:05:15
     */
    int updateByExample(@Param("record") YdSysDictDO record, @Param("example") YdSysDictDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated 2019-06-25 16:05:15
     */
    int updateByPrimaryKeySelective(YdSysDictDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated 2019-06-25 16:05:15
     */
    int updateByPrimaryKey(YdSysDictDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    YdSysDictDO selectOneByExample(YdSysDictDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    YdSysDictDO selectOneByExampleSelective(@Param("example") YdSysDictDOExample example, @Param("selective") YdSysDictDO.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsert(@Param("list") List<YdSysDictDO> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsertSelective(@Param("list") List<YdSysDictDO> list, @Param("selective") YdSysDictDO.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsert(YdSysDictDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int upsertSelective(YdSysDictDO record);
}