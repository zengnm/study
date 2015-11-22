package com.share.example.dao.example.impl;

import com.share.example.dao.BaseDao;
import com.jd.common.util.Query;
import com.share.example.dao.example.ExampleDao;
import com.share.example.domain.example.Example;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Administrator
 * Date: 2010-4-15
 * Time: 18:23:46
 */
@Repository("ExampleDao")
public class ExampleDaoImpl extends BaseDao implements ExampleDao {
    @Override
    public Example findExampleById(Integer id) {
        return (Example)selectOne("findExampleById", id);
    }

    /**
     * 插入
     * @param example 实例数据
     */
    @Override
    public void createExample(Example example) {
         insert("createExample", example);
    }

    /**
     *  删除
     * @param example  实例数据
     */
    @Override
    public void deleteExample(Example example){
        delete("deleteExample", example);
    }

    /**
     * 更新
     * @param example  实例数据
     */
    @Override
    public void updateExample(Example example){
        update("updateExample", example);
    }


    /**
     * 总数
     * @param example 参数
     * @return 总数
     */
    @Override
    public int getExamplesCount(Example example) {
        return selectCount("getExamplesCount",example);
    }


    /**
     * 分页查询
     * @param example 查询条件
     * @param offset 起始序号
     * @param limit  行数
     * @return 实例列表
     */
    @Override
    public List<Example> findExamplesPage(Example example, int offset, int limit) {
        return (List<Example>)selectList("findExamplesPage", example,offset, limit);
    }
}
