import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/showProduct","/updateProduct","/deleteProduct"})
public class ProductListServlet extends HttpServlet{
	
	DatabaseConnectivity connectivity;

    @Override
    public void init() throws ServletException {
        try {
            connectivity = new DatabaseConnectivity();
            connectivity.createTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Failed to initialize Database Connectivity", e);
        }
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		connectivity.updateProduct(9, "Book", 2000, "Good Book");
		
		List<Product> productList = connectivity.getAllProduct();
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("show_product.jsp").forward(request, resp);
        
        
        
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
			String endPoint = request.getServletPath();
        
      
        	int id = Integer.parseInt(request.getParameter("id"));
    		connectivity.deleteProduct(id);
    		
    		resp.sendRedirect("showProduct");

		
	}
	
	@Override
    public void destroy() {
        try {
            if (connectivity != null) {
                connectivity.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}