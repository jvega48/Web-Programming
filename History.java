package StoreManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/History")
public class History extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
    public History() {
        super();
        // TODO Auto-generated constructor stub
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
		
		 	ArrayList<HistoryBean> history = new ArrayList<HistoryBean>();
	        Connection c = null;
	        
	        try
	        {
	            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu53";
	            String username = "cs3220stu53";
	            String password = "!.S60cVG";

	            c = DriverManager.getConnection( url, username, password );
	            Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery( "select * from history" );

	            while( rs.next() )
	            {
	                HistoryBean transaction = new HistoryBean( rs.getInt( "order_id" ), rs.getString("username"),rs.getString( "name" ), rs.getInt( "quantity" ) , rs.getInt("price") , rs.getDouble("total") );

	            	  history.add(transaction);
	            }
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
	        finally
	        {
	            try
	            {
	                if( c != null ) c.close();
	            }
	            catch( SQLException e )
	            {
	                throw new ServletException( e );
	            }
	        }
	       //request.setAttribute( "total", itemCount );
	        request.setAttribute( "purchased", history );

	        request.getRequestDispatcher( "/WEB-INF/History.jsp" ).forward(
	            request, response );		
	}

	
	
	
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the parameters from the transaction made from the user stores them on the 
		String order_id=request.getParameter("order_id");
		String user = request.getParameter("username");
		String name = request.getParameter("name");
    	String quantity = request.getParameter("quantity");
    	String price = request.getParameter("price");
    	String total = request.getParameter("total");
    	String error = " ";
        Connection c = null;
        try
        {
        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu53";
			String username = "cs3220stu53";
			String password = "!.S60cVG" ;
			String sql = "insert into history (order_id,username,name,quantity,price,price) values(?,?,?,?,?,?)";	
			
			
            if(user == null && user.trim().isEmpty() ||order_id==null && order_id.trim().isEmpty() || total == null || total.trim().isEmpty() && name.trim().isEmpty() || name == null && quantity.trim().isEmpty() || quantity == null && price.trim().isEmpty() || price == null )
        	{	 error = "Cannot be left blank. ";
        		request.setAttribute("error", error);
        	}else
        	{
        			c = DriverManager.getConnection(url,username,password);
        			PreparedStatement pstmt = c.prepareStatement(sql);
        			pstmt.setString(1,order_id);
    				pstmt.setString(2,user);
    				pstmt.setString(3,name);
        			pstmt.setString(4,quantity);
        			pstmt.setString(5,price);
        			pstmt.setString(6, total);
    				pstmt.executeUpdate();	
        	}
          
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
        finally
        {
            try
            {
                if( c != null ) c.close();
            }
            catch( SQLException e )
            {
                throw new ServletException( e );
            }
        }

		doGet(request, response);
	}

}
