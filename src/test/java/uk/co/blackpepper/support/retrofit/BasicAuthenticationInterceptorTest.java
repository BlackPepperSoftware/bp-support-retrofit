package uk.co.blackpepper.support.retrofit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import retrofit.RequestInterceptor.RequestFacade;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BasicAuthenticationInterceptorTest {

	private ExpectedException thrown = ExpectedException.none();

	@Rule
	public ExpectedException getThrown() {
		return thrown;
	}

	@Test
	public void constructorWithNullUsernameThrowsException() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("username");
		
		new BasicAuthenticationInterceptor(null, "y");
	}

	@Test
	public void constructorWithNullPasswordThrowsException() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("password");
		
		new BasicAuthenticationInterceptor("x", null);
	}
	
	@Test
	public void interceptAddsAuthorizationHeader() {
		BasicAuthenticationInterceptor interceptor = new BasicAuthenticationInterceptor("x", "y");
		RequestFacade request = mock(RequestFacade.class);
		
		interceptor.intercept(request);
		
		// base64("x:y") = "eDp5"
		verify(request).addHeader("Authorization", "Basic eDp5");
	}
}
