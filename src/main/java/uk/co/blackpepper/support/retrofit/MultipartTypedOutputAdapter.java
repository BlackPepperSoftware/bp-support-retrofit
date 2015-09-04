package uk.co.blackpepper.support.retrofit;

import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedOutput;

public class MultipartTypedOutputAdapter extends DelegatingTypedOutput implements IMultipartTypedOutput {
	
	public MultipartTypedOutputAdapter(MultipartTypedOutput output) {
		super(output);
	}

	@Override
	public void addPart(String name, TypedOutput body) {
		getDelegate().addPart(name, body);
	}

	@Override
	public int getPartCount() {
		return getDelegate().getPartCount();
	}
	
	@Override
	public MultipartTypedOutput getDelegate() {
		return (MultipartTypedOutput) super.getDelegate();
	}
}
