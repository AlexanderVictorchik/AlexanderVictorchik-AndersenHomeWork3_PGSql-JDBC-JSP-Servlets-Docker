<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>People Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: fuchsia">
        <div>
            <a href="https://github.com/AlexanderVictorchik" class="navbar-brand"> Phone
                Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">PhonesList</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">

    <div class="container">
        <h3 class="text-center">List of Phones</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                New Phone</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Price</th>
                <th>Model</th>
                <th>Vendor Name</th>
                <th>Vendor Site</th>
                <th>Choose Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="phone" items="${listPhone}">

                <tr>
                    <td><c:out value="${phone.id}" /></td>
                    <td><c:out value="${phone.price}" /></td>
                    <td><c:out value="${phone.model}" /></td>
                    <td><c:out value="${phone.vendor_name}" /></td>
                    <td><c:out value="${phone.vendor_site}" /></td>
                    <td><a href="edit?id=<c:out value='${phone.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="delete?id=<c:out value='${phone.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
