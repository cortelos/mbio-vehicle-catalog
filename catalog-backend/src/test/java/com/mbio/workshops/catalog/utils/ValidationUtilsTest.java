package com.mbio.workshops.catalog.utils;

import java.security.InvalidParameterException;
import java.util.UUID;

import org.junit.Test;

public class ValidationUtilsTest {

    @Test(expected = InvalidParameterException.class)
    public void assertNotNull_nullObject_throwInvalidParamterException() throws Exception {

        ValidationUtils.assertNotNull(null, "sample field");
    }

    @Test(expected = InvalidParameterException.class)
    public void assertNotNull_nullObjectAndField_throwInvalidParamterException() throws Exception {

        ValidationUtils.assertNotNull(null, null);
    }

    @Test(expected = InvalidParameterException.class)
    public void assertNotNull_nullObjectAndEmptyField_throwInvalidParamterException() throws Exception {

        ValidationUtils.assertNotNull(null, "");
    }

    @Test
    public void assertNotNull_nonNullObject_doNothing() throws Exception {

        ValidationUtils.assertNotNull(UUID.randomUUID().toString(), "other field");
    }

    @Test
    public void assertNotNull_nonNullObjectNullField_doNothing() throws Exception {

        ValidationUtils.assertNotNull(UUID.randomUUID().toString(), null);
    }

    @Test
    public void assertNotNull_nonNullObjectEmptyField_doNothing() throws Exception {

        ValidationUtils.assertNotNull(UUID.randomUUID().toString(), "");
    }

    @Test(expected = InvalidParameterException.class)
    public void assertNotEmpty_nullObject_throwInvalidParamterException() throws Exception {

        ValidationUtils.assertNotEmpty(null, "another sample field");
    }

    @Test(expected = InvalidParameterException.class)
    public void assertNotEmpty_nullObjectAndField_throwInvalidParamterException() throws Exception {

        ValidationUtils.assertNotEmpty(null, null);
    }

    @Test(expected = InvalidParameterException.class)
    public void assertNotEmpty_nullObjectAndEmptyField_throwInvalidParamterException() throws Exception {

        ValidationUtils.assertNotEmpty(null, "");
    }

    @Test
    public void assertNotEmpty_nonNullObject_doNothing() throws Exception {

        ValidationUtils.assertNotEmpty(UUID.randomUUID().toString(), "other field");
    }

    @Test
    public void assertNotEmpty_nonNullObjectNullField_doNothing() throws Exception {

        ValidationUtils.assertNotEmpty(UUID.randomUUID().toString(), null);
    }

    @Test
    public void assertNotEmptynonNullObjectEmptyField_doNothing() throws Exception {

        ValidationUtils.assertNotEmpty(UUID.randomUUID().toString(), "");
    }
}