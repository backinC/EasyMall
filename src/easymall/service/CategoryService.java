package easymall.service;

import java.util.List;

import easymall.po.Category;

public interface CategoryService {
	public void addCategory(Category category);
	public void deleteCategory(String id);
	public void updateCategory(Category category);
	public List<Category> selectAllCategory();
	public Category selectCategoryByid(String id);
}
