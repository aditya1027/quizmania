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

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;

	private String question;

	private String questionImageUrl;

	@JsonProperty
	@JsonManagedReference
	@OneToMany(mappedBy = "question", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<Options> options;

	@JsonBackReference
	@ManyToOne
	private Sections section;

	public Questions(String question, String questionImageUrl, List<Options> quesOptions) {
		this.question = question;
		this.questionImageUrl = questionImageUrl;
		this.options = quesOptions;
	}
}
