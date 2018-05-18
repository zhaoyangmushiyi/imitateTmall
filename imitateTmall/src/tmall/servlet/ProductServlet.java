package tmall.servlet;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tmall.bean.Category;
import tmall.bean.Product;
import tmall.util.Page;

public class ProductServlet extends BaseBackServlet {

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		
		int cid = Integer.parseInt(request.getParameter("cid"));
		
		Category category = categoryDAO.get(cid);
		
		String name = request.getParameter("name");
		String subTitle = request.getParameter("subTitle");
		float orignalPrice = Float.parseFloat(request.getParameter("orignalPrice"));
		float promotePrice = Float.parseFloat(request.getParameter("promotePrice"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		
		Product product = new Product();
		product.setName(name);
		product.setSubTitle(subTitle);
		product.setOrignalPrice(orignalPrice);
		product.setPromotePrice(promotePrice);
		product.setStock(stock);
		product.setCategory(category);
		product.setCreateDate(new Date(System.currentTimeMillis()));
		productDAO.add(product);
		
		return "@admin_product_list?cid=" + cid;
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Product product = productDAO.get(id);
		productDAO.delete(id);
		return "@admin_product_list?cid=" + product.getCategory().getId();
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Product product = productDAO.get(id);
		request.setAttribute("product", product);
		return "admin/editeProduct.jsp";
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		int cid = Integer.parseInt(request.getParameter("cid"));	
		Category category = categoryDAO.get(cid);
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String subTitle = request.getParameter("subTitle");
		float orignalPrice = Float.parseFloat(request.getParameter("orignalPrice"));
		float promotePrice = Float.parseFloat(request.getParameter("promotePrice"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		
		Product product = new Product();
		product.setId(id);
		product.setName(name);
		product.setSubTitle(subTitle);
		product.setOrignalPrice(orignalPrice);
		product.setPromotePrice(promotePrice);
		product.setStock(stock);
		product.setCategory(category);
		
		productDAO.update(product);
		
		return "@admin_product_list?cid=" + cid;
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		
		int cid = Integer.parseInt(request.getParameter("cid"));
		Category category = categoryDAO.get(cid);
		List<Product> products = productDAO.list(cid, page.getStart(), page.getCount());
		int total = productDAO.getTotal(cid);
		page.setTotal(total);
		page.setParam("&cid=" + cid);
		request.setAttribute("products", products);
		request.setAttribute("category", category);
		
		return "admin/listProduct.jsp";
	}

}
