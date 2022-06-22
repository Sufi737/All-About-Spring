package com.allaboutspring.demo.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsesDependencies {
	@Autowired
	private ProviderInterface provider;
	
	@Autowired
	private ThirdPartyInterface thirdParty;

	public UsesDependencies(
			ProviderInterface provider,
			ThirdPartyInterface thirdParty
	) {
		this.provider = provider;
		this.thirdParty = thirdParty;
	}

	public void useProvider() {
		String message = provider.returnString();
		System.out.println(message);
	}
	
	public void useThirdParty() {
		System.out.println(thirdParty.returnString());
	}
}

/*
 * Coding for interface instead of the actual implementation class
 * @Autowired will automatically try to find the implementation class for the given interface
 * If multiple implementations present we can use @Qualifier and specify the implmentation class
 */