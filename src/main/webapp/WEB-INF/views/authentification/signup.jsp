<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Signup</title>
    <!-- Bootstrap CSS -->
    <jsp:include page="/WEB-INF/views/layouts/header.jsp"/>
    <style>
        body, html {
            height: 100%;
        }
    </style>
</head>
<body class="align-items-center justify-content-center bg-light">
<div class="container-fluid">
    <jsp:include page="/WEB-INF/views/layouts/navbar.jsp"/>
    <div class="d-flex align-items-center justify-content-center bg-light vh-100">
        <div class="card shadow-sm w-100" style="max-width: 24rem;">
            <div class="card-body">
                <div class="mb-4">
                    <h3 class="h5 font-weight-bold">Signup</h3>
                </div>
                <!-- Display error message if exists -->
                <c:if test="${not empty confirmPasswordDoesntMatch}">
                    <div class="alert alert-danger" role="alert">
                        ${confirmPasswordDoesntMatch}
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/signup" method="post">
                    <div class="form-group">
                        <label for="name" class="text-sm font-weight-medium">Name</label>
                        <input type="text" class="form-control <c:if test='${not empty nameErrorMessage}'>is-invalid</c:if>" id="name" name="name" placeholder=""
                               >
                        <c:if test="${not empty nameErrorMessage}">
                            <p class="text-danger">${nameErrorMessage}</p>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="email" class="text-sm font-weight-medium">Email</label>
                        <input type="email" class="form-control <c:if test='${not empty emailErrorMessage}'>is-invalid</c:if>" id="email" name="email" placeholder="m@example.com"
                               >
                        <c:if test="${not empty emailErrorMessage}">
                            <p class="text-danger">${emailErrorMessage}</p>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="password" class="text-sm font-weight-medium">Password</label>
                        <input type="password" class="form-control <c:if test='${not empty passwordErrorMessage}'>is-invalid</c:if>" id="password" name="password" >
                        <c:if test="${not empty passwordErrorMessage}">
                            <p class="text-danger">${passwordErrorMessage}</p>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword" class="text-sm font-weight-medium">Confirm password</label>
                        <input type="password" class="form-control <c:if test='${not empty confirmPasswordErrorMessage}'>is-invalid</c:if>" id="confirmPassword" name="confirmPassword" >
                        <c:if test="${not empty confirmPasswordErrorMessage}">
                            <p class="text-danger">${confirmPasswordErrorMessage}</p>
                        </c:if>
                    </div>
                    <button type="submit" class="btn btn-dark btn-block mb-2">Signup</button>
                    <a class="text-dark" href="${pageContext.request.contextPath}/login">Already have an account ?</a>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Include footer -->
<jsp:include page="/WEB-INF/views/layouts/footer.jsp"/>
</body>
</html>
