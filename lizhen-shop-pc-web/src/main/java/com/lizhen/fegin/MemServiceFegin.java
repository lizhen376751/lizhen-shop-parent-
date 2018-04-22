package com.lizhen.fegin;


import com.lizhen.api.service.MemberService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;


/**
 * 调用会员服务的fegin客户端
 * Created by lizhen on 2018/4/21.
 */
@Component
@FeignClient(value = "member")
public interface MemServiceFegin extends MemberService {

}
