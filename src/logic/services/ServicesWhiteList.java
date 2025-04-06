package logic.services;

import java.util.ArrayList;

public class ServicesWhiteList {
	public ServicesWhiteList() {}
	
	public ArrayList<String> whiteListServices(){
		ArrayList<String> whiteList = new ArrayList<String>();
		whiteList.add("Appinfo");
		whiteList.add("AppXSvc");
		whiteList.add("AudioEndpointBuilder");
		whiteList.add("Audiosrv");
		whiteList.add("BFE");
		whiteList.add("BrokerInfrastructure");
		whiteList.add("camsvc");
		whiteList.add("COMSysApp");
		whiteList.add("CoreMessagingRegistrar");
		whiteList.add("CryptSvc");
		whiteList.add("DcomLaunch");
		whiteList.add("Dhcp");
		whiteList.add("Dnscache");
		whiteList.add("EventSystem");
		whiteList.add("gpsvc");
		whiteList.add("IpxlatCfgSvc");
		whiteList.add("LSM");
		whiteList.add("MDCoreSvc");
		whiteList.add("mpssvc");
		whiteList.add("Netman");
		whiteList.add("netprofm");
		whiteList.add("nsi");
		whiteList.add("Power");
		whiteList.add("ProfSvc");
		whiteList.add("RpcEptMapper");
		whiteList.add("RpcLocator");
		whiteList.add("RpcSs");
		whiteList.add("SamSs");
		whiteList.add("Schedule");
		whiteList.add("SecurityHealthService");
		whiteList.add("SENS");
		whiteList.add("StateRepository");
		whiteList.add("SystemEventsBroker");
		whiteList.add("TextInputManagementService");
		whiteList.add("TimeBrokerSvc");
		whiteList.add("UserManager");
		whiteList.add("VaultSvc");
		whiteList.add("Wcmsvc");
		whiteList.add("WdNisSvc");
		whiteList.add("WinDefend");
		whiteList.add("WinHttpAutoProxySvc");
		whiteList.add("WlanSvc");
		whiteList.add("wscsvc");
		return whiteList;
	}
}
