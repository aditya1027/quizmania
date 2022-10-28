package com.pushyourself11.Entity;

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
public class Options {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int optionId;

	@JsonBackReference
	@ManyToOne
	private Questions question;

	private String option;

	private String optionImageUrl;

	private boolean isCorrectAnswer;

	public Options(Questions question, String option, String optionImageUrl, boolean isCorrectAnswer) {
			this.question = question;
			this.option = option;
			this.optionImageUrl = optionImageUrl;
			this.isCorrectAnswer = isCorrectAnswer;
		}
}

