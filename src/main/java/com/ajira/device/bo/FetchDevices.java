package com.ajira.device.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"devices"
})
public class FetchDevices {
	
	@JsonProperty("devices")
	private List<Device> devices = null;
	
	@JsonProperty("devices")
	public List<Device> getDevices() {
		return devices;
	}
	
	@JsonProperty("devices")
	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	
	
}
