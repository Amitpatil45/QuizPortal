package com.examportal.model.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.exam.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	Page<Question> findAll(Pageable pageable);

	Question findByContent(String content);


}