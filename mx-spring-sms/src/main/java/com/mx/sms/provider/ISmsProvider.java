package com.mx.sms.provider;

import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxResult;

import java.util.List;

/**
 * @Author: mengxiang.
 * @create: 2022-05-16 11:04
 * @Description:
 */
public interface ISmsProvider {

    MxResult send(List<String> mobileList, List<String> params) throws MxException;
}
