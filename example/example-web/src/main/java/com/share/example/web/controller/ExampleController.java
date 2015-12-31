package com.share.example.web.controller;

import com.jd.common.web.result.Result;
import com.share.example.domain.example.Example;
import com.share.example.service.example.ExampleService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zengnianmei
 * @version 1.0, 2015/11/21.
 */
@Controller
@RequestMapping("/example")
public class ExampleController {
    private static final Logger log = Logger.getLogger(ExampleController.class);

    @Resource
    private ExampleService exampleService;

    @RequestMapping(value = "/list")
    public String list(Model model, String name, Integer type, Integer page){
        Result result = exampleService.findExamples(name, type, page, 5);
        model.addAllAttributes(result.getMap());
        return "/example/list";
    }

    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String add(){
        return "/example/add";
    }

    @RequestMapping(value="/doAdd", method = RequestMethod.POST)
    public String doAdd(String name, Integer type, Model model){
        Example example = new Example();
        example.setName(name);
        example.setType(type);
        HashMap map = new HashMap<String, Object>();
        map.put("result", exampleService.createExample(example));
        model.addAllAttributes(map);
        return "/example/doAdd";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(Model model, Integer id){
        Example example = new Example();
        example.setId(id);
        HashMap map = new HashMap<String, Object>();
        map.put("result", exampleService.deleteExample(example));
        model.addAllAttributes(map);
        return "/example/doDelete";
    }

    @RequestMapping(value="/update", method = RequestMethod.GET)
    public String update(Integer id, Model model){
        Example example = exampleService.findExampleById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("example", example);
        model.addAllAttributes(map);
        return "/example/update";
    }
    @RequestMapping(value = "/doUpdate")
    public String doUpdate(Integer id, String name, Integer type, Model model){
        Example example = new Example();
        example.setId(id);
        example.setName(name);
        example.setType(type);
        example.setModified(new Date());
        HashMap map = new HashMap<String, Object>();
        map.put("result", exampleService.updateExample(example));
        model.addAllAttributes(map);
        return "/example/doUpdate";
    }

    public ExampleService getExampleService() {
        return exampleService;
    }

    public void setExampleService(ExampleService exampleService) {
        this.exampleService = exampleService;
    }
}
