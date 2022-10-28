package com.pushyourself11.StudentEntities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AttemptedQuestions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int attemptedId;

	private int questionId;

	private int optionId;

	@JsonBackReference
	@ManyToOne
	private AttemptedQuizDetails attemptedQuizDetails;

	public AttemptedQuestions(int questionId, int optionId, AttemptedQuizDetails attemptedQuizDetails) {
		this.questionId = questionId;
		this.optionId = optionId;
		this.attemptedQuizDetails = attemptedQuizDetails;
	}

}
