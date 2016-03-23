<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- Brand and toggle get grouped for better mobile display -->
<div class="navbar-header">
	<button type="button" class="navbar-toggle" data-toggle="collapse"
		data-target=".navbar-ex1-collapse">
		<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
		<span class="icon-bar"></span> <span class="icon-bar"></span>
	</button>
	<a class="navbar-brand" href="./">Тестовое задание для Intech</a>
</div>
<!-- Top Menu Items -->
<ul class="nav navbar-right top-nav">
	<li class="dropdown"><a href="#" class="dropdown-toggle"
		data-toggle="dropdown"><i class="fa fa-user"></i> ${credentials} <b
			class="caret"></b></a>
		<ul class="dropdown-menu">
			<li><a href="#" data-toggle="modal"
				data-target="#changePassModal"><i class="fa fa-fw fa-key"></i>Смена
					пароля</a></li>
			<li class="divider"></li>
			<li><a href="logout"><i class="fa fa-fw fa-power-off"></i>Выйти</a>
			</li>
		</ul></li>
</ul>
<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
<div class="collapse navbar-collapse navbar-ex1-collapse">
	<ul class="nav navbar-nav side-nav">
		<li <c:if test="${messagesActive}">class="active"</c:if>><a href="./"><i
				class="fa fa-fw fa-dashboard"></i> Сообщения</a></li>
		<sec:authorize access="hasRole('ROLE_ADMIN')"><li <c:if test="${usersActive}">class="active"</c:if>><a href="./admin/users"><i class="fa fa-fw fa-bar-chart-o"></i>
				Пользователи</a></li></sec:authorize>
	</ul>
</div>
<!-- /.navbar-collapse -->