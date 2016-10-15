package xcal.cs.math.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class AdditionResponse {

  @JsonProperty
  @NotNull
  private Integer sum;

  public AdditionResponse(int sum) {
    this.sum = sum;
  }

  @SuppressWarnings("unused") // for Jackson
  private AdditionResponse() {}

  public int getSum() {
    return sum;
  }
}
