package com.subway.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.subway.domain.Fault;
import com.subway.domain.FaultSearchVO;
import com.subway.domain.PageBean;
import com.subway.report.javabean.DayReportBean;
import com.subway.service.FaultService;
import com.subway.util.LogUtils;

public class FaultAction extends BaseAction<Fault> {
	final String diaodu="已处理";
	final String gongban="未处理' or isConfirm='已确定 ";
	final String gongchengshi="已分析";
	private Fault fault;
	private PageBean<Fault> pageBean;
	private FaultSearchVO faultSearchVO;
	private Integer pageIndex;
	private Integer pageSize;
	private String data;
	private int id;

	private String code;
	private String major;
	private String userowner;
	private String state;
	private String cause;
	private String timestart;
	private String timeend;

	public String getTimestart() {
		return timestart;
	}

	public void setTimestart(String timestart) {
		this.timestart = timestart;
	}

	public String getTimeend() {
		return timeend;
	}

	public void setTimeend(String timeend) {
		this.timeend = timeend;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Autowired
	@Qualifier("fs")
	private FaultService fs;

	public String deleteFaultInfo() {
		writeHtml(fs.removeFault(id));
		LogUtils.log(getSession(),(String)getSession().get("username")+"修改工单  id为"+id, "删除");

		
		return NONE;
	}

	public String CreateNewFault() {
		fault.setCode(getFaultCode());
		fs.addFault(fault);
		Fault fault2 = fs.getFaultByCode(fault.getCode());
		LogUtils.log(getSession(),(String)getSession().get("username")+"添加工单  编号为"+fault.getCode(), "添加");

		return NONE;
	}

	public String ChangeFault() {
		fs.modifyFault(fault);
		LogUtils.log(getSession(),(String)getSession().get("username")+"修改工单  编号为"+fault.getCode(), "修改");

		return NONE;
	}

	public String searchFault() {
		return NONE;
	}

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}

	public String getFaultCode() {
		int sum = fs.getTodaySum();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String code = "TH" + sdf.format(d) + sum / 10 + "" + sum % 10;
		return code;
	}

	public String getFaultInfo() {
		Fault fault = fs.getFaultById(id);
		writeJson(fault.toString());
		return NONE;
	}
	

	public String getFaultInfoByCode() {
		Fault fault = fs.getFaultByCode(code);
		System.out.println(fault);
		writeJson(fault.toString());
		return NONE;
	}

	public String getSimpleCode() {
		writeHtml(getFaultCode());
		return NONE;
	}

	/**
	 * 存储一个工单，若工单不存在则新建，新建工单的初始状态为未处理 如果已存在则修改工单数据并持久化
	 * 
	 * @return
	 */
	
	
	
	public String saveFault() {
		System.out.println("data:" + data);
		Fault fault = JSON.parseObject(data, Fault.class);
		fault.setGeneratePeople(getSession().get("username").toString());
		String result = "";
		
		if (fault.getId() == 0) {
			fault.setIsConfirm("未处理");
			fault.setCode(getFaultCode());
			result = fs.addFault(fault);
			LogUtils.log(getSession(),(String)getSession().get("username")+"添加工单编号为"+fault.getCode(), "添加");
		} else {
			result = fs.modifyFault(fault);
			LogUtils.log(getSession(),(String)getSession().get("username")+"修改工单编号为"+fault.getCode(), "修改");

		}
		writeHtml(result);
		return NONE;
	}

	/**
	 * 工班填写处理经过后提交数据库后限制再次提交
	 * 
	 * @return
	 */
	public String EditProcess() {

		Fault fault = JSON.parseObject(data, Fault.class);
		Fault faultPersist = fs.getFaultByCode(fault.getCode());
		faultPersist.setAccepter(getSession().get("username").toString());
		if (faultPersist.getIsConfirm().equals("未处理")) {
			faultPersist.setProcess(fault.getProcess());
			faultPersist.setIsConfirm("已处理");
			String result = "";
			result = fs.modifyFault(faultPersist);
			writeHtml(result);

			LogUtils.log(getSession(),(String)getSession().get("username")+"填写处理经过于工单"+fault.getCode(), "修改");
		} else {
			
			writeHtml("暂无修改权限||勿重复提交！");
		}
		return NONE;
	}
	public String CommitStatus() {
		Fault fault = JSON.parseObject(data, Fault.class);
		Fault faultPersist = fs.getFaultByCode(fault.getCode());
		faultPersist.setConfirmPeople(getSession().get("username").toString());
		String result = "";
		if (!fault.getSubwaystate().equals("已恢复")) {
			faultPersist.setSubwaystate(fault.getSubwaystate());
			faultPersist.setIsConfirm("未处理");
			result = fs.modifyFault(faultPersist);

			writeHtml("处理结果"+result+"已下发至工班重填！");
			LogUtils.log(getSession(),(String)getSession().get("username")+"因处理经过不当驳回了工单"+fault.getCode(), "修改");
		}else{
			faultPersist.setIsConfirm("已确定");
			
			faultPersist.setBacktime(new Date());
			faultPersist.setSubwaystate(fault.getSubwaystate());
			result = fs.modifyFault(faultPersist);
			writeHtml("处理结果"+result);
			LogUtils.log(getSession(),(String)getSession().get("username")+"因处理经过通过了工单"+fault.getCode(), "修改");

		}
		return NONE;
	}
	
