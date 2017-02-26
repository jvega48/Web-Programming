<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="cart" class="StoreManager.ShoppingCart" scope="session"/>
<!doctype html>
<html lang="en">
<head>
	<title>Store</title>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">	
	<%-- <link rel="stylesheet" href="https://bootswatch.com/paper/bootstrap.min.css"> --%>
	<link rel="stylesheet" type="text/css" href="StoreCSS/Store.css"></link>
</head>
<div class="container">
<jsp:setProperty property="item" name="cart" param="id"/>

	<!-- Page Header -->
	<div class="page-header">
		<h2>
			Welcome to Shop Boy's Inc
		</h2>
	</div>

	<c:if test="${empty items}">
		<div class="jumbotron">
			<h1>Uh-Oh! <small>Looks like we're sold out on everything!</small></h1>
		</div>
	</c:if>

	<c:if test="${not empty items}">
	<table class="table table-hover table-striped table-bordered">
		<thead>
			<tr>
				<th>Item</th>
				<th>Price</th>
				<th>Add To Cart</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${items}" var="item">
				<tr>
					<c:if test="${item.quantity >= 1}">					
						<td>
							<a href="Details?id=${item.id}">${item.name}</a>				
						</td>
						<td>$
							${item.price}
						</td>
						<td>
						
								<a href="Store?id=${item.id }">
									
									<button type="button" class="btn btn-default btn-sm">	
									+ <span class="glyphicon glyphicon-shopping-cart"></span>
									
									</button>
								</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:if>
	
	<a href="ShoppingCart" class="btn btn-info btn-lg">
	          <span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart ( ${cart.totalItems } )
	</a>
	

</div>


</body>
</html>