<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<script>
	
</script>
<title>产品管理</title>
<div class="workingArea">
	<ol class="breadcrumb">
		<li><a href="admin_category_list">所有分类</a></li>
		<li><a href="admin_product_list?cid=${category.id}">${category.name}</a></li>
		<li class="active">产品管理</li>
	</ol>

	<div class="listDataTableDiv">
		<table
			class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>图片</th>
					<th>产品名称</th>
					<th>产品小标题</th>
					<th width="53px">原价格</th>
					<th width="80px">优惠价格</th>
					<th width="80px">库存数量</th>
					<th width="80px">图片管理</th>
					<th width="80px">设置属性</th>
					<th width="42px">编辑</th>
					<th width="42px">删除</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${products}" var="product">
					<tr>
						<td>${product.id}</td>
						<td>
							<c:if test="${!empty product.firstProductImage}">
								<img width="40px" src="img/productSingnal/${product.firstProductImage.id}.jpg">
							</c:if>
						</td>
						<td>${product.name}</td>
						<td>${product.subTitle}</td>
						<td>${product.orignalPrice}</td>
						<td>${product.promotePrice}</td>
						<td>${product.stock}</td>
						<td><a></a></td>
						<td><a></a></td>
						<td><a></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp"%>
	</div>
	
	<%@include file="../include/admin/adminFooter.jsp"%>
	
</div>