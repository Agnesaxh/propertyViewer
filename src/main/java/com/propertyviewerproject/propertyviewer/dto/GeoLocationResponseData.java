package com.propertyviewerproject.propertyviewer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoLocationResponseData {

    private double lon;
    private double lat;
}
