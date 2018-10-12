package com.java.config;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.java.controller.StudentController;
import com.java.repository.StudentRepository;
import com.java.service.StudentService;
import com.java.service.StudentServiceImpl;
import com.java.util.TimeoutExceptionInterceptor;
@Configuration
@EnableWebMvc
@ImportResource("/WEB-INF/applicationContext.xml")
@ComponentScan(basePackages="com.java.controller")
public class SpringConfig implements WebMvcConfigurer {

	@Bean
	public AsyncTaskExecutor getExecutor() {
		ThreadPoolTaskExecutor ex= new ThreadPoolTaskExecutor();
		ex.setCorePoolSize(20);
		ex.setMaxPoolSize(50);
		ex.setThreadNamePrefix("Payal-");
		return ex;
	}
	
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setTaskExecutor(getExecutor());
		configurer.setDefaultTimeout(5000);
		configurer.registerCallableInterceptors(new TimeoutExceptionInterceptor());
		
	}

	
	@Autowired StudentRepository rep;
	@Bean
	@Scope("singleton")
	public BeanNameUrlHandlerMapping getHandlerMapping() {
		return new BeanNameUrlHandlerMapping();
	}
	
	@Bean(name= {"/addStudent","/updateStudent"})
	public StudentController getStudentController() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return new StudentController(getService());
	}

	@Bean(value="service")
	public StudentService getService() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return new StudentServiceImpl(rep);
	}

	
	@Bean
	public ViewResolver getResolver() {
		return new InternalResourceViewResolver("/WEB-INF/views/",".jsp");
	}
	
	
}
