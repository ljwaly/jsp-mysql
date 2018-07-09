package com.ljw.dao.entity;

import java.io.Serializable;
import java.util.List;

public class Dept implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1662957647000245546L;
	
	private Integer deptno;
	private String dname;
	private String location;
	
	private List<Empe> emps;
	
	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", dname=" + dname + ", location=" + location + "]";
	}
	
	
	
	public List<Empe> getEmps() {
		return emps;
	}



	public void setEmps(List<Empe> emps) {
		this.emps = emps;
	}



	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
