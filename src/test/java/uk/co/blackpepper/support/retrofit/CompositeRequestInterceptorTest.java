package uk.co.blackpepper.support.retrofit;

import org.junit.Test;

import retrofit.RequestInterceptor;
import retrofit.RequestInterceptor.RequestFacade;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CompositeRequestInterceptorTest {

	@Test
	public void interceptDelegates() {
		RequestInterceptor delegate1 = mock(RequestInterceptor.class);
		RequestInterceptor delegate2 = mock(RequestInterceptor.class);
		CompositeRequestInterceptor interceptor = new CompositeRequestInterceptor(delegate1, delegate2);
		RequestFacade request = mock(RequestFacade.class);
		
		interceptor.intercept(request);
		
		verify(delegate1).intercept(request);
		verify(delegate2).intercept(request);
	}
}
