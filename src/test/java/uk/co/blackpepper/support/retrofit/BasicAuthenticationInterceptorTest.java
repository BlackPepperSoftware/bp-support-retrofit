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
