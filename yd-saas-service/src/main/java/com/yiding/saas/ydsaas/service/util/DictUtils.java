package com.yiding.saas.ydsaas.service.util;

import com.yiding.saas.ydsaas.dao.SysDictMapper;
import com.yiding.saas.ydsaas.dao.business.YdSysDictMapper;
import com.yiding.saas.ydsaas.dao.domain.YdSysDictDO;
import com.yiding.saas.ydsaas.dao.domain.YdSysDictDOExample;
import com.yiding.saas.ydsaas.model.SysDict;
import com.yiding.saas.ydsaas.model.UniqIdModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DictUtils {
    @Autowired
    private YdSysDictMapper ydSysDictMapper;

    Map <String, Map<String,String>> map = new HashMap<String, Map<String,String>>();

    @Autowired
    private SysDictMapper sysDictMapper;

    private static DictUtils dictUtils;

    @PostConstruct
    public void init(){
      dictUtils= this;
      dictUtils.sysDictMapper=this.sysDictMapper;
    }

    public String getDictLabels(String type,String value){
        List<SysDict> list2=dictUtils.sysDictMapper.findByType(type);
        for (SysDict s:list2){
            if (s.getValue().equals(value)){
                return s.getLabel();
            }
        }
        return null;
    }

    public String getDictLable(String type,String value) {
        if(map.size()==0){
            loadDict();
        }
        if(map.size()>0 && map.get(type)!=null ){
           return  map.get(type).get(value);
        }
        return null;

    }

    public void  loadDict() {

        YdSysDictDOExample example = new YdSysDictDOExample();

        List<YdSysDictDO> list = ydSysDictMapper.selectByExample(example);

        for (YdSysDictDO sd : list){
            if (StringUtils.isNotBlank(sd.getType())&& map.get(sd.getType())==null){
                Map labmap = new HashMap();
                labmap.put(sd.getValue(), sd.getLabel());
                map.put(sd.getType(), labmap);
            }else if (StringUtils.isNotBlank(sd.getType())&& map.get(sd.getType())!=null){
                Map labmap = map.get(sd.getType());
                labmap.put(sd.getValue(), sd.getLabel());
                map.put(sd.getType(), labmap);
            }
        }

    }

}
