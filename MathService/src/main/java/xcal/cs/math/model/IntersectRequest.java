package xcal.cs.math.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

import javax.validation.constraints.NotNull;

public class IntersectRequest {

  @JsonProperty
  @NotNull
  private Collection<Integer> left;

  @JsonProperty
  @NotNull
  private Collection<Integer> right;

  public IntersectRequest(Collection<Integer> left, Collection<Integer> right) {
    this.left = left;
    this.right = right;
  }

  @SuppressWarnings("unused") // for Jackson
  private IntersectRequest() {}

  public Collection<Integer> getLeft() {
    return left;
  }

  public Collection<Integer> getRight() {
    return right;
  }
}
