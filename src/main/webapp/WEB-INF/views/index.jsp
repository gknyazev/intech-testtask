<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
				<button class="btn btn-primary" type="button" data-toggle="collapse"
					data-target="#collapseExample" aria-expanded="false"
					aria-controls="collapseExample">Адресная книга</button>
				<div class="collapse" id="collapseExample">
					<div class="well">
						<div class="container-fluid">
							<div class="row">
								<!-- /.col-lg-6 -->
								<div class="col-lg-6">
									<div class="input-group">
										<input id="addFriendText" type="text" class="form-control"
											placeholder="Логин адресата"> <span
											class="input-group-btn">
											<button class="btn btn-default" type="button"
												id="addFriendButton">Добавить</button>
										</span>
									</div>
									<!-- /input-group -->
								</div>
								<!-- /.col-lg-6 -->
							</div>
							<!-- /.row -->
							<br />
							<div id="friendList" class="row">
								<c:forEach items="${friends}" var="friend">
									<div class="col-md-2">
										<a href="#" class="addressLogin lead"><c:out
												value="${friend.login}" /></a>&nbsp;<a class="removeFriend deleteCross" data-login="${friend.login}"><span
												class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">Полученные сообщения:</h1>
					</div>
				</div>
				<table id="PMTable" class="table table-bordered sortable">
					<thead>
						<tr>
							<th class="dateHeader">Время отправки</th>
							<th class="loginHeader">Отправитель</th>
							<th>Тема сообщения</th>
						</tr>
					</thead>
					<tbody id="messageTableBody">
					<c:forEach items="${messages}" var="message">
						<tr>
							<td><fmt:formatDate pattern="dd.MM.YYYY HH:mm:ss"  value="${message.sentDate}" /></td>
							<td>${message.sender}</td>
							<td><a class="show-message-link" data-messageid="${message.id}" href="#">${message.topic}</a></td>
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

	<!-- New message modal -->
	<div class="modal fade" id="newMessageModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Отправить сообщение:</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="exampleInputEmail1">Адресат:</label> <input
							type="input" class="form-control" id="PMrecipient"
							placeholder="" disabled>
					</div>
					<div class="form-group">
					 <input
							type="text" class="form-control" id="PMtopic"
							placeholder="Тема сообщения" required>
					</div>
					<div class="form-group">
						
						<textarea rows="10" class="form-control"
							id="PMtext" placeholder="Текст сообщения"
							style="resize: none" required></textarea>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
					<button type="button" class="btn btn-primary" id="sendPM">Отправить</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- New message modal -->
	<div class="modal fade" id="viewMessageModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Сообщение от <span id="readSender"></span></h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
					 	<label>Время отправки:</label>
						<p id="readTime"></p>
					</div>
					<div class="form-group">
					 	<label>Тема:</label>
						<p id="readTopic"></p>
					</div>
					<div class="form-group">
					 	<label>Текст сообщения:</label>
						<p id="readText">asdasdasd</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- /#wrapper -->

	<jsp:include page="jslibs.jsp"></jsp:include>	
	
	<script src="resources/js/app.js"></script>
	
	<!--  Websockets and STOMP -->
	<script src="resources/js/sockjs-1.0.3.min.js"></script>
	<script src="resources/js/stomp.js"></script>
	
	<script src="resources/js/messages.js"></script>
</body>

</html>