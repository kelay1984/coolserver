package com.topda.dao;

import com.topda.pojo.MonitorInit;
import com.topda.pojo.MonitorInitExample;
import java.util.List;

public interface MonitorInitDao {
    int countByExample(MonitorInitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MonitorInit record);

    int insertSelective(MonitorInit record);

    List<MonitorInit> selectByExample(MonitorInitExample example);

    MonitorInit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MonitorInit record);

    int updateByPrimaryKey(MonitorInit record);
}