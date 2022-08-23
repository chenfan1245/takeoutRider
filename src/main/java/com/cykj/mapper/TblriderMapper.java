package com.cykj.mapper;

import com.cykj.bean.Tblcomment;
import com.cykj.bean.Tblrider;
import com.cykj.bean.Tbluser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TblriderMapper {
    // 查询骑手信息
    Tblrider findRider(@Param("ridertel")String ridertel);
    // 骑手登录
    Tblrider login (@Param("ridertel")String ridertel,
                    @Param("riderpwd")String riderpwd);
    // 验证电话是否重复
    Tblrider checkTel(@Param("ridertel")String ridertel);
    // 查询骑手今日订单数
    long findOrderNum(@Param("riderid")long riderid,
                      @Param("starttime")String starttime,
                      @Param("endtime")String endtime);

}
