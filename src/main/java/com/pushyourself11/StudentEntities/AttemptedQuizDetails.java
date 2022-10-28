package com.pushyourself11.StudentEntities;

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

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class AttemptedQuizDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int quizAttemptDetailsId;

	private int quizId;

	private int scored;

	private int totalCorrect;

	private int totalIncorrect;
	
	@JsonProperty
	@JsonManagedReference
	@OneToMany(mappedBy = "sectionalMapping", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<SectionDetail> sectionDetails;
	

	@JsonProperty
	@JsonManagedReference
	@OneToMany(mappedBy = "attemptedQuizDetails", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<AttemptedQuestions> questionDetails;

	@JsonBackReference
	@ManyToOne
	private StudentDetails studentDetails;

	public AttemptedQuizDetails(int quizAttemptDetailsId, int quizId, int scored, int totalCorrect, int totalIncorrect,
			List<AttemptedQuestions> questionDetails, StudentDetails studentDetails) {
		this.quizAttemptDetailsId = quizAttemptDetailsId;
		this.quizId = quizId;
		this.scored = scored;
		this.totalCorrect = totalCorrect;
		this.totalIncorrect = totalIncorrect;
		this.questionDetails = questionDetails;
		this.studentDetails = studentDetails;
	}

}
