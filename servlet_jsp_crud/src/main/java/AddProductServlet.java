import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String id1= (String)req.getAttribute("id");
    	String id2 = (String)req.getParameter("id");
    	
    	System.out.println(id1);
    	System.out.println(id2);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String priceStr = request.getParameter("price");
        String description = request.getParameter("description");

        if (productName == null || priceStr == null || description == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
            return;
        }

        try {
        	
            double price = Double.parseDouble(priceStr);  // Convert price to double
            
            connectivity.insertProduct(productName, price, description);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("add_product.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid price format");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

    @Override
    public void destroy() {
        // Close the database connection when the servlet is destroyed
        try {
            if (connectivity != null) {
                connectivity.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
