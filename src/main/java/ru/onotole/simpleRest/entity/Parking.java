package ru.onotole.simpleRest.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Parking {
    @SerializedName("Id")
    private String id;

    @SerializedName("Position")
    private Position position;

    @SerializedName("FreePlaces")
    private Integer freePlaces;

    @SerializedName("TotalPlaces")
    private Integer totalPlaces;
}
