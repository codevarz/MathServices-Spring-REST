package xcal.cs.math.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class MinResponse {

  @JsonProperty
  @NotNull
  private Integer min;

  public MinResponse(int min) {
    this.min = min;
  }

  @SuppressWarnings("unused") // for Jackson
  private MinResponse() {}

  public int getMin() {
    return min;
  }
}
