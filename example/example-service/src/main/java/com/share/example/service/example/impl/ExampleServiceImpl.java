package com.share.example.service.example.impl;

import com.share.common.web.result.Result;
import com.share.example.domain.example.Example;
import com.share.example.manager.example.ExampleManager;
import com.share.example.service.example.ExampleService;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zengnianmei
 * @version 1.0, 2015/11/21
 */
@Service
public class ExampleServiceImpl implements ExampleService {

    private final static Logger log = Logger.getLogger(ExampleServiceImpl.class);

    @Override
    public Example findExampleById(Integer id) {
        return exampleManager.findExampleById(id);
    }

    public Result findExamples(String name,Integer type, Integer pageIndex, int pageSize) {
        Result result = new Result();
        try {
            result.addDefaultModel("examples", exampleManager.findExamples(name, type, pageIndex, pageSize));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", name);
            map.put("type", type);
            result.addDefaultModel("query", map);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("findExamples error!", e);
        }
        return result;
    }

    public Result createExample(Example example) {

        Result result = new Result();
        try {
            int exampleId = exampleManager.createExample(example);
            result.addDefaultModel("exampleId", exampleId);
            result.addDefaultModel("example", example);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("createExample error!", e);
            result.setResultCode("system.error");
        }
        return result;
    }

    /**
     *
     * @param example
     * @return
     */
    public Result deleteExample(Example example) {

        Result result = new Result();
        try {
            int exampleId = exampleManager.deleteExample(example);
            result.addDefaultModel("exampleId", exampleId);
            result.addDefaultModel("example", example);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("createExample error!", e);
            result.setResultCode("system.error");
        }
        return result;
    }

    /**
     * 更新对象
     * @param example
     * @return
     */
    public Result updateExample(Example example) {

        Result result = new Result();
        try {
            int exampleId = exampleManager.updateExample(example);
            result.addDefaultModel("exampleId", exampleId);
            result.addDefaultModel("example", example);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("updateExample error!", e);
            result.setResultCode("system.error");
        }
        return result;
    }

    @Resource
    private ExampleManager exampleManager;

    public void setExampleManager(ExampleManager exampleManager) {
        this.exampleManager = exampleManager;
    }
}
