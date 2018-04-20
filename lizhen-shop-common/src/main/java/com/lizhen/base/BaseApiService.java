package com.lizhen.base;

import com.lizhen.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 封装Response使用方法
 * Created by lizhen on 2018/4/20 0020.
 */
public class BaseApiService {

    /**
     * 通用封装
     *
     * @param rtncode 状态码
     * @param rtnmsg  状态描述
     * @param rtndata 返回数据
     * @return ResponseBase
     */
    public ResponseBase setResponseBase(Integer rtncode, String rtnmsg, Object rtndata) {
        return new ResponseBase(rtncode, rtnmsg, rtndata);
    }

    /**
     * 返回成功，无返回值
     *
     * @return
     */
    public ResponseBase setSuccessResponseBase() {
        return new ResponseBase(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, null);
    }

    /**
     * 返回成功，有返回值
     *
     * @return
     */
    public ResponseBase setSuccessResponseBase(Object rtndata) {
        return new ResponseBase(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, rtndata);
    }

    /**
     * 返回错误，无错误描述
     *
     * @return
     */
    public ResponseBase setErrorResponseBase() {
        return new ResponseBase(Constants.HTTP_RES_CODE_500, Constants.HTTP_RES_CODE_500_VALUE, null);
    }

    /**
     * 返回错误，有错误描述
     *
     * @return
     */
    public ResponseBase setErrorResponseBase(String rtnmsg) {
        return new ResponseBase(Constants.HTTP_RES_CODE_500, rtnmsg, null);
    }

}
