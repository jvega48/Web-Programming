package StoreManager;

import java.io.IOException;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;



import javax.servlet.ServletConfig;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;





/* 

 * This shopping cart controller assumes that 

 * the user will use a cart: list of ids; where 

 * itll be used to query info from a db to view info

 * 

 */

@WebServlet("/ShoppingCart")

public class ShoppingCartController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ShoppingCartController() {

		super();

	}

	//Load mySql driver class

	public void init(ServletConfig config) throws ServletException{

		super.init( config );



		try{

			Class.forName( "com.mysql.jdbc.Driver" );

		}

		catch( ClassNotFoundException e ){

			throw new ServletException( e );

		}

	}



	//Query DB and get info for shopping cart item details

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Check if user is in session

		HttpSession sess = request.getSession(false);

		String msg = null;



		//No session exists

		if(sess == null || sess.getAttribute("cart") == null){

			//Forward to StoreFront

			response.sendRedirect("Store");

			return;

		}

		//Session and cart exists

		else{

			ShoppingCart cart = (ShoppingCart) sess.getAttribute("cart");

			//Check if the 'shoppingCart' is empty

			if (cart.getTotalItems() == 0){

				msg = "You have an empty cart";

				request.setAttribute("msg", msg);

			}

			//Cart is loaded

			else{

				HashMap<String, Integer> itemsMap = cart.getCart();

				String[] idList = itemsMap.keySet().toArray(new String[itemsMap.size()]);

				List<Item> shoppingCart = new ArrayList<Item>();

				double grandTotal = 0;

				Connection c = null;

				//get data from db

				try

				{

					String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu53";

					String username = "cs3220stu53";

					String password = "!.S60cVG";



					c = DriverManager.getConnection( url, username, password );

					Statement stmt = c.createStatement();

					String query = "SELECT * FROM store WHERE";

					for(String id: idList){

						query = query + (" id = " + id + " ");

						query = query + ("OR");

					}

					query = query + (" 1=5;");

//					int counter = 1;

//					for(String id: idList){

//						stmt.setInt(counter++, Integer.parseInt(id));

//					}

					ResultSet rs = stmt.executeQuery(query);

					

					//store data to array

					while(rs.next()){

						Item itm = new Item(rs.getInt( "id" ),

						rs.getString( "name" ), rs.getString( "description"), itemsMap.get("" + rs.getInt("id")),rs.getDouble("price") );

						shoppingCart.add(itm);

						grandTotal += itm.getPrice() * itm.getQuantity();

					}

				}

				catch( SQLException e ){

					throw new ServletException( e );

				}

				finally

				{

					try

					{

						if( c != null ) c.close();

					}

					catch( SQLException e ){

						throw new ServletException( e );

					}

				}

				//pack array to request scope

				sess.setAttribute("grandTotal", grandTotal);

				sess.setAttribute("shoppingCart", shoppingCart);

			}

			request.getRequestDispatcher("/WEB-INF/viewShoppingCart.jsp").forward(request, response);

		}

	}



	//Update the shopping cart

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*

		 * This method should be responsible for changing quantity of each items

		 */



		doGet(request, response);

	}

}