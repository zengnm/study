package com.berwin.cloud.controller;

import java.util.List;

import com.berwin.cloud.model.BaseDate;
import com.berwin.cloud.model.UserEntity;
import com.berwin.cloud.service.BaseDateService;
import com.berwin.cloud.util.RedisUtil;
import com.berwin.cloud.util.WebHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private BaseDateService basedateService;

	@RequestMapping(value={"login","/"},method= RequestMethod.GET)
	public ModelAndView login(){
		return new ModelAndView("login");
	}

	@RequestMapping(value="login",method=RequestMethod.POST)
	public ModelAndView login(UserEntity user, RedirectAttributes redirect){
		// 登录校验
//		try {
//			user = loginService.login(user);
//		} catch (Exception e) {
//			logger.debug(e.getMessage());
//			redirect.addFlashAttribute("err_code", e.getMessage());
//			redirect.addFlashAttribute("user", user);
//			return new ModelAndView("redirect:/login");
//		}
		WebHelper.setUser(user);
		return new ModelAndView("redirect:hello");
	}
	
	@RequestMapping("/hello")
	public String hello(){
		return "Hello World!" + WebHelper.getUser().getName();
	}
	
	@RequestMapping("/data")
	public ModelAndView find(Model model){
		List<BaseDate> list = basedateService.find();
		logger.info("查询得到的list为： " + list);
		model.addAttribute("list", list);
		model.addAttribute("userName", WebHelper.getUser().getName());
		return new ModelAndView("index");
	}

	/**
	 * @Cacheable 参数：
	 * value  指明缓存将被存到什么地方。
	 * key   Spring默认使用被@Cacheable注解的方法的签名来作为key
	 * condition = "#age < 25" 数将指明方法的返回结果是否被缓存。
	 */
	@RequestMapping("/cache")
	@Cacheable(value = "test")
	public String cache(){
		RedisUtil.set("test", "测试");
		System.out.println("cache success" + RedisUtil.get("test"));
		return (String) RedisUtil.get("test");
	}

	@RequestMapping("/cache1")
	@Cacheable(value = "test", keyGenerator ="keyGenerator")
	public String cache1(){
		System.out.println("进入方法啦");
		return "success1";
	}
}
