package com.share.example.manager.example;

import com.jd.common.util.PaginatedList;
import com.jd.common.util.Query;
import com.share.example.domain.example.Example;

import java.util.List;

/**
 * User: Administrator
 * Date: 2010-4-19
 * Time: 14:03:00
 */
public interface ExampleManager {
    /**
     * 根据id获取example
     * @param id
     * @return
     */
    Example findExampleById(Integer id);

    /**
     * 通过分页查找
     *
     * @param example
     *@param pageIndex
     * @param pageSize   @return
     */
    PaginatedList<Example> findExamples(String example, Integer type, Integer pageIndex, int pageSize);


    /**
     * 创建对象
     * @param example
     */
    int createExample(Example example);

    /**
     * 删除对象
     * @param example
     */
    int deleteExample(Example example);

    /**
     * 更改对象
     * @param example
     */
    int updateExample(Example example);

}
