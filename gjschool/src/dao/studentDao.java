package dao;

import java.util.List;

import model.student;

public interface studentDao {

	//create
	void add(student s);
			
	//read
	String queryAll1();
	List<student> queryAll2();
	
	//update
	
	//delete
	
}