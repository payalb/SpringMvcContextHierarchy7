package com.java.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AsyncDemo {

	@Autowired
	AsyncTaskExecutor ex;

	@RequestMapping("/async1")
	public Callable<ModelAndView> getData1(Model model) {
		System.out.println(Thread.currentThread().getName());
		Callable<ModelAndView> callable = () -> {
			ModelAndView obj = new ModelAndView();
			int sum = 0;
			for (int i = 0; i <= 100; i++) {
				sum = sum + i;
			}
			System.out.println(Thread.currentThread().getName());
			obj.setViewName("demo");
			obj.addObject("sum", sum);
			return obj;
		};

		return callable;
	}

	@RequestMapping("/async2")
	public DeferredResult<ModelAndView> getData2(Model model) {
		System.out.println(Thread.currentThread().getName());
		DeferredResult<ModelAndView> df = new DeferredResult<>();
		Thread t = new Thread(() -> {
			ModelAndView obj = new ModelAndView();
			int sum = 0;
			for (int i = 0; i <= 100; i++) {
				sum = sum + i;
			}
			System.out.println(Thread.currentThread().getName());
			obj.setViewName("demo");
			obj.addObject("sum", sum);
			df.setResult(obj);
		});
		t.start();

		return df;
	}

	@RequestMapping("/async3")
	public DeferredResult<ModelAndView> getData3(Model model) {
		System.out.println(Thread.currentThread().getName());
		DeferredResult<ModelAndView> df = new DeferredResult<>();
		ex.execute(() -> {
			ModelAndView obj = new ModelAndView();
			int sum = 0;
			for (int i = 0; i <= 100; i++) {
				sum = sum + i;
			}
			System.out.println(Thread.currentThread().getName());
			obj.setViewName("demo");
			obj.addObject("sum", sum);
			df.setResult(obj);
		});

		return df;
	}

	@RequestMapping("/async4")
	public WebAsyncTask<ModelAndView> getData4(Model model) {
		System.out.println(Thread.currentThread().getName());
		WebAsyncTask<ModelAndView> task = new WebAsyncTask<>(() -> {

			ModelAndView obj = new ModelAndView();
			int sum = 0;
			for (int i = 0; i <= 100; i++) {
				sum = sum + i;
			}
			System.out.println(Thread.currentThread().getName());
			obj.setViewName("demo");
			obj.addObject("sum", sum);
			return obj;

		});
		return task;

	}
	// Callable
	// DeferredResult
}
