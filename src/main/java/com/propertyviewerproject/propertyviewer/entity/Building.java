package com.propertyviewerproject.propertyviewer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BUILDING_TABLE")
public class Building {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String street;
    private String postalCode;
    private String city;
    private String country;
    private String description;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private int lon;
    private int lat;

}
