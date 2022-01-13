package com.cognixia.jump.dao;

import java.util.List;

public interface EmployeeDao {
	
	public List<Employee> getAllEmployees();
	
	
	public Employee getEmployeeById(int id);
	
	public boolean addEmployee(Employee employee);
	public boolean updateEmployee(Employee employee);
	
	public boolean deleteEmployee(Employee employee);
	public boolean deleteEmployeeById(int id);

}
