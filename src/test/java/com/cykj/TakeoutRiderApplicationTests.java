package com.cykj;

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

@SpringBootTest
class TakeoutRiderApplicationTests {
    @Autowired
    TblriderService tblriderService;

    @Test
    void contextLoads() {
        // 前端传来的数据
        String ridertel = "15750765393";

        // 测试
        Tblrider rider = tblriderService.findRider(ridertel);
        // 系统当前日期
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String nowTime = formatter.format(date);
        // 今日订单的数量
        String a = nowTime + " 00:00:00";
        String b = nowTime + " 23:59:59";
        long ordernumNow = tblriderService.findOrderNum(rider.getRiderid(),a,b);
        rider.setOrdernumNow(ordernumNow);

        // 打印
        System.out.println("-------------------------------------------------------------");
        System.out.println("骑手："+rider);
        System.out.println("-------------------------------------------------------------");

    }

}
