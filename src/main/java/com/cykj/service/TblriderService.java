package com.cykj.service;

import com.cykj.bean.Tblgoods;
import com.cykj.bean.Tblorder;
import com.cykj.bean.Tblrider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblriderService {
    // 查询骑手信息
    Tblrider findRider(long riderid);
    // 骑手登录
    Tblrider login (String ridertel, String riderpwd);
    // 验证电话是否重复
    boolean checkTel(String ridertel);
    //  骑手注册
    boolean enroll(String ridertel,String riderpwd);
    //  修改密码
    boolean updPwd(String ridertel,String riderpwd);
    // 查询骑手今日订单数
    long findOrderNum(long riderid, String starttime, String endtime);
    // 查询骑手已完成的订单
    List<Tblorder> findOrderFinish(long riderid);
    // 查询已取消单
    List<Tblorder> findOrderCancel(long riderid);
    // 查询骑手累计收入
    Double findMoney(long riderid);
    // 更新骑手收入
    boolean updateMoney(double balance, long riderid);
    // 查询订单
    List<Tblorder> findOrder(long riderid, long orderid);
    // 查询订单中的商品
    List<Tblgoods> findOrderDetail(long orderid);

}
