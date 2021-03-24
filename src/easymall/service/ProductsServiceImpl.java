package easymall.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.ProductsDao;
import easymall.po.Category;
import easymall.po.Products;
import easymall.pojo.MyProducts;

@Service("productService")
public class ProductsServiceImpl implements ProductsService {
	@Autowired
	private ProductsDao productsDao;

	@Override
	public List<Category> allcategorys() {
		List<Category> categorys = productsDao.allcategorys();
		return categorys;
	}

	@Override
	public List<Products> prodlist(Map<String, Object> map) {
		List<Products> products = productsDao.prodlist(map);
		return products;
	}

	@Override
	public Products selectOneProd(String pid) {
		Products product = productsDao.selectOneProd(pid);
		return product;
	}

	@Override
	public List<Products> proclass(Integer proclass) {
		return productsDao.proclass(proclass);

	}

	@Override
	public void deleteprod(String id) {
		productsDao.deleteprod(id);
	}

	@Override
	public String save(MyProducts myproducts, HttpServletRequest request) {
		// 1.判断是否合法
		// 获取图的名称、后缀名称
		String originName = myproducts.getImgurl().getOriginalFilename();

		// 截取后缀substring split(".png" ".jpg")
		String extName = originName.substring(originName.lastIndexOf("."));

		if (!(extName.equalsIgnoreCase(".jpg") || extName.equalsIgnoreCase(".png")
				|| extName.equalsIgnoreCase(".gif"))) {
			return "图片后缀不合法！";
		}

		// 判断木马（java的类型判断是否时图片属性，也可以引入第三方jar包判断
		try {
			BufferedImage bufImage = ImageIO.read(myproducts.getImgurl().getInputStream());
			bufImage.getHeight();
			bufImage.getWidth();
		} catch (Exception e) {
			return "该文件不是图片！";
		}

		// 2.创建upload开始的一个路径
		// 生成多级路径
		String imgurl = "";
		for (int i = 0; i < 8; i++) {
			imgurl += "/" + Integer.toHexString(new Random().nextInt(16));
		}
		String realpath = request.getServletContext().getRealPath("/WEB-INF");
		realpath += "/upload";

		System.out.println(realpath + imgurl);
		File file = new File(realpath + imgurl, originName);
		if (!file.exists()) {
			file.mkdirs();
		}

		// 上传文件
		try {
			myproducts.getImgurl().transferTo(file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// 凭借图片存入数据库的路径
		imgurl = "/upload" + imgurl + "/" + originName;
		String id = UUID.randomUUID().toString();
		Products products = new Products();
		products.setId(id);
		products.setSoldnum(0);
		products.setName(myproducts.getName());
		products.setCategory(myproducts.getCategory());
		products.setPrice(myproducts.getPrice());
		products.setPnum(myproducts.getPnum());
		products.setImgurl(imgurl);
		products.setDescription(myproducts.getDescription());
		if (productsDao.findByImgurl(products.getImgurl()) == null) {
			productsDao.save(products);
		} else {
			String fname = imgurl.substring(0, imgurl.lastIndexOf("."));
			imgurl = fname + System.currentTimeMillis() + extName;
			products.setImgurl(imgurl);
			System.out.println(products.getImgurl());
			productsDao.save(products);
		}
		return "商品添加成功";
	}

	@Override
	public String update(MyProducts myproducts, HttpServletRequest request, String id) {
		// 1.判断是否合法
		// 获取图的名称、后缀名称
		String originName = myproducts.getImgurl().getOriginalFilename();
		if(null == originName || "" == originName){
//			String id = UUID.randomUUID().toString();
			Products products = new Products();
			products.setId(id);
			products.setName(myproducts.getName());
			products.setCategory(myproducts.getCategory());
			products.setPrice(myproducts.getPrice());
			products.setPnum(myproducts.getPnum());
//			products.setImgurl(imgurl);
			products.setDescription(myproducts.getDescription());
			System.out.println(1);
			productsDao.update(products);
		}
		else{
			// 截取后缀substring split(".png" ".jpg")
			String extName = originName.substring(originName.lastIndexOf("."));

			if (!(extName.equalsIgnoreCase(".jpg") || extName.equalsIgnoreCase(".png")
					|| extName.equalsIgnoreCase(".gif"))) {
				return "图片后缀不合法！";
			}

			// 判断木马（java的类型判断是否时图片属性，也可以引入第三方jar包判断
			try {
				BufferedImage bufImage = ImageIO.read(myproducts.getImgurl().getInputStream());
				bufImage.getHeight();
				bufImage.getWidth();
			} catch (Exception e) {
				return "该文件不是图片！";
			}
			// 2.创建upload开始的一个路径
			// 生成多级路径
			String imgurl = "";
			for (int i = 0; i < 8; i++) {
				imgurl += "/" + Integer.toHexString(new Random().nextInt(16));
			}
			String realpath = request.getServletContext().getRealPath("/WEB-INF");
			realpath += "/upload";

			System.out.println(realpath + imgurl);
			File file = new File(realpath + imgurl, originName);
			if (!file.exists()) {
				file.mkdirs();
			}

			// 上传文件
			try {
				myproducts.getImgurl().transferTo(file);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			// 凭借图片存入数据库的路径
			imgurl = "/upload" + imgurl + "/" + originName;
			System.out.println("imgurl" + imgurl);
			
//			String id = UUID.randomUUID().toString();
			Products products = new Products();
			products.setId(id);
			products.setName(myproducts.getName());
			products.setCategory(myproducts.getCategory());
			products.setPrice(myproducts.getPrice());
			products.setPnum(myproducts.getPnum());
			products.setImgurl(imgurl);
			products.setDescription(myproducts.getDescription());
			
			
			if (productsDao.findByImgurl(products.getImgurl()) == null) {
				System.out.println(2);
				productsDao.update(products);
			} else {
				System.out.println(3);
				String fname = imgurl.substring(0, imgurl.lastIndexOf("."));
				imgurl = fname + System.currentTimeMillis() + extName;
				products.setImgurl(imgurl);
				System.out.println(products.getImgurl());
				productsDao.update(products);
			}
		}
		return "商品修改成功";
	}
}
