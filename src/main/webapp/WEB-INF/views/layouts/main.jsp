<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
    <jsp:include page="/WEB-INF/views/layouts/header.jsp" />
</head>
<body>
    <jsp:include page="/WEB-INF/views/authentification/login.jsp" />
    <jsp:include page="/WEB-INF/views/layouts/footer.jsp" />
</body>
</html>
