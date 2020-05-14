package com.gokcek.springdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gokcek.springdemo.model.Student;
import com.gokcek.springdemo.repositories.StudentRepository;;


@Service
public class StudentService {

    @Autowired
    private StudentRepository StudentRepository;

    public List<Student> findAll() {

        return StudentRepository.findAll();
    }
    
    public Optional<Student> findById(Long StudentId) {
    	
    	
    	return StudentRepository.findById(StudentId);
    }

    public Long count() {

        return StudentRepository.count();
    }

    public void deleteById(Long StudentId) {

        StudentRepository.deleteById(StudentId);
    }
    
    public void saveStudent(Student student) {
    	
    	 StudentRepository.save(student);
    }
    
    public void updateStudent(Student student) {
    	    	
    	
    	Student st = StudentRepository.findById(student.getId()).orElse(null);
    	
    	st.setFname(student.getFname());
    	st.setLname(student.getLname());
    	StudentRepository.save(st);
    	
    }
    
    
}
