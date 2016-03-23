<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div class="modal fade" id="changePassModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Изменение пароля:</h4>
			</div>
			<div class="modal-body">
				<div>
					<input name="newPass1" type="password" class="form-control"
						id="newPass1" placeholder="Новый пароль" required>
				</div>
				<div>
					<input name="newPass2" type="password" class="form-control"
						id="newPass2" placeholder="Подтверждение пароля" required>
				</div>
				<div id="passwordMismatch" class="alert alert-danger hidden"
					role="alert">Пароли не совпадают.</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
				<button type="button" class="btn btn-primary" id="changePassButton">Изменить</button>
			</div>
		</div>
	</div>
</div>