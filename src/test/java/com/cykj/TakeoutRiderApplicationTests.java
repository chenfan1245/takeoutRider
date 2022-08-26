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
        String ridertel = "15750765393";

        // 测试
        System.out.println("------显示骑手信息------");
        double riderMoney = tblriderService.findMoney(riderid);
        double balance = Double.parseDouble(String.format("%.2f", riderMoney));
        System.out.println("收入："+balance);
        boolean flag = tblriderService.updateMoney(balance, riderid);
        Tblrider rider = tblriderService.findRider(riderid);
        if (flag) {
            // 系统当前日期
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String nowTime = formatter.format(date);
            // 今日订单的数量
            String a = nowTime + " 00:00:00";
            String b = nowTime + " 23:59:59";
            long ordernumNow = tblriderService.findOrderNum(rider.getRiderid(),a,b);
            rider.setOrdernumNow(ordernumNow);
        } else {
            System.out.println("有误");
        }

        // 打印
        System.out.println("-------------------------------------------------------------");
        System.out.println("骑手信息："+rider);
        System.out.println("-------------------------------------------------------------");

    }

}
