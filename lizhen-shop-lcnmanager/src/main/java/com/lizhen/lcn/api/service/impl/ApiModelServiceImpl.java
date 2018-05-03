package com.lizhen.lcn.api.service.impl;

import com.lizhen.lcn.api.service.ApiModelService;
import com.lizhen.lcn.manager.ModelInfoManager;
import com.lizhen.lcn.model.ModelInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by lorne on 2017/11/13
 */
@Service
public class ApiModelServiceImpl implements ApiModelService {


    @Override
    public List<ModelInfo> onlines() {
        return ModelInfoManager.getInstance().getOnlines();
    }


}
