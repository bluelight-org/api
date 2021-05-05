package de.bluelight.api.database.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "missions")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long missionId;
    private String address;
    private double latitude;
    private double longitude;
    private String keyword;
    private String details;
    private boolean alerted;
    private long alertTime;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "missionId", referencedColumnName = "missionId")
    private List<Vehicle> vehicles;

    public Mission(long missionId, String address,
                   double latitude,
                   double longitude,
                   String keyword,
                   String details,
                   boolean alerted,
                   long alertTime,
                   List<Vehicle> vehicles) {
        this.missionId = missionId;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.keyword = keyword;
        this.details = details;
        this.alerted = alerted;
        this.alertTime = alertTime;
        this.vehicles = vehicles;
    }

    public long getMissionId() {
        return missionId;
    }

    public void setMissionId(long missionId) {
        this.missionId = missionId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isAlerted() {
        return alerted;
    }

    public void setAlerted(boolean alerted) {
        this.alerted = alerted;
    }

    public long getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(long alertTime) {
        this.alertTime = alertTime;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
