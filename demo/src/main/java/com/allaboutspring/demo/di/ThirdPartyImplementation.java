package com.allaboutspring.demo.di;

public class ThirdPartyImplementation implements ThirdPartyInterface {

	@Override
	public String returnString() {
		return "from third party class";
	}

}

/*
* Imitating third party class here
* Third party classes will not have @Component annotation
* So even if we add them as a dependency, spring will not be able to scan such classes
* In order to resolve these classes dependencies, we need to add a method in our configuration class
* Our configuration class is our main DemoApplication class
* In that class we have added returnThirdParty() method to demonstrate how we can register third party classes as beans
*
*/