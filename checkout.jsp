<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<DOCTYPE! html>

    <head>

        <title>Checkout - $hop Boyz Inc</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    </head>

    <div class="container">

    <body>

        <!--outline the ordered items-->

        <header>

            <h1>Items Ordered</h1>

            <h2 style="color: red;">${requestScope.errorMsg}</h2>

        </header>

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

        <!--show input fields for user's data-->

        <form action="Checkout" method="POST" class="form-inline">

            <div class="form-group">

            <label for="firstName">First Name:</label> 

                <input type="text" name="firstName" value="${requestScope.fName}"/>

            </div>

            <div class="form-group">

            <label for="middleName">Middle Name:</label> 

                <input type="text" name="middleName" value="${requestScope.mName}"/>

            </div>

            <div class="form-group">

            <label for="lastName">Last Name:</label>

                <input type="text" name="lastName" value="${requestScope.lName}"/>

            </div>

            <div class="form-group">

            <label for="email">Email Address:</label>

                <input type="email" name="email" value="${requestScope.email}"/>

            </div>

            <input type="submit" value="Complete Purchase"/>

        </form>

    </body>

    </div>

</html>

