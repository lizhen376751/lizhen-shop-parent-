package com.lizhen.lcn.manager.service;


import com.lizhen.lcn.model.TxServer;
import com.lizhen.lcn.model.TxState;

/**
 * create by lorne on 2017/11/11
 */
public interface MicroService {

    String  tmKey = "tx-manager";

    TxServer getServer();

    TxState getState();
}
