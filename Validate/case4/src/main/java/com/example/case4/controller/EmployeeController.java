package com.example.case4.controller;

import com.example.case4.model.Employee;
import com.example.case4.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public String listAll(Model model) {
        List<Employee> employees = service.findAll();
        model.addAttribute("employees", employees);
        return "employee/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        List<Employee> employees = service.searchByName(keyword);
        model.addAttribute("employees", employees);
        return "employee/search";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/create";
    }

    @PostMapping
    public String create(@ModelAttribute("employee") Employee employee) {
        service.save(employee);
        return "redirect:/admin/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Employee> employee = service.findById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "employee/edit";
        } else {
            return "redirect:/admin/employees";
        }
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("employee") Employee employee) {
        employee.setId(id);
        service.save(employee);
        return "redirect:/admin/employees";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/admin/employees";
    }
}
