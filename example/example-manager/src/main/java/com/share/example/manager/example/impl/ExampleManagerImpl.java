package com.share.example.manager.example.impl;

import com.share.example.manager.BaseManager;
import com.jd.common.util.PaginatedList;
import com.jd.common.util.Query;
import com.jd.common.util.base.BaseQuery;
import com.jd.common.util.base.PaginatedArrayList;
import com.share.example.dao.example.ExampleDao;
import com.share.example.domain.example.Example;
import com.share.example.manager.example.ExampleManager;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.JVMRandom;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: Administrator
 * Date: 2010-4-19
 * Time: 14:03:37
 */
@Service
public class ExampleManagerImpl extends BaseManager implements ExampleManager {
    private final static Logger log = Logger.getLogger(ExampleManagerImpl.class);
    @Resource
    private ExampleDao exampleDao;

    /**
     * 根据id获取example
     * @param id
     * @return
     */
    @Override
    public Example findExampleById(Integer id){
        return exampleDao.findExampleById(id);
    }

    public PaginatedList<Example> findExamples(String name, Integer type, Integer pageIndex, int pageSize) {
        Example example = new Example();
        if(StringUtils.isNotBlank(name)){
            example.setName(name); // 如果是"",不加入判断
        }
        example.setType(type);
        if(null == pageIndex) {
            pageIndex = 0;
        }

        PaginatedList<Example> examples = new PaginatedArrayList<Example>(pageIndex, pageSize); //构造返回对象，主要是用在页面上显示
        examples.setTotalItem(exampleDao.getExamplesCount(example)); //得到总的记录数量);//设置总记录数

        List<Example> exampleList = exampleDao.findExamplesPage(example, examples.getStartRow()-1, examples.getEndRow());//查询得到相应的记录
        examples.addAll(exampleList);

        return examples;
    }

    public int createExample(final Example example) {
        example.setId((int) sequenceUtil.get("pop_user_example_id"));
        try {
            exampleDao.createExample(example);
        }catch (Exception e){
            log.error("createExample error! " + e);
            throw new RuntimeException("example" + e);
        }
        return example.getId();
    }

    /**
     *
     * @param example
     * @return
     */
    public int deleteExample(final Example example) {
        try {
            exampleDao.deleteExample(example);
        } catch (Exception e) {
            log.error("deleteExample error!",e);
            throw new RuntimeException("example",e);
        }

        return example.getId();
    }

    /**
     *
     * @param example
     * @return
     */
    public int updateExample(final Example example) {
        try {
            exampleDao.updateExample(example);
        } catch (Exception e) {
            log.error("updateExample error!",e);
            throw new RuntimeException("example",e);
        }

        return example.getId();
    }

    public void setExampleDao(ExampleDao exampleDao) {
        this.exampleDao = exampleDao;
    }
}
