package com.subway.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import com.subway.action.DbBackAction;
import com.subway.action.RestoreDBAction;
import com.subway.dao.FaultDAO;
import com.subway.domain.Fault;
import com.subway.domain.FaultSearchVO;
import com.subway.domain.PageBean;
import com.subway.service.FaultService;

@Component
public class FaultServiceImpl implements FaultService {

	private FaultDAO dao = new FaultDAO();

	@Override
	public String addFault(Fault fault) {
		dao.save(fault);
		return "OK";
	}

	@Override
	public Fault getFaultById(int id) {

		return dao.findById(id);
	}

	@Override
	public Fault getFaultByCode(String code) {
		return (Fault) (dao.findByCode(code).get(0));
	}

	@Override
	public String modifyFault(Fault fault) {
		dao.attachDirty(fault);
		return "OK";
	}

	@Override
	public PageBean<Fault> getList(PageBean<Fault> pb) {
		return dao.findPage(pb);
	}

	@Override
	public PageBean<Fault> getList(PageBean<Fault> pb, FaultSearchVO vo) {
		return dao.findPage(pb, vo);
	}

	@Override
	public List<Fault> getAll() {
		return dao.findAll();
	}

	@Override
	public PageBean getFaults(PageBean<Fault> pageBean,
			FaultSearchVO faultSearchVO) {
		return dao.findPage(pageBean, faultSearchVO);
	}

	@Override
	public String backupFaultTable() {
		int index = 0;
		int sum = 10;
		HSSFWorkbook wb = new HSSFWorkbook();
		List<String> title = new ArrayList<String>();

		title.add("id");
		title.add("pdate");
		title.add("code");
		title.add("grade");
		title.add("major");

		title.add("userowner");
		title.add("finder");
		title.add("accepter");
		title.add("acceptTime");
		title.add("ptime");

		title.add("backtime");
		title.add("place");
		title.add("present");
		title.add("process");
		title.add("reqModify");

		title.add("reqback");
		title.add("subwaystate");
		title.add("cause");
		title.add("isConfirm");
		title.add("confirmPeople");

		title.add("generatePeople");
		title.add("device");
		title.add("causeAnalyse");
		title.add("analyseConfirmPeo");
		title.add("causeConfirmPeo");

		int[] recol = new int[25];
		for (int i = 0; i < 25; i++) {
			recol[i] = i;
		}

		List<String[]> values = new ArrayList<String[]>();
		HSSFSheet sheet = DbBackAction.createMySheet(wb, "usersheet");
		DbBackAction
				.export(wb, sheet, "syslog", "syslog", title, recol, values);
		List<Fault> listuser = dao.getSomeInfo(index, sum);
		System.out.println("size:" + listuser.size());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while (listuser.size() > 0) {
			listuser = dao.getSomeInfo(index, sum);
			values.clear();
			for (int i = 0; i < listuser.size(); i++) {
				String[] s = new String[25];
				s[0] = listuser.get(i).getId().toString();
				s[1] = listuser.get(i).getPdate() == null ? "" : df
						.format(listuser.get(i).getPdate());
				s[2] = listuser.get(i).getCode();
				s[3] = listuser.get(i).getGrade() == null ? "" : listuser
						.get(i).getGrade().toString();
				s[4] = listuser.get(i).getMajor();

				s[5] = listuser.get(i).getUserowner();
				s[6] = listuser.get(i).getFinder();
				s[7] = listuser.get(i).getAccepter();
				s[8] = listuser.get(i).getAcceptTime() == null ? "" : df
						.format(listuser.get(i).getAcceptTime());
				s[9] = listuser.get(i).getPtime() == null ? "" : df
						.format(listuser.get(i).getPtime());

				s[10] = listuser.get(i).getBacktime() == null ? "" : df
						.format(listuser.get(i).getBacktime());
				s[11] = listuser.get(i).getPlace();
				s[12] = listuser.get(i).getPresent();
				s[13] = listuser.get(i).getProcess();
				s[14] = listuser.get(i).getReqModify() == null ? "" : listuser
						.get(i).getReqModify().toString();

				s[15] = listuser.get(i).getReqback() == null ? "" : listuser
						.get(i).getReqback().toString();
				s[16] = listuser.get(i).getSubwaystate() == null ? ""
						: listuser.get(i).getSubwaystate().toString();
				s[17] = listuser.get(i).getCause() == null ? "0" : listuser
						.get(i).getCause().toString();
				s[18] = listuser.get(i).getIsConfirm() == null ? "0" : listuser
						.get(i).getIsConfirm().toString();
				s[19] = listuser.get(i).getConfirmPeople();

				s[20] = listuser.get(i).getGeneratePeople();
				s[21] = listuser.get(i).getDevice();
				s[22] = listuser.get(i).getCauseAnalyse();
				s[23] = listuser.get(i).getAnalyseConfirmPeo();
				s[24] = listuser.get(i).getCauseConfirmPeo();
				values.add(s);
			}
			DbBackAction.writeInfo(wb, sheet, title, values, index);
			index = index + listuser.size();
			listuser.clear();
			listuser = dao.getSomeInfo(index, sum);
			System.out.println("size:" + listuser.size());
			System.out.println("hello......");

		}
		DbBackAction.writeWorkbook(wb,"../webapps/fault.xls");// /ssh/dbBack/
		System.out.println("okokokoko");
		return "OK";
	}

