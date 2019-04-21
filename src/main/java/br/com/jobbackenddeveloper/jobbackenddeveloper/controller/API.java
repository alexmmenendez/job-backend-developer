package br.com.jobbackenddeveloper.jobbackenddeveloper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class API {

	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";

	public String getToken(HttpServletRequest request) {
		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));

		if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
			token = Optional.of(token.get().substring(7));
		}

		return token.get();
	}

	protected Map<String, String> getErros(BindingResult bindingResult) {
		Map<String, String> erros = new HashMap<>();
		bindingResult.getAllErrors().forEach(
				error -> {
					if(error instanceof FieldError) {
						erros.put(((FieldError)error).getField(), error.getDefaultMessage());
					} else if(error instanceof ObjectError) {
						erros.put(error.getObjectName(), error.getDefaultMessage());
					}
				}
		);
		return erros;
	}

	protected ResponseEntity<Map<String, String>> returnErrors(BindingResult bindingResult, HttpStatus httpStatus) {
		return new ResponseEntity<>(getErros(bindingResult), httpStatus);
	}


}
