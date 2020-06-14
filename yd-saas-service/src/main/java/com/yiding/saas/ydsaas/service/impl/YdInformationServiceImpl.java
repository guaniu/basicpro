package com.yiding.saas.ydsaas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiding.saas.ydsaas.common.enums.CommonEnum;
import com.yiding.saas.ydsaas.dao.YdInformationMapper;
import com.yiding.saas.ydsaas.model.YdInformation;
import com.yiding.saas.ydsaas.service.YdInformationService;

@Service
public class YdInformationServiceImpl implements YdInformationService {
	
	@Autowired
	private YdInformationMapper ydInformationMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return ydInformationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(YdInformation record) {
		return ydInformationMapper.insert(record);
	}

	@Override
	public int insertSelective(YdInformation record) {
		return ydInformationMapper.insertSelective(record);
	}

	@Override
	public YdInformation selectByPrimaryKey(Integer id) {
		return ydInformationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(YdInformation record) {
		return ydInformationMapper.updateByPrimaryKeySelective(record);
				
	}

	@Override
	public int updateByPrimaryKey(YdInformation record) {
		return ydInformationMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageInfo<YdInformation> selectAll(YdInformation record) {
		PageHelper.startPage(record.getPageNum(), record.getPageSize());
        //处理数据权限
       /* List<String> orgIds_str = new ArrayList<>();
        for (Long orgid : record.getOrgIds()) {
            orgIds_str.add("-" + orgid + "-");
        }*/
        //数据权限封装
//        record.setOrgStrIds(orgIds_str);
		List<YdInformation> list = ydInformationMapper.selectAll(record);
		if(list!=null && list.size()>0) {
			for (YdInformation ydInformation : list) {
				ydInformation.setTypeLabel(CommonEnum.getInfoType(ydInformation.getType().toString()));
			}
		}
		PageInfo<YdInformation> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

}
