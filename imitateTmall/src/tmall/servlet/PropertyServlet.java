package tmall.servlet;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tmall.bean.Category;
import tmall.bean.Property;
import tmall.dao.CategoryDAO;
import tmall.util.Page;

public class PropertyServlet extends BaseBackServlet {

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		int cid = Integer.parseInt(request.getParameter("cid"));
		String name = request.getParameter("name");
		Category category = categoryDAO.get(cid);
		Property property = new Property();
		property.setCategory(category);
		property.setName(name);
		propertyDAO.add(property);
		return "@admin_property_list?cid";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Property property = propertyDAO.get(id);
		propertyDAO.delete(id);
		return "admin_property_list?cid=" + property.getCategory().getId();
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Property property = propertyDAO.get(id);
		request.setAttribute("property", property);
		return "admin/editProperty.jsp";
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		 int cid = Integer.parseInt("cid");
		 int id = Integer.parseInt("id");
		 Category category = categoryDAO.get(cid);
		 Property property = propertyDAO.get(id);
		 String name = request.getParameter("name");
		 property.setName(name);
		 property.setCategory(category);
		 property.setId(id);
		 propertyDAO.update(property);
		 return "admin_property_list?cid="+property.getCategory().getId();
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		int cid = Integer.parseInt(request.getParameter("cid"));
		int id = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(cid);
		List<Property> properties = propertyDAO.list(cid, page.getStart(), page.getCount());
		int total = propertyDAO.getTotal(cid);
		page.setTotal(total);
		page.setParam("&cid=" + category.getId());
		request.setAttribute("properties", properties);
		request.setAttribute("category", category);
		request.setAttribute("page", page);
		
		return "admin/listProperty.jsp";
	}

}
