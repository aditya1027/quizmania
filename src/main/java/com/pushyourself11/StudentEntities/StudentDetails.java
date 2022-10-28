package com.pushyourself11.StudentEntities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class StudentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentDetailsId;

	private String profilePicUrl;
	
	private String mobileNumber;
	
	@JsonProperty
	@JsonManagedReference
	@OneToMany(mappedBy = "studentDetails", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<AttemptedQuizDetails> quizDetails;

	public StudentDetails(int studentDetailsId, String profilePicUrl, String mobileNumber,
			List<AttemptedQuizDetails> quizDetails) {
		this.studentDetailsId = studentDetailsId;
		this.profilePicUrl = profilePicUrl;
		this.mobileNumber = mobileNumber;
		this.quizDetails = quizDetails;
	}

	

}
