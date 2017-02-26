<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">  

</head>

<div class="container">

<body>

    <h1>Your Order is Complete</h1>

    <h2>Your Order Number: ${requestScope.orderId}</h2>

    <p><a href="Store" class="btn btn-success btn-lg">

        <span class="glyphicon glyphicon-arrow-left"></span> Back To Store

</a></p>

</body>

</div>

</html>