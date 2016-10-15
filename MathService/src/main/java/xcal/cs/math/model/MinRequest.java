package xcal.cs.math.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MinRequest {

  @JsonProperty
  @NotNull
  @Size(min = 1)
  private Collection<Integer> numbers;

  public MinRequest(Collection<Integer> numbers) {
    this.numbers = numbers;
  }

  @SuppressWarnings("unused") // for Jackson
  private MinRequest() {}

  public Collection<Integer> getNumbers() {
    return numbers;
  }
}
