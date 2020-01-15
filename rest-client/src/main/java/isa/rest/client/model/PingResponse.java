package isa.rest.client.model;

public class PingResponse {

	String version;

	Boolean pong;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Boolean getPong() {
		return pong;
	}

	public void setPong(Boolean pong) {
		this.pong = pong;
	}

	@Override
	public String toString() {
		return "PingResponse{" +
			"version='" + version + '\'' +
			", pong=" + pong +
			'}';
	}
}
