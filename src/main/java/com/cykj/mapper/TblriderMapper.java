package com.cykj.mapper;

import com.cykj.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TblriderMapper {
    // 查询骑手信息
    Tblrider findRider(@Param("riderid")long riderid);
    // 骑手登录
    Tblrider login (@Param("ridertel")String ridertel,
                    @Param("riderpwd")String riderpwd);
    // 验证电话是否重复
    Tblrider checkTel(@Param("ridertel")String ridertel);
    //  骑手注册
    int enroll(@Param("ridertel")String ridertel,@Param("riderpwd")String riderpwd);
    //  修改密码
    int updPwd(@Param("ridertel")String ridertel,@Param("riderpwd")String riderpwd);

    // 查询骑手今日订单数
    long findOrderNum(@Param("riderid")long riderid,
                      @Param("starttime")String starttime,
                      @Param("endtime")String endtime);
    // 查询骑手已完成的订单
    List<Tblorder> findOrderFinish(@Param("riderid")long riderid);
    // 查询已取消单
    List<Tblorder> findOrderCancel(@Param("riderid")long riderid);
    // 查询骑手累计收入
    Double findMoney(@Param("riderid")long riderid);
    // 更新骑手收入
    boolean updateMoney(@Param("balance")double balance,
                        @Param("riderid")long riderid);
    // 查询订单
    List<Tblorder> findOrder(@Param("riderid")long riderid,
                             @Param("orderid")long orderid);
    // 查询订单中的商品
    List<Tblgoods> findOrderDetail(@Param("orderid")long orderid);

}
