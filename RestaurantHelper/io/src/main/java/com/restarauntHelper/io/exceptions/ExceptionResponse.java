package com.restarauntHelper.io.exceptions;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {

}
