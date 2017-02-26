package StoreManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Store")
public class StoreFront extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Item> items = new ArrayList<Item>();
		HttpSession sess = request.getSession(false);
		ShoppingCart cart = null;
		if(sess == null || sess.getAttribute("cart") == null){
			cart = new ShoppingCart();
			request.getSession().setAttribute("cart", cart);
		}
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu53";
			String username = "cs3220stu53";
			String password = "!.S60cVG";
			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM store");
			while (rs.next()) {
					Item entry = new Item(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("quantity"), rs.getDouble("price"));
					items.add(entry);
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
		}
		request.setAttribute("items", items);
		request.getRequestDispatcher("/WEB-INF/Store.jsp").forward(request, response);
	}
}
