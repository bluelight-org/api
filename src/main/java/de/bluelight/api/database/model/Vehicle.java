package de.bluelight.api.database.model;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long vehicleId;
    private int fms;
    private String name;
    private String type;

    public Vehicle() {
    }

    public Vehicle(long vehicleId, int fms, String name, String type) {
        this.vehicleId = vehicleId;
        this.fms = fms;
        this.name = name;
        this.type = type;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getFms() {
        return fms;
    }

    public void setFms(int fms) {
        this.fms = fms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
