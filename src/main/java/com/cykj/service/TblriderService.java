package com.cykj.service;

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

}
