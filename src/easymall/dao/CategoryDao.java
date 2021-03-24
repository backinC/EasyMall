package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.Category;

@Repository("categoryDao")
@Mapper
public interface CategoryDao {
	public void addCategory(Category category);
	public void deleteCategory(String id);
	public void updateCategory(Category category);
	public List<Category> selectAllCategory();
	public Category selectCategoryByid(String id);
}
