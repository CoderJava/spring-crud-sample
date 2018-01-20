package com.ysn.springmvc.model.location;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location {

    @GenericGenerator(
            name = "locationSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "locationSequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )

    @Id
    @GeneratedValue(generator = "locationSequenceGenerator")
    private long id;
    private String datetime;
    /*private double latitude;
    private double longitude;
    private int speed;*/

    public Location() {
    }

    /*public Location(long id, String datetime, double latitude, double longitude, int speed) {
        this.id = id;
        this.datetime = datetime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
    }*/

    public Location(long id, String datetime) {
        this.id = id;
        this.datetime = datetime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }


    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
