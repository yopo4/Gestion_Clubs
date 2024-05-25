<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Events</title>
    <jsp:include page="/WEB-INF/views/layouts/header.jsp"/>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/layouts/navbar.jsp"/>
    <div class="container mt-5">
        <h1 class="mb-4">Events Management</h1>
        <div class="mb-3">
            <a href="${pageContext.request.contextPath}/admin/events/add" class="btn btn-dark">Add Event</a>
        </div>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">Event Title</th>
                    <th scope="col">Start Date</th>
                    <th scope="col">End Date</th>
                    <th scope="col">Description</th>
                    <th scope="col">Number of Members</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="event" items="${events}">
                    <tr>
                        <td>${event.titre}</td>
                        <td>${event.dateDebut}</td>
                        <td>${event.dateFin}</td>
                        <td>${event.description}</td>
                        <td>${eventMembersCount[event.id_evenement]}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/events/update?id=${event.id_evenement}" class="btn btn-primary">Edit</a>
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
