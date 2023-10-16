package com.examportal.services.implement;

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
import com.examportal.model.repo.CategoryRepository;
import com.examportal.services.CategoryService;

@Service
public class CatagoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public GenericResponse addCategory(Category category) throws Exception {
		if (category.getTitle() == null || category.getTitle().isBlank()) {
			throw new DataValidationException("please fill title");
		} else {
			category.setTitle(category.getTitle().trim());
		}
		Category titalUnique = categoryRepository.findByTitle(category.getTitle());
		if (titalUnique != null) {
			throw new DataValidationException("Category With Same Tital is Already present");

		}
		categoryRepository.save(category);
		return new GenericResponse(201, "Created Succesfully");
	}

	@Override
	public GenericResponse updateCategory(Category category, Long cid) {
		Optional<Category> optionalCategory = categoryRepository.findById(cid);
		if (optionalCategory.isPresent()) {

			Category presentCategory = optionalCategory.get();
			if (category.getTitle() != null && !category.getTitle().isBlank()) {
				category.setTitle(category.getTitle().trim());
			} else {
				throw new DataValidationException("Please fill a valid title ");
			}

			if (!titalUnique(category.getTitle(), cid)) {
				throw new DataValidationException("Category With Same Tital is Already present");
			}
			presentCategory.setTitle(category.getTitle().trim());
			presentCategory.setDescription(category.getDescription());
			categoryRepository.save(presentCategory);
			return new GenericResponse(202, "Updated Successfully");
		}
		if (optionalCategory.isEmpty()) {
			throw new DataValidationException("Category not found");
		}
		return null;
	}

	private Boolean titalUnique(String tital, Long presentCid) {
		Category categorywithSameTital = categoryRepository.findByTitle(tital);
		if (categorywithSameTital != null) {
			return categorywithSameTital.getId().equals(presentCid);
		}

		return true;

	}

	@Override
	public Page<Category> getCategories(int page, int size) {

		Pageable pageable1 = PageRequest.of(page, size, Sort.by("id").descending());

		Page<Category> category;

		category = categoryRepository.findAll(pageable1);
		return category;
	}

	@Override
	public Category getCategory(Long categoryId) {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if (optionalCategory.isEmpty()) {
			throw new DataValidationException("Category not Found");

		}

		return categoryRepository.findById(categoryId).get();
	}

	@Override
	public GenericResponse changeCategoryStatus(Long categoryId, Boolean newStatus) {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

		if (optionalCategory.isPresent()) {
			Category category = optionalCategory.get();
			category.setIsActive(newStatus);
			categoryRepository.save(category);

			return new GenericResponse(202, "Category status updated successfully.");
		}
		throw new DataValidationException("Category not Found");

	}

	@Override
	public List<Category> getActiveCategories() {
		return categoryRepository.findByIsActiveTrue();
	}

}
