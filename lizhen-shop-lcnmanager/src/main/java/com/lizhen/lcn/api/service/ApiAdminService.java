package com.lizhen.lcn.api.service;



import com.lizhen.lcn.compensate.model.TxModel;
import com.lizhen.lcn.model.ModelName;
import com.lizhen.lcn.model.TxState;
import com.lorne.core.framework.exception.ServiceException;

import java.util.List;

/**
 * create by lorne on 2017/11/12
 */
public interface ApiAdminService {

    TxState getState();

    String loadNotifyJson();

    List<ModelName> modelList();


    List<String> modelTimes(String model);

    List<TxModel> modelInfos(String path);

    boolean compensate(String path) throws ServiceException, ServiceException;

    boolean hasCompensate();

    boolean delCompensate(String path);

}
