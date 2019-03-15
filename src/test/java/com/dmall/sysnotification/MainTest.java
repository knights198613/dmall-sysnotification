package com.dmall.sysnotification;

import com.dmall.sysnotification.factory.ZkFactory;
import com.dmall.sysnotification.listener.ZkDataChangeListener;
import com.dmall.sysnotification.listener.ZkStateChangeListener;
import com.dmall.sysnotification.utils.PathUtils;
import com.dmall.sysnotification.watched.WatcherOperater;
import com.dmall.sysnotification.watched.impl.WatcherOperaterImpl;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Creator: jiang.wei
 * Date: 2019/2/27
 * DESC:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/spring-cfg.xml")
public class MainTest {
    @Autowired
    ZkFactory zkFactory;


    @Test
    public void test() {
        String znodePath1 = "/test0001";
        String znodePath2 = "/test0002";
        WatcherOperater watcherOperater = new WatcherOperaterImpl(zkFactory.getZkClient());
        ZkDataChangeListener dataChangeListener1 = new ZkDataChangeListener();
        ZkDataChangeListener dataChangeListener2 = new ZkDataChangeListener();

        watcherOperater.addStateChangeWatcher(new ZkStateChangeListener());
        watcherOperater.addDataChangeWatcher(dataChangeListener1, znodePath1, "13333", CreateMode.EPHEMERAL);
        watcherOperater.addDataChangeWatcher(dataChangeListener2, znodePath2, "14444", CreateMode.EPHEMERAL);


        String result1 = zkFactory.getZkClient().readData(PathUtils.appendPaths(new String[]{zkFactory.getAppName(), znodePath1}));
        String result2 = zkFactory.getZkClient().readData(PathUtils.appendPaths(new String[]{zkFactory.getAppName(), znodePath2}));
        System.out.println("节点设置之前的值为："+result1);
        System.out.println("节点设置之前的值为："+result2);

        try {
            Thread.sleep(50*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        zkFactory.getZkClient().writeData(PathUtils.appendPaths(new String[]{zkFactory.getAppName(), znodePath2}), "14444");
        zkFactory.getZkClient().writeData(PathUtils.appendPaths(new String[]{zkFactory.getAppName(), znodePath1}), "13333");

        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        zkFactory.getZkClient().close();

        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
