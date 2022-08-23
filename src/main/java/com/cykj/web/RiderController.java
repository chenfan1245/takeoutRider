package com.cykj.web;

import com.alibaba.fastjson.JSON;
import com.cykj.bean.Tblrider;
import com.cykj.bean.Tblshoppingcard;
import com.cykj.bean.Tbluser;
import com.cykj.service.TblriderService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rider")
public class RiderController {
    @Autowired
    HttpSession session;
    @Autowired
    private TblriderService tblriderService;

    /* 登录 */
    @ApiOperation(value = "login",notes = "登录方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usertel",value = "登录骑手电话"),
            @ApiImplicitParam(name = "userpwd",value = "登录骑手密码")
    })
    @RequestMapping("/login")
    public String login(String ridertel,String riderpwd){
        Tblrider tblrider = tblriderService.login(ridertel,riderpwd);
        String json = JSON.toJSONString(tblrider);
        return json;
    }

    /* 查询骑手信息 */
    @ApiOperation(value = "findRider",notes = "查询骑手信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ridertel",value = "登录的骑手帐号")
    })
    @RequestMapping(value="/findRider",produces = { "text/html;charset=UTF-8;", "application/json;charset=UTF-8;" })
    public String findRider(String ridertel){
        System.out.println("------显示骑手信息------");
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

        String json = JSON.toJSONString(rider);
        return json;
    }

}
