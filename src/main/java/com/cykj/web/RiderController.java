package com.cykj.web;

import com.alibaba.fastjson.JSON;
import com.cykj.bean.Tblorder;
import com.cykj.bean.Tblrider;
import com.cykj.bean.Tblshoppingcard;
import com.cykj.bean.Tbluser;
import com.cykj.service.TblriderService;
import com.cykj.utils.SMSUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    /* 查询骑手的取消单 */
    @ApiOperation(value = "findOrderCancel",notes = "查询骑手的取消单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "riderid",value = "骑手id")
    })
    @RequestMapping(value="/findOrderCancel",produces = { "text/html;charset=UTF-8;", "application/json;charset=UTF-8;" })
    public String findOrderCancel(long riderid){
        List<Tblorder> orderCancelList = tblriderService.findOrderCancel(riderid);
        String json = JSON.toJSONString(orderCancelList);
        return json;
    }

    /* 查询骑手已完成的订单 */
    @ApiOperation(value = "findOrderFinish",notes = "查询骑手已完成的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "riderid",value = "骑手id")
    })
    @RequestMapping(value="/findOrderFinish",produces = { "text/html;charset=UTF-8;", "application/json;charset=UTF-8;" })
    public String findOrderFinish(long riderid){
        List<Tblorder> orderFinishList = tblriderService.findOrderFinish(riderid);
        String json = JSON.toJSONString(orderFinishList);
        return json;
    }

    /* 登录 */
    @ApiOperation(value = "login",notes = "登录方法")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usertel",value = "登录骑手电话"),
            @ApiImplicitParam(name = "userpwd",value = "登录骑手密码")
    })
    @RequestMapping(value = "/login",produces = { "text/html;charset=UTF-8;", "application/json;charset=UTF-8;" })
    public String login(String ridertel,String riderpwd){
        Tblrider tblrider = tblriderService.login(ridertel,riderpwd);
        String json = JSON.toJSONString(tblrider);
        return json;
    }
    // 点击发送验证码
    @RequestMapping("/sendMs")
    public String sendMs (HttpServletRequest request, String ridertel){
        if(ridertel!=null&&!ridertel.equals("")){
            String s = SMSUtil.sendSMS(request,ridertel);
            JSONObject json = (JSONObject)request.getSession().getAttribute("MsCode");
            System.out.println(json.getString("Code"));
            return json.getString("Code");
        }else{
            return "error";
        }
    }
    // 点击注册
    @RequestMapping("/enroll")
    public String enroll( String ridertel, String riderpwd) {
        /* 判断账号是否重复 */
        if (tblriderService.checkTel(ridertel)){
            return "3";
        }else {
            // 将骑手信息存入数据库、这里省略
            if (tblriderService.enroll(ridertel,riderpwd)){
                return "1";
            }else {
                return "2";
            }
        }
    }

    /* 忘记密码 */
    @RequestMapping("/setPwd")
    public String setPwd( String ridertel, String riderpwd){
        System.out.println(ridertel);
        /* 判断账号是否存在 */
        if (!tblriderService.checkTel(ridertel)){
            return "3";
        }else {
            // 修改骑手的密码
            if (tblriderService.updPwd(ridertel,riderpwd)){
                return "1";
            }else {
                return "2";
            }
        }
    }

    /* 查询骑手信息 */
    @ApiOperation(value = "findRider",notes = "查询骑手信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ridertel",value = "登录的骑手帐号")
    })
    @RequestMapping(value="/findRider",produces = { "text/html;charset=UTF-8;", "application/json;charset=UTF-8;" })
    public String findRider(long riderid){
        System.out.println("------显示骑手信息------");
        double riderMoney = tblriderService.findMoney(riderid);     // 查询骑手累计收入
        double balance = Double.parseDouble(String.format("%.2f", riderMoney)); // 收入保留两位小数
        boolean flag = tblriderService.updateMoney(balance, riderid);   // 更新骑手累计收入
        Tblrider rider = tblriderService.findRider(riderid);    // 查询骑手信息
        if (flag) {
            // 系统当前日期
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String nowTime = formatter.format(date);
            // 今日订单的数量
            String a = nowTime + " 00:00:00";
            String b = nowTime + " 23:59:59";
            long ordernumNow = tblriderService.findOrderNum(rider.getRiderid(),a,b);
            rider.setOrdernumNow(ordernumNow);  // 更新 今日订单量
        } else {
            return "0"; // 有误
        }

        String json = JSON.toJSONString(rider);
        return json;
    }

}
