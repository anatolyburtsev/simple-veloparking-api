package ru.onotole.simpleRest.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class ParkingItems {
    @SerializedName("Items")
    private List<Parking> items;
}
