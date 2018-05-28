package com.sinotech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinotech.service.MQsnService;

/**
 * Created by haakon on 2017-08-08.
 */
@Controller
public class UserController {

    @Autowired
    public MQsnService mQsnService;
    
    @RequestMapping("/testBiz")
	@ResponseBody
	public String testBizUrl(@RequestBody String jsonStr) {
    	mQsnService.addMQsn(jsonStr);
		return "1";
	}
    
}
