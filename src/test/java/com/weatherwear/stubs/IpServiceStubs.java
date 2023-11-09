package com.weatherwear.stubs;

import com.weatherwear.services.IpService;

public class IpServiceStubs implements IpService {
    //these stubs also work as spies because i can only inject one dependency per interface
    public int getIpAddressCallCount = 0;
    String sampleIp = "sampleIp";

    @Override
    public String getIpAddress() {
        getIpAddressCallCount++;
        return sampleIp;
    }

}
