package com.dmall.sysnotification.listener;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkDataListener;

/**
 * Creator: jiang.wei
 * Date: 2019/2/26
 * DESC:
 */

public class ZkDataChangeListener implements IZkDataListener {


    @Override
    public void handleDataChange(String dataPath, Object data) throws Exception {
        System.out.println("监控器触发后返回结果：dataPath=" + dataPath +"; data=" + JSON.toJSONString(data));
    }

    @Override
    public void handleDataDeleted(String dataPath) throws Exception {

    }
}
