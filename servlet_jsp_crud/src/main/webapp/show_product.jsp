<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Product List</title>
    <style>
        table {
            width: 60%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

    <h2 style="text-align: center;">Product List</h2>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Description</th>
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
                <form action="addProduct" method="POST" style="display:inline;">
                    <input type="hidden" name="id" value="${product.id}" />
                    <input type="submit" value="Update" />
                </form>
                <form action="deleteProduct" method="POST" style="display:inline;" onsubmit="return confirm('Do you really want to delete?')">
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
