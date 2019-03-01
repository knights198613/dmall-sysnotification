package com.dmall.sysnotification.utils;

import com.dmall.sysnotification.enums.ExceptionMsgEnums;
import com.dmall.sysnotification.exception.PathillegalException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * Creator: jiang.wei
 * Date: 2019/2/27
 * DESC: 路径工具类
 */

public class PathUtils {

    /**
     * 按顺序拼接路径
     * @param paths
     * @return
     */
    public static String appendPaths(String... paths) {
        StringBuilder resultPath = new StringBuilder(File.separator);
        if(paths != null && paths.length > 0) {
            for(String path : paths) {
                String[]  subPath = path.split(File.separator);
                for(String subP : subPath) {
                    if(StringUtils.isNotBlank(subP)) {
                        resultPath.append(subP).append(File.separator);
                    }
                }
            }
        }
        return resultPath.deleteCharAt(resultPath.length()-1).toString();
    }

    /**
     *  路径中是否含有层级   true: 是   false:非
     * @param path
     * @return
     */
    public static boolean hasSubPath(String path) {
        if(StringUtils.isNotBlank(path) && StringUtils.indexOf(path, File.separator) > -1) {
            if(StringUtils.countMatches(path, File.separator) > 1) {
                return true;
            }else {
                return false;
            }
        } else {
            throw new PathillegalException(ExceptionMsgEnums.PATH_ILLEGAL_EXC);
        }
    }


    public static void main(String[] args) {
        String[] ss = {"sss", "ddd", "est01/test02/test03"};
        System.out.println(appendPaths(ss));
    }
}
