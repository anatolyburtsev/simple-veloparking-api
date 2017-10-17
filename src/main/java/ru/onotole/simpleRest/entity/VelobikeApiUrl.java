package ru.onotole.simpleRest.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Getter
@ConfigurationProperties(prefix = "velobikeurl")
public class VelobikeApiUrl {
    public final static String path = "/ride/parkings";
    public String host;
}
