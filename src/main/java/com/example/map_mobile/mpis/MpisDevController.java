package com.example.map_mobile.mpis;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mig.before.MigMigrationService;

@Controller
@RequestMapping(value="/mpis")
public class MpisDevController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Resource(name = "mpisJsonService")
	private MpisJsonService mpisJsonService;
	
	@RequestMapping(value="/getMpisData.do")
	@ResponseBody
	public String loadPostgresColumn(@RequestBody Map param ,  ModelMap mv) throws  IOException{ 
		String url = param.get("jsonUrl").toString();
		
		@SuppressWarnings("unchecked")
		 
		
		String jsonData = mpisJsonService.getPostData(url,  param);
		return jsonData;
		
	}
	 
	
	@RequestMapping(value="/getMpisData2.do") 
	@ResponseBody 
	public String modelLoad(HttpServletRequest request, @ModelAttribute("pageInfo") MpisDataVo vo  ,  ModelMap mv) throws  IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{ 
		 
		Map<String, Object> testMap = BeanUtils.describe(vo);
		
		
		 
		String url = vo.getJsonUrl();
		
		
		String jsonData = mpisJsonService.getPostData(url,  testMap);
		return jsonData;
		
	}
	 
}
