package com.gokcek.springdemo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gokcek.springdemo.model.Student;
import com.gokcek.springdemo.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("student", new Student());
		return "index";
	}
	
	@RequestMapping("/records")
	public String records(Model model) {
		model.addAttribute("students", studentService.findAll());
		return "records";
	}

	@PostMapping("/addStudent")
	public String addStudent(@Valid Student student, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "index";
		}
		studentService.saveStudent(student);
		model.addAttribute("students", studentService.findAll());
		return "records";
	}

	@PostMapping("/deleteStudent")
	public String deleteStudent(@RequestParam String id, Model model) {
		studentService.deleteById(Long.parseLong(id));

		model.addAttribute("students", studentService.findAll());
		return "records";
	}

	@GetMapping("/editStudent")
	public String showUpdateForm(@RequestParam String id, Model model) {
		Student student = studentService.findById(Long.parseLong(id))
				.orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));

		model.addAttribute("student", student);
		return "updateStudent";
	}

	@PostMapping("/updateStudent")
	public String updateStudent(@RequestParam String id, @Valid Student student, BindingResult result, Model model) {
		if (result.hasErrors()) {
			student.setId(Long.parseLong(id));
			return "updateStudent";
		}

		studentService.saveStudent(student);
		model.addAttribute("students", studentService.findAll());
		return "records";
	}

//  @GetMapping("/delete/{id}")
//	public String deleteStudent(@PathVariable("id")Long id, Model model) {
//	    Student student = studentService.findById(id)
//	      .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
//	    studentService.deleteById(id);
//	    model.addAttribute("students", studentService.findAll());
//	    return "records";
//	}

//	   @GetMapping("/studentsForm")
//	    public String studentForm(Model model) {
//	        model.addAttribute("studentForm", new Student());
//	        return "studentsForm";
//	    }

//		@PostMapping("/students-find")
//		public ModelAndView findStudent(@ModelAttribute Student student) 
//		{
//			Student thisStudent = studentService.findById(student.getId()).orElseThrow();   
//		    return new ModelAndView("result3","studentForm", thisStudent);
//		}
//	

//		
//		
//		@PostMapping("/students-save")
//		public String updateStudent(@ModelAttribute Student student) 
//		{
//			studentService.saveStudent(student);   
//		    return ("redirect:/index");
//		}

//	@RequestMapping(value = "/fine", method = RequestMethod.GET)
//	public String viewStudentDetails2(ModelMap model) {
//		System.out.println("-----------Came inside my controller ----------");
//	    model.addAttribute("greetingsFine", "I am doing Fine Excellent, THanks !!!!");
//	    return "fine";
//	}
//	
//	
//	@RequestMapping(value = "/studentslist", method = RequestMethod.GET)
//	public ModelAndView viewStudentList() {
//		//System.out.println("-----------Came inside my controller for picking up students list ----------");
//	    ArrayList<Student> studentsList = StudentsList.getStudentsList();
//	    
//	    return new ModelAndView("studentslist","studentsList", studentsList);
//	}
//	
//	@RequestMapping(value = "/getstudentdetails", method = RequestMethod.GET)
//	public ModelAndView getStudentDetail(@RequestParam(name = "id") String studentId) {
//		System.out.println("---------Here is my ID from the form text box -------"+studentId);
//	    ArrayList<Student> studentsList = StudentsList.getStudentsList();
//	    Student thisStudent = null;
//	    for (Student student:studentsList) {
//	    	if (student.getId() == Integer.parseInt(studentId)) {
//	    		thisStudent = student;
//	    	}
//	    }
//	    return new ModelAndView("getstudentdetails","studentDetails",thisStudent);
//	    
//	}
//	
//	@RequestMapping(value = "/addnewstudent", method = {RequestMethod.GET,RequestMethod.POST})
//	public ModelAndView addNewStudent(@RequestParam(name = "id") String studentId,
//			@RequestParam(name = "fname") String fName,
//			@RequestParam(name = "lname") String lName
//			) {
//		System.out.println("---------Here is all the info for adding this student -------"+
//			studentId + "," + fName + "," + lName );
//		// Create a new student and add the new student to the list
//		Student student = new Student(Integer.parseInt(studentId), fName, lName);
//
//		// get the latest student list and set the model with the list
//		ArrayList<Student> studentsList = StudentsList.addStudent(student);
//		
//	    return new ModelAndView("studentslist","studentsList",studentsList);
//	}

}