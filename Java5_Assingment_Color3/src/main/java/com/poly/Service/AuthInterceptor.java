package com.poly.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poly.Model.NguoiDung;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthInterceptor implements HandlerInterceptor {
	@Autowired
	SessionService session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		NguoiDung user = session.get("session_NguoiDung"); // lấy từ session

		if (user == null) {
			response.sendRedirect("/login");
			return false;
		} else {
			if (user.isChucVu() == false) {
				response.sendRedirect("/login");
				return false;
			} else if (user.isChucVu()) {
				return true;
			}
		}
		return true;
	}
}
