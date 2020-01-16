package isa.rest.client.model;

import javax.json.bind.annotation.JsonbProperty;

public class FailResponse {

	@JsonbProperty
	int code;

	@JsonbProperty
	String result;

	public FailResponse(int code, String result) {
		this.code = code;
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
