package com.ajira.device.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"value"
})
public class SignalStrength {
	
	@JsonProperty("value")
	private Integer value;
	
	@JsonProperty("value")
	public Integer getValue() {
		return value;
	}
	
	@JsonProperty("value")
	public void setValue(Integer value) {
		this.value = value;
	}
	
}