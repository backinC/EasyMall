package easymall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.scenario.effect.Blend.Mode;

import easymall.po.Category;
import easymall.service.CategoryService;

@Controller("categoryControllerAdmin")
@RequestMapping("/admin")
public class CategoryConrollerAdmin {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/showcategory")
	public String showcategory (Model model) {
		List<Category> categorys = categoryService.selectAllCategory();
		model.addAttribute("categorys", categorys); 
		return "admin/category_manager";
	}
	
	@RequestMapping("/newcategory")
	public String newcategory () {
		return "admin/category_add";
	}
	
	@RequestMapping("/addcategory")
	public String addcategory (@ModelAttribute Category category,
			HttpServletRequest request, Model model) {
		categoryService.addCategory(category);
		return "redirect:/admin/showcategory";
	}
	
	@RequestMapping("/deletecategory")
	public String deletecategory (String id, Model model) {
		categoryService.deleteCategory(id);
		return "redirect:/admin/showcategory";
	}
	
	@RequestMapping("/editcategory")
	public String editcategory (String id, Model model){
		Category category = categoryService.selectCategoryByid(id);
		model.addAttribute("category", category);
		return "admin/category_edit";
	}
	
	@RequestMapping("/updatecategory")
	public String updatecategory (@ModelAttribute Category category, Integer id, Model model) {
		category.setId(id);
		categoryService.updateCategory(category);
		return "redirect:/admin/showcategory";
	}
}
