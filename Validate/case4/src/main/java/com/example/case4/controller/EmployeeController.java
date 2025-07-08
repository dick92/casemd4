package com.example.case4.controller;

import com.example.case4.model.Employee;
import com.example.case4.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<Employee> listAll() {
        return service.findAll();
    }

    @GetMapping("/search")
    public List<Employee> search(@RequestParam String keyword) {
        return service.searchByName(keyword);
    }

    @GetMapping("/{id}")
    public Optional<Employee> detail(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return service.save(employee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}

