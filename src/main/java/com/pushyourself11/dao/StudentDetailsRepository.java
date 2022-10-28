package com.pushyourself11.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pushyourself11.StudentEntities.StudentDetails;

public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Integer> {

}
