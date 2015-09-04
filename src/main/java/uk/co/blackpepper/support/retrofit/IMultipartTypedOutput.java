package uk.co.blackpepper.support.retrofit;

import retrofit.mime.TypedOutput;

public interface IMultipartTypedOutput extends TypedOutput {

	void addPart(String name, TypedOutput body);
	
	int getPartCount();
}
