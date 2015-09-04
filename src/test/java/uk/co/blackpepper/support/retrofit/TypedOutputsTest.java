package uk.co.blackpepper.support.retrofit;

import java.io.IOException;

import org.junit.Test;

import retrofit.mime.TypedString;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TypedOutputsTest {

	@Test
	public void toStringReturnsString() throws IOException {
		TypedString output = new TypedString("x");
		
		assertThat(TypedOutputs.toString(output), is("x"));
	}
}
