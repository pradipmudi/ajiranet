package com.ajira.device.service.manager;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.ajira.device.bo.Connection;
import com.ajira.device.bo.Device;
import com.ajira.device.bo.FetchDevices;
import com.ajira.device.bo.SignalStrength;
import com.ajira.device.data.storage.DataStorage;
import com.ajira.device.response.Response;
import com.ajira.device.util.JsonUtil;

public class ServiceManager {

	private static DataStorage dataStorage = new DataStorage();
	Response response = new Response();

	public Response addDevice(String data) {
		try {
			HashSet<String> deviceTypesMap = new HashSet<String>();
			
			deviceTypesMap.add("COMPUTER");
			deviceTypesMap.add("REPEATER");
			
			data = data.replaceAll("/devices", "");
			Device device = (Device) JsonUtil.getInstance().fromJson(data, Device.class);
			//dataStorage.setDevicesMap(null);
			if(!deviceTypesMap.contains(device.getType())) {
				response.setMsg("type '"+device.getType()+"' is not supported");
				return response;
			}
			
			Map<String, Device> devicesMap = dataStorage.getDevicesMap();
			List<Device> devicesList = dataStorage.getDevicesList();
			Map<String, SignalStrength> signalStrengthMap = dataStorage.getSignalStrengthMap();
			if(devicesMap.containsKey(device.getName())) {
				response.setMsg("Device '"+device.getName()+"' already exists");
			}else {
				devicesMap.put(device.getName(), device);
				devicesList.add(device);
				
				SignalStrength signalStrength = new SignalStrength();
				signalStrength.setValue(5);
				signalStrengthMap.put(device.getName(), signalStrength);
				
				response.setMsg("Successfully added "+device.getName());
			}
			
			dataStorage.setDevicesMap(devicesMap);
			dataStorage.setDevicesList(devicesList);
			System.out.println(device.toString());
		} catch (Exception e) {
			response.setMsg("Invalid command.");
		}
		return response;
	}

	public Response addConnections(String data) {
		try {
			
			Map<String, List<String>> connectionsMap = dataStorage.getConnectionsMap();
			data = data.replaceAll("/connections", "");
			Connection connection = (Connection) JsonUtil.getInstance().fromJson(data, Connection.class);
			if(connection.getTargets().size() == 1 && connection.getSource().equals(connection.getTargets().get(0))) {
				response.setMsg("Cannot connect device to itself");
				return response;
			}
			Map<String, Device> devicesMap = dataStorage.getDevicesMap();
			if(connectionsMap.containsKey(connection.getSource()) && devicesMap.containsKey(connection.getSource())) {
				// Device is present and also 
				// connection is there with other devices
				List<String> nodeConnections = connectionsMap.get(connection.getSource());
				for(String node : connection.getTargets()) {
					if(nodeConnections.contains(node)) {
						response.setMsg("Devices are already connected");
						return response;
					}else {
						nodeConnections.add(node);
					}
				}
				connectionsMap.put(connection.getSource(), nodeConnections);
				response.setMsg("Successfully connected");
			}else if(devicesMap.containsKey(connection.getSource()) && !connectionsMap.containsKey(connection.getSource())) {
				// if no connections has been added previously, 
				// adding a connection if the device is present
				connectionsMap.put(connection.getSource(), connection.getTargets());
				response.setMsg("Successfully connected");
			}
			else {
				// Device is not present
				response.setMsg("Node '"+connection.getSource()+"' not found");
			}
			
			// updating the device connections map for future references for a session 
			dataStorage.setConnectionsMap(connectionsMap);
		} catch (Exception e) {
			response.setMsg("Invalid command syntax");
		}
		return response;
	}

	public FetchDevices fetchDevices(String data) {
		List<Device> devicesList = dataStorage.getDevicesList();
		FetchDevices devices = new FetchDevices();
		devices.setDevices(devicesList);
		return devices;
	}

	public Response fetchRoutes(String data) {
		try {
			data = data.replaceAll(" /info-routes", "").replace("?", "");
			if(data.contains("from") && data.contains("to")) {
				data = data.replace("from", "").replace("to", "").replace("=", "");
				Map<String, Device> devicesMap = dataStorage.getDevicesMap();
				String[] nodes = data.split("\\&");
				if(nodes[0].contains("R") || nodes[1].contains("R")) {
					// assuming Device names with R are repeaters
					response.setMsg("Route cannot be calculated with repeater");
					return response;
				}
				for(String node : nodes) {					
					if(!devicesMap.containsKey(node)) {
						response.setMsg("Node '"+node+"' not found");
						return response;
					}
				}
				String route = "";
				if(nodes[0].equals(nodes[1])) {
					response.setMsg("Route is "+nodes[0]+"->"+nodes[0]);
					return response;
				}
				Map<String, List<String>> connectionsMap = dataStorage.getConnectionsMap();
				boolean found = false;
				if(connectionsMap.containsKey(nodes[0])) {					
					route += nodes[0]+"->";
					found = true;
				}
				if(!connectionsMap.get(nodes[0]).contains(nodes[1])) {
					response.setMsg("Route not found");
					return response;
				}
				for(String node : connectionsMap.get(nodes[0])) {
					if(found) {
						if(node.equals(nodes[1])) {
							route += node;
							break;
						}
						route += node+"->";
					}
					
				}
				response.setMsg("Route is "+route);
				
			}else {
				response.setMsg("Invalid Request");
			}
			
		} catch (Exception e) {
			response.setMsg("Invalid Request");
		}
		return response;
	}

	public Response updateSignalStrength(String data) throws NumberFormatException{
		try {
			data = data.replaceAll("/devices/", "").replaceAll("\n", "").replace("strength", "");
			String[] nodes = data.split("/");
			Map<String, Device> devicesMap = dataStorage.getDevicesMap();
			Map<String, SignalStrength> signalStrengthMap = dataStorage.getSignalStrengthMap();
			String node = nodes[0].replaceAll(" ", "");
			if(devicesMap.get(node).getType().equals("REPEATER")) {
				response.setMsg("Repeater strength cannot be defined.");
			}else if(devicesMap.containsKey(node)) {
				SignalStrength signalStrength = (SignalStrength) JsonUtil.getInstance().fromJson(nodes[1], SignalStrength.class);
				if(signalStrength.getValue()<0)
					response.setMsg("Negative Signal strenth cannot be assigned");
				else {					
					signalStrengthMap.put(node, signalStrength);
					response.setMsg("Successfully defined strength");
				}
			}else if(!devicesMap.containsKey(nodes[0])){
				response.setMsg("Device Not Found");				
			}
		} catch (Exception e) {
			response.setMsg("value should be an integer");
		}
		return response;
	}  

}
