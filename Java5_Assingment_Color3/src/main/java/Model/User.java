package Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@NotEmpty(message = "{NotEmpty.user.hoTen}")
	@Min(value = 0, message = "{Min.user.hoTen}")
	@Pattern(regexp = "^[^0-9]*$", message = "{Pattern.user.hoTen}")
	@Pattern(regexp = "^[\\w\\s]*$", message = "{Pattern.user.hoTen}")
	String hoTen;
	
	@NotBlank(message = "{NotBlank.user.taiKhoan}")
	@Pattern(regexp = "^[^0-9]*$", message = "{Pattern.user.taiKhoan}")
	@Pattern(regexp = "^[\\w\\s]*$", message = "{Pattern.user.taiKhoan}")
	String taiKhoan;
	
	@NotBlank(message = "{NotBlank.user.matKhau}")
	@Min(value = 0, message = "{Min.user.matKhau}")
	@Max(value = 10, message = "{Max.user.matKhau}")
	String matKhau;
	
	@NotBlank(message = "{NotBlank.user.email}")
	@Pattern(regexp = "^\\w+@fpt\\.edu\\.vn$", message = "{Pattern.user.email}")
	String email;
	
	
	
	@Min(value = 10, message = "{Min.user.sdt}")
	@Max(value = 11, message = "{Max.user.sdt}")
	int sdt;
	
	@NotBlank(message = "{NotBlank.user.diaChi}")
	@Pattern(regexp = "^[\\w\\s]*$", message = "{Pattern.user.diaChi}")
	String diaChi;
}
