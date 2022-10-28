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

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class SectionDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sectionDetailId;
	
	private int sectionScore;
	
	private String sectionName;
	
	private int sectionalCorrectAnswers;
	
	private int sectionalIncorrectAnswers;
	
	@JsonBackReference
	@ManyToOne
	private AttemptedQuizDetails sectionalMapping;

	public SectionDetail(int sectionScore, int sectionalCorrectAnswers, int sectionalIncorrectAnswers,
			AttemptedQuizDetails sectionalMapping) {
		this.sectionScore = sectionScore;
		this.sectionalCorrectAnswers = sectionalCorrectAnswers;
		this.sectionalIncorrectAnswers = sectionalIncorrectAnswers;
		this.sectionalMapping = sectionalMapping;
	}
}
