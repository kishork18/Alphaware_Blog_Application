package com.blog.service;

import java.util.List;

import com.blog.entity.Category;
import com.blog.exception.CategoryException;

public interface CategoryService {
	public Category createCategory(Category category) throws CategoryException;

	public List<Category> findAllCategory() throws CategoryException;

	public Category findByName(String name) throws CategoryException;

	public Category updateCategory(Category category) throws CategoryException;

	public Category deleteCategory(String name) throws CategoryException;
}
