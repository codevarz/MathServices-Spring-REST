package xcal.cs.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import xcal.cs.math.model.ErrorResponse;

@ControllerAdvice
public class ErrorHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ErrorHandler.class);

  @ExceptionHandler(ArithmeticException.class)
  public ResponseEntity<ErrorResponse> handleArithmeticException(ArithmeticException exception) {
    LOG.error("Caught ArithmeticException; returning error response.", exception);
    return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
  }
}
