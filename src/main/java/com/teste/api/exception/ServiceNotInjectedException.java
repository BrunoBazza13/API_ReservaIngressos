package com.teste.api.exception;

public class ServiceNotInjectedException extends Exception {

	 public ServiceNotInjectedException(String message) {
	    super(message);
	 }

	 public ServiceNotInjectedException(String message, Throwable cause) {
	    super(message, cause);
	 }

	 public ServiceNotInjectedException(Throwable cause) {
	    super(cause);
	 }
	}
