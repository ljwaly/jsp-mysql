package com.ljw.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

import com.ljw.dao.entity.Emp;





/**
 * 员工表的Dao组件
 * @author PC
 *
 */
@Mapper
public interface EmpDao {

	
	List<Emp> findAll();
	
	List<Emp> findByName(String ename);
}
