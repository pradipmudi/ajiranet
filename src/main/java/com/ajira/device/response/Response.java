package com.ajira.device.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"msg"
})
public class Response {
	
	@JsonProperty("msg")
	private String msg;
	
	@JsonProperty("msg")
	public String getMsg() {
		return msg;
	}
	
	@JsonProperty("msg")
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}