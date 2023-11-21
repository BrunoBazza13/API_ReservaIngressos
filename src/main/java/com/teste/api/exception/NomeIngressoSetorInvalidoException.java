package com.teste.api.exception;

public class NomeIngressoSetorInvalidoException extends Exception {
	
	  public NomeIngressoSetorInvalidoException(String message) {
	      super(message);
	  }

	  public NomeIngressoSetorInvalidoException(String message, Throwable cause) {
	      super(message, cause);
	  }

	  public NomeIngressoSetorInvalidoException(Throwable cause) {
	      super(cause);
	  }

}
