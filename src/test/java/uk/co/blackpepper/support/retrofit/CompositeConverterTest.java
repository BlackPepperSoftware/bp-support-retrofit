package uk.co.blackpepper.support.retrofit;

import org.junit.Before;
import org.junit.Test;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;
import retrofit.mime.TypedString;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CompositeConverterTest {
	
	private CompositeConverter composite;
	
	@Before
	public void setUp() {
		composite = new CompositeConverter();
	}

	@Test
	public void fromBodyWithSetTypeInvokesConverter() throws ConversionException {
		Converter converter = mock(Converter.class);
		composite.setConverter(String.class, converter);
		
		TypedInput body = newTypedInput();
		composite.fromBody(body, String.class);
		
		verify(converter).fromBody(body, String.class);
	}

	@Test(expected = ConversionException.class)
	public void fromBodyWithUnsetTypeThrowsException() throws ConversionException {
		composite.fromBody(newTypedInput(), String.class);
	}
	
	@Test
	public void toBodyWithSetTypeInvokesConverter() {
		Converter converter = mock(Converter.class);
		composite.setConverter(String.class, converter);

		composite.toBody("x");
		
		verify(converter).toBody("x");
	}

	@Test
	public void toBodyWithSetTypeReturnsTypedOutput() {
		TypedOutput output = newTypedOutput();
		
		Converter converter = mock(Converter.class);
		when(converter.toBody("x")).thenReturn(output);
		composite.setConverter(String.class, converter);
		
		TypedOutput actual = composite.toBody("x");
		
		assertEquals(output, actual);
	}
	
	@Test(expected = IllegalStateException.class)
	public void toBodyWithUnsetTypeThrowsException() {
		composite.toBody("x");
	}
	
	@Test(expected = NullPointerException.class)
	public void getConverterWithNullTypeThrowsException() {
		composite.getConverter(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void setConverterWithNullTypeThrowsException() {
		composite.setConverter(null, mock(Converter.class));
	}
	
	@Test(expected = NullPointerException.class)
	public void setConverterWithNullConverterThrowsException() {
		composite.setConverter(String.class, null);
	}
	
	private static TypedInput newTypedInput() {
		return new TypedString("_string");
	}

	private static TypedOutput newTypedOutput() {
		return new TypedString("_string");
	}
}
