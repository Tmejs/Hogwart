<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pl">
<head>

    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

    <!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/welcome">Home</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/dodaj"></a>Dodaj</li>
                <li class="active"><a href="/usun">Usun</a></li>
                <li><a href="/all">Wszyscy</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h1> Wybierz nauczyciela do usuniecia</h1>
    <form method="GET" action="/nauczycielDel/" name="wgtmsr">
        <select name="wgtmsr" name="groupname">
            <c:forEach items="${nauczycielsList}" var="nauczyciel">
                <tr>
                    <option  value="${nauczyciel.id}">${nauczyciel.id} ${nauczyciel.imie} ${nauczyciel.nazwisko}</option>
                </tr>
            </c:forEach>
        </select>
        <input type="submit" value="Usuń">
    </form>

</div>

<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>