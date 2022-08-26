package com.cykj.service.impl;

import com.cykj.bean.Tblgoods;
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
    public Tblrider findRider(long riderid) {
        return riderMapper.findRider(riderid);
    }

    // 骑手登录
    @Override
    public Tblrider login(String ridertel, String riderpwd) {
        return riderMapper.login(ridertel, riderpwd);
    }

    // 验证电话是否重复
    @Override
    public boolean checkTel(String ridertel) {
        Tblrider tblrider = riderMapper.checkTel(ridertel);
        if (tblrider != null){
            return true;
        }
        return false;
    }

    //  骑手注册
    @Override
    public boolean enroll(String ridertel, String riderpwd) {
        int num = riderMapper.enroll(ridertel, riderpwd);
        if (num > 0){
            return true;
        }
        return false;
    }

    //  骑手修改密码
    @Override
    public boolean updPwd(String ridertel, String riderpwd) {
        int num = riderMapper.updPwd(ridertel, riderpwd);
        if (num > 0){
            return true;
        }
        return false;
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

    // 查询骑手累计收入
    @Override
    public Double findMoney(long riderid) {
        return riderMapper.findMoney(riderid);
    }

    // 更新骑手收入
    @Override
    public boolean updateMoney(double balance, long riderid) {
        return riderMapper.updateMoney(balance, riderid);
    }
    // 查询订单
    @Override
    public List<Tblorder> findOrder(long riderid, long orderid) {
        return riderMapper.findOrder(riderid, orderid);
    }

    // 查询订单中的商品
    @Override
    public List<Tblgoods> findOrderDetail(long orderid) {
        return riderMapper.findOrderDetail(orderid);
    }
}
