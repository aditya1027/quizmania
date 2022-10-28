package com.pushyourself11.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pushyourself11.Entity.Questions;

public interface QuestionRepository extends JpaRepository<Questions, Integer> {

}
