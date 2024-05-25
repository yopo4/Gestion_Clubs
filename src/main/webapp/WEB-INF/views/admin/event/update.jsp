<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Event</title>
    <jsp:include page="/WEB-INF/views/layouts/header.jsp"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/views/layouts/navbar.jsp"/>
    <div class="container mt-5">
        <h1>Update Event</h1>
        <form action="${pageContext.request.contextPath}/admin/events/update" method="post">
            <input type="hidden" name="eventId" value="${event.id_evenement}">
            <div class="form-group">
                <label for="eventName">Event Name</label>
                <input type="text" class="form-control" id="eventName" name="eventName" value="${event.titre}" required>
            </div>
            <div class="form-group">
                <label for="eventDescription">Description</label>
                <textarea class="form-control" id="eventDescription" name="eventDescription" rows="3" required>${event.description}</textarea>
            </div>
            <div class="form-group">
                <label for="startDate">Start Date</label>
                <input type="date" class="form-control" id="startDate" name="startDate" value="${event.dateDebut}" required>
            </div>
            <div class="form-group">
                <label for="endDate">End Date</label>
                <input type="date" class="form-control" id="endDate" name="endDate" value="${event.dateFin}" required>
            </div>
            <div class="form-group">
                <label for="clubId">Club</label>
                <select class="form-control" id="clubId" name="clubId" required>
                    <option value="">--Select a club--</option>
                    <c:forEach var="club" items="${clubs}">
                        <option value="${club.idClub}" <c:if test="${club.idClub == event.id_club}">selected</c:if>>${club.nom}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Update Event</button>
        </form>
    </div>
    <jsp:include page="/WEB-INF/views/layouts/footer.jsp"/>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
