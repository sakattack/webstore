<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js"></script>
<script src="/webstore/resources/js/controllers.js"></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Products</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Products</h1>
			</div>
		</div>
	</section>
	<section class="container" ng-app="cartApp">
		<div class="row">
			<div class="col-md-5">
				<img src="<c:url value="/img/${product.productId}.jpg"></c:url>" alt="image" style = "width:100%"/>
			</div>
			<div class="col-md-5">
				<h3>${product.name}</h3>
				<p>${product.description}</p>
				<p>
					<strong><spring:message code="addProduct.form.productId.label"/></strong> : <span class="label label warning">${product.productId}
					</span>
				</p>
				<p>
					<strong><spring:message code="addProduct.form.manufacturer.label"/></strong> : ${product.manufacturer}
				</p>
				<p>
					<strong><spring:message code="addProduct.form.category.label"/></strong> : ${product.category}
				</p>
				<p>
					<strong><spring:message code="addProduct.form.unitsInStock.label"/></strong> :
					${product.unitsInStock}
				</p>
				<h4>${product.unitPrice}<spring:message code="currency"/></h4>
				<p ng-controller="cartCtrl">
					<a href="<spring:url value="/market/products" />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span> <spring:message code="buttons.back"/>
					</a>
					<a href="#" class="btn btn-warning btn-large" ng-click="addToCart('${product.productId}')">
						<span class="glyphicon-shopping-cart glyphicon"> </span> <spring:message code="buttons.orderNow"/>
					</a>
					<a href="<spring:url value="/cart" />" class="btn btn-default">
						<span class="glyphicon-hand-right glyphicon"></span> View Cart
					</a>
				</p>
			</div>
		</div>
	</section>
</body>
</html>