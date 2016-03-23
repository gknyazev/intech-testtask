$(document).ready(function() {
	
	window.messages = new Messages();
	 
	messages.token = $("meta[name='_csrf']").attr("content");
	messages.header = $("meta[name='_csrf_header']").attr("content");
	messages.contextPath = $("meta[name='context_path']").attr("content");
	
	$.fn.dataTable.moment( 'D.MM.YYYY HH:mm' );
	
	messages.table = $('#PMTable').DataTable(messages.pmTableConfig);
	
	$("#addFriendButton").click(messages.addFriend);
	
	$("#sendPM").click(messages.sendPM);
	
	$("#friendList" ).on("click", ".removeFriend", messages.removeFriend);
	
	$("#friendList" ).on( "click", "a.addressLogin", function() {
		$('#newMessageModal').modal();
		$('#PMrecipient').val($(this).text());	
	});
	
	$("#messageTableBody" ).on( "click", ".show-message-link", function() {
		messages.getPM($(this).data('messageid'));
	});

	messages.listenWebsocket();
});

function Messages(){
	
}

Messages.prototype.errorMessages = {
		userisnotfound : 'Пользователь не найден.',
		cantaddyourself: 'Нельзя добавить себя.',
		friendexists: 'Адресат уже существует.'
}

Messages.prototype.addFriend = function() {
	var userName = $('#addFriendText').val();
	if (userName){
		var insertionHtml = '<div class="col-md-2"><a class="addressLogin lead" href="#">FRIENDLOGIN</a>&nbsp;<a class="removeFriend deleteCross" data-login="FRIENDLOGIN"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></div>'.replace(/FRIENDLOGIN/g, userName);
		
		$.ajax({
			type : "POST",
			url : "./addFriend",
			data : {
				friendLogin : userName
			},
			dataType: 'text',
			beforeSend : app.setHeaders,
			success : function(html) {
				$.notify({
					message: 'Адресат добавлен.' 
				},{
					type: 'success'
				});
				$('#friendList').append(insertionHtml);
			},
			error : function(xhr) {
				var errorText = messages.errorMessages[xhr.responseText];
				if (!errorText) {
					errorText = 'Произошла ошибка';
				}
				$.notify({
					message: errorText 
				},{
					type: 'danger'
				});
			}
		});
		$('#addFriendText').val('');
	}
}

Messages.prototype.removeFriend = function() {
	var userName = $(this).data("login");
	if (userName){
		$.ajax({
			type : "POST",
			url : "./removeFriend",
			data : {
				friendLogin : userName
			},
			dataType: 'text',
			beforeSend : app.setHeaders,
			success : function(html) {
				$.notify({
					message: 'Адресат удалён.' 
				},{
					type: 'success'
				});
			},
			error : function(e) {
				console.log("Error:" + e);
			}
		});	
		$(this).parent().remove();
	}
}

Messages.prototype.sendPM = function(){
	$.ajax({
	    headers: app.jsonHeaders,
		type : "POST",
		url : "./newPM",
		data : JSON.stringify({
			recipient : $('#PMrecipient').val(),
			topic: ($('#PMtopic').val()),
			text: ($('#PMtext').val()),
		}),
		dataType: 'text',
		beforeSend : app.setHeaders,
		success : function(html) {
			$.notify({
				message: 'Сообщение отправлено.' 
			},{
				type: 'success'
			});
		},
		error : function(e) {
			
		}
	});
	$('#newMessageModal').modal('hide');
}


Messages.prototype.getPM = function(messageId){
	$.ajax({
	    headers: app.jsonHeaders,
		type : "GET",
		url : "./getPM",
		data : {
			id: messageId
		},
		dataType: 'text',
		beforeSend : app.setHeaders,
		success : messages.onGetPM,
		error : function(e) {
			
		}
	});
}

Messages.prototype.onGetPM = function(data){
	var message = JSON.parse(data);
	$("#readTime").text( moment(message.sentDate).format('D.MM.YYYY HH:mm:ss'));
	$("#readSender").text(message.sender);
	$("#readTopic").text(message.topic);
	$("#readText").text(message.text);
	$('#viewMessageModal').modal('show');
}


Messages.prototype.listenWebsocket = function(){
	var socket = new SockJS(this.contextPath+'/hello');
	messages.stompClient = Stomp.over(socket);
	messages.stompClient.connect(app.csrfHeaders, function(frame) {
		console.log('Connected: ' + frame);
		messages.stompClient.subscribe('/user/pm', function(greeting){
			var newMessage = JSON.parse(greeting.body);
			messages.table.row.add( [
                    moment(newMessage.sentDate).format('D.MM.YYYY HH:mm:ss'),
                    newMessage.sender,
                    newMessage.topic,
                ] ).draw( false );
        
			$.notify({
				message: newMessage.sender + ": " + newMessage.topic
			},{
				type: 'info'
			});
		});
	}); 
}

Messages.prototype.pmTableConfig = {
	"language": {
		  "processing": "Подождите...",
		  "search": "Поиск:",
		  "lengthMenu": "Показать _MENU_ сообщений",
		  "info": "Сообщения с _START_ до _END_ из _TOTAL_ сообщений",
		  "infoEmpty": "Сообщения с 0 до 0 из 0 сообщений",
		  "infoFiltered": "(отфильтровано из _MAX_ сообщений)",
		  "infoPostFix": "",
		  "loadingRecords": "Загрузка сообщений...",
		  "zeroRecords": "Сообщения отсутствуют.",
		  "emptyTable": "Сообщения отсутствуют",
		  "paginate": {
		    "first": "Первая",
		    "previous": "Предыдущая",
		    "next": "Следующая",
		    "last": "Последняя"
		  },
		  "aria": {
		    "sortAscending": ": активировать для сортировки столбца по возрастанию",
		    "sortDescending": ": активировать для сортировки столбца по убыванию"
		  }
		},
		"order": [[ 0, "desc" ]],
		 aoColumns : [
		            { sWidth: '15%' },
		            { sWidth: '15%' },
		            { sWidth: '70%' },
		          ]
}