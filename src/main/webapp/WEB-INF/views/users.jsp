<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="head.jsp"></jsp:include>
</head>

<body>

	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<jsp:include page="navigation.jsp"></jsp:include>
		</nav>

		<div id="page-wrapper">

			<div class="container-fluid">
				<!-- Page Heading -->
				<a href="admin/createUser" class="btn btn-primary" type="button" data-toggle="collapse"
					data-target="#collapseExample" aria-expanded="false"
					aria-controls="collapseExample">Добавить пользователя</a>
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Управление пользователями:</h1>
					</div>
				</div>
				<table id="UsersTable" class="table table-bordered sortable">
					<thead>
						<tr>
							<th>Логин</th>
							<th>Имя</th>
							<th>Отчество</th>
							<th>Фамилия</th>
							<th>Email</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="messageTableBody">
						<c:forEach items="${users}" var="user">
							<tr id="row${user.id}">
								<td>${user.login}</td>
								<td>${user.fName}</td>
								<td>${user.mName}</td>
								<td>${user.lName}</td>
								<td>${user.email}</td>
								<td><span class="center-block"><a
										data-role="${user.role}" data-userid="${user.id}"
										class="changeRights" href="#"><i
											class="fa fa-lg ${(user.role).equals("ROLE_USER") ? "fa-user" : "fa-user-secret"}"></i></a>&nbsp<a
										class="deleteCross deleteUser" data-userid="${user.id}"
										href="#"><i class="fa fa-lg fa-times"></i></a></span></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>

	<!-- Change password modal -->
	<jsp:include page="changepass.jsp"></jsp:include>

	<!-- /#wrapper -->

	<jsp:include page="jslibs.jsp"></jsp:include>

	<script src="resources/js/app.js"></script>

	<script src="resources/js/users.js"></script>

</body>

</html>