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
