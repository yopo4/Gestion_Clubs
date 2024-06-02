<%@page language="java" contentType="text/html; charset=UTF-8"
        isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03"
            aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand mr-6" href="<c:choose>
           <c:when test="${sessionScope.user != null && sessionScope.user.getRole() == 'admin'}">${pageContext.request.contextPath}/admin/home</c:when>
           <c:otherwise>${pageContext.request.contextPath}/home</c:otherwise>
       </c:choose>">
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
             stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="h-6 w-6">
            <path d="m8 3 4 8 5-5 5 15H2L8 3z"></path>
        </svg>
    </a>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/home"
                    <c:if test="${sessionScope.user != null && sessionScope.user.getRole()}">hidden</c:if>>Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:choose>
                    <c:when test="${sessionScope.user != null && sessionScope.user.getRole()}">${pageContext.request.contextPath}/admin/clubs/show</c:when>
                    <c:otherwise>${pageContext.request.contextPath}/clubs</c:otherwise>
                </c:choose>">Clubs <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:choose>
                    <c:when test="${sessionScope.user != null && sessionScope.user.getRole()}">${pageContext.request.contextPath}/admin/events/show</c:when>
                    <c:otherwise>${pageContext.request.contextPath}/events</c:otherwise>
                </c:choose>">Events <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <c:if test="${sessionScope.user != null && !sessionScope.user.getRole()}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/myClubs">My club <span class="sr-only">(current)</span></a>
                </c:if>
            </li>
        </ul>
        <c:if test="${sessionScope.user == null}">
            <form class="form-inline my-2 my-lg-0">
                <a class="btn btn-outline-dark my-2 my-sm-0" href="${pageContext.request.contextPath}/login">Login</a>
            </form>
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <form class="form-inline my-2 my-lg-0">
                <a class="btn btn-outline-dark my-2 my-sm-0" href="${pageContext.request.contextPath}/logout">Logout</a>
            </form>
        </c:if>
    </div>
</nav>