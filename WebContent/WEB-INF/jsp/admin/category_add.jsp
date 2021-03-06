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
	<div>
	<jsp:useBean id="category" class="easymall.po.Category" scope="request"/>
	<form:form modelAttribute="category" onsubmit="return formobj.checkForm();" method="POST"
	   enctype="multipart/form-data" action="${ pageContext.request.contextPath }/admin/addcategory">			
		<fieldset>
		<legend>新增商品类别</legend>			
		<p>
            <label>类别名字:</label>
            <form:input path="name"/>
        </p><p>
            <label>类别描述:</label>
            <form:textarea path="description" rows="3" cols="20"/>
        </p><p id="buttons">
            <input id="submit" type="submit" value="新增">
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