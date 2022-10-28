package com.pushyourself11.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Quizes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int quizId;

	private String name;
	
	private int quizType;

	private int quizDuration;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "quiz", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<Sections> sections;

	private int draftVersion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false , updatable = false)
	private Date createdOn;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date modifiedOn;

	private int totalScore;

	private boolean hasSectionalTime;
	
	@JsonBackReference
	@ManyToOne
	private Teacher teacher;

	public Quizes(String name, int quizType, int quizDuration, List<Sections> sections, int draftVersion,
			Date createdOn, Date modifiedOn, int totalScore, boolean hasSectionalTime, Teacher teacher) {
		this.name = name;
		this.quizType = quizType;
		this.quizDuration = quizDuration;
		this.sections = sections;
		this.draftVersion = draftVersion;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.totalScore = totalScore;
		this.hasSectionalTime = hasSectionalTime;
		this.teacher = teacher;
	}

}
