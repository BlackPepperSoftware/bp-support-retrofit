/*
 * Copyright 2014 Black Pepper Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
