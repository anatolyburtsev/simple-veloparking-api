package ru.onotole.simpleRest.entity;

import feign.RequestLine;

public interface IParkingItems {

    @RequestLine("GET " + VelobikeApiUrl.PATH)
    ParkingItems parkings();
}
