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
        <h1 class="mb-4">Welcome to the Homepage</h1>
        <h2 class="mb-4">Top 3 Most Registered Clubs:</h2>
        <div id="carouselExampleIndicatorsClubs" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <c:forEach var="event" items="${events}" varStatus="loop">
                    <li data-target="#carouselExampleIndicatorsClubs" data-slide-to="${loop.index}"
                        class="${loop.index == 0 ? 'active' : ''}"></li>
                </c:forEach>
            </ol>
            <div class="carousel-inner">
                <div class="mb-3">
                    <c:forEach var="club" items="${clubs}" varStatus="loop">
                        <div class="carousel-item ${loop.index == 0 ? 'active' : ''} text-center">
                            <div class="d-flex flex-column justify-content-center align-items-center">
                                <a href="${pageContext.request.contextPath}/club?id_club=${club.idClub}" class="text-decoration-none text-dark">
                                    <h4>${club.nom}</h4>
                                    <p><strong>Manager :</strong> ${gerantNames.get(club.idClub)}</p>
                                    <p class="mb-2"><strong>Members :</strong> ${clubMembersCount.get(club.idClub)}</p>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicatorsClubs" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicatorsClubs" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
    <div class="container mt-5">
        <h2 class="mb-4">Top 3 Most Registered Events:</h2>
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <c:forEach var="event" items="${events}" varStatus="loop">
                    <li data-target="#carouselExampleIndicators" data-slide-to="${loop.index}"
                        class="${loop.index == 0 ? 'active' : ''}"></li>
                </c:forEach>
            </ol>
            <div class="carousel-inner">
                <c:forEach var="event" items="${events}" varStatus="loop">
                    <div class="carousel-item ${loop.index == 0 ? 'active' : ''} text-center">
                        <div class="d-flex flex-column justify-content-center align-items-center">
                            <a href="${pageContext.request.contextPath}/event?id_event=${event.id_evenement}" class="text-decoration-none text-dark">
                                <h4>${event.titre}</h4>
                                <p><strong>Start Date:</strong> ${event.dateDebut}</p>
                                <p><strong>End Date:</strong> ${event.dateFin}</p>
                                <p><strong>Description:</strong> ${event.description}</p>
                                <p class="mb-2"><strong>Members:</strong> ${eventMembersCount.get(event.id_evenement)}</p>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>

    <!-- Your other HTML content here -->

    <jsp:include page="/WEB-INF/views/layouts/footer.jsp" />
</body>
</html>
