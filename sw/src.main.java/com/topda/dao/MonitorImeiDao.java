package com.topda.dao;

import com.topda.pojo.MonitorImei;
import com.topda.pojo.MonitorImeiExample;
import java.util.List;

public interface MonitorImeiDao {
    int countByExample(MonitorImeiExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MonitorImei record);

    int insertSelective(MonitorImei record);

    List<MonitorImei> selectByExample(MonitorImeiExample example);

    MonitorImei selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MonitorImei record);

    int updateByPrimaryKey(MonitorImei record);
}