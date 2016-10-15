package xcal.cs.math.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubtractionRequest {

	@JsonProperty
	@NotNull
	private Integer minuend;
	
	@JsonProperty
	@NotNull
	private Integer subtrahend;

	public SubtractionRequest(int minuend, int subtrahend) {
		this.minuend = minuend;
		this.subtrahend = subtrahend;
	}
	
	@SuppressWarnings("unused")
	private SubtractionRequest(){}

	public int getMinuend() {
		return minuend;
	}

	public Integer getSubtrahend() {
		return subtrahend;
	}

	
}
