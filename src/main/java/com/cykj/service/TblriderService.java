package com.cykj.service;

import com.cykj.bean.Tblorder;
import com.cykj.bean.Tblrider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblriderService {
    // 查询骑手信息
    Tblrider findRider(String ridertel);
    // 骑手登录
    Tblrider login (String ridertel, String riderpwd);
    // 验证电话是否重复
    Tblrider checkTel(String ridertel);
    // 查询骑手今日订单数
    long findOrderNum(long riderid, String starttime, String endtime);
    // 查询骑手已完成的订单
    List<Tblorder> findOrderFinish(long riderid);
    // 查询已取消单
    List<Tblorder> findOrderCancel(long riderid);

}
