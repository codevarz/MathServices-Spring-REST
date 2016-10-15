package xcal.cs.math.model;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubtractionResponse {

	@JsonProperty
	@NotNull
	private Integer difference;

	public SubtractionResponse(int difference) {
		this.difference = difference;
	}
	  
	@SuppressWarnings("unused")
	private SubtractionResponse(){}

	public int getDifference() {
		return difference;
	}
	
	
}
