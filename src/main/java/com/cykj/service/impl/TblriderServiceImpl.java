package com.cykj.service.impl;

import com.cykj.bean.Tblorder;
import com.cykj.bean.Tblrider;
import com.cykj.mapper.TblriderMapper;
import com.cykj.service.TblriderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TblriderServiceImpl implements TblriderService {
    @Autowired
    private TblriderMapper riderMapper;

    // 查询骑手信息
    @Override
    public Tblrider findRider(String ridertel) {
        return riderMapper.findRider(ridertel);
    }

    // 骑手登录
    @Override
    public Tblrider login(String ridertel, String riderpwd) {
        return riderMapper.login(ridertel, riderpwd);
    }

    // 验证电话是否重复
    @Override
    public Tblrider checkTel(String ridertel) {
        return riderMapper.checkTel(ridertel);
    }

    // 查询骑手今日订单数
    @Override
    public long findOrderNum(long riderid, String starttime, String endtime) {
        return riderMapper.findOrderNum(riderid, starttime, endtime);
    }

    // 查询骑手已完成的订单
    @Override
    public List<Tblorder> findOrderFinish(long riderid) {
        return riderMapper.findOrderFinish(riderid);
    }

    // 查询已取消单
    @Override
    public List<Tblorder> findOrderCancel(long riderid) {
        return riderMapper.findOrderCancel(riderid);
    }
}
