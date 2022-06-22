package com.allaboutspring.demo.beanscope;

import org.springframework.stereotype.Component;

@Component
public class SingletonScope {
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

/*
 * As no scope is defined here by default singleton scope is considered
 */