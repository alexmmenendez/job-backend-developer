package br.com.jobbackenddeveloper.jobbackenddeveloper.security.output;

public class TokenOutput {

	private String token;
	
	public TokenOutput() {
	}
	
	public TokenOutput(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
