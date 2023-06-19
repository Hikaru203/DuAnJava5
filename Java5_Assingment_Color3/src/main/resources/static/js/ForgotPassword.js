function validateFormForgotPassword() {
	var email = document.getElementById("emailInputForgotPassword").value;
	if (email.trim() === "") {
		document.getElementById("emailError").textContent = "Vui lòng nhập email.";
		return false; // Ngăn form được gửi đi nếu có lỗi
	} else {
		document.getElementById("emailError").textContent = ""; // Xóa thông báo lỗi nếu hợp lệ
		return true; // Cho phép form được gửi đi nếu hợp lệ
	}
}
function showVerificationModal() {
	// Hiển thị dialog mã xác thực
	// Code xử lý hiển thị dialog ở đây
	/*validateFormForgotPassword();*/
	// Gửi yêu cầu đến máy chủ
	var email = document.getElementById("emailInputForgotPassword").value;

	/*$.ajax({
		url: "/user/register",
		type: "POST",
		contentType: "application/json",
		data: email,
		success: function(response) {
			if (response === "Email chưa được đăng ký") {
				document.getElementById("emailError").textContent = response;
			} else {*/
				$('#verificationModal').modal('show');
	/*		}
		},
	});*/
}
function verifyCode() {
	var verificationCode = document.getElementById("verificationCodeInput").value;

	$.ajax({
		url: "/verify-code",
		type: "POST",
		contentType: "application/json",
		data: verificationCode,
		success: function(response) {
			// Xử lý phản hồi thành công
			console.log(response);

			if (response === "") {
				// Mã xác nhận sai
				alert("Mã xác nhận không đúng. Vui lòng thử lại.");
			} else {
				// Mã xác nhận đúng
				$('#verificationModal').modal('hide');
				$('#newPasswordModal').modal('show');
			}
		},
	});
}


function resetPasswrod(){

	var newPassword = $('#new-password').val();
	var confirmPassword = $('#confirm-password').val();

	// Kiểm tra hai trường mật khẩu
	if (newPassword !== confirmPassword) {
		alert("Mật khẩu mới và xác nhận mật khẩu không khớp. Vui lòng thử lại.");
		return;
	}
	console.log(newPassword);

	$.ajax({
		url: "/reset-password",
		type: "POST",
		contentType: "application/json",
		data: newPassword,
		success: function(response) {
			console.log(response);
			// Xử lý phản hồi thành công
			$('#newPasswordModal').modal('hide'); // Ẩn modal đổi mật khẩu
			$('#loginModal').modal('show'); // Hiển thị modal đăng nhập
		},
		error: function(xhr, status, error) {
			// Xử lý lỗi
			console.log(status);
			alert("Đã xảy ra lỗi. Vui lòng thử lại sau.");
		}
	});
}
