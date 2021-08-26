package com.cp.sendmq.factoryPatternDemo;

/**
 * <p>
 *策略工厂模式
 * </p>
 *
 * @author yang
 * @since 2021/08/10
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Facrory/test")
@Api(tags = "Facrory测试")
public class FacroryController {

    private final Logger log = LoggerFactory.getLogger(FacroryController.class);

    @Autowired
    private FactoryForStrategy factoryForStrategy;

    @GetMapping("/test1")
    @ApiOperation("test1测试")
    //@Transactional(rollbackFor = Exception.class)
    public String test1() throws Exception {
        ReceiveHandle strategy = factoryForStrategy.getStrategy("receive1");
        String name = strategy.getName();
        return name;
    }

}
