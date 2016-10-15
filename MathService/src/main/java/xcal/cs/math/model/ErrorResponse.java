package xcal.cs.math.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

  @JsonProperty
  private String message;

  public ErrorResponse(String message) {
    this.message = message;
  }

  @SuppressWarnings("unused") // for Jackson
  private ErrorResponse() {}

  public String getMessage() {
    return message;
  }
}
