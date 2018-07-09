package com.ljw.dao.entity;

import java.io.Serializable;
import java.util.List;

public class Condition implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6248564410688544926L;
	
	private Integer deptno;
	private Double salary;
	private List<Integer> empnos;
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deptno == null) ? 0 : deptno.hashCode());
		result = prime * result + ((empnos == null) ? 0 : empnos.hashCode());
		result = prime * result + ((salary == null) ? 0 : salary.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Condition other = (Condition) obj;
		if (deptno == null) {
			if (other.deptno != null)
				return false;
		} else if (!deptno.equals(other.deptno))
			return false;
		if (empnos == null) {
			if (other.empnos != null)
				return false;
		} else if (!empnos.equals(other.empnos))
			return false;
		if (salary == null) {
			if (other.salary != null)
				return false;
		} else if (!salary.equals(other.salary))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Condition [deptno=" + deptno + ", salary=" + salary + ", empnos=" + empnos + "]";
	}
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public List<Integer> getEmpnos() {
		return empnos;
	}
	public void setEmpnos(List<Integer> empnos) {
		this.empnos = empnos;
	}
	
	
	
}
