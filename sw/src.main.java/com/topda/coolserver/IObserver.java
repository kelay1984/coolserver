package com.topda.coolserver;

public interface IObserver {
    /**
     * 更新接口
     * @param state    更新的状态
     */
    public void update(String state);
}
