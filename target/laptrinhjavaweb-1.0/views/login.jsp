<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Login</title>

</head>

<body>
	<div id="login">

		<h3 class="text-center text-white pt-5">Login form</h3>
		<div class="container">
			<div id="login-row"
				class="row justify-content-center align-items-center">
				<div id="login-column" class="col-md-6">
					<div id="login-box" class="col-md-12">
						<div class="alert alert-success">
							Login Success
						</div>
						<div class="alert alert-danger">
							Login fail
						</div>
						<form id="login-form" class="form"
							action='<c:url value="/dang-nhap?action=login"/>' method="POST">

							<h3 class="text-center text-info">Login</h3>
							<div class="form-group">
								<label for="username" class="text-info">Username:</label><br>
								<input type="text" name="userName" id="username"
									class="form-control">
							</div>
							<div class="form-group">
								<label for="password" class="text-info">Password:</label><br>
								<input type="text" name="passWord" id="password"
									class="form-control">
							</div>
							<div class="form-group">
								<label for="remember-me" class="text-info"><span>Remember
										me</span> <span><input id="remember-me" name="remember-me"
										type="checkbox"></span></label><br> <input type="submit"
									name="submit" class="btn btn-info btn-md" value="submit">
							</div>
							<div id="register-link" class="text-right">
								<a href="#" class="text-info">Register here</a>
							</div>
							<input type="hidden" value="login" id="action" name="action" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>