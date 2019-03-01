package com.dmall.sysnotification.watched;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * Creator: jiang.wei
 * Date: 2019/2/26
 * DESC: 监听者操作接口
 */

public interface WatcherOperater {

    /**
     * 添加节点监听器，如果存在路径层级，路径不存在就循环创建路径层级
     * @param zkDataListener
     * @param path
     * @param nodeData
     * @param createMode  节点类型
     */
    void addDataChangeWatcher(IZkDataListener zkDataListener, String path, Object nodeData, CreateMode createMode);


    void addStateChangeWatcher(IZkStateListener zkStateListener);


    List<String> addChildrenChangeWatcher(IZkChildListener zkChildListener, String path);




}
