import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/showProduct", "/updateProduct", "/deleteProduct"})
public class ProductListServlet extends HttpServlet {
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
        String endPoint = req.getServletPath();
        
        // Handle show product
        if (endPoint.equals("/showProduct")) {
            List<Product> productList = connectivity.getAllProduct();
            req.setAttribute("productList", productList);
            req.getRequestDispatcher("show_product.jsp").forward(req, resp);
        }
        
        // Handle product update
        else if (endPoint.equals("/updateProduct")) {
            String id = req.getParameter("id");

            if (id != null) {
                try {
                    int productId = Integer.parseInt(id);
                    
                    // Fetch product by ID from database
                    Product product = connectivity.getProductById(productId);
                    
                    if (product != null) {
                        // If product exists, forward to the add product form (for update)
                        req.setAttribute("product", product);
                        req.getRequestDispatcher("add_product.jsp").forward(req, resp);
                    } else {
                        // If product not found
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                    }

                } catch (NumberFormatException e) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID");
                } catch (SQLException e) {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
                }
            } else {
                resp.sendRedirect("showProduct");
            }
        }
    }

    // Handle POST for deleteProduct
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String endPoint = request.getServletPath();
        
        // Handle product deletion
        if (endPoint.equals("/deleteProduct")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                connectivity.deleteProduct(id);
                resp.sendRedirect("showProduct");
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
            }
        }
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
