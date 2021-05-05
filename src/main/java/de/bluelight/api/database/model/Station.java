package de.bluelight.api.database.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long stationId;
    private String name;
    private double latitude;
    private double longitude;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "stationId", referencedColumnName = "stationId")
    private List<Vehicle> vehicles;

    public Station(long stationId, String name, double latitude, double longitude, List<Vehicle> vehicles) {
        this.stationId = stationId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.vehicles = vehicles;
    }

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