	public String getTodayFault()
	{
		DayReportBean drp=new DayReportBean();
		List<Fault> list=drp.getFaultToday();
		StringBuffer sb=new StringBuffer("");
		for (Fault fault : list) {
			sb.append("<li>"+fault.getPlace()+" "+fault.getCause()+" "+fault.getSubwaystate()+"</li>");
		}
		writeHtml(sb.toString());
		return NONE;
	}
	
	/**
	 * 分析故障原因	
	 * @return
	 */
	public String CauseAnalyse() {
			Fault fault = JSON.parseObject(data, Fault.class);
			Fault faultPersist = fs.getFaultByCode(fault.getCode());
			faultPersist.setAnalyseConfirmPeo(getSession().get("username").toString());
			String result = "";
			if (faultPersist.getSubwaystate().equals("已恢复")&&(faultPersist.getIsConfirm().trim().equals("已确定"))){
				faultPersist.setCause(fault.getCause());
				faultPersist.setDevice(fault.getDevice());
				faultPersist.setCauseAnalyse(fault.getCauseAnalyse());
				faultPersist.setIsConfirm("已分析");
				result = fs.modifyFault(faultPersist);
				writeHtml("故障分析" +result);
				LogUtils.log(getSession(),(String)getSession().get("username")+"分析了故障原因  工单"+fault.getCode(), "修改");

			}else{
				writeHtml("暂无修改权限||勿重复提交！");
			}
			return NONE;
		}
	/**
	 * 确认或驳回分析原因
	 * @return
	 */
	public String ConfirmCause(){
		Fault fault = JSON.parseObject(data, Fault.class);
		Fault faultPersist = fs.getFaultByCode(fault.getCode());
		faultPersist.setCauseConfirmPeo(getSession().get("username").toString());
		String result="";
		if(!faultPersist.getIsConfirm().equals("完成")){
			if (fault.getIsConfirm().equals("原因确认")) {
				faultPersist.setIsConfirm("完成");
				result = fs.modifyFault(faultPersist);
				writeHtml("原因分析确认" +result);
				LogUtils.log(getSession(),(String)getSession().get("username")+"因分析原因合理 通过了工单"+fault.getCode(), "修改");

			}else if(fault.getIsConfirm().equals("重新分析")){
				faultPersist.setIsConfirm(fault.getIsConfirm());
				faultPersist.setIsConfirm("已确定");
				result = fs.modifyFault(faultPersist);
				writeHtml("原因驳回" +result);
				LogUtils.log(getSession(),(String)getSession().get("username")+"因分析原因不当 驳回了工单"+fault.getCode(), "修改");

			}
			
		}else{
			writeHtml("暂无权限");
			
		}
		return NONE;
	}

	/**
	 * 根据限制条件查找故障单
	 * 
	 * @return
	 */
	public String findFaultBySearchVO() {
		faultSearchVO = new FaultSearchVO();
		faultSearchVO.setCode(getRequest().getParameter("code"));
		faultSearchVO.setGrade(getRequest().getParameter("level"));
		faultSearchVO.setMajor(getRequest().getParameter("major"));
		faultSearchVO.setCause(getRequest().getParameter("cause"));
		faultSearchVO.setStatus(getRequest().getParameter("state"));
		faultSearchVO.setIsConfirm(getRequest().getParameter("isConfirm"));
		faultSearchVO.setUserowner(getRequest().getParameter("userowner"));
		faultSearchVO.setDevice(getRequest().getParameter("device"));
		faultSearchVO.setPlace(getRequest().getParameter("place"));
		faultSearchVO.setTimestart(getRequest().getParameter("timestart"));
		faultSearchVO.setTimeend(getRequest().getParameter("timeend"));
		pageBean = new PageBean<Fault>();
		pageBean.setPageSize(pageSize);
		pageBean.setPageIndex(pageIndex + 1);
		fs.getFaults(pageBean, faultSearchVO);
		writeJson(getJsonFromString(pageBean));
		return NONE;
	}
	
	public String getUnprocessSum()
	{
		String userowner=(String)getSession().get("userowner");
		String authen=getSession().get("authen").toString();
		DayReportBean drb = new DayReportBean();
		int i=drb.getUnprocessSum(userowner, authen);
		writeHtml(i+"");
		return NONE;
	}
	
