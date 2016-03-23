$(document).ready(function() {
	window.users = new Users();

	users.table = $('#UsersTable').DataTable(users.usersTableConfig);
	
	$("#UsersTable" ).on("click", ".deleteUser", users.deleteUser);
	
	$("#UsersTable" ).on("click", ".changeRights", users.changeRights);
});

function Users() {

}

Users.prototype.changeRights = function(event){
	//event.preventDefault();
	var a = $(this);
	if (a.data("role")!=null){
		var role = a.data("role");
		a.removeAttr("data-role");
		a.removeData("role");
		if (role=="ROLE_USER"){
			users.grantAdmin(a);
		} else {
			users.revokeAdmin(a);
		}
	}
	return false;
}

Users.prototype.grantAdmin = function(a){
	$.ajax({
		type : "POST",
		url : "./admin/grantAdmin",
		data : {
			userId : a.data("userid")
		},
		dataType: 'text',
		beforeSend : app.setHeaders,
		success : function(html) {
			a.data("role", "ROLE_ADMIN");
			var i = a.children("i");
			i.removeClass("fa-user");
			i.addClass("fa-user-secret");
			$.notify({
				message: 'Пользователь получил права администратора.'
				
			},{
				type: 'success'
			});
			
		},
		error : function(e) {
			console.log("Error:" + e);
		}
	});	
}

Users.prototype.revokeAdmin = function(a){
	$.ajax({
		type : "POST",
		url : "./admin/revokeAdmin",
		data : {
			userId : a.data("userid")
		},
		dataType: 'text',
		beforeSend : app.setHeaders,
		success : function(html) {
			a.data("role", "ROLE_USER");
			var i = a.children("i");
			i.removeClass("fa-user-secret");
			i.addClass("fa-user");
			$.notify({
				message: 'Пользователь лишился прав администратора.'
				
			},{
				type: 'success'
			});
			
		},
		error : function(e) {
			console.log("Error:" + e);
		}
	});	
}

Users.prototype.deleteUser = function (event){
	var id = $(this).data("userid");
	$.ajax({
		type : "POST",
		url : "./admin/removeUser",
		data : {
			userId : $(this).data("userid")
		},
		dataType: 'text',
		beforeSend : app.setHeaders,
		success : function(html) {
			users.table.row($("#row"+id)).remove().draw();
			$.notify({
				message: 'Пользователь удалён.'
				
			},{
				type: 'success'
			});
			
		},
		error : function(e) {
			console.log("Error:" + e);
		}
	});	
	return false;
}

Users.prototype.usersTableConfig = {
	"language" : {
		"processing" : "Подождите...",
		"search" : "Поиск:",
		"lengthMenu" : "Показать _MENU_ пользователей",
		"info" : "Пользователи с _START_ до _END_ из _TOTAL_ пользователей",
		"infoEmpty" : "Пользователи с 0 до 0 из 0 пользователей",
		"infoFiltered" : "(отфильтровано из _MAX_ пользователей)",
		"infoPostFix" : "",
		"loadingRecords" : "Загрузка пользователей...",
		"zeroRecords" : "Сообщения отсутствуют.",
		"emptyTable" : "Сообщения отсутствуют",
		"paginate" : {
			"first" : "Первая",
			"previous" : "Предыдущая",
			"next" : "Следующая",
			"last" : "Последняя"
		},
		"aria" : {
			"sortAscending" : ": активировать для сортировки столбца по возрастанию",
			"sortDescending" : ": активировать для сортировки столбца по убыванию"
		}
		 

	}, aoColumns : [
		            {  },
		            {  },
		            {  },
		            {  },
		            {  },
		            { sWidth: '4%' }
		          ]
}