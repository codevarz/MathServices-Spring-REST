package xcal.cs.math.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class DivisionRequest {

  @JsonProperty
  @NotNull
  private Integer dividend;

  @JsonProperty
  @NotNull
  private Integer divisor;

  public DivisionRequest(int dividend, int divisor) {
    this.dividend = dividend;
    this.divisor = divisor;
  }

  @SuppressWarnings("unused") // for Jackson
  private DivisionRequest() {}

  public int getDividend() {
    return dividend;
  }

  public int getDivisor() {
    return divisor;
  }
}
