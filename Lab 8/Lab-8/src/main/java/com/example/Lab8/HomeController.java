package com.example.Lab8;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class HomeController {
    private final EmployeeService employeeService;


    @GetMapping("/add")
    public String contact(Model model) {
        model.addAttribute("employee", new Employees());
        return "add";
    }

    @GetMapping()
    public String home(Model model) {
        List<Employees> list = employeeService.findAll();
        model.addAttribute("employees", list);
        return "index";
    }

    @PostMapping("/add")
    public String saveEmployee(@ModelAttribute("employee") Employees employees) {
        employeeService.saveEmployee(employees);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") int id, Model model) {
        Employees employees = employeeService.findById(id);
        model.addAttribute("employee", employees);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable("id") int id, @ModelAttribute("employee") Employees employees) {
        employeeService.saveEmployee(employees);
        return "redirect:/employees";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}
