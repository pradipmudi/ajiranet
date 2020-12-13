package com.ajira.device.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"source",
	"targets"
})
public class Connection {
	
	@JsonProperty("source")
	private String source;
	@JsonProperty("targets")
	private List<String> targets = null;
//	@JsonIgnore
//	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	@JsonProperty("source")
	public String getSource() {
		return source;
	}
	
	@JsonProperty("source")
	public void setSource(String source) {
		this.source = source;
	}
	
	@JsonProperty("targets")
	public List<String> getTargets() {
		return targets;
	}
	

	@JsonProperty("targets")
	public void setTargets(List<String> targets) {
		this.targets = targets;
	}
	
	@Override
	public String toString() {
		return "Connection [source=" + source + ", targets=" + targets + "]";
	}
//	@JsonAnyGetter
//	public Map<String, Object> getAdditionalProperties() {
//		return this.additionalProperties;
//	}
//	
//	@JsonAnySetter
//	public void setAdditionalProperty(String name, Object value) {
//		this.additionalProperties.put(name, value);
//	}
	
}
