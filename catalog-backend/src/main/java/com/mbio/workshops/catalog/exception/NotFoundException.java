package com.mbio.workshops.catalog.exception;

/**
 * The exception thrown when a resource isn't found. 
 */
public class NotFoundException extends Exception {

    /**
	 * The serial version unique identifier.
	 */
	private static final long serialVersionUID = 7246898502303890374L;

	public NotFoundException(String message) {
        super(message);
    }
}
