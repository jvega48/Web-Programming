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

@WebServlet(urlPatterns = "/Inventory", loadOnStartup = 1)
public class Inventory extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Inventory()
    {
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

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	//List<TodoBean> entries = new ArrayList<TodoBean>();
        ArrayList<StoreItems> items = new ArrayList<StoreItems>();
        //int itemCount = 0;
        Connection c = null;
        
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu53";
            String username = "cs3220stu53";
            String password = "!.S60cVG";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from store" );

            while( rs.next() )
            {
                StoreItems item = new StoreItems( rs.getInt( "id" ),rs.getString( "name" ), rs.getString( "description" ) , rs.getInt("quantity") , rs.getDouble("price") );

            	  items.add(item);
            	  //itemCount++;
            	  
              
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
        request.setAttribute( "items", items );

        request.getRequestDispatcher( "/WEB-INF/Manager.jsp" ).forward(
            request, response );
    }

    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	
    	//int id = Integer.parseInt(request.getParameter("id"));
    	String name = request.getParameter("name");
    	String description = request.getParameter("description");
    	String quantity = request.getParameter("quantity");
    	String price = request.getParameter("price");
    	String error = " ";
        Connection c = null;
        try
        {
        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu53";
			String username = "cs3220stu53";
			String password = "!.S60cVG" ;
			String sql = "insert into store (name,description,quantity,price) values(?,?,?,?)";	
			
			
            if(description == null || description.trim().isEmpty() && name.trim().isEmpty() || name == null && quantity.trim().isEmpty() || quantity == null && price.trim().isEmpty() || price == null )
        	{	 error = "Cannot be left blank. ";
        		request.setAttribute("error", error);
        	}else
        	{
        			c = DriverManager.getConnection(url,username,password);
        			PreparedStatement pstmt = c.prepareStatement(sql);
    				pstmt.setString(1,name);
        			pstmt.setString(2,description);
    				pstmt.setString(3,quantity);
        			pstmt.setString(4,price);
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

    	
        doGet( request, response );
    }

}