package ru.pakage1.tests;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

public class GeoIpServiceTest {

    @Test
    public void testMyIP(){

        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("192.168.1.252");
        System.out.println("Локация - "+ipLocation);
    }

}

