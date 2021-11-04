<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Phone Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: indigo">
        <div>
            <a href="https://github.com/AlexanderVictorchik" class="navbar-brand"> Phone Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Phones</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${phone != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${phone == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${phone != null}">
                                Edit Phone
                            </c:if>
                            <c:if test="${phone == null}">
                                Add New Phone
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${phone != null}">
                        <input type="hidden" name="id" value="<c:out value='${phone.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Phone Model</label> <input type="text"
                                                          value="<c:out value='${phone.model}' />" class="form-control"
                                                          name="model">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Phone Price</label> <input type="text"
                                                          value="<c:out value='${phone.price}' />" class="form-control"
                                                          name="price" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Phone Vendor</label> <input type="text"
                                                           value="<c:out value='${phone.vendor_name}' />" class="form-control"
                                                           name="vendor_name">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Vendor Site</label> <input type="text"
                                                          value="<c:out value='${phone.vendor_site}' />" class="form-control"
                                                          name="vendor_site">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
