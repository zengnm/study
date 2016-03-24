package com.share.example.dao.example;

import com.share.example.domain.example.Example;
import java.util.List;

/**
 * @author zengnianmei
 * @version 1.0, 2015/11/21
 */
public interface ExampleDao {
    /**
     * 根据id获取example
     * @param id 序号
     * @return 单个实例
     */
    Example findExampleById(Integer id);

    /**
     * 取得总记录数
     * @return 记录数量
     */
    int getExamplesCount(Example example);

    /**
     * 取得相关的记录数
     * @param example 查询条件
     * @param offset 起始序号
     * @param limit  行数
     * @return 实例列表
     */
    List<Example> findExamplesPage(Example example, int offset, int limit);

    /**
     * 创建对象
     * @param example 实例
     */
    void createExample(Example example);

    /**
     * 删除对象
     * @param example id
     */
    void deleteExample(Example example);

    /**
     * 更改对象
     * @param example name,type,modified ( where id)
     */
    void updateExample(Example example);
}
