package org.blog.controller;

import org.blog.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello {

	@Autowired
	@Qualifier("helloService")
	HelloService helloService;
	
	@RequestMapping(value="/", method=RequestMethod.GET, produces="text/plain")
	@ResponseBody
	public String helloWorld() {
		helloService.sayHello();
		return "Hello World! :3";
    }
	
}