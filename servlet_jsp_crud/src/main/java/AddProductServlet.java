import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

    // Handle GET request to pre-fill the form for update
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	String idStr = req.getParameter("id");
        
        System.out.println(idStr);

        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Product product = connectivity.getProductById(id); // Fetch product details by ID

                if (product != null) {
                    // Set product attributes to pre-populate the form for update
                    req.setAttribute("product", product);
                }
            } catch (NumberFormatException | SQLException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID");
                return;
            }
        }

        // Forward to the JSP form (add_product.jsp)
        req.getRequestDispatcher("add_product.jsp").forward(req, resp);
    }

    // Handle POST request for adding/updating products
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productName");
        String priceStr = req.getParameter("price");
        String description = req.getParameter("description");
        String idStr = req.getParameter("id");

        if (productName == null || priceStr == null || description == null || productName.isEmpty() || priceStr.isEmpty() || description.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input");
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);

            if (idStr == null || idStr.isEmpty()) {
                // Add new product
                connectivity.insertProduct(productName, price, description);
            } else {
                // Update existing product
                int id = Integer.parseInt(idStr);
                connectivity.updateProduct(id, productName, price, description);
            }

            resp.sendRedirect("showProduct");

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid price format");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
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
