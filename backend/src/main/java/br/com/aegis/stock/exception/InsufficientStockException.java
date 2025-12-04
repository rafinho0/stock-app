package br.com.aegis.stock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InsufficientStockException extends RuntimeException {

	public InsufficientStockException(String msg) {
		super(msg);
	}
}
