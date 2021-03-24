<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>添加商品</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link href="${ pageContext.request.contextPath }/css/managestyle.css" rel="stylesheet" type="text/css">
	</head>
	<body>
	<div class="top">
		<h1>&nbsp;&nbsp;EasyMall商城管理后台</h1>
	</div>	
	<div class="content">
		<div class="left">			
			<%@ include file = "_left.jsp" %>
		</div>
	<div class="right">	
	<div class="addprod">
	<jsp:useBean id="myproducts" class="easymall.pojo.MyProducts" scope="request"/>
	<form:form modelAttribute="myproducts" onsubmit="return formobj.checkForm();" method="POST"
	   enctype="multipart/form-data" action="${ pageContext.request.contextPath }/admin/updateprod?id=${id }">			
		<fieldset>
		<legend>修改商品</legend>			
		<p>
            <label>商品名:</label>
            <form:input path="name" value="${ product.name }"/>
        </p><p>
            <label>商品价格:</label>
            <form:input path="price" value="${ product.price }"/>
        </p><p>
            <label>商品类别:</label>
            <form:select path="category" value="${product.category }">
            <!-- 通过循环语句将所有商品类别显示在下拉列表中 -->
			<c:forEach items="${categorys }" var ="c">
			<c:choose>
				<c:when test="${c.id == product.category}">
					<option value="${ c.id }" selected>${c.name }</option>
				</c:when>
				<c:otherwise>
					<option value="${ c.id }">${c.name }</option>
				</c:otherwise>
			</c:choose>
				</c:forEach>
			</form:select>
        </p><p>
            <label>库存:</label>
            <form:input path="pnum" value="${product.pnum }"/>
        </p><p>
            <label>图片:</label>
            <img src="${pageContext.request.contextPath}${product.imgurl}" border="0" style="height:100px;width:100px"></img>
            <input type="file" name="imgurl"/>
        </p><p>
            <label>商品描述:</label>
            <form:input path="description" value="${product.description }"/>
        </p><p id="buttons">
            <input id="reset" type="reset">
            <input id="submit" type="submit" value="修改">
        </p>
    	</fieldset>
    	<!-- 取出所有验证错误 -->
    	<form:errors path="*"/>
	</form:form>
	</div>
	</div><!-- right结束 -->
	</div><!-- content结束 -->		
	
	
	</body>
</html>