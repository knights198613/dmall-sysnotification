package com.dmall.sysnotification.watched.impl;

import com.dmall.sysnotification.enums.ExceptionMsgEnums;
import com.dmall.sysnotification.exception.PathillegalException;
import com.dmall.sysnotification.factory.ZkFactory;
import com.dmall.sysnotification.utils.PathUtils;
import com.dmall.sysnotification.watched.WatcherOperater;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;

import java.io.File;
import java.util.List;

/**
 * Creator: jiang.wei
 * Date: 2019/2/26
 * DESC: 监听者操作器
 */

public class WatcherOperaterImpl implements WatcherOperater {
    private ZkFactory zkFactory;

    public WatcherOperaterImpl(ZkFactory zkFactory) {
        this.zkFactory = zkFactory;
    }

    @Override
    public void addDataChangeWatcher(IZkDataListener zkDataListener, String path, Object nodeData, CreateMode createMode) {
        if(StringUtils.isBlank(path)) {
            throw new PathillegalException(ExceptionMsgEnums.PATH_NULL_EXC);
        }else {
            String tmpPath = PathUtils.appendPaths(new String[]{zkFactory.getAppName()});
            if (!createMode.isEphemeral()) {
                if (PathUtils.hasSubPath(path)) {
                    String[] subPaths = StringUtils.split(path, File.separator);
                    for (int i = 0; i < subPaths.length; i++) {
                        if (!hasNode(File.separator + subPaths[i])) {
                            tmpPath = PathUtils.appendPaths(tmpPath, subPaths[i]);
                            if (i < subPaths.length - 1) {
                                createZnode(tmpPath, null, createMode);
                            } else {
                                createZnode(tmpPath, nodeData, createMode);
                            }
                        }
                    }
                } else {
                    createZnode(path, nodeData, createMode);
                }
            } else {
                if(PathUtils.hasSubPath(path)) {
                    throw new PathillegalException(ExceptionMsgEnums.PATH_NO_SUB_FOR_EPHEMERAL);
                }else {
                    tmpPath = PathUtils.appendPaths(tmpPath, path);
                    createZnode(tmpPath, nodeData, createMode);
                }
            }
            zkFactory.getZkClient().subscribeDataChanges(PathUtils.appendPaths(new String[]{zkFactory.getAppName(), path}), zkDataListener);
        }
    }

    @Override
    public void addStateChangeWatcher(IZkStateListener zkStateListener) {
        zkFactory.getZkClient().subscribeStateChanges(zkStateListener);
    }

    @Override
    public List<String> addChildrenChangeWatcher(IZkChildListener zkChildListener, String path) {
        return zkFactory.getZkClient().subscribeChildChanges(PathUtils.appendPaths(new String[]{zkFactory.getAppName(), path}), zkChildListener);
    }

    /**
     * 判断节点是否存在
     * @param nodePath
     * @return
     */
    private boolean hasNode(String nodePath) {
        return zkFactory.getZkClient().exists(nodePath);
    }

    /**
     * 创建节点
     * @param nodePath
     * @param nodeData
     * @param createMode
     */
    private void createZnode(String nodePath, Object nodeData, CreateMode createMode) {
        zkFactory.getZkClient().create(nodePath, nodeData, createMode);
    }

}
