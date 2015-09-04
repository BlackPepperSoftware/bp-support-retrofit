package uk.co.blackpepper.support.retrofit;

import retrofit.RequestInterceptor;

import static com.google.common.base.Preconditions.checkNotNull;

public class AcceptInterceptor implements RequestInterceptor {

	private static final String ACCEPT_HEADER = "Accept";
	
	private final String accept;
	
	public AcceptInterceptor(String accept) {
		this.accept = checkNotNull(accept, "accept");
	}

	@Override
	public void intercept(RequestFacade request) {
		request.addHeader(ACCEPT_HEADER, accept);
	}
}
