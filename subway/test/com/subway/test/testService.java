package com.subway.test;

import junit.framework.Assert;

import org.junit.Test;

import com.subway.dao.FaultDAO;
import com.subway.dao.SubwayUserDAO;
import com.subway.domain.SubwayUser;
import com.subway.service.impl.FaultServiceImpl;
import com.subway.service.impl.SysLogServiceImpl;
import com.subway.service.impl.UserServiceImpl;

public class testService {
	private UserServiceImpl impl=new UserServiceImpl();
	private SysLogServiceImpl logdervice=new SysLogServiceImpl();
	private FaultServiceImpl faultservice=new FaultServiceImpl();
	private SubwayUserDAO dao=new SubwayUserDAO();
	private FaultDAO fdao=new FaultDAO();
	
	@Test 
	public void testGetTodaySum()
	{
		Assert.assertEquals(0, fdao.getTodayNum());
	}
	@Test
	public void backupDB()
	{
		org.junit.Assert.assertTrue(dao.backupdb().equals("OK"));
	}
	
	@Test
	public void restoreDB()
	{
		org.junit.Assert.assertTrue(dao.restoredb().equals("OK"));
	}
	
	@Test
	public void testBackUser()
	{
		Assert.assertTrue(impl.backupUserTable().equals("OK"));
	}
	
	@Test
	public void testGetUserById() {
		SubwayUser user=impl.getUserById(1);
		Assert.assertNotNull(user);
		System.out.println(user.getName());
	}

	@Test
	public void testGetUserByName() {
		SubwayUser user=impl.getUserByName("b");
		Assert.assertNotNull(user);
		System.out.println(user.getName());
	}
	@Test
	public void testGetUser() {
		SubwayUser user=impl.getUserByName("b");
		Assert.assertNotNull(user);
		System.out.println(user.getName());
	}
	
	@Test
	public void testBackUplog()
	{
		Assert.assertTrue(logdervice.backupLogTable().equals("OK"));
	}
	
	@Test
	public void testBackUpFault()
	{
		Assert.assertTrue(faultservice.backupFaultTable().equals("OK"));
	}
	
	@Test
	public void testRestoreUser()
	{
		Assert.assertTrue(impl.restoreUserTable().equals("OK"));
	}
	
	@Test
	public void testRestorelog()
	{
		Assert.assertTrue(logdervice.restoreLogTable().equals("OK"));
	}
	
	@Test
	public void testRestoreFault()
	{
		Assert.assertTrue(faultservice.restoreFaultTable().equals("OK"));
	}

}
