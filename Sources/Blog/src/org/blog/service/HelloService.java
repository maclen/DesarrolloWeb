package org.blog.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("helloService")
public class HelloService {

	public void sayHello(){
		System.out.println("Hello! :3");
	}
	
}