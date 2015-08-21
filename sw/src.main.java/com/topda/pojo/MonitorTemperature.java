package com.topda.pojo;

import java.io.Serializable;
import java.util.Date;

public class MonitorTemperature implements Serializable {
    private Integer keyid;

    private Date datehappen;

    private Float temperature;

    private Date serverdatetime;

    private String boxsn;

    private Integer power;

    private Integer doorstate;

    private Integer supplyid;

    private Integer swarning;

    private Integer srestriction;

    private Integer lrestriction;

    private Integer lwarning;

    private Integer reserve;

    private Float outtemperature;

    private static final long serialVersionUID = 1L;

    public Integer getKeyid() {
        return keyid;
    }

    public void setKeyid(Integer keyid) {
        this.keyid = keyid;
    }

    public Date getDatehappen() {
        return datehappen;
    }

    public void setDatehappen(Date datehappen) {
        this.datehappen = datehappen;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Date getServerdatetime() {
        return serverdatetime;
    }

    public void setServerdatetime(Date serverdatetime) {
        this.serverdatetime = serverdatetime;
    }

    public String getBoxsn() {
        return boxsn;
    }

    public void setBoxsn(String boxsn) {
        this.boxsn = boxsn == null ? null : boxsn.trim();
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getDoorstate() {
        return doorstate;
    }

    public void setDoorstate(Integer doorstate) {
        this.doorstate = doorstate;
    }

    public Integer getSupplyid() {
        return supplyid;
    }

    public void setSupplyid(Integer supplyid) {
        this.supplyid = supplyid;
    }

    public Integer getSwarning() {
        return swarning;
    }

    public void setSwarning(Integer swarning) {
        this.swarning = swarning;
    }

    public Integer getSrestriction() {
        return srestriction;
    }

    public void setSrestriction(Integer srestriction) {
        this.srestriction = srestriction;
    }

    public Integer getLrestriction() {
        return lrestriction;
    }

    public void setLrestriction(Integer lrestriction) {
        this.lrestriction = lrestriction;
    }

    public Integer getLwarning() {
        return lwarning;
    }

    public void setLwarning(Integer lwarning) {
        this.lwarning = lwarning;
    }

    public Integer getReserve() {
        return reserve;
    }

    public void setReserve(Integer reserve) {
        this.reserve = reserve;
    }

    public Float getOuttemperature() {
        return outtemperature;
    }

    public void setOuttemperature(Float outtemperature) {
        this.outtemperature = outtemperature;
    }
}