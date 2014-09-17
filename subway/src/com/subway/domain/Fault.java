package com.subway.domain;

import java.util.Date;

/**
 * Fault entity. @author MyEclipse Persistence Tools
 */
public class Fault extends AbstractFault implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Fault() {
	}

	/** full constructor */
	public Fault(Integer id,Date pdate, String code, String grade, String major,
			String userowner, String finder, String accepter, Date acceptTime,
			Date ptime, Date backtime, String place, String present,
			String process, String reqModify, String reqback,
			String subwaystate, String cause, String isConfirm,
			String confirmPeople, String generatePeople, String device,
			String causeAnalyse, String analyseConfirmPeo,
			String causeConfirmPeo) {
		super(id,pdate, code, grade, major, userowner, finder, accepter,
				acceptTime, ptime, backtime, place, present, process,
				reqModify, reqback, subwaystate, cause, isConfirm,
				confirmPeople, generatePeople, device, causeAnalyse,
				analyseConfirmPeo, causeConfirmPeo);
	}

	
	
}
