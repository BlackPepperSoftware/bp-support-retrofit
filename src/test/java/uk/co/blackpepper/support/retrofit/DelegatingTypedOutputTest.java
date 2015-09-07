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

import java.io.IOException;
import java.io.OutputStream;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import retrofit.mime.TypedOutput;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DelegatingTypedOutputTest {

	private ExpectedException thrown = ExpectedException.none();

	@Rule
	public ExpectedException getThrown() {
		return thrown;
	}
	
	@Test
	public void constructorSetsDelegate() {
		TypedOutput delegate = mock(TypedOutput.class);
		
		DelegatingTypedOutput actual = newDelatingTypedOutput(delegate);
		
		assertThat(actual.getDelegate(), is(delegate));
	}

	@Test
	public void constructorWithNullDelegateThrowsException() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("delegate");
		
		newDelatingTypedOutput(null);
	}
	
	@Test
	public void fileNameDelegates() {
		TypedOutput delegate = mock(TypedOutput.class);
		
		newDelatingTypedOutput(delegate).fileName();
		
		verify(delegate).fileName();
	}
	
	@Test
	public void fileNameReturnsDelegateValue() {
		TypedOutput delegate = mock(TypedOutput.class);
		when(delegate.fileName()).thenReturn("x");
		
		String actual = newDelatingTypedOutput(delegate).fileName();
		
		assertThat(actual, is("x"));
	}
	
	@Test
	public void mimeTypeDelegates() {
		TypedOutput delegate = mock(TypedOutput.class);
		
		newDelatingTypedOutput(delegate).mimeType();
		
		verify(delegate).mimeType();
	}
	
	@Test
	public void mimeTypeReturnsDelegateValue() {
		TypedOutput delegate = mock(TypedOutput.class);
		when(delegate.mimeType()).thenReturn("x");
		
		String actual = newDelatingTypedOutput(delegate).mimeType();
		
		assertThat(actual, is("x"));
	}
	
	@Test
	public void lengthDelegates() {
		TypedOutput delegate = mock(TypedOutput.class);
		
		newDelatingTypedOutput(delegate).length();
		
		verify(delegate).length();
	}
	
	@Test
	public void lengthReturnsDelegateValue() {
		TypedOutput delegate = mock(TypedOutput.class);
		when(delegate.length()).thenReturn(1L);
		
		long actual = newDelatingTypedOutput(delegate).length();
		
		assertThat(actual, is(1L));
	}
	
	@Test
	public void writeToDelegates() throws IOException {
		TypedOutput delegate = mock(TypedOutput.class);
		OutputStream out = mock(OutputStream.class);
		
		newDelatingTypedOutput(delegate).writeTo(out);
		
		verify(delegate).writeTo(out);
	}
	
	private static DelegatingTypedOutput newDelatingTypedOutput(TypedOutput delegate) {
		return new DelegatingTypedOutput(delegate) {
			// concrete class
		};
	}
}
