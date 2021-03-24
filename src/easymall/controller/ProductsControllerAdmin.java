package easymall.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.Category;
import easymall.po.ExcelFileGenerator;
import easymall.po.Products;
import easymall.pojo.MyProducts;
import easymall.service.ProductsService;

@Controller("productsControllerAdmin")
@RequestMapping("/admin")
public class ProductsControllerAdmin {
	@Autowired
	private ProductsService productsService;
	
	@RequestMapping("/addprod")
	public String addprod(Model model) {
		//查找商品表中所有的商品类别
		List<Category> categorys = productsService.allcategorys();
		model.addAttribute("categorys", categorys);
		model.addAttribute("myproducts", new MyProducts());
		return "admin/add_prod";
	}
	
	@RequestMapping("/save")
	public String save(@ModelAttribute MyProducts myproducts,
			HttpServletRequest request, Model model) throws Exception {
		String msg = productsService.save(myproducts, request);
		model.addAttribute("msg", msg);
		return "redirect:/admin/showprod";
	}
	

	
	@RequestMapping("/showprod")
	public String showprod (Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "");
		map.put("category", "");
		map.put("minPrice", 0);
		map.put("maxPrice", Double.MAX_VALUE);
		List<Products> prolist = productsService.prodlist(map);
		model.addAttribute("products", prolist);
		return "admin/product_manager";
	}

	@RequestMapping("/deleteprod")
	public String deleteprod (String id) {
		productsService.deleteprod(id);
		return "redirect:/admin/showprod";
	}
	
	@RequestMapping("/editprod")
	public String editprod (String id, Model model) {
		Products product = productsService.selectOneProd(id);
		List<Category> categorys = productsService.allcategorys();
		model.addAttribute("categorys", categorys);
		model.addAttribute("product", product);
		model.addAttribute("id", id);
		return "admin/product_edit";
	}
	
	@RequestMapping("/updateprod")
	public String updateprod (@ModelAttribute MyProducts myproducts, String id,HttpServletRequest request, Model model) {
		String msg = productsService.update(myproducts, request, id);
		model.addAttribute("msg", msg);
		return "redirect:/admin/showprod";
	}
	
	@RequestMapping("/poi")
	public String poi (HttpServletResponse response,Model model) throws Exception {
		ArrayList<String> fieldName =  new ArrayList<String>();
		fieldName.add("id");
		fieldName.add("name");
		fieldName.add("price");
		fieldName.add("category");
		fieldName.add("pnum");
		fieldName.add("soldnum");
		fieldName.add("imgurl");
		fieldName.add("description");
		Map<String,Object> map = new HashMap<>();
		Integer _minPrice = Integer.MIN_VALUE;
		Integer _maxPrice = Integer.MAX_VALUE;
		map.put("minPrice",_minPrice);
		map.put("maxPrice",_maxPrice);
		List<Products> products = productsService.prodlist(map);
		Collections.sort(products, new Comparator<Products>()
        {
            public int compare(Products o1, Products o2)
            {
                return o1.getSoldnum()-o2.getSoldnum();
            }
        });
		ArrayList<List<String>> fieldData = new ArrayList<List<String>>();
		for(Products product:products) {
			List<String> Data = new ArrayList<String>();
			Data.add(product.getId());
			Data.add(product.getName());
			Data.add(product.getPrice().toString());
			Data.add(product.getCategory().toString());
			Data.add(product.getPnum().toString());
			Data.add(product.getSoldnum().toString());
			Data.add(product.getImgurl());
			Data.add(product.getDescription());
			fieldData.add(Data);
		}
		ExcelFileGenerator excel = new ExcelFileGenerator(fieldName,fieldData);
		String fileName = "soldlist";
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        //根据传进来的file对象创建可写入的Excel工作薄  
        OutputStream out = response.getOutputStream();
		excel.expordExcel(out);
		List<Category> categorys = productsService.allcategorys();
		model.addAttribute("categorys",categorys);
		model.addAttribute("name","");
		model.addAttribute("minPrice",_minPrice);
		model.addAttribute("maxPrice",_maxPrice);
		model.addAttribute("products",products);
		return null;
	}
	
}
