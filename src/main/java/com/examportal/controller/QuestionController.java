package com.examportal.controller;

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
import com.examportal.model.exam.Question;
import com.examportal.services.QuestionService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	// add
	@PostMapping("/")
	public ResponseEntity<GenericResponse> add(@RequestBody Question question) throws Exception {
		GenericResponse response = questionService.addQuestion(question);
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@GetMapping("/")
	public Page<Question> getAllCategories(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size) {
		return questionService.getQuestions(page, size);
	}

	// update
	@PutMapping("/{questionId}")
	public ResponseEntity<GenericResponse> update(@RequestBody Question question,
			@PathVariable("questionId") Long questionId) {
		GenericResponse response = questionService.updateQuestion(question, questionId);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{qid}")

	public Question question(@PathVariable("qid") Long qid) {
		return questionService.getQuestion(qid);

	}

}
