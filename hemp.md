Spring MVC

1) Contexts: 

ApplicationContext: root application context: beans (parent)
It would be available for all child contexts
/WEB-INF/applicationContext.xml
ContextLoaderListener: web application: servletContext

WebApplicationContext
child context: DispatcherServlet
https://javabeat.net/spring-mvc-application-contex
Bean defined here, not availabke in root context

Multiple child contexts, but each would be accessing root context but cannot access each other contexts

2 dispatcher servlets:
dispatcher	dispatcher1
rep		@Import
@Configuration

@Import: include java config
@ImportResource: xml config


web.xml: WebApplicationInitializer

abstract method:

root context: WebApplicationContext: load
child context: : load



2) Filter: servlet: Spring application
Interceptor:


3) Asynchronous : controller

 Web application: Tomcat
	multiple users: access: 
	threads: 300	
	user logged in ->   3hrs --->
	request --> server --> fetch the data ---> 15 sec --> response

	request --> server --> user thread -> fetch the data   --> response 	
		server --> server thread--> response

Queue: 30elements : pop

BlockingQueue: 40 requests
	request -->


asynchronously

	async-supported: true
	dispatcher servlet: async-supported: true

	spring
	




