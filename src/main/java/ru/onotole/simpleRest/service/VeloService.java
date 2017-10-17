package ru.onotole.simpleRest.service;

import feign.Feign;
import feign.gson.GsonDecoder;
import lombok.extern.java.Log;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onotole.simpleRest.entity.VelobikeApiUrl;
import ru.onotole.simpleRest.entity.IParkingItems;
import ru.onotole.simpleRest.entity.Parking;
import ru.onotole.simpleRest.entity.ParkingItems;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Log
public class VeloService {

    @Autowired
    private VelobikeApiUrl velobikeApiUrl;

    public ParkingItems getAllParkingItems() {
        return getAllParking();
    }

    public ParkingItems getNearestParking(Double lat, Double lon, Boolean needFreePlace) {
        ParkingItems items = getAllParkingItems();
        double distanceNearest = calculateDistance(lat, lon, items.getItems().get(0));
        String idNearest = items.getItems().get(0).getId();
        for (Parking parking: items.getItems()) {
            if (needFreePlace != null) {
                if (needFreePlace && parking.getFreePlaces() == 0) {
                    continue;
                }
                if (!needFreePlace && Objects.equals(parking.getFreePlaces(), parking.getTotalPlaces())) {
                    continue;
                }
            }

            double distance = calculateDistance(lat, lon, parking);
            if (distance < distanceNearest) {
                distanceNearest = distance;
                idNearest = parking.getId();
            }
        }
        String idFinalNearest = idNearest;
        return new ParkingItems(items.getItems()
            .stream()
            .filter(p -> StringUtils.equals(idFinalNearest, p.getId()))
            .collect(Collectors.toList()));
    }

    private double calculateDistance(Double currentLat, Double currentLon, Parking parking) {
        return Math.sqrt(
            Math.pow(parking.getPosition().getLat() - currentLat, 2) +
                Math.pow(parking.getPosition().getLon() - currentLon, 2)
        );
    }

    private ParkingItems getAllParking() {
        IParkingItems parkingItems = Feign.builder()
            .decoder(new GsonDecoder())
            .target(IParkingItems.class, velobikeApiUrl.getHost());
        log.warning(parkingItems.parkings().toString());
        return parkingItems.parkings();
    }

}
