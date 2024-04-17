package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.Category;
import com.blog.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryServ;

	@PostMapping("/createcategory")
	public ResponseEntity<Category> createCategoryHandler(@RequestBody Category category) {
        Category cat= categoryServ.createCategory(category);
        return new ResponseEntity<Category>(cat, HttpStatus.CREATED);
	}
	
	@GetMapping("/findallcategory")
	public ResponseEntity<List<Category>>  findAllCategory(){
		List<Category> list= categoryServ.findAllCategory();
		return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
	}
	@GetMapping("/findbyname")
	public ResponseEntity<Category> findByNameHandler(@RequestParam ("name") String name){
		Category cat= categoryServ.findByName(name);
		return new ResponseEntity<Category>(cat, HttpStatus.CREATED);
	}
	@PutMapping("/update")
	public ResponseEntity<Category> updateCategoryHandler(@RequestBody Category category){
		Category cat= categoryServ.updateCategory(category);
		return new ResponseEntity<Category>(cat, HttpStatus.OK);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Category> deleteCategory(@RequestParam("name") String name){
		Category cat=categoryServ.deleteCategory(name);
		return new ResponseEntity<Category>(cat, HttpStatus.OK);
	}

}
