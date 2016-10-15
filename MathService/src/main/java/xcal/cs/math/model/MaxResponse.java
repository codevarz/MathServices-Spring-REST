package xcal.cs.math.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class MaxResponse {

  @JsonProperty
  @NotNull
  private Integer max;

  public MaxResponse(int max) {
    this.max = max;
  }

  @SuppressWarnings("unused") // for Jackson
  private MaxResponse() {}

  public int getMax() {
    return max;
  }
}
