package com.tarim.backend.tarimApp;

import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tarim.backend.tarimApp.business.exception.BusinessException;
import com.tarim.backend.tarimApp.business.exception.ProblemDetails;
import com.tarim.backend.tarimApp.business.exception.ValidationProblemDetails;

@SpringBootApplication
@RestControllerAdvice
@EnableScheduling
public class TarimAppApplication {
	private static final Logger logger = LoggerFactory.getLogger(TarimAppApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TarimAppApplication.class, args);
		logger.info("http://localhost:8080/swagger-ui.html");
	}

	@Bean
	public ModelMapper getModelMapper (){
		return new ModelMapper();
	}


	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(BusinessException businessException){
		ProblemDetails problemDetails = new ProblemDetails();
		problemDetails.setMessage(businessException.getMessage());
		return problemDetails;

	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)

	public ProblemDetails handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException){
		ValidationProblemDetails problemDetails = new ValidationProblemDetails();
		problemDetails.setMessage("VALIDATION.EXCEPTION");
		problemDetails.setValidationErrors(new HashMap<String, String>());

		for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()){
			problemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return problemDetails;
	}
}
