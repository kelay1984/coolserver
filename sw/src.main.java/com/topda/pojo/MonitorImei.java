package com.topda.pojo;

import java.io.Serializable;
import java.util.Date;

public class MonitorImei implements Serializable {
    private Integer id;

    private String imei;

    private Date updatetime;

    private String boxsn;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getBoxsn() {
        return boxsn;
    }

    public void setBoxsn(String boxsn) {
        this.boxsn = boxsn == null ? null : boxsn.trim();
    }
}