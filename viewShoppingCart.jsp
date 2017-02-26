<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<DOCTYPE! html>

    <head>

        <title>Cart Items - $hop Boyz</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">  

    </head>

    

    <div class="container">

    <body>

        <header>

            <h1>

                Your Order

            </h1>

        

            <h2>

                ${requestScope.msg}

            </h2>

            

        </header>

        <%-- Check if shopping cart is empty --%>

        

        <c:if test="${empty requestScope.msg}">

        

            <table class="table table-hover table-striped table-bordered">

                <thead>

                    <tr><th>Name</th>

                        <th>Description</th>

                        <th>Price</th>

                        <th>Quantity</th>

                        <th>Total Price</th>

                    <!--<th>Change Amount</th>

                        <th>Delete</th>-->

                    </tr>

                </thead>

                <tbody>

                    

                    <c:forEach items="${sessionScope.shoppingCart}" var="item">

                        <tr>

                            <td>${item.name}</td>

                            <td>${item.description}</td>

                            <td>$ ${item.price}</td>

                            <td>${item.quantity}</td>

                            <td>$ ${item.price * item.quantity}</td>

                        </tr>

                    </c:forEach>

                        

                </tbody>

                <tfoot>

                    <tr>

                        <td></td>

                        <td></td>

                        <td></td>

                        <td></td>

                        <td>Grand Total: $ ${sessionScope.grandTotal}</td>

                    </tr> 

                </tfoot>

            </table>

            <p>

                <a href="Checkout" class="btn btn-default btn-sm">Checkout</a>

            </p>

        </c:if>

    </body>

    </div>

</html>