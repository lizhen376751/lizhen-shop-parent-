package com.lizhen.lcn.compensate.service;



import com.lizhen.lcn.compensate.model.TransactionCompensateMsg;
import com.lizhen.lcn.compensate.model.TxModel;
import com.lizhen.lcn.model.ModelName;
import com.lizhen.lcn.netty.model.TxGroup;
import com.lorne.core.framework.exception.ServiceException;

import java.util.List;

/**
 * create by lorne on 2017/11/11
 */
public interface CompensateService {

    boolean saveCompensateMsg(TransactionCompensateMsg transactionCompensateMsg);

    List<ModelName> loadModelList();

    List<String> loadCompensateTimes(String model);

    List<TxModel> loadCompensateByModelAndTime(String path);

    void autoCompensate(String compensateKey, TransactionCompensateMsg transactionCompensateMsg);

    boolean executeCompensate(String key) throws ServiceException;

    void reloadCompensate(TxGroup txGroup);

    boolean hasCompensate();

    boolean delCompensate(String path);

    TxGroup  getCompensateByGroupId(String groupId);
}
