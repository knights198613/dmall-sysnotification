package com.dmall.sysnotification.factory;

import com.dmall.sysnotification.enums.ExceptionMsgEnums;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.File;


/**
 * Creator: jiang.wei
 * Date: 2019/2/26
 * DESC:
 */

public class ZkFactory {
    private final static Logger logger = LoggerFactory.getLogger(ZkFactory.class);
    /**
     * 特殊字符集合
     */
    private final static String SPECIAL_CHARACTERS = "\\&*^<>,./?[]{}@#$%~`|";
    /**
     * 默认回话超时时间
     */
    private final static int DEFAULT_SESSION_TIMEOUT = 15 * 1000;
    /**
     * 默认连接超时时间
     */
    private final static int DEFAULT_CONNCTION_TIMEOUT = 15 * 1000;
    /**
     * zk 连接地址串
     */
    private  String serverUrls;
    /**
     * zk 回话超时时间
     */
    private  int sessionTimeout;
    /**
     * zk 连接超时时间
     */
    private int connectionTimeout;
    /**
     * 引用名称 （路径前缀）
     */
    private String appName;



    private ZkClient zkClient;


    public void doInit() {
        zkClient = new ZkClient(getServerUrls(), getSessionTimeout(),
                getConnectionTimeout());
        logger.info("connected zk servers OK, serverUrls={}, sessionTimeout={}, connectionTimeout={}.",
                serverUrls, getSessionTimeout(), getConnectionTimeout());
        if(!zkClient.exists(File.separator+getAppName())) {
            zkClient.createPersistent(File.separator + getAppName());
        }

    }

    public String getServerUrls() {
        return serverUrls;
    }

    public void setServerUrls(String serverUrls) {
        this.serverUrls = serverUrls;
    }

    public int getSessionTimeout() {
        if(sessionTimeout == 0) {
            return DEFAULT_SESSION_TIMEOUT;
        }
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public int getConnectionTimeout() {
        if(connectionTimeout == 0) {
            return DEFAULT_CONNCTION_TIMEOUT;
        }
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public ZkClient getZkClient() {
        return zkClient;
    }

    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    public String getAppName() {
        if(appName !=null && appName.trim().length() > 0) {
            return appName;
        }
        throw new NullPointerException("应用名称不能为空！");
    }

    public void setAppName(String appName) {
        if(StringUtils.isNotBlank(appName)) {
            if(StringUtils.containsAny(appName, SPECIAL_CHARACTERS)) {
                throw new IllegalArgumentException(ExceptionMsgEnums.APPNAME_ILLEGAL_EXC.getMsg());
            }else {
                this.appName = appName;
            }
        }else {
            throw new IllegalArgumentException(ExceptionMsgEnums.APPNAME_NULL_EXC.getMsg());
        }
    }
}
