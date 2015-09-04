package uk.co.blackpepper.support.retrofit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import retrofit.RequestInterceptor.RequestFacade;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AcceptInterceptorTest {

	private ExpectedException thrown = ExpectedException.none();

	@Rule
	public ExpectedException getThrown() {
		return thrown;
	}

	@Test
	public void constructorWithNullAcceptThrowsException() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("accept");
		
		new AcceptInterceptor(null);
	}
	
	@Test
	public void interceptAddsAcceptHeader() {
		AcceptInterceptor interceptor = new AcceptInterceptor("x");
		RequestFacade request = mock(RequestFacade.class);
		
		interceptor.intercept(request);
		
		verify(request).addHeader("Accept", "x");
	}
}
