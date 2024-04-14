package com.travel.travtronics.util;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.travel.travtronics.response.APIResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class CustomExceptionalHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllUncaughtException(Exception exception, HttpServletRequest request) {
		String message = String.format("unknown error occurred : %s", exception.getLocalizedMessage());
		logger.error("unknown error occurred", exception);
		return buildResponseEntity(
				new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Collections.emptyList()), request,
				exception);
	}

	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = "malformed json request..please check request body formatting";
		logger.error(ex.getMessage());
		return handleExceptionInternal(ex, new APIResponse(HttpStatus.BAD_REQUEST.value(), message, new ArrayList<>()),
				new HttpHeaders(), HttpStatus.CONFLICT, request);

	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> finalerrors = new ArrayList<>();
		List<String> feilderrors = ex.getBindingResult().getFieldErrors().stream()
				.map(er -> er.getField() + " : " + er.getDefaultMessage()).collect(Collectors.toList());
		finalerrors.addAll(feilderrors);
		List<String> gloabalerrors = ex.getBindingResult().getGlobalErrors().stream()
				.map(er -> er.getObjectName() + " : " + er.getDefaultMessage()).collect(Collectors.toList());
		finalerrors.addAll(gloabalerrors);
		logger.error(finalerrors);
		return handleExceptionInternal(ex, new APIResponse(HttpStatus.BAD_REQUEST.value(), "Validation Error",
				Collections.emptyList(), finalerrors), headers, status, request);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(final HttpServletRequest request,
			final MethodArgumentTypeMismatchException exception) {

		String message = String
				.format(exception.getName() + " should be of type " + exception.getRequiredType().getName());
		return buildResponseEntity(new APIResponse(HttpStatus.BAD_REQUEST.value(), message, Collections.emptyList()),
				request, exception);
	}

	public ResponseEntity<Object> handleConstraintViolationException(final HttpServletRequest request,
			final ConstraintViolationException ex) {
		String message = "Property Violation Occurred";
		logger.error(ex.getCause().getMessage());

		List<String> violatedConstraints = ex.getConstraintViolations().stream()
				.map(cv -> cv.getRootBeanClass().getName() + " " + cv.getPropertyPath() + ":" + cv.getMessage())
				.collect(Collectors.toList());
		return buildResponseEntity(
				new APIResponse(HttpStatus.BAD_REQUEST.value(), message, Collections.emptyList(), violatedConstraints),
				request, ex);

	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolatonException(final HttpServletRequest request,
			final DataIntegrityViolationException ex) {
		String message = "Property Violation Occurred";
		logger.error(ex.getCause().getMessage());
		return buildResponseEntity(new APIResponse(HttpStatus.BAD_REQUEST.value(), message, Collections.emptyList()),
				request, ex);
	}

	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getParameterName() + " parameter is missing");
		return handleExceptionInternal(ex,
				new APIResponse(HttpStatus.BAD_REQUEST.value(), "Missing Parameters", Collections.emptyList(), details),
				headers, status, request);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> handleNoSuchElementFoundException(final HttpServletRequest request,
			final NoSuchElementException exception) {

		return buildResponseEntity(
				new APIResponse(HttpStatus.NOT_FOUND.value(), "unknown error occurred", Collections.emptyList()),
				request, exception);

	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundFoundException(final HttpServletRequest request,
			final NotFoundException exception) {

		APIResponse errorResponse = new APIResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(),
				Collections.emptyList(), null);

		return buildResponseEntity(errorResponse, request, exception);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<APIResponse> handleNullPointerException(NullPointerException ex) {

		String parameterName = extractParameterName(ex);

		APIResponse errorResponse = new APIResponse(HttpStatus.BAD_REQUEST.value(),
				"A NullPointerException occurred for parameter: " + parameterName, Collections.emptyList(), null);

		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<APIResponse> handleNumberException(NumberFormatException ex) {

		String parameterName = extractNumberFormatParameterName(ex);

		APIResponse errorResponse = new APIResponse(HttpStatus.BAD_REQUEST.value(),
				"A NumberFormatException occurred for parameter: " + parameterName, Collections.emptyList(), null);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<APIResponse> handleIllegalArgumentException(IllegalArgumentException ex) {

		System.out.println("----------------------> IllegalArgumentException START ERRORS <-----------------------");

		String parameterName = findErrorFormatParameterName(ex);

		String errorMessage = "Bad Request at line " + parameterName;

		APIResponse errorResponse = new APIResponse(HttpStatus.BAD_REQUEST.value(),
				"A IllegalArgumentException occurred for parameter: " + errorMessage, Collections.emptyList(), null);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<APIResponse> handleDateTimeParseException(DateTimeParseException ex) {

		StackTraceElement[] stackTrace = ex.getStackTrace();
		if (stackTrace.length > 0) {
			StackTraceElement element = stackTrace[0];
			String fileName = element.getFileName();
			String methodName = element.getMethodName();
			int lineNumber = element.getLineNumber();

			System.out.println("=============date time errors ===================>");
			String errorMessage = "DateTimeParseException occurred in file " + fileName + " at method " + methodName
					+ " on line " + lineNumber;

			System.out.println("=============Error===================>" + errorMessage);

			APIResponse errorResponse = new APIResponse(HttpStatus.BAD_REQUEST.value(), errorMessage,
					Collections.emptyList(), null);

			// Set additional fields in the error response if needed

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}

		// Default response if stack trace is empty

		APIResponse errorResponse = new APIResponse(HttpStatus.BAD_REQUEST.value(), "A DateTimeParseException occurred",
				Collections.emptyList(), null);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	private String findErrorFormatParameterName(IllegalArgumentException ex) {

		String traceInfo = "Unknown parameter";
		for (StackTraceElement element : ex.getStackTrace()) {

			if (element.getLineNumber() != -1) {

				System.out.println(element.getModuleName() + " -> " + element.getClassLoaderName() + " --> "
						+ element.getClassName());

				String methodName = element.getMethodName();
				String fileName = element.getFileName();

				System.out.println(
						"----------------------> IllegalArgumentException START ERRORS <-----------------------");
				System.out.println(fileName + " --> " + methodName + " --> LineNO: " + element.getLineNumber());
				System.out.println(
						"----------------------> IllegalArgumentException END ERRORS <-----------------------");

				traceInfo = methodName;
				if (element != null && element.getLineNumber() > 0 && element.getClassLoaderName() != null
						&& element.getModuleName() == null) {
					traceInfo = " " + traceInfo + " -> " + element.getLineNumber();
					if (element.getModuleName() == null) {
						return traceInfo;
					}
				}
			}
		}
		return traceInfo;
	}

	private String extractNumberFormatParameterName(NumberFormatException ex) {

		String traceInfo = "Unknown parameter";
		for (StackTraceElement element : ex.getStackTrace()) {

			if (element.getLineNumber() != -1) {

				System.out.println(element.getModuleName() + " -> " + element.getClassLoaderName() + " --> "
						+ element.getClassName());

				String methodName = element.getMethodName();
				String fileName = element.getFileName();

				System.out
						.println("----------------------> NumberFormatException START ERRORS <-----------------------");
				System.out.println(fileName + " --> " + methodName + " --> LineNO: " + element.getLineNumber());
				System.out.println("----------------------> NumberFormatException END ERRORS <-----------------------");

				traceInfo = methodName;
				if (element != null && element.getLineNumber() > 0 && element.getClassLoaderName() != null
						&& element.getModuleName() == null) {
					traceInfo = " " + traceInfo + " -> " + element.getLineNumber();
					if (element.getModuleName() == null) {
						return traceInfo;
					}
				}
			}
		}
		return traceInfo;
	}

	// Utility method to extract the parameter name from the stack trace
	private String extractParameterName(NullPointerException ex) {

		for (StackTraceElement element : ex.getStackTrace()) {

			System.out.println(element);
			System.out.println(element.toString());
			if (element.getLineNumber() != -1) {

				String methodName = element.getMethodName();
				String fileName = element.getFileName();

				System.out.println("----------------------> START ERRORS <-----------------------");
				System.out.println(fileName);
				System.out.println(methodName);
				System.out.println("LineNO: " + element.getLineNumber());

				System.out.println("----------------------> END ERRORS <-----------------------");

				String traceInfo = methodName;
				if (element != null && element.getLineNumber() > 0) {
					return traceInfo = traceInfo + " -> " + element.getLineNumber();
				}
			}
		}

		return "Unknown parameter";
	}

	private ResponseEntity<Object> buildResponseEntity(final APIResponse response, final HttpServletRequest request,
			final Exception exception) {
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