	@Override
	public String printFaultTable(List<Fault> listuser, String fileName) {
		HSSFWorkbook wb = new HSSFWorkbook();
		List<String> title = new ArrayList<String>();

		title.add("id");
		title.add("pdate");
		title.add("code");
		title.add("grade");
		title.add("major");

		title.add("userowner");
		title.add("finder");
		title.add("accepter");
		title.add("acceptTime");
		title.add("ptime");

		title.add("backtime");
		title.add("place");
		title.add("present");
		title.add("process");
		title.add("reqModify");

		title.add("reqback");
		title.add("subwaystate");
		title.add("cause");
		title.add("isConfirm");
		title.add("confirmPeople");

		title.add("generatePeople");
		title.add("device");
		title.add("causeAnalyse");
		title.add("analyseConfirmPeo");
		title.add("causeConfirmPeo");

		int[] recol = new int[25];
		for (int i = 0; i < 25; i++) {
			recol[i] = i;
		}

		List<String[]> values = new ArrayList<String[]>();
		HSSFSheet sheet = DbBackAction.createMySheet(wb, "usersheet");
		DbBackAction
				.export(wb, sheet, "syslog", "syslog", title, recol, values);
		System.out.println("size:" + listuser.size());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		{
			values.clear();
			for (int i = 0; i < listuser.size(); i++) {
				String[] s = new String[25];
				s[0] = listuser.get(i).getId().toString();
				s[1] = listuser.get(i).getPdate() == null ? "" : df
						.format(listuser.get(i).getPdate());
				s[2] = listuser.get(i).getCode();
				s[3] = listuser.get(i).getGrade() == null ? "" : listuser
						.get(i).getGrade().toString();
				s[4] = listuser.get(i).getMajor();

				s[5] = listuser.get(i).getUserowner();
				s[6] = listuser.get(i).getFinder();
				s[7] = listuser.get(i).getAccepter();
				s[8] = listuser.get(i).getAcceptTime() == null ? "" : df
						.format(listuser.get(i).getAcceptTime());
				s[9] = listuser.get(i).getPtime() == null ? "" : df
						.format(listuser.get(i).getPtime());

				s[10] = listuser.get(i).getBacktime() == null ? "" : df
						.format(listuser.get(i).getBacktime());
				s[11] = listuser.get(i).getPlace();
				s[12] = listuser.get(i).getPresent();
				s[13] = listuser.get(i).getProcess();
				s[14] = listuser.get(i).getReqModify() == null ? "" : listuser
						.get(i).getReqModify().toString();

				s[15] = listuser.get(i).getReqback() == null ? "" : listuser
						.get(i).getReqback().toString();
				s[16] = listuser.get(i).getSubwaystate() == null ? ""
						: listuser.get(i).getSubwaystate().toString();
				s[17] = listuser.get(i).getCause() == null ? "0" : listuser
						.get(i).getCause().toString();
				s[18] = listuser.get(i).getIsConfirm() == null ? "0" : listuser
						.get(i).getIsConfirm().toString();
				s[19] = listuser.get(i).getConfirmPeople();

				s[20] = listuser.get(i).getGeneratePeople();
				s[21] = listuser.get(i).getDevice();
				s[22] = listuser.get(i).getCauseAnalyse();
				s[23] = listuser.get(i).getAnalyseConfirmPeo();
				s[24] = listuser.get(i).getCauseConfirmPeo();
				values.add(s);
			}
			DbBackAction.writeInfo(wb, sheet, title, values, 0);
			System.out.println("size:" + listuser.size());
			System.out.println("hello......");

		}
		DbBackAction.writeWorkbook(wb, fileName);// /ssh/dbBack/
		System.out.println("okokokoko");
		return "OK";
	}

