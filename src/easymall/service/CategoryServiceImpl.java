package easymall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.CategoryDao;
import easymall.po.Category;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public void addCategory(Category category) {
		// TODO Auto-generated method stub
		categoryDao.addCategory(category);
	}
	
	@Override
	public void deleteCategory(String id) {
		// TODO Auto-generated method stub
		categoryDao.deleteCategory(id);
	}
	
	@Override
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub
		categoryDao.updateCategory(category);
	}
	
	@Override
	public List<Category> selectAllCategory() {
		return categoryDao.selectAllCategory();
	}
	
	@Override
	public Category selectCategoryByid(String id) {
		return categoryDao.selectCategoryByid(id);
	}
}
