package com.cognixia.jump;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.dao.ConnectionManager;
import com.cognixia.jump.dao.Employee;
import com.cognixia.jump.dao.EmployeeDaoFile;

public class EmsMenu {

	EmployeeDaoFile runner = new EmployeeDaoFile();

	public void mainMenu() {

		int choice = 1;

		do {

			System.out.println("Welcome to the EMS Application");
			System.out.println("Select an option:");

			System.out.println("1. View Employees" + " \n2. Add Employee " + "\n3. Delete Employee "
					+ "\n4. Edit Employee" + "\n5. Exit Program");

			Scanner sc = new Scanner(System.in);

			System.out.println("Enter an option:");
			try {
				choice = sc.nextInt();
				System.out.println("You choose :: " + choice);

			} catch (InputMismatchException e) {

				System.out.println("That was not an Integer");
			}

			switch (choice) {
			case 1:
				viewEmployees();
				break;
			case 2:
				addEmployees();
				break;
			case 3:
				deleteEmployees();
				break;
			case 4:
				editEmployees();
				break;
			default:
				System.out.println("Entered an incorrect Option");
				break;
			}

			sc.close();

		} while (choice == 5);
	}

	public void deleteEmployees() {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int id = 0;
		boolean check;

		System.out.println("Enter the emp_id, you want to delete ? :");

		id = sc.nextInt();

		check = runner.deleteEmployeeById(id);

		if (check == true) {
			System.out.println("EmpId :" + id + "Deleted from the database");
		}
		sc.close();
	}

	public void addEmployees() {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		Employee emp = new Employee();
		int deptId = 0;
		String fName, lName;

		boolean check;

		System.out.println("Enter the First Name :");

		fName = sc.nextLine();

		System.out.println("Enter the Last Name :");

		lName = sc.nextLine();

		System.out.println("Enter the Dept_Id (starts from 1000):");

		deptId = sc.nextInt();

		emp.setEmpFirstName(fName);
		emp.setEmpLastName(lName);
		emp.setDeptId(deptId);

		check = runner.addEmployee(emp);

		if (check == true) {
			System.out.println("Employee :" + fName + " " + lName + " Added to the database");
		}

	}

	public void viewEmployees() {

		List<Employee> emp = new ArrayList<Employee>();

		emp = runner.getAllEmployees();

		System.out.println("List of the employees are below :");

		for (Employee employee : emp) {
			System.out.println(employee.toString());
		}

		try {
			ConnectionManager.getConnection().close();

		} catch (SQLException e) {

			System.out.println("Could not close connection viewEmployees");
		}

	}

	public void editEmployees() {
		

		int id;
		boolean check;
		Employee emp = new Employee();
		int deptId = 0;
		String fName="", lName="";
		
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the emp_id, which you want to edit ? :");

		id = sc.nextInt();

		System.out.println("Enter the First Name :");
		sc.nextLine();

		fName = sc.nextLine();

		System.out.println("Enter the Last Name :");

		lName = sc.nextLine();

		System.out.println("Enter the Dept_Id (starts from 1000):");

		deptId = sc.nextInt();
		
		emp.setEmpId(id);
		emp.setEmpFirstName(fName);
		emp.setEmpLastName(lName);
		emp.setDeptId(deptId);

		check = runner.updateEmployee(emp);

		//check = runner.updateEmployeeById(id);

		if (check == true) {
			System.out.println("EmpId :" + id + " Updated on the database!!");
		}
		sc.close();

	}

}
