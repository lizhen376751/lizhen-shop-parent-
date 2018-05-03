package com.lizhen.lcn.api.service.impl;



import com.lizhen.lcn.api.service.ApiTxManagerService;
import com.lizhen.lcn.compensate.model.TransactionCompensateMsg;
import com.lizhen.lcn.compensate.service.CompensateService;
import com.lizhen.lcn.config.ConfigReader;
import com.lizhen.lcn.manager.service.MicroService;
import com.lizhen.lcn.manager.service.TxManagerSenderService;
import com.lizhen.lcn.manager.service.TxManagerService;
import com.lizhen.lcn.model.TxServer;
import com.lizhen.lcn.model.TxState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lorne on 2017/7/1.
 */
@Service
public class ApiTxManagerServiceImpl implements ApiTxManagerService {


    @Autowired
    private TxManagerService managerService;

    @Autowired
    private MicroService eurekaService;

    @Autowired
    private CompensateService compensateService;


    @Autowired
    private TxManagerSenderService txManagerSenderService;

    @Autowired
    private ConfigReader configReader;


    @Override
    public TxServer getServer() {
        return eurekaService.getServer();
    }


    @Override
    public int cleanNotifyTransaction(String groupId, String taskId) {
        return managerService.cleanNotifyTransaction(groupId,taskId);
    }


    @Override
    public boolean sendCompensateMsg(long currentTime, String groupId, String model, String address, String uniqueKey, String className, String methodStr, String data, int time,int startError) {
        TransactionCompensateMsg transactionCompensateMsg = new TransactionCompensateMsg(currentTime, groupId, model, address, uniqueKey, className, methodStr, data, time, 0,startError);
        return compensateService.saveCompensateMsg(transactionCompensateMsg);
    }

    @Override
    public String sendMsg(String model,String msg) {
        return txManagerSenderService.sendMsg(model, msg, configReader.getTransactionNettyDelayTime());
    }


    @Override
    public TxState getState() {
        return eurekaService.getState();
    }
}
