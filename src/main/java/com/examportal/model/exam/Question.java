package com.examportal.model.exam;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;
	private String option1;
	private String option2;
	private String option3;
	private String option4;

	private CorrectOption correctOption;

	@ManyToOne()
	@JsonIgnoreProperties(value = { "title" ,"description", "isActive" })
	private Category category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public CorrectOption getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(CorrectOption correctOption) {
		this.correctOption = correctOption;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Question(Long id, String content, String option1, String option2, String option3, String option4,
			CorrectOption correctOption, Category category) {
		super();
		this.id = id;
		this.content = content;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correctOption = correctOption;
		this.category = category;
	}

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
