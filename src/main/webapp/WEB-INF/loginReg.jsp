<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <title>Login Registration Index</title>

    <style>
        form{
            margin: 30px 30px 30px 30px;
        }
        h1{
            margin: 30px 30px 0 30px;
        }
        .login, .signup{
            margin: 20px 20px 20px 20px;
            display: inline-block;
            border: 2px solid black;
        }
        .login{
            vertical-align: top;
        }
        button{
            margin-top: 20px;
            display: flex;
            justify-content: center;
        }
        .error{
        	color: red;
        	text-align: center;
        }

    </style>
</head>
<body>
    <div class="container-fluid">
        <div class="signup">
			<h1>Register</h1>

            <form:form action="/register" method="post" modelAttribute="user">
            	<input type="hidden" name="register"/>
				<div class="form-group">
                    <form:label path="username" class="col-form-label">Username</form:label>
                    <form:input path="username" type="text" class="form-control" placeholder="Enter username" name="username"/>
                    <form:errors path="username" cssClass="error"/>
                </div>
				<div class="form-group">
                    <form:label path="email" class="col-form-label">Email Address</form:label>
                    <form:input path="email" type="text" class="form-control" placeholder="Enter email" name="email"/>
                    <form:errors path="email" cssClass="error"/>
                </div>
      			<div class="form-group">              
                    <form:label path="password" class="col-form-label">Password</form:label>
                    <form:input path="password" type="password" class="form-control" placeholder="Enter password" name="password"/>
                    <form:errors path="password" cssClass="error"/>
                </div>
				<div class="form-group">
                    <form:label path="passwordConfirmation" class="col-form-label">Confirm Password</form:label>
                    <form:input path="passwordConfirmation" type="password" class="form-control" placeholder="Confirm password" name="passwordConfirmation"/>
                    <form:errors path="passwordConfirmation" cssClass="error"/>
                </div>

	            <button type="submit" class="btn btn-primary">Submit</button>
            </form:form>
        </div>
        <div class="login">
			<c:if test="${logoutMessage != null}">
				<p class="error mt-3">${logoutMessage}</p>
			</c:if>
			<h1>Log In</h1>

			<form action='/login' method='post'>
            <p class="error"><c:out value="${error}"/></p>                  
            	<input type="hidden" name="login"/>
                <div class="form-group">
                    <label class="col-form-label">Email Address</label>
                    <input type="text" class="form-control" placeholder="Enter email" name="username"/>

                    <label class="col-form-label">Password</label>
                    <input type="password" class="form-control" placeholder="Enter password" name="password"/>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<c:if test="${errorMessage != null}">
					<p class="error">${errorMessage}</p>
				</c:if>
				<button type="submit" class="btn btn-primary">Submit</button> 
            </form>
        </div>
    </div>
</body>
</html>