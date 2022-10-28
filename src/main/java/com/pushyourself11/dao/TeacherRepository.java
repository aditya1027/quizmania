package com.pushyourself11.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pushyourself11.Entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
	public Teacher findByEmail(String email);
}
