package com.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.provider.service.TicketService;
import org.springframework.stereotype.Component;

/**
 * Created by Janus on 2018/7/11.
 */
@Component
//类似于xml的配置：<dubbo:service interface="com.luo.service.TestService" ref="testService" />
@Service//将服务发布出去，注意： 这里的@Service非Spring注解而是Dubbo的注解！
public class TicketServiceImpl implements TicketService {

    @Override
    public String getTicket() {
        return "《厉害了，我的国》";
    }
}
