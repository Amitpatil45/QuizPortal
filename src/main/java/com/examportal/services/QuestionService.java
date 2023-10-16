package com.examportal.services;

import org.springframework.data.domain.Page;

import com.examportal.exception.GenericResponse;
import com.examportal.model.exam.Question;

public interface QuestionService {

	public GenericResponse addQuestion(Question question) throws Exception;

	public GenericResponse updateQuestion(Question question , Long questionId);

	public Page<Question> getQuestions(int page, int size);

	public Question getQuestion(Long questionId);

}
