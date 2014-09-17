package com.subway.domain;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {

	private Integer pageIndex;

	private Integer pageSize;

	private Integer totalRecords;

	private List<T> datas=new ArrayList<T>();

	
	
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

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public Integer getFirstPage() {
		return 1;
	}

	public Integer getLastPage() {
		return this.getTotalPages();
	}

	public Integer getPreviousPage() {
		if (this.pageIndex <= 1) {
			return 1;
		}
		return this.pageIndex - 1;
	}

	public Integer getNextPage() {
		if (this.pageIndex >= this.getTotalPages()) {
			return this.getTotalPages();
		}
		return this.pageIndex + 1;
	}

	public Integer getTotalPages() {
		return (this.totalRecords + this.pageSize - 1) / this.pageSize;
	}

}
