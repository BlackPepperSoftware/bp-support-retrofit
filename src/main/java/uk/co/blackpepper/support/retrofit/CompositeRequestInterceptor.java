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
