package ru.onotole.simpleRest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.onotole.simpleRest.entity.ParkingItems;
import ru.onotole.simpleRest.service.VeloService;

@RestController
@Api(value = "velo parkings", description = "Description for velo parking api")
public class VeloParkingController {

    @Autowired
    private VeloService veloService;

    @GetMapping(value = "/parking")
    @ApiOperation(value = "return all velo parkings")
    public ParkingItems veloParking() {
        return veloService.getAllParkingItems();
    }

    @GetMapping(value = "/nearestParking")
    @ApiOperation(value = "find nearest parking")
    public ParkingItems nearestParking(
        @RequestParam(value = "lat", required = true) Double lat,
        @RequestParam(value = "lon", required = true) Double lon,
        @RequestParam(value = "needFreePlace", required = false) Boolean needFreePlace
    ) {
        return veloService.getNearestParking(lat, lon, needFreePlace);
    }
}
