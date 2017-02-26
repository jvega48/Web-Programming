package StoreManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteItem")
public class DeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public DeleteItem() {
        super();
      
    }

    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		try{
			
			Integer id = Integer.valueOf(request.getParameter("id"));
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu53";
			String username = "cs3220stu53";
			String password = "!.S60cVG" ;
			String sql = "delete from store where id = ?" ;	
				c = DriverManager.getConnection(url,username,password);
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
			
		}
		catch(SQLException e)
		{
			throw new ServletException( e );
		}
		finally{
			try{
				if(c!=null)
				{
					c.close();
				}
			}
			catch(SQLException e){
				throw new ServletException( e );
			}
		}
		response.sendRedirect("Inventory");



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
