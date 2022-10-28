package com.pushyourself11.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Sections {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sectionId;

	@JsonBackReference
	@ManyToOne
	private Quizes quiz;

	private String sectionName;

	private int sectionDuration;

	private int questionsPerSection;

	private int marks;

	private int negMarks;

	private int sectionScore;
	
	@JsonProperty
	@JsonManagedReference
	@OneToMany(mappedBy = "section", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<Questions> questions;


	public Sections(Quizes quiz, String sectionName, int sectionDuration, int questionsPerSection, int marks,
			int negMarks, int sectionScore, List<Questions> questions) {
		this.quiz = quiz;
		this.sectionName = sectionName;
		this.sectionDuration = sectionDuration;
		this.questionsPerSection = questionsPerSection;
		this.marks = marks;
		this.negMarks = negMarks;
		this.sectionScore = sectionScore;
		this.questions = questions;
	}
}
