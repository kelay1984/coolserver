package com.topda.common;

public class Constant {
    //[AlamLight]
    public static byte bLight = 0x00;                                                  
    public static byte bState = 0x05;                         //状态字                 
    //[CollectTimeInterval]
    public static byte bCollCycle = 0x01;                     //采集时间周期           
    //[StorageTimeInterval]
    public static byte bStoreCycle = 0x05;                    //存储时间周期           
    //AlarmStorageTimeInterval
    public static byte bSupStoreCycle = 0x02;                 //超警存储时间周期       
    //SendTimeInterval
    public static byte bUpCycle = 0x05;                       //上传时间周期           
    //AlamSendTimeInterval
    public static byte bSupUpCycle = 0x05;                    //超警上传时间周期       
    //HighTemperatureAlam
    public static byte bSHWaring = 0x08;                      //超高警温度阈值         
    //HighTemperatureLimit
    public static byte bSHRestriction = 0x07;                 //超高限温度阈值         
    //LowTemperatureAlam
    public static byte bSLWaring = 0x03;                      //超低警温度阈值        
    //LowTemperatureLimt
    public static byte bSLRestriction = 0x02;                 //超低限温度阈值
    public static byte bReserve = 0x00;                       //保留字段
}
