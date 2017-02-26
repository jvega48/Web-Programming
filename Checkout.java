package StoreManager;

import java.io.IOException;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.util.HashMap;

import java.util.UUID;

import javax.servlet.ServletConfig;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;




@WebServlet("/Checkout")

public class Checkout extends HttpServlet {

	private static final long serialVersionUID = 1L;



	public Checkout() {

		super();

	}

	/*

	 * Check the shopping cart

	 * Generate error message 'msg'

	 */



	public void init(ServletConfig config) throws ServletException {

		super.init(config);



		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {

			throw new ServletException(e);

		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//forward to the checkout.jsp

		request.getRequestDispatcher("/WEB-INF/checkout.jsp").forward(request, response);

	}



	/* process the checkout form 

	 * update database

	 * redirect to the completed checkout page

	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean errorExists = false;

		String errorMsg = "";

		String fName = request.getParameter("firstName");

		request.setAttribute("fName", fName);

		String mName = request.getParameter("middleName");

		request.setAttribute("mName", mName);

		String lName = request.getParameter("lastName");

		request.setAttribute("lName", lName);

		String email = request.getParameter("email");

		if(fName == null || fName.trim().isEmpty()){

			//Narrow down the error msg

			errorExists = true;

			errorMsg = "Error: \n INVALID FIELD(S): FIRST NAME";

		}



		if(mName == null || mName.trim().isEmpty()){

			//Narrow down the error msg

			if(errorExists){

				errorMsg = errorMsg + ", MIDDLE NAME";

			}

			else{

				errorExists = true;

				errorMsg = "Error: \n INVALID FIELD(S): MIDDLE NAME";

			}

		}

		if(lName == null || lName.trim().isEmpty()){

			//Narrow down the error msg

			if(errorExists){

				errorMsg = errorMsg + ", LAST NAME";

			}

			else{

				errorExists = true;

				errorMsg = "Error: \n INVALID FIELD(S): LAST NAME";

			}

		}

		if(email == null || email.trim().isEmpty() || !email.contains("@")){

			//Narrow down the error msg

			if(errorExists){

				errorMsg = errorMsg + ", EMAIL ADDRESS";

			}

			else{

				errorExists = true;

				errorMsg = "Error: \n INVALID FIELD(S): EMAIL ADDRESS";

			}

		}

		else{

			request.setAttribute("email", email);

		}

		if(errorExists){

			request.setAttribute("errorMsg", errorMsg);

			doGet(request, response);

			return;

		}

		//Update inventory Database

		

		Connection c = null;

		try {

			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu53";

			String username = "cs3220stu53";

			String password = "!.S60cVG";



			c = DriverManager.getConnection(url, username, password);

			HashMap<String, Integer> itemsOrdered = ((ShoppingCart) (request.getSession().getAttribute("cart"))).getCart();

			String[] ids = itemsOrdered.keySet().toArray(new String[itemsOrdered.size()]);

			int soldQuantity =0;

			int id = 0;

			for(int i = 0; i < itemsOrdered.size(); i++){

				id = Integer.parseInt(ids[i]);

				soldQuantity = itemsOrdered.get("" + id);

				PreparedStatement psmt = c.prepareStatement("UPDATE store SET quantity = quantity - ? WHERE id = ?;");

				psmt.setInt(1, soldQuantity);

				psmt.setInt(2, id);

				psmt.execute();

			}

		} catch (SQLException e) {

			throw new ServletException(e);

		} finally {

			try {

				if (c != null)

					c.close();

			} catch (SQLException e) {

				throw new ServletException(e);

			}

			//Transaction in Database



			//Create unique Order Number

			String orderId = UUID.randomUUID().toString();

			//Attach Order Number to request object

			request.setAttribute("orderId", orderId);

			//Invalidate Session

			request.getSession().invalidate();

			//Forward to Completed Checkout page

			request.getRequestDispatcher("/WEB-INF/viewCompletedCheckout.jsp").forward(request, response);

		}
	}
}