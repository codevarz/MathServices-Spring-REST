package xcal.cs.math.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class AdditionRequest {

  @JsonProperty
  @NotNull
  private Integer augend;

  @JsonProperty
  @NotNull
  private Integer addend;

  public AdditionRequest(int augend, int addend) {
    this.augend = augend;
    this.addend = addend;
  }

  @SuppressWarnings("unused") // for Jackson
  private AdditionRequest() {}

  public int getAugend() {
    return augend;
  }

  public int getAddend() {
    return addend;
  }
}
