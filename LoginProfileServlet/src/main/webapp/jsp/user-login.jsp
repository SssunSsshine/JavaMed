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
	<div class="container col-md-5">
		<div class="card" style = "background:#b0cef969;">
			<div class="card-body">
			    <form action="<%=request.getContextPath()%>/sign-in" method="post">
                    <fieldset class="form-group">
                        <label>Login</label> <input type="email"
                                class="form-control"
                            name="login" placeholder="email" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Password</label> <input type="password"
                                class="form-control"
                            name="password" required="required">
                    </fieldset>
                    <c:if test="${error != null}">
                        <font color="red"> <c:out value='${error}' /></font>
                    </c:if>
                    <div class="text-center">
                        <button type="submit" class="btn btn-success">Sign in</button>
                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success" role="button">Sign up</a>
                    <div class="text-center"></div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>