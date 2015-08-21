package com.topda.dao;

import com.topda.pojo.MonitorTemperature;
import com.topda.pojo.MonitorTemperatureExample;
import java.util.List;

public interface MonitorTemperatureDao {
    int countByExample(MonitorTemperatureExample example);

    int deleteByPrimaryKey(Integer keyid);

    int insert(MonitorTemperature record);

    int insertSelective(MonitorTemperature record);

    List<MonitorTemperature> selectByExample(MonitorTemperatureExample example);

    MonitorTemperature selectByPrimaryKey(Integer keyid);

    int updateByPrimaryKeySelective(MonitorTemperature record);

    int updateByPrimaryKey(MonitorTemperature record);
}