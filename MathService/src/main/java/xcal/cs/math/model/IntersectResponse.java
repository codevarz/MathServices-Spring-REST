package xcal.cs.math.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

import javax.validation.constraints.NotNull;

public class IntersectResponse {

  @JsonProperty
  @NotNull
  private Collection<Integer> intersection;

  public IntersectResponse(Collection<Integer> intersection) {
    this.intersection = intersection;
  }

  @SuppressWarnings("unused") // for Jackson
  private IntersectResponse() {}

  public Collection<Integer> getIntersection() {
    return intersection;
  }
}
