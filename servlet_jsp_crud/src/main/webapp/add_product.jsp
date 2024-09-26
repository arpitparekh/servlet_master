<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: white;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin: 10px 0 5px;
            font-weight: bold;
            color: #555;
        }

        input[type="text"],
        input[type="number"],
        textarea {
            padding: 12px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 6px;
            margin-bottom: 15px;
            transition: border-color 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="number"]:focus,
        textarea:focus {
            border-color: #2196F3;
            outline: none;
        }

        input[type="submit"] {
            padding: 12px;
            font-size: 1em;
            background-color: #2196F3;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #1976D2;
        }

        /* Responsive Design */
        @media (max-width: 600px) {
            .container {
                width: 90%;
                margin: 20px auto;
            }

            input[type="text"],
            input[type="number"],
            textarea {
                font-size: 0.9em;
            }

            input[type="submit"] {
                font-size: 0.9em;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Add Product</h1>
        <form action="addProduct" method="post">
            <label for="productName">Product Name:</label>
            <input type="text" id="productName" name="productName" required>

            <label for="price">Price:</label>
            <input type="number" id="price" name="price" step="0.01" required>

            <label for="description">Product Description:</label>
            <textarea id="description" name="description" rows="4" required></textarea>

            <input type="submit" value="Add Product">
        </form>
    </div>
</body>
</html>
