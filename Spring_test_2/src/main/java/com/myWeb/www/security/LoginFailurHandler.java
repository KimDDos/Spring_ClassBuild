package com.myWeb.www.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
public class LoginFailurHandler implements AuthenticationFailureHandler {

	private String authEmail;
	private String errorMessage;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		setAuthEmail(request.getParameter("email"));
		
		// exception 발생시 메시지 저장
		// InternalAuthenticationServiceException : 인증할때 에러
		if( exception instanceof BadCredentialsException ||
				exception instanceof InternalAuthenticationServiceException) {
			setErrorMessage(exception.getMessage().toString());
		}
		request.setAttribute("email", getAuthEmail());
		request.setAttribute("errMsg", getErrorMessage());
		request.getRequestDispatcher("/member/login?error").forward(request, response);
	}

	public String getAuthEmail() {
		return authEmail;
	}

	public void setAuthEmail(String authEmail) {
		this.authEmail = authEmail;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

	
	
}
