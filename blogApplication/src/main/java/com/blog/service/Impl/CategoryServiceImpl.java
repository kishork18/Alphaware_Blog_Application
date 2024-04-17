package com.blog.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.exception.CategoryException;
import com.blog.repository.CategoryRepository;
import com.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository catRepo;

	@Override
	public Category createCategory(Category category) throws CategoryException {
		Optional<Category> cat = catRepo.findByName(category.getName());
		if (cat.isPresent()) {
			throw new CategoryException("category with name " + category.getName() + " already present");
		}
		category.setPosts(new ArrayList<>());
		return catRepo.save(category);
	}

	@Override
	public List<Category> findAllCategory() throws CategoryException {
		List<Category> list = catRepo.findAll();
		if (list.isEmpty()) {
			throw new CategoryException("No category found ");
		}
		return list;
	}

	@Override
	public Category findByName(String name) throws CategoryException {
		Optional<Category> cat = catRepo.findByName(name);
		if (!cat.isPresent()) {
			throw new CategoryException("category with name " + name + " not present");
		}
		return cat.get();
	}

	@Override
	public Category updateCategory(Category category) throws CategoryException {
		Optional<Category> cat = catRepo.findByName(category.getName());
		if (!cat.isPresent()) {
			throw new CategoryException("category with name " + category.getName() + " not present");
		}
		Category cat1 = cat.get();
		cat1.setPosts(category.getPosts());

		return catRepo.save(cat1);
	}

	@Override
	public Category deleteCategory(String name) throws CategoryException {
		Optional<Category> cat = catRepo.findByName(name);
		if (!cat.isPresent()) {
			throw new CategoryException("category with name " + name + " not present");
		}
        catRepo.delete(cat.get());
		return cat.get();
	}

}
