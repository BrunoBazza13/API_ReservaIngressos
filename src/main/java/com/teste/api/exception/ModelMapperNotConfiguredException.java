package com.teste.api.exception;

public class ModelMapperNotConfiguredException extends Exception {

	 public ModelMapperNotConfiguredException(String message) {
	     super(message);
	 }

	 public ModelMapperNotConfiguredException(String message, Throwable cause) {
	     super(message, cause);
	 }

	 public ModelMapperNotConfiguredException(Throwable cause) {
	     super(cause);
	 }
	}

