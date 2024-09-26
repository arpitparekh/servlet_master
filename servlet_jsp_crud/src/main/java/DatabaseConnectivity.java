import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnectivity {

    public Connection connection = null;
    public Statement statement = null;

    public DatabaseConnectivity() throws SQLException, ClassNotFoundException {
        System.out.println("Start");
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Start2");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_database", "root", "Walden0042$$");
        System.out.println("Start3");
        if (connection == null) {
            System.out.println("Not Connected");
        } else {
            System.out.println("Connected to the database successfully!");
        }

        statement = connection.createStatement();
    }

    // Create table if it doesn't exist
    public void createTable() {
        try {
            System.out.println("Attempting to create table...");
            statement.execute("CREATE TABLE IF NOT EXISTS product (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), price FLOAT, description VARCHAR(255))");
            System.out.println("Table is Created !");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Insert product data into the database
    public void insertProduct(String name, double price, String description) {
    	
        String query = "INSERT INTO product (name, price, description) VALUES (?, ?, ?)";
        
        try {
        	
        	PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setString(3, description);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Product inserted successfully!");
            }
            
        } catch (SQLException e) {
            System.out.println("Error inserting product: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public List<Product> getAllProduct(){
    	
    	List<Product> list = new ArrayList<>();
    	
    	String sql = "select * from product";
    	
    	try {
			ResultSet resultSet =  statement.executeQuery(sql);
			
			while(resultSet.next()) {
				
				Product p = new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"));
				list.add(p);
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    	return list;	
    }
    
    public void deleteProduct(int id) {
    	String sql = "delete from product where id="+id;
    	try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    }
    
    public void updateProduct(int id,String name,double price,String description) {
        String sql = "UPDATE product SET name = '" + name + "', price = " + price + ", description = '" + description + "' WHERE id = " + id;    	
    	System.out.println(sql);
    	
    	List<Product> list = new ArrayList<>();
    	try {
    		
    		statement.executeUpdate(sql);
    		System.out.println("Product Updated Successfully");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    

    public void close() throws SQLException {
        if (statement != null) statement.close();
        if (connection != null) connection.close();
    }
}
