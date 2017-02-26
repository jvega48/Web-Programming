<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
	<title>Transaction</title>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">	
	<%-- <link rel="stylesheet" href="https://bootswatch.com/paper/bootstrap.min.css"> --%>
</head>
<body>
<div class="container">

	<!-- Page Header -->
	<div class="page-header">
		<h2>
		Transaction History
		</h2>
	</div>
	<c:if test="${empty purchased}">
	<div> 
		<center><h1>Uh-Oh!</h1><small>Unfortunatly no transactions.</small></center>
	</div>
	</c:if>
	
<c:if test="${not empty purchased}">
	<table class="table table-hover table-striped table-bordered">
		<thead>
			<tr>
				<%-- <th><span id="total">${fn:length(item)}</span> </th>--%>
				<th>Name</th>
				<th>Description</th>
				<th>Quantity</th>
				<th>Price</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${purchased}" var="item">
				<tr>
					<td>
					${item.id}
					</td>					
					<td>
					${item.name}
					</td>
					<td>
					${item.quantity}
					</td>
					<td>
					$ ${item.price}
					</td>
					<td>
					$ ${item.total}
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:if>

</div>

</body>
</html>