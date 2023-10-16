package com.examportal.model.exam;

public class UserQuizAnswersDTO {

	private Long questionId;
	private CorrectOption userAnswer;
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public CorrectOption getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(CorrectOption userAnswer) {
		this.userAnswer = userAnswer;
	}
	public UserQuizAnswersDTO(Long questionId, CorrectOption userAnswer) {
		super();
		this.questionId = questionId;
		this.userAnswer = userAnswer;
	}
	public UserQuizAnswersDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



}
