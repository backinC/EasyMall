package easymall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.OrderItem;
import easymall.po.Orders;
import easymall.po.Products;
import easymall.po.User;
import easymall.pojo.OrderInfo;
import easymall.service.CartService;
import easymall.service.OrderService;
import easymall.service.ProductsService;

@Controller("orderControllerAdmin")
@RequestMapping("/admin")
public class OrderControllerAdmin {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductsService productsService;	
	
	@RequestMapping("/deliver")
	public String deliver (String id, Model model) {
		orderService.deliver(id);
		return "redirect:/admin/showorder";
	}
	
	@RequestMapping("/showorder")
	public String showorder(HttpSession session, Model model){
		List<OrderInfo> orderInfoList = findAllOrderInfo();
		model.addAttribute("orderInfos", orderInfoList);
		return "admin/order_manager";
	}
	
	private List<OrderInfo> findAllOrderInfo(){
		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
		
		List<Orders> orderList = orderService.findAllOrder();
		for(Orders order : orderList) {
			List<OrderItem> orderitems = orderService.orderitem(order.getId());
			Map<Products, Integer> map = new HashMap<Products, Integer>();
			for (OrderItem orderItem : orderitems) {
				Products product = productsService.selectOneProd(orderItem.getProduct_id());
				map.put(product, orderItem.getBuynum());
			}
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setOrder(order);
			orderInfo.setMap(map);
			orderInfoList.add(orderInfo);
		}
		return orderInfoList;
	}
}
