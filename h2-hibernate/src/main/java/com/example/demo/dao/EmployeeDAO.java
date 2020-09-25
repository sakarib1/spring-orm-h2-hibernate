package com.example.demo.dao;

public interface EmployeeDAO {

	Integer addEmployee(String fname, String lname, int salary);

	void listEmployees();

	void updateEmployee(Integer EmployeeID, int salary);

	void deleteEmployee(Integer EmployeeID);

}