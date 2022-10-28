package com.pushyourself11.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pushyourself11.StudentEntities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	public Student findByEmail(String email);
}
