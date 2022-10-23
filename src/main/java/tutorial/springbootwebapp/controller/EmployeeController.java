package tutorial.springbootwebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tutorial.springbootwebapp.entity.Employee;
import tutorial.springbootwebapp.repository.EmployeeRepository;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping({ "/", "/list", "/showEmployees" })
	public ModelAndView showEmployees() {
		ModelAndView mav = new ModelAndView("list-employees");
		List<Employee> list = employeeRepository.findAll();
		mav.addObject("employees", list);
		return mav;
	}

	@GetMapping("/addEmployeeForm")
	public ModelAndView addEmployeeForm() {
		ModelAndView mav = new ModelAndView("employee-data-form");
		Employee newEmployee = new Employee();
		mav.addObject("employee", newEmployee);
		return mav;
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee employee) {
		employeeRepository.save(employee);
		return "redirect:/list";
	}

	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Long employeeId) {
		ModelAndView mav = new ModelAndView("employee-data-form");
		Employee employee = employeeRepository.findById(employeeId).get();
		mav.addObject("employee", employee);
		return mav;
	}

	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Long employeeId) {
		employeeRepository.deleteById(employeeId);
		return "redirect:/list";
	}

}
