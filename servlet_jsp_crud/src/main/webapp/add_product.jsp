<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add or Update Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #343a40;
        }
        form {
            max-width: 500px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin: 10px 0 5px;
            color: #495057;
        }
        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ced4da;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            margin-top : 20px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
       		
            background-color: #0056b3;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h2>${product != null ? "Update" : "Add"} Product</h2>
    
    <form action="addProduct" method="post">
        <!-- Hidden field to store product ID for update functionality -->
        <input type="hidden" name="id" value="${product != null ? product.id : ''}">

        <label for="productName">Product Name:</label>
        <input type="text" id="productName" name="productName" value="${product != null ? product.name : ''}" required>

        <label for="price">Price:</label>
        <input type="number" step="0.01" id="price" name="price" value="${product != null ? product.price : ''}" required>

        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="4" required>${product != null ? product.description : ''}</textarea>

        <input type="submit" value="${product != null ? "Update" : "Add"} Product">
    </form>
    
    <a href="showProduct">Back to Product List</a>
</body>
</html>
