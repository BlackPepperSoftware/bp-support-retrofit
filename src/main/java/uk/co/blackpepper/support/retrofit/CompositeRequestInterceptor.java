package uk.co.blackpepper.support.retrofit;

import java.util.List;

import com.google.common.collect.ImmutableList;

import retrofit.RequestInterceptor;

public class CompositeRequestInterceptor implements RequestInterceptor {

	private final List<RequestInterceptor> interceptors;
	
	public CompositeRequestInterceptor(RequestInterceptor... interceptors) {
		this.interceptors = ImmutableList.copyOf(interceptors);
	}
	
	@Override
	public void intercept(RequestFacade request) {
		for (RequestInterceptor interceptor : interceptors) {
			interceptor.intercept(request);
		}
	}
}
