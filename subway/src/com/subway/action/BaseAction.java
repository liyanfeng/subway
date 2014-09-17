package com.subway.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.subway.domain.PageBean;

public class BaseAction<T> extends ActionSupport  implements ServletRequestAware, ServletResponseAware, SessionAware {

	private static final long serialVersionUID = 1L;

	protected  T t ;
	
	private HttpServletRequest request;

	private HttpServletResponse response;

	private Map<String, Object> session;

	private List<T> list;
	
	protected String getRealPath() {
		return request.getSession().getServletContext().getRealPath("/");
	}

	
	public String getJsonFromString(PageBean<T> pb) {
		StringBuffer jsonStr = new StringBuffer("");
		jsonStr.append("{total:" + pb.getTotalRecords() + ",data:[");
		for (T t : pb.getDatas()) {
			if (!t.equals(pb.getDatas().get(pb.getDatas().size() - 1)))
				jsonStr.append(t.toString() + ",");
			else
				jsonStr.append(t.toString());
		}
		// jsonStr.substring(0, jsonStr.length()-2);
		jsonStr.append("]}");
		System.out.println(jsonStr.toString());
		return jsonStr.toString();
	}
	public String getJsonArryFromString(String key,List<String> list)
	{
		StringBuffer jsonStr=new StringBuffer("[{\""+key+"\":\"\"},");
		int i;
		for (i = 0; i < list.size()-1; i++) {
			
			jsonStr.append("{\""+key+"\":\""+list.get(i)+"\"},");
		}	
		jsonStr.append("{\""+key+"\":\""+list.get(i)+"\"}]");
		return jsonStr.toString();
	}
	
	protected Map<String, Object> getSession() {
		return this.session;
	}

/**
 * hello
 * @return
 */
	@SuppressWarnings("finally")
	public HttpServletRequest getRequest() {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			return request;
		}

	}

	public HttpServletResponse getResponse() {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "must-revalidate");
		response.addHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		return response;
	}



	public void writeJson(String json) {
		getResponse().setContentType("application/json");
		try {
			getResponse().getWriter().write(json);
			getResponse().getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeHtml(String html) {
		getResponse().setContentType("text/html");
		try {
			getResponse().getWriter().write(html);
			getResponse().getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write(String str) {
		try {
			getResponse().getWriter().write(str);
			getResponse().getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public T getT() {
		return t;
	}



	public void setT(T t) {
		this.t = t;
	}



	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}


	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
	

	
}
