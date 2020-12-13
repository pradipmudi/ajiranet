package com.ajira.device.data.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ajira.device.bo.Device;
import com.ajira.device.bo.SignalStrength;

public class DataStorage {
	private static Map<String, List<String>> connectionsMap = new HashMap<>();
	private static Map<String, Device> devicesMap = new HashMap<String, Device>();
	private static List<Device> devicesList = new ArrayList<Device>();
	private static Map<String, SignalStrength> signalStrengthMap = new HashMap<String, SignalStrength>();

	public Map<String, SignalStrength> getSignalStrengthMap() {
		return signalStrengthMap;
	}

	public static void setSignalStrengthMap(Map<String, SignalStrength> signalStrengthMap) {
		DataStorage.signalStrengthMap = signalStrengthMap;
	}

	public Map<String, Device> getDevicesMap() {
		return devicesMap;
	}

	public void setDevicesMap(Map<String, Device> devicesMap) {
		DataStorage.devicesMap = devicesMap;
	}

	
	public List<Device> getDevicesList() {
		return devicesList;
	}

	public void setDevicesList(List<Device> devicesList) {
		DataStorage.devicesList = devicesList;
	}

	public Map<String, List<String>> getConnectionsMap() {
		return connectionsMap;
	}

	public void setConnectionsMap(Map<String, List<String>> connectionsMap) {
		DataStorage.connectionsMap = connectionsMap;
	}
	
}
