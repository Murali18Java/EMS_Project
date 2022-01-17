package com.cognixia.jump.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.cognixia.jump.dao.ConnectionManager;
import com.cognixia.jump.dao.Employee;
import com.cognixia.jump.dao.EmployeeDaoFile;

class EmployeeDAOFileTest {

	EmployeeDaoFile testRunner = new EmployeeDaoFile();

	private Connection con = ConnectionManager.getConnection();

	/*
	 * @Test void test() { fail("Not yet implemented"); }
	 */

	@Test
	void testAddEmployee() {

		// Added am employee using the constructor.

		Employee emp = new Employee(2, "Add", "Employee", 1001);

		boolean actual = testRunner.addEmployee(emp);

		boolean expected = false;

		try {

			// Select the last row in the database = the Jake and Cake
			Statement stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * from employees ORDER BY" + " emp_id DESC LIMIT 1 ;");

			while (rset.next()) {
				
				
				int empId = rset.getInt("emp_id");
				String empFirstName = rset.getString("first_name");
				String empLastName = rset.getString("last_name");
				int deptId = rset.getInt("dept_id");

				if (empFirstName.matches("Add") && empLastName.matches("Employee"))
				{
					expected = true;
					// System.out.println(empFirstName+" "+empLastName);
				}
				testRunner.deleteEmployeeById(empId);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("Add Employee");
		System.out.println("Act: "+actual+" Exp: "+expected);
		assertEquals(actual, expected);

	}
	

	@Test
	void testDeleteEmployee() {
		
	Employee emp = new Employee(2, "Delete", "Employee", 1002);
	
	testRunner.addEmployee(emp);

	boolean actual = false; 		

	boolean expected = false;
	
	try {

		// Select the last row in the database = the Jake and Cake
		Statement stmt = con.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT * from employees ORDER BY" + " emp_id DESC LIMIT 1 ;");

		while (rset.next()) {
			
			
			int empId = rset.getInt("emp_id");
			String empFirstName = rset.getString("first_name");
			String empLastName = rset.getString("last_name");
			int deptId = rset.getInt("dept_id");
			
			actual = testRunner.deleteEmployeeById(empId);

			if (empFirstName.matches("Delete") && empLastName.matches("Employee")) {
				expected = true;
				// System.out.println(empFirstName+" "+empLastName);
			}
		}

	} catch (SQLException e) {

		e.printStackTrace();
	}
	System.out.println("Delete Employee");
	System.out.println("Act: "+actual+" Exp: "+expected);
	assertEquals(actual, expected);
			
	}
	
	
	@Test
	void testGetAllEmployee() {
		
		//Employee emp = new Employee(2, "GetAll", "Employee", 1003);
		
		int count =0;

		int actual = 0, expected = 0;
		
		try {

			// Select the last row in the database = the Jake and Cake
			Statement stmt = con.createStatement();

			ResultSet rset = stmt.executeQuery("SELECT * from employees;");

			while (rset.next()) {
				count++;
			}
			

			} catch (SQLException e) {

			e.printStackTrace();
		}
		
		expected =count;
		
		List<Employee> employees = new ArrayList<Employee>();
		
		employees= testRunner.getAllEmployees();
		
		actual = employees.size();
		
		//System.out.println("Act: "+actual+" Exp: "+expected);
		
		assertEquals(actual, expected);
				
		}
		
	@Test
	void testGetEmployeeByID() {
		
		Employee emp = new Employee(2, "GetEmployee", "ByID", 1004);
		
		Employee emp2 = new Employee();

		boolean actual = false;
		
				
				testRunner.addEmployee(emp);

		boolean expected = false;

		try {

			// Select the last row in the database = the Jake and Cake
			Statement stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * from employees ORDER BY" + " emp_id DESC LIMIT 1 ;");

			while (rset.next()) {
				
				
				int empId = rset.getInt("emp_id");
				String empFirstName = rset.getString("first_name");
				String empLastName = rset.getString("last_name");
				int deptId = rset.getInt("dept_id");
				
				emp2 = testRunner.getEmployeeById(empId);
				
				System.out.println(emp2.toString());

				if (emp2.getEmpId()==empId)
				{
					expected = true;
					
				}
				
				if (empFirstName.equals(emp.getEmpFirstName())) {
					actual = true;
				
				}
				System.out.println(emp.toString());
				testRunner.deleteEmployeeById(empId);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		assertEquals(actual, expected);
	
	}
	
	
	@Test
	void testUpdateEmployee(){
		
		// Added am employee using the constructor.

		Employee emp = new Employee(2, "Update", "Employee", 1005);

		boolean actual = testRunner.addEmployee(emp);

		boolean expected = false;

		try {

			// Select the last row in the database = the Jake and Cake
			Statement stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * from employees ORDER BY" + " emp_id DESC LIMIT 1 ;");

			while (rset.next()) {
				
				
				int empId = rset.getInt("emp_id");
				String empFirstName = rset.getString("first_name");
				String empLastName = rset.getString("last_name");
				int deptId = rset.getInt("dept_id");

				if (empFirstName.matches("Update") && empLastName.matches("Employee"))
				{
					expected = true;
					// System.out.println(empFirstName+" "+empLastName);
				}
				testRunner.deleteEmployeeById(empId);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		System.out.println("Test Update Employee");
		System.out.println("Act: "+actual+" Exp: "+expected);
		assertEquals(actual, expected);

	
	
	}

}
