package com.cykj.web;

import com.alibaba.fastjson.JSON;
import com.cykj.bean.*;
import com.cykj.service.TblriderService;
import com.cykj.utils.ImageToBase64ByUrl;
import com.cykj.utils.Result;
import com.cykj.utils.SMSUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /* 点击订单，查询订单详细信息 */
    @ApiOperation(value = "findOrder",notes = "查询骑手的取消单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderid",value = "点击订单，查看订单详细信息")
    })
    @RequestMapping(value="/findOrder",produces = { "text/html;charset=UTF-8;", "application/json;charset=UTF-8;" })
    public String findOrder(long riderid, long orderid){
        List<Tblorder> orderList = tblriderService.findOrder(riderid, orderid);
        for (Tblorder tblorder : orderList) {
            List<Tblgoods> goodsList = tblriderService.findOrderDetail(orderid);
            tblorder.setGoodsList(goodsList);
        }
        String json = JSON.toJSONString(orderList);
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
    //景庆
    //用户证件图片上传
    @RequestMapping("/userImgUp")
    @ResponseBody
    public Result userImgUp(MultipartFile userImg, HttpServletRequest request){
        //图片保存
        String fn=userImg.getOriginalFilename();
        System.out.println("文件名："+fn);
        //保存路径
        try {
            //target目录
            String resourcePath= ResourceUtils.getURL("classpath:").getPath();
            System.out.println("target目录地址:"+resourcePath);
            //访问的url
            String imgUpPath=resourcePath +"upload";
            System.out.println("url:"+imgUpPath);
            File file=new File(imgUpPath);
            if (!file.exists()){
                file.mkdir();
            }
            userImg.transferTo(new File(imgUpPath+"/"+fn)); //保存文件

        } catch (IOException e) {
            e.printStackTrace();
            return Result.ok(-1);
        }
        return Result.ok(fn);
    }

    //解析身份证图片
    @RequestMapping("/getIdCardData")
    public Map<String,Object> getIdCardData(String photoUrl){
        System.out.println(photoUrl);
        String fileContent="";
        Map map =new HashMap();
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            Credential cred = new Credential("AKIDwiFzSeZDbhW4MN7ZlgKhurXv2tTlmWzB", "wc190Zsuf9NK0PXOyHI3XjehTyC9oCJ9");
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            OcrClient client = new OcrClient(cred, "ap-beijing", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            IDCardOCRRequest req = new IDCardOCRRequest();
            // 返回的resp是一个IDCardOCRResponse的实例，与请求对象对应
//            req.setImageUrl("http://localhost:9906/upload/jingqing.jpg");
            ImageToBase64ByUrl toBase64ByUrl=new ImageToBase64ByUrl();
//            req.setImageBase64(ImageToBase64ByUrl.ImageToBase64ByUrl("http://localhost:9906/upload/jingqing.jpg"));
            req.setImageBase64(ImageToBase64ByUrl.ImageToBase64ByUrl(photoUrl));
//            req.setImageBase64(ImageToBase64ByUrl.ImageToBase64ByUrl("http://localhost:9906/upload/jingqing2.jpg"));
            IDCardOCRResponse resp = client.IDCardOCR(req);
            // 输出json格式的字符串回包
//            System.out.println(IDCardOCRResponse.toJsonString(resp));
            fileContent=IDCardOCRResponse.toJsonString(resp);
            System.out.println(fileContent + "----------------116");
            map.put("fileContent",fileContent);
            map.put("code",20000);
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        System.out.println(fileContent + "----------------117");
        return map;
    }
    @RequestMapping("/riderIdentity")
    public Map<String,Object>riderIdentity(String ridername,String rideridcard,String idcardfront,String idcardback,String healthcard){
        boolean riderIdentity=tblriderService.riderIdentity(ridername,rideridcard,idcardfront,idcardback,healthcard);
        Map map =new HashMap();
        if(riderIdentity){
            map.put("message","已成功提交等待审核");
        }else {
            map.put("message","提交失败请重新提交");
        }
        return map;
    }

}
