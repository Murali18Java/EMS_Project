package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoFile implements EmployeeDao  {
	
	private Connection connection = ConnectionManager.getConnection();

	@Override
	public List<Employee> getAllEmployees() {
		
		// use a File stream in order to read the data from
		// employees.txt
		
		List<Employee> employees = new ArrayList<Employee>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM employees");

			while (rs.next()) {

				int empId = rs.getInt("emp_id");
				String empFirstName = rs.getString("first_name");
				String empLastName = rs.getString("last_name");
				int deptId = rs.getInt("dept_id");

				Employee employee = new Employee(empId, empFirstName, empLastName, deptId);
				employees.add(employee);

			}

			rs.close();
			statement.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return employees;
		
		
	}

	//@Override
	//public Employee getEmployee(int id) {
		
	//}

	@Override
	public Employee getEmployeeById(int id) {
		
		Employee employee = new Employee();

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE id = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				employee.setEmpId(id); ////
				employee.setEmpFirstName(rs.getString("first_name"));
				employee.setEmpLastName(rs.getString("last_name"));
				employee.setDeptId(rs.getInt("dept_id"));
			}

			rs.close();
			statement.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return employee;
	}

	@Override
	public boolean addEmployee(Employee employee) {
		int count = 0;

		try {
			PreparedStatement statement = connection
					.prepareStatement("insert into employees(first_name, last_name,dept_id)" + "values(?,?,?);");

			// statement.setInt(1, employee.getEmpId());
			statement.setString(1, employee.getEmpFirstName());
			statement.setString(2, employee.getEmpLastName());
			statement.setInt(3, employee.getDeptId());

			count = statement.executeUpdate();

			statement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (count == 1)
			return true;

		return false;
	}

	@Override
	public boolean updateEmployee(Employee employee1) {
		
		
		int count = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE employees " + " SET first_name= ?, last_name= ?, dept_id= ? " + "WHERE emp_id = ?;");

			statement.setInt(4,employee1.getEmpId());
			statement.setString(1,employee1.getEmpFirstName());
			statement.setString(2,employee1.getEmpLastName());
			statement.setInt(3,employee1.getDeptId());

			count = statement.executeUpdate();

			statement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (count == 1)
			return true;

		return false;

	}

	@Override
	public boolean deleteEmployee(Employee employee) {

		int count = 0;

		try {
			PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE emp_id = ?");

			statement.setInt(1, employee.getEmpId());

			count = statement.executeUpdate();

			statement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (count == 1)
			return true;

		return false;
	}

	@Override
	public boolean deleteEmployeeById(int id) {

		int count = 0;

		try {
			PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE emp_id = ?");

			statement.setInt(1, id);

			count = statement.executeUpdate();

			statement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (count == 1)
			return true;

		return false;

	}

	/*
	 * @Override public boolean updateEmployeeById(int id) {
	 * 
	 * int count = 0;
	 * 
	 * try { PreparedStatement statement = connection.prepareStatement(
	 * "UPDATE employees" + "SET first_name= ?, last_name=?, dept_id=?" +
	 * "WHERE emp_id = ?");
	 * 
	 * statement.setInt(4, employee.getEmpId()); statement.setString(1,
	 * employee.getEmpFirstName()); statement.setString(2,
	 * employee.getEmpLastName()); statement.setInt(3, employee.getDeptId());
	 * 
	 * count = statement.executeUpdate();
	 * 
	 * statement.close();
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * if (count == 1) return true;
	 * 
	 * return false; }
	 */
	


}
