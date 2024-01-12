package com.myWeb.www.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	// 아래에서 설정하는 것들은 기존 web.xml에서 설정하는 부분들을 선언하는 메서드임
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// root-context.xml 파일을 대체해서 설정하는 부분
		return new Class[] {RootConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// servlet-context.xml 파일을 대체해서 설정하는 부분
		return new Class[] {ServletConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		// servlet-mapping 파일을 대체해서 설정하는 부분
		return new String[] {"/"};
	}

	// EncodingFilter 추가
	
	@Override
	protected Filter[] getServletFilters() {
		// filter 설정
		CharacterEncodingFilter encoding = new CharacterEncodingFilter();
		encoding.setEncoding("UTF-8");
		encoding.setForceEncoding(true);  // 외부로 나가는 데이터도 인코딩 설정
		
		return new Filter[] {encoding};
		// return new Filter[] {encoding, 000 ,  0000};
		// 이렇게 여러가지 필터를 붙일수도 있음!
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		// 그외 기타 사용자 설정
		// multipartConfig 설정
		// 사용자 지정 익셉션 처리 지정 => 404 page 설정
		
		// 파일 업로드 설정
		String uploadLocation = "D:\\KDY\\_myProject\\_java\\_fileUpload";
		int maxFileSize = 1024*1024*20; // 20Mb
		int maxReqSize = maxFileSize * 2;
		int fileSizeThreshold = maxFileSize;
		
		// multipartConfig 설정
		MultipartConfigElement multipartConfig = new MultipartConfigElement(uploadLocation, maxFileSize, maxReqSize, fileSizeThreshold);
		registration.setMultipartConfig(multipartConfig);
	}
	
	
	
}
