<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Club</title>
    <jsp:include page="/WEB-INF/views/layouts/header.jsp"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/layouts/navbar.jsp"/>
    <div class="container mt-5">
        <h1>Update Club</h1>
        <form action="${pageContext.request.contextPath}/admin/clubs/update" method="post">
            <input type="hidden" name="clubId" value="${club.idClub}">
            <div class="form-group">
                <label for="clubName">Club Name</label>
                <input type="text" class="form-control" id="clubName" name="clubName" value="${club.nom}" required>
            </div>
            <div class="form-group">
                <label for="gerantId">Gerant ID</label>
                <p class="text-muted">Previous manager: ${manager.nom}</p>
                <select class="form-control" name="gerantId" id="gerantId" required>
                    <option value="0">--Select a new manager--</option>
                    <option value="0">No manager</option>
                    <c:forEach var="membre" items="${noManagerMembers}">
                        <option value="${membre.idMembre}">${membre.nom}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Update Club</button>
        </form>
    </div>
    <jsp:include page="/WEB-INF/views/layouts/footer.jsp"/>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
