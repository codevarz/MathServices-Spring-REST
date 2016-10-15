package xcal.cs.math.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class MultiplicationResponse {

  @JsonProperty
  @NotNull
  private Long product;

  public MultiplicationResponse(long product) {
    this.product = product;
  }

  @SuppressWarnings("unused") // for Jackson
  private MultiplicationResponse() {}

  public long getProduct() {
    return product;
  }
}
