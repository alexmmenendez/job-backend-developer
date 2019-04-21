package br.com.jobbackenddeveloper.jobbackenddeveloper.hardcoded;

public enum ResponseError {

	UNAUTHORIZED("Invalid token.", "1"),
	NOT_ALLOWED("Not allowed", "2"),
	NOT_NUMERIC("Value not numeric.", "3"),
	NOT_FOUND("Not found.", "4");

	private String message;
	private String code;
	
	private ResponseError(String message, String code) {
		this.setMessage(message);
		this.setCode(code);
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}
