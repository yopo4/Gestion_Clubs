<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Home</title>
    <jsp:include page="/WEB-INF/views/layouts/header.jsp"/>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/layouts/navbar.jsp"/>
    <div class="container d-flex flex-column justify-content-center align-items-center mt-5">
        <h1 class="mb-4">Welcome to the Admin Page</h1>
        <p class="mb-4">Choose to manage:</p>
        <div class="d-flex">
            <a class="btn btn-primary mr-2" href="${pageContext.request.contextPath}/admin/clubs/show">Clubs</a>
            <span class="mx-2">or</span>
            <a class="btn btn-primary ml-2" href="${pageContext.request.contextPath}/admin/events/show">Events</a>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/layouts/footer.jsp"/>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
