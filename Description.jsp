<!-- Display name, details, quantity, price -->
<!-- Add the item to the shopping cart -->
<!-- click the shopping cart to view its contents -->
<!-- click to go back to the storefront -->

<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>${item.name} Details</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">	
</head>
<body>
<div class="container">
<jsp:setProperty property="item" name="cart" param="itemId"/>
<h1>${item.name} Details</h1>

<table class="table">
	<thead class="thead-inverse">
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Add To Cart</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>${item.name }</td>
			<td>${item.description }</td>
			<td>${item.quantity }</td>
			<td>${item.price }</td>
			<td><a href="Detials?id=${item.id}&itemId=${item.id}">
				<button type="button" class="btn btn-default btn-sm">	
				+ <span class="glyphicon glyphicon-shopping-cart"></span>				
				</button>
				</a></td>						
		</tr>
	</tbody>

</table>

<a href="ShoppingCart" class="btn btn-info btn-lg">
	          <span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart ( ${cart.totalItems } )
</a>

<a href="Store" class="btn btn-success btn-lg">
		<span class="glyphicon glyphicon-arrow-left"></span> Back To Store
</a>

</div>

</body>
</html>