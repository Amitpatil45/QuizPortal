package com.examportal.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.examportal.exception.GenericResponse;
import com.examportal.model.exam.Quiz;
import com.examportal.model.exam.QuizDto;
import com.examportal.model.exam.UserActiveQuizDTO;
import com.examportal.model.exam.UserQuizAnswersDTO;

public interface QuizService {
	public GenericResponse addQuiz(Quiz quiz) throws Exception;

	public GenericResponse updateQuiz(Quiz quiz , Long quizId);

	public Page<Quiz> getQuizzes(int page, int size);

	public Quiz getQuiz(Long quizId);

	List<UserActiveQuizDTO> getUserActiveQuizzes();

	GenericResponse changeQuizStatus(Long quizId, boolean newStatus);

	QuizDto convertQuizToQuizDto(Quiz quiz);

	GenericResponse submitQuizAnswers(Long quizId, List<UserQuizAnswersDTO> userAnswers);


}