	public String findFaultBySearchVOforGongban() {
		faultSearchVO = new FaultSearchVO();
		faultSearchVO.setIsConfirm(gongban);
		faultSearchVO.setCode(getRequest().getParameter("code"));
		faultSearchVO.setGrade(getRequest().getParameter("level"));
		faultSearchVO.setMajor(getRequest().getParameter("major"));
		faultSearchVO.setCause(getRequest().getParameter("cause"));
		faultSearchVO.setStatus(getRequest().getParameter("state"));
		faultSearchVO.setUserowner(getSession().get("userowner").toString());
		
		faultSearchVO.setDevice(getRequest().getParameter("device"));
		faultSearchVO.setPlace(getRequest().getParameter("place"));
		faultSearchVO.setTimestart(getRequest().getParameter("timestart"));
		faultSearchVO.setTimeend(getRequest().getParameter("timeend"));
		pageBean = new PageBean<Fault>();
		pageBean.setPageSize(pageSize);
		pageBean.setPageIndex(pageIndex + 1);
		fs.getFaults(pageBean, faultSearchVO);
		writeJson(getJsonFromString(pageBean));
		return NONE;
	}
	
	
	public String findFaultBySearchVOforGongchengshi() {
		faultSearchVO = new FaultSearchVO();
		faultSearchVO.setIsConfirm(gongchengshi);
		faultSearchVO.setCode(getRequest().getParameter("code"));
		faultSearchVO.setGrade(getRequest().getParameter("level"));
		faultSearchVO.setMajor(getRequest().getParameter("major"));
		faultSearchVO.setCause(getRequest().getParameter("cause"));
		faultSearchVO.setStatus(getRequest().getParameter("state"));
		faultSearchVO.setUserowner(getSession().get("userowner").toString());
		
		faultSearchVO.setDevice(getRequest().getParameter("device"));
		faultSearchVO.setPlace(getRequest().getParameter("place"));
		faultSearchVO.setTimestart(getRequest().getParameter("timestart"));
		faultSearchVO.setTimeend(getRequest().getParameter("timeend"));
		pageBean = new PageBean<Fault>();
		pageBean.setPageSize(pageSize);
		pageBean.setPageIndex(pageIndex + 1);
		fs.getFaults(pageBean, faultSearchVO);
		writeJson(getJsonFromString(pageBean));
		return NONE;
	}
	
	public String findFaultBySearchVOforDiaodu() {
		faultSearchVO = new FaultSearchVO();
		faultSearchVO.setIsConfirm(diaodu);
		faultSearchVO.setCode(getRequest().getParameter("code"));
		faultSearchVO.setGrade(getRequest().getParameter("level"));
		faultSearchVO.setMajor(getRequest().getParameter("major"));
		faultSearchVO.setCause(getRequest().getParameter("cause"));
		faultSearchVO.setStatus(getRequest().getParameter("state"));
		faultSearchVO.setUserowner(getSession().get("userowner").toString());
		
		faultSearchVO.setDevice(getRequest().getParameter("device"));
		faultSearchVO.setPlace(getRequest().getParameter("place"));
		faultSearchVO.setTimestart(getRequest().getParameter("timestart"));
		faultSearchVO.setTimeend(getRequest().getParameter("timeend"));
		pageBean = new PageBean<Fault>();
		pageBean.setPageSize(pageSize);
		pageBean.setPageIndex(pageIndex + 1);
		fs.getFaults(pageBean, faultSearchVO);
		writeJson(getJsonFromString(pageBean));
		return NONE;
	}
	
	
	
	

	
		
	
	
	
	
	public String exportdata() {
		System.out
				.println("............................export data to excel 1........................");
		faultSearchVO = new FaultSearchVO();
		faultSearchVO.setCode(getRequest().getParameter("code"));
		faultSearchVO.setGrade(getRequest().getParameter("level"));
		faultSearchVO.setMajor(getRequest().getParameter("major"));
		faultSearchVO.setCause(getRequest().getParameter("cause"));
		faultSearchVO.setStatus(getRequest().getParameter("state"));
		faultSearchVO.setIsConfirm(getRequest().getParameter("isConfirm"));
		faultSearchVO.setUserowner(getRequest().getParameter("userowner"));
		faultSearchVO.setDevice(getRequest().getParameter("device"));
		faultSearchVO.setPlace(getRequest().getParameter("place"));
		faultSearchVO.setTimestart(getRequest().getParameter("timestart"));
		faultSearchVO.setTimeend(getRequest().getParameter("timeend"));
		pageBean = new PageBean<Fault>();
		pageBean.setPageSize(99999999);
		pageBean.setPageIndex(1);
		System.out
				.println("............................export data to excel 2........................");
		pageBean = fs.getFaults(pageBean, faultSearchVO);
		System.out
				.println(pageBean.getDatas().size()
						+ "............................export data to excel 3........................");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String basePath = getRealPath();
		Date d = new Date();
		System.out.println("basepath:" + basePath);
		fs.printFaultTable(pageBean.getDatas(),
				basePath + "dbBack/" + sdf.format(d) + ".xls");
		System.out.println(basePath + "dbBack/" + sdf.format(d) + ".xls");
		writeHtml(sdf.format(d) + ".xls");
		System.out.println("ok....");
		return NONE;
	}

	public PageBean<Fault> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Fault> pageBean) {
		this.pageBean = pageBean;
	}

	public FaultSearchVO getFaultSearchVO() {
		return faultSearchVO;
	}

	public void setFaultSearchVO(FaultSearchVO faultSearchVO) {
		this.faultSearchVO = faultSearchVO;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getUserowner() {
		return userowner;
	}

	public void setUserowner(String userowner) {
		this.userowner = userowner;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

}
