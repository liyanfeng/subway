package com.subway.dao.test;

import com.subway.dao.FaultDAO;

public class FaultDAOTest {
	 public static void main(String[] args) {
			FaultDAO fd = new FaultDAO();
			System.out.println(fd.findByCode("TH14080601"));
		}
}
