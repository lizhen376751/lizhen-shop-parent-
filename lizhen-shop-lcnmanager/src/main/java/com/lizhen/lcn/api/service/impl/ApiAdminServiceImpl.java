package com.lizhen.lcn.api.service.impl;


import com.lizhen.lcn.api.service.ApiAdminService;
import com.lizhen.lcn.compensate.model.TxModel;
import com.lizhen.lcn.compensate.service.CompensateService;
import com.lizhen.lcn.manager.service.MicroService;
import com.lizhen.lcn.model.ModelName;
import com.lizhen.lcn.model.TxState;
import com.lizhen.lcn.redis.service.RedisServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lorne.core.framework.exception.ServiceException;
import java.util.List;

/**
 * create by lorne on 2017/11/12
 */
@Service
public class ApiAdminServiceImpl implements ApiAdminService {


    @Autowired
    private MicroService eurekaService;

    @Autowired
    private RedisServerService redisServerService;

    @Autowired
    private CompensateService compensateService;

    @Override
    public TxState getState() {
        return eurekaService.getState();
    }

    @Override
    public String loadNotifyJson() {
        return redisServerService.loadNotifyJson();
    }

    @Override
    public List<ModelName> modelList() {
        return compensateService.loadModelList();
    }


    @Override
    public List<String> modelTimes(String model) {
        return compensateService.loadCompensateTimes(model);
    }

    @Override
    public List<TxModel> modelInfos(String path) {
        return compensateService.loadCompensateByModelAndTime(path);
    }

    @Override
    public boolean compensate(String path) throws ServiceException {
        return compensateService.executeCompensate(path);
    }

    @Override
    public boolean delCompensate(String path) {
        return compensateService.delCompensate(path);
    }

    @Override
    public boolean hasCompensate() {
        return compensateService.hasCompensate();
    }
}
