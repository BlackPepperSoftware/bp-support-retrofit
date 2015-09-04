package uk.co.blackpepper.support.retrofit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit.mime.TypedOutput;

import static com.google.common.base.Charsets.UTF_8;

public final class TypedOutputs {
	
	private TypedOutputs() {
		throw new AssertionError();
	}
	
	public static String toString(TypedOutput output) throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		output.writeTo(stream);
		return stream.toString(UTF_8.name());
	}
}
