package uk.co.blackpepper.support.retrofit;

import java.io.IOException;
import java.io.OutputStream;

import retrofit.mime.TypedOutput;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class DelegatingTypedOutput implements TypedOutput {
	
	private final TypedOutput delegate;
	
	public DelegatingTypedOutput(TypedOutput delegate) {
		this.delegate = checkNotNull(delegate, "delegate");
	}

	@Override
	public String fileName() {
		return delegate.fileName();
	}

	@Override
	public String mimeType() {
		return delegate.mimeType();
	}

	@Override
	public long length() {
		return delegate.length();
	}

	@Override
	public void writeTo(OutputStream out) throws IOException {
		delegate.writeTo(out);
	}
	
	public TypedOutput getDelegate() {
		return delegate;
	}
}
