package com.cykj;

import com.cykj.bean.Tblorder;
import com.cykj.bean.Tblrider;
import com.cykj.service.TblriderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class TakeoutRiderApplicationTests {
    @Autowired
    TblriderService tblriderService;

    @Test
    void contextLoads() {
        // 前端传来的数据
        long riderid = 2;

        // 测试
        List<Tblorder> orderFinishList = tblriderService.findOrderFinish(riderid);

        // 打印
        System.out.println("-------------------------------------------------------------");
        System.out.println("已完成订单："+orderFinishList);
        System.out.println("-------------------------------------------------------------");

    }

}
