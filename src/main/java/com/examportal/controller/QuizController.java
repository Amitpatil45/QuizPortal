package com.examportal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.exception.GenericResponse;
import com.examportal.model.exam.Quiz;
import com.examportal.model.exam.QuizDto;
import com.examportal.model.exam.UserActiveQuizDTO;
import com.examportal.model.exam.UserQuizAnswersDTO;
import com.examportal.services.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

	@Autowired
	private QuizService quizService;

	// add
	@PostMapping("/")
	public ResponseEntity<GenericResponse> add(@RequestBody Quiz quiz) throws Exception {
		GenericResponse response = quizService.addQuiz(quiz);
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	// update
	@PutMapping("/{quizId}")
	public ResponseEntity<GenericResponse> update(@RequestBody Quiz quiz, @PathVariable("quizId") Long quizId) {
		GenericResponse response = quizService.updateQuiz(quiz, quizId);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

	}

	// get
	@GetMapping("/")
	public Page<Quiz> getAllQuizes(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "25") int size) {
		return quizService.getQuizzes(page, size);

	}

	@GetMapping("/{qid}")
	public Quiz quiz(@PathVariable("qid") Long qid) {
		return quizService.getQuiz(qid);

	}

	@PutMapping("/status/{QuizId}")
	public ResponseEntity<GenericResponse> updateStatus(@PathVariable("id") long id,
			@RequestBody Map<String, Boolean> requestBody) {
		Boolean newStatus = requestBody.get("isActive");

		GenericResponse response = quizService.changeQuizStatus(id, newStatus);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

	}
//---------------------------------------------------------------------------------------------------------//

	@GetMapping("/active")
	public List<UserActiveQuizDTO> getUserActiveQuizzes() {
		return quizService.getUserActiveQuizzes();
	}

	@GetMapping("/play/{quizId}")
	public ResponseEntity<QuizDto> playQuiz(@PathVariable Long quizId) {

		Quiz quiz = quizService.getQuiz(quizId);

		QuizDto quizDto = quizService.convertQuizToQuizDto(quiz);
		return new ResponseEntity<>(quizDto, HttpStatus.OK);

	}

	@PostMapping("/submit/{quizId}")
	public ResponseEntity<GenericResponse> submitQuizAnswers(@PathVariable("quizId") Long quizId,
			@RequestBody List<UserQuizAnswersDTO> userAnswers) {
		GenericResponse response = quizService.submitQuizAnswers(quizId, userAnswers);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
