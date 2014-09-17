package com.subway.action;

import java.util.Map;

import javax.jms.Session;

import com.jspsmart.upload.Request;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SeeFault extends ActionSupport{
	@Override
	public String execute() throws Exception {
		Map session=ActionContext.getContext().getSession();
		if(session.get("authen").toString().equals("工班"))
			return "gongban";
		else if(session.get("authen").toString().equals("调度"))
			return "diaodu";
		else if(session.get("authen").toString().equals("工程师"))
			return "gongchengshi";
		return super.execute();
	}
}
