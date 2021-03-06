<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Тестовое задание для Intech</title>

<!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="resources/css/signin.css" rel="stylesheet">

</head>

<body>

	<div class="container">

		<form class="form-signin" name="f" th:action="@{/login}" method="post">
			<h2 class="form-signin-heading">Пожалуйста, авторизируйтесь:</h2>
			<c:if test="${not empty error}">
				<div class="alert alert-danger" role="alert">Неверное имя или
					пароль.</div>
			</c:if>
			<label for="inputEmail" class="sr-only">Email address</label> <input
				type="text" id="inputEmail" name="username" class="form-control"
				placeholder="Логин" required autofocus> <label
				for="inputPassword" class="sr-only">Password</label> <input
				type="password" id="inputPassword" name="password"
				class="form-control" placeholder="Пароль" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">Запомнить
				</label>
			</div>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />


			<button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
			<a href="register" role="button">Зарегистрироваться</a>

		</form>
		
	</div>
	<!-- /container -->

</body>
</html>