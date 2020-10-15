package com.city.common.utils;

import com.alibaba.fastjson.JSON;
import com.city.common.exception.CustomException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: mirror6
 * @Date: 2020/10/15 16:26
 */
@Slf4j
@NoArgsConstructor
public class JsonUtils {

    /**
     * 对象转json字符串
     */
    public static String parseStr(Object src) {
        if (src == null) {
            return null;
        }
        try {
            return src instanceof String ? (String) src : JSON.toJSONString(src);
        } catch (Exception e) {
            log.error("解析对象:[{}]为字符串异常，错误", src, e);
            throw new CustomException("对象转json异常");
        }
    }

    /**
     * 字符串转对象
     */
    public static <T> T parseObj(String src, Class<T> clazz) {
        if (null == src || null == clazz) {
            return null;
        }
        try {
            return JSON.parseObject(src, clazz);
        } catch (Exception e) {
            log.error("解析json为对象异常，String：{}，T：{}，error：{}", src, clazz, e);
            throw new CustomException("字符串转对象异常");
        }
    }
}
