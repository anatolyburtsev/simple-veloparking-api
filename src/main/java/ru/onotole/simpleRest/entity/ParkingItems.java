package ru.onotole.simpleRest.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ParkingItems {
    public ParkingItems(List<Parking> items) {
        this.items = items;
    }

    @SerializedName("Items")
    private List<Parking> items;
}
