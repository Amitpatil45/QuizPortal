package com.examportal.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.examportal.exception.GenericResponse;
import com.examportal.model.exam.Category;

public interface CategoryService {
	public GenericResponse addCategory(Category category) throws Exception;

	public GenericResponse updateCategory(Category category, Long cid);

	public Category getCategory(Long categoryId);

	Page<Category> getCategories(int page, int size);

	GenericResponse changeCategoryStatus(Long categoryId, Boolean newStatus);

	List<Category> getActiveCategories();

}
