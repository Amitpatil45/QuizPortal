package com.examportal.services.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examportal.exception.DataValidationException;
import com.examportal.exception.GenericResponse;
import com.examportal.model.exam.Category;
import com.examportal.model.exam.CorrectOption;
import com.examportal.model.exam.Question;
import com.examportal.model.exam.QuestionDto;
import com.examportal.model.exam.Quiz;
import com.examportal.model.exam.QuizDto;
import com.examportal.model.exam.UserActiveQuizDTO;
import com.examportal.model.exam.UserQuizAnswersDTO;
import com.examportal.model.repo.CategoryRepository;
import com.examportal.model.repo.QuestionRepository;
import com.examportal.model.repo.QuizRepository;
import com.examportal.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public GenericResponse addQuiz(Quiz quiz) throws Exception {
		if (quiz.getTitle() == null || quiz.getTitle().isBlank()) {
			throw new DataValidationException("Please enter Tital for Quiz");
		} else {
			quiz.setTitle(quiz.getTitle().trim());
		}

		if (quiz.getCategory() == null || quiz.getCategory().getId() == null) {
			throw new DataValidationException("Please select a category for the quiz.");
		}
		
		if (quiz.getQuestions() == null || quiz.getQuestions().isEmpty()) {
			throw new DataValidationException("Please provide a list of questions for the quiz.");
		}
		
		Optional<Category> optionalCategory = categoryRepository.findById(quiz.getCategory().getId());
		if (optionalCategory.isEmpty()) {
			throw new DataValidationException("Category not found");
		}

		Quiz quizUnique = quizRepository.findByTitle(quiz.getTitle());
		if (quizUnique != null) {
			throw new DataValidationException("A quiz with this title already exists.");

		}
		
		
		Question question;
		Optional<Question> optionalQuestion = questionRepository.findById(question.getId());
		if (optionalQuestion.isEmpty()) {
			throw new DataValidationException("Question not Available.");
		}
		
		List<Question> uniqueQuestions = new ArrayList<>();
		for(Question question : quiz.getQuestions()) {
			
			Optional<Question> optionalQuestion = questionRepository.findById(question.getId());
			if (optionalQuestion.isEmpty()) {
				throw new DataValidationException("Question not Available.");
			}


			if(uniqueQuestions.contains(question)) {
				throw new DataValidationException("Questions Are Repited.");
			}
			if(!question.getCategory().equals(quiz.getCategory())){
				throw new DataValidationException("Question is not present in the category");

			}
		}
		


		quizRepository.save(quiz);
		return new GenericResponse(201, "Created Succesfully");

	}

	@Override
	public GenericResponse updateQuiz(Quiz quiz, Long quizId) {
		Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
		if (optionalQuiz.isEmpty()) {
			throw new DataValidationException("Quiz with ID " + quizId + " not found.");
		}
		Quiz existingQuiz = optionalQuiz.get();
		if (quiz.getTitle() == null || quiz.getTitle().isBlank()) {
			throw new DataValidationException("Please enter a title for the quiz.");
		} else {
			quiz.setTitle(quiz.getTitle().trim());
		}

		if (quiz.getCategory() == null || quiz.getCategory().getId() == null) {
			throw new DataValidationException("Select a category for the question.");
		}
		Optional<Category> optionalCategory = categoryRepository.findById(quiz.getCategory().getId());
		if (optionalCategory.isEmpty()) {
			throw new DataValidationException("Category not found");
		}

		if (!titalUnique(quiz.getTitle(), quizId)) {
			throw new DataValidationException("Quiz With Same Tital is Already present");

		}

		if (quiz.getQuestions() == null || quiz.getQuestions().isEmpty()) {
			throw new DataValidationException("Please provide a list of questions for the quiz.");
		}

		existingQuiz.setTitle(quiz.getTitle());
		existingQuiz.setQuestions(quiz.getQuestions());
		existingQuiz.setCategory(quiz.getCategory());

		quizRepository.save(existingQuiz);

		return new GenericResponse(202, "Updated Successfully");
	}

	private Boolean titalUnique(String tital, Long Qid) {
		Quiz quizWithSameTital = quizRepository.findByTitle(tital);
		if (quizWithSameTital != null) {
			return quizWithSameTital.getId().equals(Qid);
		}

		return true;

	}

	@Override
	public Page<Quiz> getQuizzes(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
		Page<Quiz> quiz;

		quiz = quizRepository.findAll(pageable);

		return quiz;
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		Optional<Quiz> quizoptional = quizRepository.findById(quizId);
		if (quizoptional.isEmpty()) {
			throw new DataValidationException("Quiz Not Available");

		}
		return this.quizRepository.findById(quizId).get();
	}

	@Override
	public GenericResponse changeQuizStatus(Long quizId, boolean newStatus) {
		Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);

		if (optionalQuiz.isPresent()) {
			Quiz quiz = optionalQuiz.get();
			quiz.setActive(newStatus);
			quizRepository.save(quiz);
			return new GenericResponse(200, "Quiz status updated  successfully.");
		}
		throw new DataValidationException("Quiz Not Available");
	}

	@Override
	public List<UserActiveQuizDTO> getUserActiveQuizzes() {
		List<Quiz> activeQuizzes = quizRepository.findByIsActive(true);

		List<UserActiveQuizDTO> userActiveQuizzes = new ArrayList<>();
		for (Quiz quiz : activeQuizzes) {
			UserActiveQuizDTO dto = new UserActiveQuizDTO();
			dto.setId(quiz.getId());
			dto.setTitle(quiz.getTitle());
			dto.setDescription(quiz.getDescription());
			userActiveQuizzes.add(dto);
		}

		return userActiveQuizzes;
	}

	@Override
	public QuizDto convertQuizToQuizDto(Quiz quiz) {
		QuizDto quizDto = new QuizDto();
		quizDto.setId(quiz.getId());
		quizDto.setTitle(quiz.getTitle());
		quizDto.setDescription(quiz.getDescription());

		List<QuestionDto> questionDtos = new ArrayList<>();
		for (Question question : quiz.getQuestions()) {
			QuestionDto questionDto = new QuestionDto(question.getId(), question.getContent(), question.getOption1(),
					question.getOption2(), question.getOption3(), question.getOption4()

			);
			questionDtos.add(questionDto);
		}

		quizDto.setQuestions(questionDtos);

		return quizDto;

	}

	@Override
	public GenericResponse submitQuizAnswers(Long quizId, List<UserQuizAnswersDTO> userAnswers) {
		Quiz quiz = quizRepository.findById(quizId).orElse(null);
		if (quiz == null) {
			throw new DataValidationException("Quiz Not Available");
		}

		List<Question> questions = quiz.getQuestions();
		int totalQuestions = questions.size();
		int correctAnswers = 0;

		for (UserQuizAnswersDTO userAnswer : userAnswers) {
			Long questionId = userAnswer.getQuestionId();
			CorrectOption answer = userAnswer.getUserAnswer();

			Optional<Question> questionOptional = questions.stream().filter(q -> q.getId().equals(questionId))
					.findFirst();

			if (questionOptional.isPresent()) {
				Question question = questionOptional.get();
				if (answer != null && answer.equals(question.getCorrectOption())) {
					// if (answer != null &&
					// answer.equalsIgnoreCase(question.getCorrectOption().toString())) {
					correctAnswers++;
				}
			}
		}

		String score = correctAnswers + "/" + totalQuestions;
		return new GenericResponse(200, "Score = " + score);
	}

}
