<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
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
                    <h3 class="h5 font-weight-bold">Login</h3>
                    <!-- <p class="text-muted">Entrez votre email ci-dessous pour vous connecter à votre compte.</p> -->
                </div>
                <!-- Display error message if exists -->
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger" role="alert">
                        ${errorMessage}
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="form-group">
                        <label for="email" class="text-sm font-weight-medium">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="m@example.com"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="password" class="text-sm font-weight-medium">Password</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-dark btn-block">Login</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Include footer -->
<jsp:include page="/WEB-INF/views/layouts/footer.jsp"/>
</body>
</html>
