package com.cp.sendmq.controller;


import com.cp.sendmq.bean.SysPersonalInfo;
import com.cp.sendmq.service.SendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/06/02
 */
@RestController
@RequestMapping("test")
@Api(tags = "rabbitmq测试")
public class TestController {

    @Autowired
    private SendService sendService;

    @GetMapping("/test1")
    @ApiOperation("test1测试")
    public String test1() {
        SysPersonalInfo sysPersonalInfo = new SysPersonalInfo();
        sysPersonalInfo.setId("9666");
        sysPersonalInfo.setPersonalName("pppp");
        sendService.send("SysPersonalInfoVO", sysPersonalInfo);
        return "success";
    }
}
