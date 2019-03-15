package com.dmall.sysnotification;

import com.dmall.sysnotification.factory.ZkFactory;
import org.I0Itec.zkclient.ZkClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Creator: jiang.wei
 * Date: 2019/3/6
 * DESC:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/spring-cfg.xml")
public class AsychCreateNode {

    @Autowired
    ZkFactory zkFactory;


    @Test
    public void createZnodeAsych() {
        ZkClient zkClient = zkFactory.getZkClient();

    }


}
