package com.java.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.java.util.MyTimeoutException;

@ControllerAdvice
public class MyControllerService {

	@ExceptionHandler(MyTimeoutException.class)
	public ModelAndView handle() {
		return new ModelAndView("error");
	}
}
