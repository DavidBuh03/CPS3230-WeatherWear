package com.weatherwear.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class OnlineIpService implements IpService {
    @Override
    public String getIpAddress() {
        try {
            String ip;
            URL ipServiceUrl = new URL("http://checkip.amazonaws.com");
            BufferedReader response = new BufferedReader(new InputStreamReader(ipServiceUrl.openStream()));
            ip = response.readLine();
            return ip;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
