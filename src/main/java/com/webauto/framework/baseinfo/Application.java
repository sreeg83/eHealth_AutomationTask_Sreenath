package com.webauto.framework.baseinfo;

import java.util.List;

public class Application {
	private String TestCaseNo;
	private String SearchInput;

	public Application() {

	}

	public Application(List<String> in_list) {

		this.TestCaseNo = in_list.get(0);
		this.SearchInput = in_list.get(1);
		

	}

	public String get_TestCaseNo() {
		return TestCaseNo;
	}

	public String get_SearchInput() {
		return SearchInput;
	}

}