<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Clubs</title>
    <jsp:include page="/WEB-INF/views/layouts/header.jsp"/>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/layouts/navbar.jsp"/>
    <div class="container mt-5">
        <h1 class="mb-4">Clubs Management</h1>
        <div class="mb-3">
            <a href="${pageContext.request.contextPath}/admin/clubs/add" class="btn btn-dark">Add Club</a>
        </div>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">Club Name</th>
                    <th scope="col">Gerant Name</th>
                    <th scope="col">Number of Members</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="club" items="${clubs}">
                    <tr>
                        <td>${club.nom}</td>
                        <c:choose>
                            <c:when test="${not empty gerantNames[club.idClub]}">
                                <td>${gerantNames[club.idClub]}</td>
                            </c:when>
                            <c:otherwise>
                                <td>No manager</td>
                            </c:otherwise>
                        </c:choose>
                        <td>${clubMembersCount[club.idClub]}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/clubs/update?id=${club.idClub}" class="btn btn-primary">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <jsp:include page="/WEB-INF/views/layouts/footer.jsp"/>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
