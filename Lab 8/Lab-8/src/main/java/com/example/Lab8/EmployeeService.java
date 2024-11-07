package com.example.Lab8;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;


    public List<Employees> findAll() {
        return employeeRepo.findAll();
    }

    public void saveEmployee(Employees employee) {
        Optional<Employees> employees = employeeRepo.findById(employee.getEmployeeId());
        if (employees.isPresent()) {
            employees.get().setEmail(employee.getEmail());
            employees.get().setName(employee.getName());
            employees.get().setPhone(employee.getPhone());
            employees.get().setAddress(employee.getAddress());
            employeeRepo.save(employees.get());
        } else {
            Employees newEmployee = new Employees();
            newEmployee.setEmail(employee.getEmail());
            newEmployee.setName(employee.getName());
            newEmployee.setPhone(employee.getPhone());
            newEmployee.setAddress(employee.getAddress());
            employeeRepo.save(newEmployee);
        }
    }

    public void deleteEmployee(int id) {
        employeeRepo.deleteById(id);
    }

    public Employees findById(int id) {
        return employeeRepo.findById(id).orElse(null);
    }

}
