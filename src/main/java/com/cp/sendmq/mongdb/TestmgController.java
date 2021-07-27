package com.cp.sendmq.mongdb;


import com.cp.sendmq.bean.SysPersonalInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/06/02
 */
@RestController
@RequestMapping("test1")
@Api(tags = "mgongdb测试")
public class TestmgController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/test1")
    @ApiOperation("test1测试")
    public String test1() {
        //SysPersonalInfo sysPersonalInfo = new SysPersonalInfo();
        //sysPersonalInfo.setId("9666");
        //sysPersonalInfo.setPersonalName("pppp1");
        SysPersonalInfo sysPersonalInfo2 = new SysPersonalInfo();
        //sysPersonalInfo.setId("9666");
        sysPersonalInfo2.setPersonalName("pppp3");
        sysPersonalInfo2.setExpireAt(LocalDateTime.now().minusSeconds(-10));
        ArrayList<SysPersonalInfo> list = new ArrayList<>();

        //list.add(sysPersonalInfo);
        list.add(sysPersonalInfo2);
        mongoTemplate.insertAll(list);
        return "success";
    }

    @GetMapping("/test2")
    @ApiOperation("批量修改测试")
    public String test2() {
        SysPersonalInfo sysPersonalInfo = new SysPersonalInfo();
        sysPersonalInfo.setPersonalName("pppp1");
        ArrayList<SysPersonalInfo> list = new ArrayList<>();
        list.add(sysPersonalInfo);
        ArrayList<Pair<Query, Update>> list2 = new ArrayList<>();
        BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, SysPersonalInfo.class);
        for (SysPersonalInfo personalInfo : list) {
            Query query = new Query();
            query.addCriteria(Criteria.where("personalName").is("pppp1"));
            Update update = new Update();
            update.set("personalName", "999");
            Pair<Query, Update> of = Pair.of(query, update);
            list2.add(of);
        }

        bulkOperations.updateMulti(list2);
        bulkOperations.execute();
        return "success";
    }

    @GetMapping("/test3")
    @ApiOperation("批量删除测试")
    public String test3() {
        SysPersonalInfo sysPersonalInfo = new SysPersonalInfo();
        sysPersonalInfo.setPersonalName("pppp1");
        ArrayList<SysPersonalInfo> list = new ArrayList<>();
        list.add(sysPersonalInfo);
        ArrayList<Pair<Query, Update>> list2 = new ArrayList<>();
        BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, SysPersonalInfo.class);

        ArrayList<Query> list1 = new ArrayList<>();
        for (SysPersonalInfo personalInfo : list) {
            Query query = new Query();
            query.addCriteria(Criteria.where("personalName").is("999"));
            list1.add(query);
            Query query2 = new Query();
            query2.addCriteria(Criteria.where("personalName").is("pppp2"));
            list1.add(query2);

        }
        bulkOperations.remove(list1);
        bulkOperations.execute();
        return "success";
    }

}
