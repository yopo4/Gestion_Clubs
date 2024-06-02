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
            <h4>${club.nom}</h4>
            <p><strong>Manager :</strong> ${gerantNames}</p>
            <p class="mb-2"><strong>Members :</strong> ${clubMembersCount}</p>
            <c:if test='${isPartOfClub == false}'>
                <form action="${pageContext.request.contextPath}/AttendClubController?id=${club.idClub}" method="post">
                    <button class="btn btn-dark">Join</button>
                </form>
            </c:if>
            <c:if test='${isGerantOfClub}'>
                <p class="mb-2"><strong>Requests :</strong> ${clubRequestsCount}</p>
                <div class="grid">
                    <a href="${pageContext.request.contextPath}/members?id_club=${club.idClub}"><button class="btn btn-dark">Members</button></a>
                    <a href="${pageContext.request.contextPath}/requests?id_club=${club.idClub}"><button class="btn btn-dark">Requests to join</button></a>
                </div>
            </c:if>
        </div>
    </div>
</div>

<!-- Your other HTML content here -->

<jsp:include page="/WEB-INF/views/layouts/footer.jsp"/>
</body>
</html>
