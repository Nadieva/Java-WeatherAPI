package com.nadia.weather.entity.city;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity // This tells Hibernate to make a table out of this class
public class City {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name="city_name")
    @NotBlank(message = "City name is mandatory")
    private String cityName;
    @Column(name="country")
    @NotBlank(message = "Country name is mandatory")
    private String country;
}
