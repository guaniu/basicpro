package com.yiding.saas.ydsaas.web.biz;

import com.yiding.saas.ydsaas.model.YdTobacco;
import com.yiding.saas.ydsaas.service.YdTobaccoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TobaccoBizService {

    @Autowired
    private YdTobaccoService ydTobaccoService;

    /**
     * 保存烟信息
     *
     * @param ydTobacco
     */
    public void save(YdTobacco ydTobacco) {
        ydTobacco.setCreateTime(new Date());
        ydTobaccoService.insert(ydTobacco);
    }


    /**
     * 查询烟品信息 数据太少 不分页
     *
     * @return
     */
    public List<YdTobacco> list() {
        return ydTobaccoService.list();
    }


    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteById(Long id) {
        ydTobaccoService.deleteById(id);
        return true;
    }

    /**
     * 回显
     * @param id
     * @return
     */
    public YdTobacco get(Long id){
        return ydTobaccoService.queryById(id);
    }

    public boolean update(YdTobacco ydTobacco){
        ydTobacco.setUpdateTime(new Date());
        ydTobaccoService.update(ydTobacco);
        return true;
    }

}
