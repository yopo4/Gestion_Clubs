<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Members</title>
    <!-- Your CSS links and other head tags here -->
    <jsp:include page="/WEB-INF/views/layouts/header.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/layouts/navbar.jsp"/>
<div class="container mt-5">
    <div class="mb-3">
        <div class="d-flex flex-column justify-content-center align-items-center">
            <h4>${club.nom}</h4>
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="member" items="${members}" varStatus="loop">
                        <tr>
                            <td>${member.idMembre}</td>
                            <td>${member.nom}</td>
            <c:if test="${officials == true}">
                            <td>
                                <form method = "post" action="${pageContext.request.contextPath}/members/delete">
                                    <input type="text" name="club" value="${club.idClub}" hidden/>
                                    <input type="text" name="member" value="${member.idMembre}" hidden/>
                                    <button type="submit" class="badge badge-danger">Delete</button>
                                </form>
                            </td>
            </c:if>
            <c:if test="${officials == false}">
                            <td><a href="#" class="badge badge-secondary">Accept</a><a href="#" class="badge badge-danger">Refuse</a></td>
            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
        </div>
    </div>
</div>

<!-- Your other HTML content here -->

<jsp:include page="/WEB-INF/views/layouts/footer.jsp"/>
</body>
</html>
