<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Product List</title>
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
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        th, td {
            border: 1px solid #dee2e6;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        td {
            background-color: #f8f9fa;
        }
        tr:hover td {
            background-color: #e9ecef;
        }
        .button {
            display: block;
            width: 150px;
            margin: 20px auto;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
        }
        .button:hover {
            background-color: #218838;
        }
        .action-form {
            display: inline;
        }
    </style>
</head>
<body>

<h2>Product List</h2>

<a href="addProduct" class="button">Add Product</a>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="product" items="${productList}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.description}</td>
                <td>
                    <!-- Update Button -->
                    <form action="addProduct" method="GET" class="action-form">
                        <input type="hidden" name="id" value="${product.id}" />
                        <input type="submit" value="Update" />
                    </form>
                    
                    <!-- Delete Button -->
                    <form action="deleteProduct" method="POST" class="action-form" onsubmit="return confirm('Do you really want to delete?')">
                        <input type="hidden" name="id" value="${product.id}" />
                        <input type="submit" value="Delete" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
