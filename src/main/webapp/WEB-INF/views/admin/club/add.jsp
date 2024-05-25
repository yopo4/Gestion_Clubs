<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Club</title>
    <jsp:include page="/WEB-INF/views/layouts/header.jsp"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/layouts/navbar.jsp"/>
    <div class="container mt-5">
        <h1>Add Club</h1>
        <form action="${pageContext.request.contextPath}/admin/clubs/add" method="post">
            <div class="form-group">
                <label for="clubName">Club Name</label>
                <input type="text" class="form-control" id="clubName" name="clubName" required>
            </div>
            <div class="form-group">
                <label for="gerantId">Gerant</label>
                <select class="form-control" name="gerantId" id="gerantId" required>
                    <option value="0" selected>--Select a manager--</option>
                    <option value="0">No manager</option>
                    <c:forEach var="user" items="${noManagerUsers}">
                        <option value="${user.id_user}">${user.nom}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Add Club</button>
        </form>
    </div>
    <jsp:include page="/WEB-INF/views/layouts/footer.jsp"/>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
