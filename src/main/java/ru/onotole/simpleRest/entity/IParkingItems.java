package ru.onotole.simpleRest.entity;

import feign.RequestLine;
import org.springframework.beans.factory.annotation.Autowired;

public interface IParkingItems {

    @RequestLine("GET " + VelobikeApiUrl.path)
    ParkingItems parkings();
}
