<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <!-- Vos liens CSS et autres balises head ici -->
    <jsp:include page="/WEB-INF/views/layouts/header.jsp"/>
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Bienvenue sur la page d'accueil</h1>

        <h2 class="mb-3">Top 3 des événements les plus inscrits :</h2>
        <ul class="list-group">
            <c:forEach var="event" items="${events}">
                <li class="list-group-item">
                    <h4 class="mb-1">${event.titre}</h4>
                    <p class="mb-1"><strong>Date de début :</strong> ${event.dateDebut}</p>
                    <p class="mb-1"><strong>Date de fin :</strong> ${event.dateFin}</p>
                    <p class="mb-1"><strong>Description :</strong> ${event.description}</p>
                    <!-- Ajoutez d'autres informations sur l'événement si nécessaire -->
                </li>
            </c:forEach>
        </ul>
    </div>

    <!-- Vos autres contenus HTML ici -->

    <jsp:include page="/WEB-INF/views/layouts/footer.jsp" />
</body>
</html>
