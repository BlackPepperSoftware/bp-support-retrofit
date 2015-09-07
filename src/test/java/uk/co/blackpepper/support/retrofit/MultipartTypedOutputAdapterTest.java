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

import org.junit.Test;

import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedOutput;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class MultipartTypedOutputAdapterTest {

	@Test
	public void constructorSetsDelegate() {
		MultipartTypedOutput output = new MultipartTypedOutput();
		
		MultipartTypedOutputAdapter actual = new MultipartTypedOutputAdapter(output);
		
		assertThat(actual.getDelegate(), is(output));
	}
	
	@Test
	public void addPartAddsPart() {
		MultipartTypedOutput output = new MultipartTypedOutput();
		TypedOutput body = mock(TypedOutput.class);
		
		new MultipartTypedOutputAdapter(output).addPart("x", body);
		
		assertThat(output.getPartCount(), is(1));
	}
}
