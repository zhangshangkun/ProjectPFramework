package com.zsk.idlefish.tools;

import com.zsk.idlefish.utils.NetStatusUtil;

/**
 * 网络改变观察者，观察网络改变后回调的方法
 */
public interface NetChangeObserver {

    /**
     * 网络连接回调 type为网络类型
     */
     void onNetConnected(NetStatusUtil.NetState type);

    /**
     * 没有网络
     */
     void onNetDisConnect();
}
