package com.example.serviceImpl;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;

//import org.springframework.data.domain.Sort;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee createEmployee(Employee employee) {
		employee=employeeRepository.save(employee);
		return employee;
	}

//	@Override
//	public List<Employee> sortEmployees(String field) {
//		return employeeRepository.findAll(Sort.by(field));
//	}
	@Override
	public List<Employee> findAllOrderByNameAsc(){
		List<Employee> emp=(List<Employee>) readAll();
		int s=emp.size();
		for(int i=0;i<s;i++) {
			for(int j=i+1;j<s;j++) {
				String a=emp.get(i).getFirstName()+" "+emp.get(i).getLastName();
				String b=emp.get(j).getFirstName()+" "+emp.get(j).getLastName();
				if(a.compareTo(b)>0) {
					Collections.swap(emp, i, j);
				}
			}
		}
		return emp;
	}
	
	private List<Employee> readAll() {
		// TODO Auto-generated method stub
		return (List<Employee>) employeeRepository.findAll();
	}

	@Override
	public List<Employee> findAllOrderByAgeAsc(){
		List<Employee> emp1=(List<Employee>) readAll();
		int s=emp1.size();
		for(int i=0;i<s;i++) {
			for(int j=i+1;j<s;j++) {
				if(emp1.get(i).getAge()>emp1.get(j).getAge()) {
					Collections.swap(emp1, i, j);
				}
			}
		}
		return emp1;
	}
	
	
	
	
	
	

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		
		System.out.println ("Getting data from DB" );
		
		return employeeRepository.findAll();
	}

	@Override
	public Employee updateEmployee(Employee employee,Long empId) {
		employee.setEmpId(empId);
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Long empId) {
		employeeRepository.deleteById(empId);
		
	}

}
