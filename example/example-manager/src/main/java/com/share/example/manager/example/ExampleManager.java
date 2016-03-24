package com.share.example.manager.example;

import com.share.common.util.PaginatedList;
import com.share.example.domain.example.Example;

/**
 * @author zengnianmei
 * @version 1.0, 2015/11/21
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
