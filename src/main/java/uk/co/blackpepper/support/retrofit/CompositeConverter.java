package uk.co.blackpepper.support.retrofit;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

import static com.google.common.base.Preconditions.checkNotNull;

public class CompositeConverter implements Converter {

	private final Map<Type, Converter> convertersByType;
	
	public CompositeConverter() {
		convertersByType = new HashMap<>();
	}
	
	@Override
	public Object fromBody(TypedInput body, Type type) throws ConversionException {
		Converter converter = getConverter(type);
		
		if (converter == null) {
			throw new ConversionException("No converter registered for type: " + type);
		}
		
		return converter.fromBody(body, type);
	}

	@Override
	public TypedOutput toBody(Object object) {
		Type type = object.getClass();
		Converter converter = getConverter(type);
		
		if (converter == null) {
			throw new IllegalStateException("No converter registered for type: " + type);
		}
		
		return converter.toBody(object);
	}
	
	public Converter getConverter(Type type) {
		checkNotNull(type, "type");
		
		return convertersByType.get(type);
	}
	
	public void setConverter(Type type, Converter converter) {
		checkNotNull(type, "type");
		checkNotNull(converter, "converter");
		
		convertersByType.put(type, converter);
	}
}
