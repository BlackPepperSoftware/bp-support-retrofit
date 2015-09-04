package uk.co.blackpepper.support.retrofit;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;

import retrofit.RequestInterceptor;

import static com.google.common.base.Preconditions.checkNotNull;

public class BasicAuthenticationInterceptor implements RequestInterceptor {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	
	private final String authorization;
	
	public BasicAuthenticationInterceptor(String username, String password) {
		checkNotNull(username, "username");
		checkNotNull(password, "password");
		
		String credentials = String.format("%s:%s", username, password);
		String cookie = BaseEncoding.base64().encode(credentials.getBytes(Charsets.UTF_8));
		authorization = String.format("Basic %s", cookie);
	}

	@Override
	public void intercept(RequestFacade request) {
		request.addHeader(AUTHORIZATION_HEADER, authorization);
	}
}
