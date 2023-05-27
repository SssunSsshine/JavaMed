<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>Profile Registration</title>
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
</head>
<body>
  <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/user/page">Home</a>
  <a class="nav-link active" href="<%=request.getContextPath()%>/sign-out">Sign Out</a>
	<div class="container col-md-5">
        <div class="card" style = "background:#b0cef969;">
    	    <div class="card-body">
                <caption>
                    <h2>
                         Information about your products
                    </h2>
                </caption>

                <c:if test="${products != null}">
                    <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">Information</th>
                            <th scope="col"></th>
                          </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="product" items="${products}">
                                <tr>
                                    <td>
                                        <p>ID: ${product.id}</p>
                                        <p>Name: ${product.name}</p>
                                        <p>Price: ${product.price}</p>
                                    </td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/product/edit?id=${product.id}" class="btn btn-success" role="button">Edit</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                      </table>
                </c:if>
            </div>
        </div>
	</div>
</body>
</html>