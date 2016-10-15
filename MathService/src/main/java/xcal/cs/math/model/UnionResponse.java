package xcal.cs.math.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

import javax.validation.constraints.NotNull;

public class UnionResponse {

  @JsonProperty
  @NotNull
  private Collection<Integer> union;

  public UnionResponse(Collection<Integer> union) {
    this.union = union;
  }

  @SuppressWarnings("unused") // for Jackson
  private UnionResponse() {}

  public Collection<Integer> getUnion() {
    return union;
  }
}
