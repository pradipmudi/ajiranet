package com.ajira.device.process.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ajira.device.response.Response;
import com.ajira.device.service.manager.ServiceManager;
import com.ajira.device.util.JsonUtil;

@Path("/process")
public class ProcessResources {
	
	ServiceManager serviceManager = new ServiceManager();

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getMessages(String data) {
		Response response = new Response();
		data = data.replaceAll("content-type : application/json", "");
		if(data.contains("CREATE")) {
			data = data.replaceAll("CREATE", "");
			if(data.contains("/devices")) {	
				// adding new device
				response =  serviceManager.addDevice(data);
			}else if(data.contains("/connections")){
				// adding new connections
				response =  serviceManager.addConnections(data);
			}
		}
		else if(data.contains("FETCH")) {
			data = data.replaceAll("FETCH", "");
			if(data.contains("/devices")) {
				// fetching device lists
				return JsonUtil.getInstance().toJson(serviceManager.fetchDevices(data));
			}else if(data.contains("/info-routes")) {
				// fetching route informations
				response = serviceManager.fetchRoutes(data);
			}
		}
		else if(data.contains("MODIFY")) {
			data = data.replaceAll("MODIFY", "");
			if(data.contains("/devices")) {
				// Modifying signal strengths
				response = serviceManager.updateSignalStrength(data);
			}
		}
		return JsonUtil.getInstance().toJson(response);
	}
	
}
