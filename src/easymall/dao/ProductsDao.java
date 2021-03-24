package easymall.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.Category;
import easymall.po.Products;

@Repository("productsDao")
@Mapper
public interface ProductsDao {
	public List<Category> allcategorys();
	
	public List<Products> prodlist(Map<String, Object> map);
	
	public Products selectOneProd(String pid);
	
	public List<Products> proclass(Integer proclass);
	
	public void save(Products products);
	
	public Object findByImgurl(String imgurl);
	
	public void deleteprod(String id);
	
	public void update(Products products);
}
