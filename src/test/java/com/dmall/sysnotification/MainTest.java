package com.dmall.sysnotification;

import com.dmall.sysnotification.factory.ZkFactory;
import com.dmall.sysnotification.listener.ZkDataChangeListener;
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
        String znodePath = "/test0001";
        WatcherOperater watcherOperater = new WatcherOperaterImpl(zkFactory);
        ZkDataChangeListener dataChangeListener = new ZkDataChangeListener();

        watcherOperater.addDataChangeWatcher(dataChangeListener, znodePath, "13333", CreateMode.EPHEMERAL);

        String result = zkFactory.getZkClient().readData(PathUtils.appendPaths(new String[]{zkFactory.getAppName(), znodePath}));
        System.out.println("节点设置之前的值为："+result);
        zkFactory.getZkClient().writeData(PathUtils.appendPaths(new String[]{zkFactory.getAppName(), znodePath}), "13333");

        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        zkFactory.getZkClient().close();

    }
}
