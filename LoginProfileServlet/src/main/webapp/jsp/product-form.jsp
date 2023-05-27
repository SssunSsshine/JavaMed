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
		<div class="card"  style = "background:#b0cef969;">
			<div class="card-body">
				<c:if test="${product != null}">
					<form action="<%=request.getContextPath()%>/product/update" method="post">
				</c:if>
				<c:if test="${product == null}">
					<form action="<%=request.getContextPath()%>/product/insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${product != null}">
            			    Edit Product
            		    </c:if>
						<c:if test="${product == null}">
            			    Insert New Product
            		    </c:if>
					</h2>
				</caption>

				<c:if test="${product != null}">
                    <input type="hidden" name="id" value="<c:out value='${product.id}' />" />


                <fieldset class="form-group">
					<label>Product Name</label> <input type="text"
						value="<c:out value='${product.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Product Price</label> <input type="text"
						value="<c:out value='${product.price}' />" class="form-control"
						name="price" required="required">
				</fieldset>
                </c:if>
                <c:if test="${product == null}">
                <fieldset class="form-group">
                    <label>Product Name</label> <input type="text"
                        value="<c:out value='${name}' />" class="form-control"
                        name="name" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label>Product Price</label> <input type="text"
                        value="<c:out value='${price}' />" class="form-control"
                        name="price" required="required">
                </fieldset>
                </c:if>
                <c:if test="${error != null}">
                    <font color="red"> <c:out value='${error}' /></font>
                </c:if>
                <div class="text-center">
				    <button type="submit" class="btn btn-success">Save</button>
				    <c:if test="${product != null}">
				        <a href="<%=request.getContextPath()%>/product/delete?id=${product.id}" class="btn btn-danger" role="button">Delete</a>
                    </c:if>
                </div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>