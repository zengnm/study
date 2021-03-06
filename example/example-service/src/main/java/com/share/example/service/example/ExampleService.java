package com.share.example.service.example;

import com.share.common.web.result.Result;
import com.share.example.domain.example.Example;
 

/**
 * @author zengnianmei
 * @version 1.0, 2015/11/21
 */
public interface ExampleService {
    /**
     * 根据id获取example
     * @param id
     * @return
     */
    Example findExampleById(Integer id);

    /**
     * 通过分页查找
     * @param name
     * @param type
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Result findExamples(String name, Integer type, Integer pageIndex, int pageSize) ;


    /**
     * 创建对象
     * @param example
     */
    Result createExample(Example example);

    /**
     * 删除对象
     * @param example
     * @return
     */
    Result deleteExample(Example example);

    /**
     * 更新对象
     * @param example
     * @return
     */
    Result updateExample(Example example);
}
