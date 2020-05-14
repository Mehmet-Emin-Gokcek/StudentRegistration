package com.gokcek.springdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gokcek.springdemo.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>
{

}
