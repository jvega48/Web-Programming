<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
	<title>Store</title>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">	
	<%-- <link rel="stylesheet" href="https://bootswatch.com/paper/bootstrap.min.css"> --%>
</head>
<body>
<div class="container">

	<!-- Page Header -->
	<div class="page-header">
		<h2>
		Store Item's	
		</h2>
		<div class="form" class="">
		<form>
		<input type="text" class="form" id="search" name="search" placeholder="search" required>
		<input type="button" value="submit">
		</form>
		</div>

	</div>

	<form action="Inventory" method="post">
		<div class="row">
			<div class="col-xs-9 col-sm-10">
		<div class="form-group">	    
	   		 <input type="text"  class="form-control" id="name" name="name" placeholder="Enter Item Name">
	    	 <input type="text"  class="form-control" id="description" name="description" placeholder="Enter Item Description">
	   		 <input type="text"  class="form-control" id="quantity" name="quantity" placeholder="Enter Item Count">
	   		 <input type="text"  class="form-control" id="price" name="price" placeholder="Enter Item Price">
	  </div>	
				<c:if test="${not empty error}">
					<center><p class="well-sm bg-danger">${error}</p></center>
				</c:if>
			</div>
			<div class="col-xs-3 col-sm-2">
				<button type="submit" class="btn btn-success btn-block">Add Store Item</button>
			</div>
		</div>
	</form>
	<c:if test="${empty items}">
	<div> 
		<center><h1>Uh-Oh!</h1><small>Unfortunatly no item's in the store</small></center>
	</div>
	</c:if>
	
<c:if test="${not empty items}">
	<table class="table table-hover table-striped table-bordered">
		<thead>
			<tr>
				<th>Name</th>
				<th>Description</th>
				<th>Quantity</th>
				<th>Price</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${items}" var="item">
				<tr>					
					<td>
					${item.name}
					</td>
					<td>
					${item.description}
					</td>
					<td>
					${item.quantity}
					</td>
					<td>
					$ ${item.price}
					</td>
					<td>
								<%-- <a class="btn btn-warning btn-xs" href="UpdateItem?id=${item.id}">Update</a>--%>
								<%--<a class="btn btn-primary btn-xs" href="${updateUrl}">Update</a>--%>

								<a class="btn btn-danger btn-xs" href="DeleteItem?id=${item.id}">Delete</a>
								<%--<a class="btn btn-danger btn-xs" href="${deleteItemUrl}">Delete</a>--%>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:if>

</div>
<footer><div class="background-grey" class="color-blue">All Rights Server Shop Boy's Inc</div></footer>
</body>
</html>