	private Fault getSyslogFrom(List<String> list) {
		Fault fault = new Fault();
		DateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			fault.setId(Integer.parseInt(list.get(0).trim()));
			fault.setPdate(list.get(1).trim().equals("") ? null : dd.parse(list
					.get(1).trim()));
			fault.setCode(list.get(2).trim());
			fault.setGrade((list.get(3).trim()));
			fault.setMajor(list.get(4).trim());

			fault.setUserowner(list.get(5).trim());
			fault.setFinder(list.get(6).trim());
			fault.setAccepter(list.get(7).trim());
			fault.setAcceptTime(list.get(8).trim().equals("") ? null : dd
					.parse(list.get(8).trim()));
			fault.setPtime(list.get(9).trim().equals("") ? null : dd.parse(list
					.get(9).trim()));

			fault.setBacktime(list.get(10).trim().equals("") ? null : dd
					.parse(list.get(10).trim()));
			fault.setPlace(list.get(11).trim());
			fault.setPresent(list.get(12).trim());
			fault.setProcess(list.get(13).trim());
			fault.setReqModify((list.get(14).trim()));

			fault.setReqback((list.get(15).trim()));
			fault.setSubwaystate((list.get(16).trim()));
			fault.setCause((list.get(17).trim()));
			fault.setIsConfirm((list.get(18).trim()));
			fault.setConfirmPeople(list.get(19).trim());

			fault.setGeneratePeople(list.get(20).trim());
			fault.setDevice(list.get(21).trim());
			fault.setCauseAnalyse(list.get(22).trim());
			fault.setAnalyseConfirmPeo(list.get(23).trim());
			fault.setCauseConfirmPeo(list.get(24).trim());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return fault;
	}

	@SuppressWarnings("unused")
	private Fault getSyslogFromExportFiles(List<String> list) {
		Fault fault = new Fault();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date d=new Date();
		SimpleDateFormat dd = new SimpleDateFormat("yyyyMMdd");
		try {
			fault.setId(Integer.parseInt(list.get(0).trim()));
			fault.setPdate(list.get(1).trim().equals("") ? null : dd.parse(list
					.get(1).trim()));
			fault.setCode(list.get(2).trim());
			fault.setGrade(list.get(3).trim());
			fault.setMajor(list.get(4).trim());

			fault.setUserowner(list.get(5).trim());
			fault.setFinder(list.get(6).trim());
			fault.setAccepter(list.get(7).trim());
			fault.setAcceptTime(list.get(8).trim().equals("")||list.get(9).trim().equals("/") ?null : sdf
					.parse(list.get(8).trim()));
			fault.setPtime(list.get(9).trim().equals("")||list.get(9).trim().equals("/") ? null : sdf
					.parse(list.get(9).trim()));

			fault.setBacktime(list.get(10).trim().equals("")||list.get(10).trim().equals("/") ? null : sdf
					.parse(list.get(10).trim()));
			fault.setPlace(list.get(11).trim());
			fault.setPresent(list.get(12).trim());
			fault.setProcess(list.get(13).trim());
			fault.setReqModify("");

			fault.setReqback("");
			fault.setSubwaystate((list.get(14).trim()));
			fault.setCause((list.get(16).trim()));
			fault.setIsConfirm("未确认");
			fault.setConfirmPeople("");

			fault.setGeneratePeople("");
			fault.setDevice(list.get(15).trim());
			fault.setCauseAnalyse("");
			fault.setAnalyseConfirmPeo("");
			fault.setCauseConfirmPeo("");
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return fault;
	}

	@Override
	public String restoreFaultTable() {
		dao.clearDBTable();
		List<String> list = null;
		try {
			RestoreDBAction er = new RestoreDBAction("../webapps/fault.xls");
			list = er.readExcelFile();
			list = er.readExcelFile();
			list = er.readExcelFile();
			while (list != null) {
				Fault log = getSyslogFrom(list);
				if (log != null&&log.getCode()!=null) {
					dao.save(log);
				}
				list = er.readExcelFile();
			}
			er.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("fault table restore ok!");
		return "OK";
	}

	private void printListContent(List<String> list)
	{
		System.out.print("Content:");
		System.out.println("size:"+list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.print(i+":"+list.get(i)+" ");
		}
		System.out.println();
	}
	
	@Override
	public String restoreFaultTable(String fileName) {
		if (!(fileName.endsWith("xls")||fileName.endsWith("xlsx"))) {
			System.out.println("文件类型不对！！！");
			return "error";
		}
		//dao.clearDBTable();
		List<String> list = null;
		try {
			RestoreDBAction er = new RestoreDBAction(fileName);
			list = er.readExcelFile();
			list = er.readExcelFile();
			list = er.readExcelFile();
			list = er.readExcelFile();
			int sum=0;
			while (list != null) {
				if (list.size()<14||list.get(2).trim().equals("")) {
					list = er.readExcelFile();
					continue;
				}
				sum++;
				if (list.size()<17) {
					list.add("");
					list.add("");
				
				}
//				printListContent(list);
//				System.out.println("%%   sixe:"+list.size());
//				System.out.println("@@   sum:"+sum);
				Fault log = getSyslogFromExportFiles(list);
				if (log != null&&log.getCode()!=null) {
					dao.save(log);
				}
				list = er.readExcelFile();
			}
			er.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("fault table restore ok!");
		return "OK";
	}

	@Override
	public int getTodaySum() {
		return dao.getTodayNum() + 1;
	}

	@Override
	public String removeFault(int id) {
		dao.delete(dao.findById(id));
		return "OK";
	}
}
