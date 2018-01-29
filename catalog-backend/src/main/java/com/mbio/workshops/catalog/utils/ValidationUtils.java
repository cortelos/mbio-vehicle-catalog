package com.mbio.workshops.catalog.utils;

import java.security.InvalidParameterException;

import org.springframework.util.StringUtils;

/**
 * Validation utility class.
 */
public class ValidationUtils {

	/**
	 * This class should be used in a static way
	 */
	private ValidationUtils() {
	}

	/**
	 * Check if a given object is null.
	 *
	 * @param object
	 *            the object to validate
	 * @param fieldName
	 *            the field name to include in the exception message
	 * @throws InvalidParameterException
	 *             if object is null
	 */
	public static void assertNotNull(Object object, String fieldName) throws InvalidParameterException {

		if (object == null) {
			throw new InvalidParameterException("Field " + fieldName + " can not be null!");
		}
	}

	/**
	 * Check if a given text is null or empty.
	 *
	 * @param text
	 *            the text to verify
	 * @param fieldName
	 *            the field name to include in the exception message
	 * @throws InvalidParameterException
	 *             if text is null or empty
	 */
	public static void assertNotEmpty(String text, String fieldName) throws InvalidParameterException {

		if (StringUtils.isEmpty(text)) {
			throw new InvalidParameterException("Field " + fieldName + " can not be null or empty!");
		}
	}
}
