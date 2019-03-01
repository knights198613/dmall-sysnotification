package com.dmall.sysnotification.listener;

import org.I0Itec.zkclient.IZkStateListener;
import org.apache.zookeeper.Watcher;

/**
 * Creator: jiang.wei
 * Date: 2019/2/26
 * DESC:
 */

public class ZkStateChangeListener implements IZkStateListener {

    @Override
    public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {

    }

    @Override
    public void handleNewSession() throws Exception {

    }
}
