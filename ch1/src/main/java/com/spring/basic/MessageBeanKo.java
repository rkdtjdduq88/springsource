package com.spring.basic;

public class MessageBeanKo implements MessageBean {

	@Override
	public void sayHello(String name) {
		System.out.println("안녕하세요, "+name);
	}

}
