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
