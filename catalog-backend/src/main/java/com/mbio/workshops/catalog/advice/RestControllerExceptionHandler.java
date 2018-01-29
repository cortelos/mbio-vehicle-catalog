package com.mbio.workshops.catalog.advice;

import java.security.InvalidParameterException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mbio.workshops.catalog.exception.NotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * Provides a centralized exception handling across all Catalog mapping methods
 * through {@link ExceptionHandler} methods.
 * 
 * This class extends the base class {@link ResponseEntityExceptionHandler} and
 * provides several @ExceptionHandler methods for handling Spring MVC
 * exceptions.
 */
@Slf4j
@RestControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidParameterException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void handleInvalidParameterException(InvalidParameterException exception) {

		log.debug("Invalid parameter exception: {}", exception.getMessage());
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public void handleNotFoundException(NotFoundException exception) {

		log.debug("Not found exception: {}", exception.getMessage());
	}
}
