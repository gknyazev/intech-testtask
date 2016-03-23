<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">


<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="context_path" content="${pageContext.request.contextPath}" />
<base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/">

<title>Signin Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="resources/css/signin.css" rel="stylesheet">

</head>

<body>

	<div class="container">
		<form class="form-register" name="f" action="${isAdmin ? "admin/newUser" : "newUser"}" method="post">
			<h2 class="form-signin-heading">${isAdmin ? "Создание" : "Регистрация"} нового пользователя:</h2>
			<fieldset>
				<div class="form-group">
					<label for="exampleInputEmail1">Имя:</label> <input name="fName"
						type="text" class="form-control" id="exampleInputEmail1"
						placeholder="Имя" required>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">Фамилия:</label> <input
						name="lName" type="text" class="form-control"
						id="exampleInputEmail1" placeholder="Фамилия" required>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">Отчество:</label> <input
						name="mName" type="text" class="form-control"
						id="exampleInputEmail1" placeholder="Отчество" required>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">Логин:</label> <input name="login"
						type="text" class="form-control" id="registerLogin"
						placeholder="Логин">
				</div>
				<%-- 			<c:if test="${not empty error}"> --%>
				<%-- 			</c:if> --%>

				<div class="form-group">
					<label for="exampleInputEmail1">Email:</label> <input name="email"
						type="email" class="form-control" id="exampleInputEmail1"
						placeholder="Email" required>
				</div>
				<%-- 			<c:if test="${not empty error}"> --%>
				<%-- 			</c:if> --%>
				<div class="form-group">
					<label for="exampleInputPassword1">Пароль:</label> <input
						type="password" class="form-control" id="passw1"
						name="passw1" placeholder="Password" required>
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Подтверждение пароля:</label> <input
						type="password" class="form-control" id="passw2"
						name="passw2" placeholder="Password" required>
				</div>
				<%-- 			<c:if test="${not empty error}"> --%>
				<%-- 			</c:if> --%>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />


				<button class="btn btn-lg btn-primary btn-block" type="submit">${isAdmin ? "Создать" : "Зарегистрироваться"}</button>
				<a href="${isAdmin ? "admin/users" : "login"}" role="button">Назад</a>
			</fieldset>
		</form>

	</div>
	<!-- /container -->
	<!-- jQuery -->
	<script src="resources/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="resources/js/bootstrap.min.js"></script>

	<script
		src="resources/js/plugins/validation/jquery.validate.js"></script>
		<script
		src="resources/js/plugins/validation/localization/messages_ru.min.js"></script>	

	<script src="resources/js/register.js"></script>


</body>
</html>