package com.java.util;

import java.util.concurrent.Callable;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

public class TimeoutExceptionInterceptor implements CallableProcessingInterceptor{

	public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
			throw new MyTimeoutException("Time out occured Processing request"+ request);
			
		}
}
