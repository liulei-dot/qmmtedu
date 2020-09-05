package com.qmmt.edu.controllers.json;

public class PageResultForBootstrap implements Results{
	private Integer total;

	private Object rows;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	
}
