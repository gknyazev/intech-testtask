$(document).ready(function() {
	window.app = new Application();
	 
	app.token = $("meta[name='_csrf']").attr("content");
	app.header = $("meta[name='_csrf_header']").attr("content");
	app.contextPath = $("meta[name='context_path']").attr("content");
	app.csrfHeaders = {}
	app.csrfHeaders[app.header] = app.token;
	$("#changePassButton").click(app.changePassword);
});

function Application() {

}

Application.prototype.jsonHeaders = {
	'Accept' : 'Application/json',
	'Content-Type' : 'Application/json'
}

Application.prototype.setHeaders = function(xhr) {
	xhr.setRequestHeader(app.header, app.token);
}

Application.prototype.changePassword = function() {
	var pass1 = $('#newPass1').val();
	var pass2 = $('#newPass2').val();
	if (pass1 === pass2) {
		$('#changePassModal').modal('toggle');
		$.ajax({
			type : "POST",
			url : "./changePassword",
			data : {
				newPassword : pass1
			},
			dataType : 'text',
			beforeSend : app.setHeaders,
			success : function(html) {
				$('#newPass1').val('');
				$('#newPass2').val('');
				$('#passwordMismatch').addClass('hidden');
				$.notify({
					message : 'Пароль был успешно изменён.'
				}, {
					type : 'success'
				});
			},
			error : function(e) {
				console.log("Error:" + e);
			}
		});
	} else {
		$('#passwordMismatch').removeClass('hidden');
	}
}