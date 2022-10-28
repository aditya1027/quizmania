package com.pushyourself11.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pushyourself11.StudentEntities.AttemptedQuizDetails;

public interface AttemptedQuizDetailsRepository extends JpaRepository<AttemptedQuizDetails, Integer> {
	AttemptedQuizDetails findByQuizId(int quizId);
}
