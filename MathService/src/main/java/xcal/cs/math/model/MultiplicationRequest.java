package xcal.cs.math.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class MultiplicationRequest {

  @JsonProperty
  @NotNull
  private Long multiplicand;

  @JsonProperty
  @NotNull
  private Long multiplier;

  public MultiplicationRequest(long multiplicand, long multiplier) {
    this.multiplicand = multiplicand;
    this.multiplier = multiplier;
  }

  @SuppressWarnings("unused") // for Jackson
  private MultiplicationRequest() {}

  public long getMultiplicand() {
    return multiplicand;
  }

  public long getMultiplier() {
    return multiplier;
  }
}
