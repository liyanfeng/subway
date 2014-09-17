package com.subway.service;

import java.util.List;

import com.subway.domain.Fault;
import com.subway.domain.FaultSearchVO;
import com.subway.domain.PageBean;

public interface FaultService {
	public String addFault(Fault fault);
	public Fault getFaultById(int id);
	public Fault getFaultByCode(String code);
	public String modifyFault(Fault fault);
	public PageBean<Fault> getList(PageBean<Fault> pb);
	public PageBean<Fault> getList(PageBean<Fault> pb,FaultSearchVO vo);
	public List<Fault> getAll();
	public PageBean getFaults(PageBean<Fault> pageBean, FaultSearchVO faultSearchVO);
	public String backupFaultTable();
	public String restoreFaultTable();
	public String printFaultTable(List<Fault> list,String fileName);
	public int getTodaySum();
	public String removeFault(int id);
	public String restoreFaultTable(String fileName);
}
