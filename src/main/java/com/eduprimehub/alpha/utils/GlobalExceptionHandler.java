package com.eduprimehub.alpha.utils;

import com.eduprimehub.alpha.models.objects.BaseResponse;
import com.eduprimehub.alpha.models.objects.Error;
import com.eduprimehub.alpha.exceptions.GenericException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResponse<String>> genericError(Exception e) {
		log.error(e.getMessage(), e);
		Error error = new Error(INTERNAL_SERVER_ERROR.name(), "Something went wrong");
		return status(500).body(new BaseResponse<String>("error", singletonList(error),"500"));
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<BaseResponse<String>> httpClientErrorException(HttpClientErrorException e) {
		log.error(e.getMessage(), e);
		String result = null;
		HttpHeaders responseHeaders = e.getResponseHeaders();
		if (null != responseHeaders) {
			List<String> list = responseHeaders.get("WWW-Authenticate");
			if (null != list) {
				result = list.stream().filter(s -> s != null && !"".equals(s)).collect(Collectors.joining(" "));
			}
		}
		return status(500)
				.body(new BaseResponse<String>(e.getMessage(), singletonList(new Error(e.getStatusCode().getReasonPhrase(),
						StringUtils.defaultIfBlank(result, "Something went wrong"))),""));
	}

	@ExceptionHandler(GenericException.class)
	public ResponseEntity<BaseResponse<String>> genericException(GenericException e) {
		log.error(e.getMessage(), e);
		return status(e.getStatus())
				.body(new BaseResponse<String>(e.getMessage(), singletonList(new Error(e.getCode(), e.getResult())),""));
	}


}