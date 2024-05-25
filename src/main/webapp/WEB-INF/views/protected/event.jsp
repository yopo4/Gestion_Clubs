<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <!-- Your CSS links and other head tags here -->
    <jsp:include page="/WEB-INF/views/layouts/header.jsp"/>
    <style>
        .carousel-control-prev-icon,
        .carousel-control-next-icon {
            filter: invert(1) sepia(1) saturate(5) hue-rotate(180deg); /* Adjust these values to change the color */
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/layouts/navbar.jsp"/>
<div class="container mt-5">
    <div class="mb-3">
        <div class="d-flex flex-column justify-content-center align-items-center">
            <h4>${event.titre}</h4>
            <p><strong>Start Date:</strong> ${event.dateDebut}</p>
            <p><strong>End Date:</strong> ${event.dateFin}</p>
            <p class="mb-2"><strong>Description:</strong> ${event.description}</p>
            <p><strong>Members: </strong> ${eventMembersCount}</p>
            <form action="${pageContext.request.contextPath}/AttendEventController?id=${event.id_evenement}" method="post">
                <button class="btn btn-dark">Attend</button>
            </form>
        </div>
    </div>
</div>

<!-- Your other HTML content here -->

<jsp:include page="/WEB-INF/views/layouts/footer.jsp"/>
</body>
</html>
