package easymall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import easymall.po.Category;
import easymall.po.Products;
import easymall.service.ProductsService;

@Controller("productsController")
public class ProductsController {
	
	@Autowired
	private ProductsService productsService;
	
	@RequestMapping("/prodInfo")
	public String proinfo(String pid, Model model){
		Products product = productsService.selectOneProd(pid);
		System.out.println(product.getId());
		System.out.println(product.getName());
		model.addAttribute("product", product);
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("product", product);
//		mv.setViewName("prod_info");
		
		return "prod_info";
	}
	
	@RequestMapping("/echarts")
	public String echarts (@RequestParam(value ="categorys") List<String> categorys,Model model){
		List<Category> cs = productsService.allcategorys();
		List<String> n = new ArrayList<String>();
		for (Category c : cs) {
			for (String id : categorys) {
				if(c.getId().toString().equalsIgnoreCase(id)){
					n.add(c.getName());
				}
			}
		}
		System.out.println(n.size());
		model.addAttribute("categorys",n);
		return "echarts";
	}
	
	@RequestMapping(value="/prodclass/{proclass}", method=RequestMethod.GET)
	public String proclass(@PathVariable Integer proclass, Model model){
		List<Products> products = productsService.proclass(proclass);
		model.addAttribute("products", products);
		return "prod_list";
	}
	
	@RequestMapping("/prodlist")
	public String prodlist(String name, Integer category, 
			String minprice, String maxprice, Model model){
		//������Ʒ����������Ʒ���
		List<Category> categorys = productsService.allcategorys();
		model.addAttribute("categorys", categorys);
		
		//Ϊ������������Ĭ�ϣ� ����������Ƿ�Ϸ�
		double _minPrice = 0;
		Double _maxPrice = Double.MAX_VALUE;
		
		String reg = "^\\d+$";//ֻ����������
		if(minprice != null && !"".equals(minprice.trim())
				&& minprice.matches(reg)){
			_minPrice = Double.parseDouble(minprice);
		}
		if(maxprice != null && !"".equals(maxprice.trim())
				&& maxprice.matches(reg)){
			if(Double.parseDouble(maxprice) >= _minPrice){
				_minPrice = Double.parseDouble(minprice);
			}
		}
		//����map�����ڴ�Ų�ѯ����
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("category", category);
		map.put("minPrice", _minPrice);
		map.put("maxPrice", _maxPrice);
		//����������ѯ������������Ʒ��Ϣ
		List<Products> products = productsService.prodlist(map);
		//�����ѯ����
		model.addAttribute("name", name);
		model.addAttribute("minPrice", _minPrice);
		model.addAttribute("maxPrice", _maxPrice);
		model.addAttribute("products", products);
		return "prod_list";
	}
}
