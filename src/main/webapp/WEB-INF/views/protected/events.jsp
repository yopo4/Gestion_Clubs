<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Events</title>
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
    <div class="row">
        <c:forEach var="event" items="${events}" varStatus="loop">
            <div class="col-4">
                <a href="${pageContext.request.contextPath}/event?id_event=${event.id_evenement}" class="text-decoration-none text-dark">
                    <div class="card shadow mb-2" style="height: 250px;overflow: auto">
                        <div class="card-body">
                            <h3>${event.titre}</h3>
                            <p><strong>Start Date: </strong>${event.dateDebut}</p>
                            <p><strong>End Date: </strong>${event.dateFin}</p>
                            <p><strong>Description: </strong>${event.description}</p>
                            <p><strong>Members: </strong> ${clubMembersCount.get(event.id_evenement)}</p>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>

<!-- Your other HTML content here -->

<jsp:include page="/WEB-INF/views/layouts/footer.jsp"/>
</body>
</html>
