$(document).ready(function() {
	$(".form-register").validate({
		errorClass : "alert alert-danger",
		errorElement : "div",
		rules : {
			login : {
				minlength : 3,
				required : true,
				remote : {
					url : "checkLogin"
				}
			},
			passw1 : {
				required : true,
				minlength : 5
			},
			passw2 : {
				required : true,
				minlength : 5,
				equalTo : "#passw1"
			},
			email : {
				required : true,
				email:true,
				remote: "checkEmail"
			}

		},
		messages : {
			login : {
				remote : "Пользователь с таким логином уже существует"
			},
			passw2 : {
				equalTo : "Введённые пароли не совпадают"
			},
			email : {
				remote : "Пользователь с таким email уже существует"
			}
		}
	});
});
