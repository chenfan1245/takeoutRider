package com.cykj.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Time;

@ApiModel("商品表")
public class Tblgoods {
  @ApiModelProperty(value = "商品id")
  private long goodsid;
  @ApiModelProperty(value = "商家id")
  private long shopid;
  @ApiModelProperty(value = "类型id")
  private long typeid;
  @ApiModelProperty(value = "商家商品类型分类id")
  private long shopgoodstypeid;
  @ApiModelProperty(value = "商品名称")
  private String goodsname;
  @ApiModelProperty(value = "商品数量")
  private long goodsnum;
  @ApiModelProperty(value = "商品价格")
  private double goodsprice;
  @ApiModelProperty(value = "商品详细介绍")
  private String goodsdescribe;
  @ApiModelProperty(value = "商品图片")
  private String goodsimg;
  @ApiModelProperty(value = "商品状态（上架 / 下架）")
  private String goodsstate;
  @ApiModelProperty(value = "商品的审核状态（未审核 / 审核通过 / 审核未通过）")
  private String auditstate;
  @ApiModelProperty(value = "月销量")
  private long monsales;
  /* 商家 */
  @ApiModelProperty(value = "角色id")
  private long roleid;
  @ApiModelProperty(value = "店铺名称")
  private String shopname;
  @ApiModelProperty(value = "商家登录的帐号（手机号）")
  private String shoptel;
  @ApiModelProperty(value = "开始营业时间")
  private Time opentime;
  @ApiModelProperty(value = "结束营业时间")
  private Time endtime;
  @ApiModelProperty(value = "商家登录密码")
  private String shoppwd;
  @ApiModelProperty(value = "店铺地址")
  private String shopaddress;
  @ApiModelProperty(value = "营业执照")
  private String buslicense;
  @ApiModelProperty(value = "营业许可证")
  private String foodlicense;
  @ApiModelProperty(value = "店铺简介")
  private String shopinfo;
  @ApiModelProperty(value = "店铺状态（营业 / 休息）")
  private String shopstate;
  @ApiModelProperty(value = "商家审核情况（未审核 / 审核通过 / 审核未通过）")
  private String shopAuditstate;
  @ApiModelProperty(value = "余额")
  private double income;
  @ApiModelProperty(value = "店铺销量")
  private double shopsales;
  @ApiModelProperty(value = "店铺评分")
  private double shopscore;
  @ApiModelProperty(value = "法人姓名")
  private String legalpersonname;
  @ApiModelProperty(value = "法人身份证号")
  private String legalpersonid;
  /* 商品类型 */
  @ApiModelProperty(value = "类型名称（目前共20种）")
  private String typename;
  @ApiModelProperty(value = "类型图标")
  private String typeimg;
  /*购买商品类*/
  @ApiModelProperty(value = "id")
  private long id;
  @ApiModelProperty(value = "订单id")
  private long orderid;
  @ApiModelProperty(value = "购买的商品数量")
  private long bugnum;

  public Tblgoods() {
  }

  @Override
  public String toString() {
    return "商品{" +
            "商品id=" + goodsid +
            ", 商家id=" + shopid +
            ", 类型id=" + typeid +
            ", 商家商品类型id=" + shopgoodstypeid +
            ", 商品名称='" + goodsname + '\'' +
            ", 商品库存=" + goodsnum +
            ", 商品价格=" + goodsprice +
            ", 商品描述='" + goodsdescribe + '\'' +
            ", 商品图片='" + goodsimg + '\'' +
            ", 商品状态='" + goodsstate + '\'' +
            ", 商品审核状态='" + auditstate + '\'' +
            ", 月销量=" + monsales +
            ", 角色id=" + roleid +
            ", 店铺名称='" + shopname + '\'' +
            ", 商家电话='" + shoptel + '\'' +
            ", 开始营业时间=" + opentime +
            ", 结束营业时间=" + endtime +
            ", 商家密码='" + shoppwd + '\'' +
            ", 商家地址='" + shopaddress + '\'' +
            ", 营业执照='" + buslicense + '\'' +
            ", 营业许可证='" + foodlicense + '\'' +
            ", 店铺简介='" + shopinfo + '\'' +
            ", 商家状态='" + shopstate + '\'' +
            ", 店铺审核状态='" + shopAuditstate + '\'' +
            ", 收入=" + income +
            ", 销量=" + shopsales +
            ", 评分=" + shopscore +
            ", 法人姓名='" + legalpersonname + '\'' +
            ", 法人身份证号='" + legalpersonid + '\'' +
            ", 类型名称='" + typename + '\'' +
            ", 类型图标='" + typeimg + '\'' +
            ", 购物车购买商品id=" + id +
            ", 订单id=" + orderid +
            ", 购买数量=" + bugnum +
            '}';
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getOrderid() {
    return orderid;
  }

  public void setOrderid(long orderid) {
    this.orderid = orderid;
  }

  public long getBugnum() {
    return bugnum;
  }

  public void setBugnum(long bugnum) {
    this.bugnum = bugnum;
  }

  public double getShopsales() {
    return shopsales;
  }

  public void setShopsales(double shopsales) {
    this.shopsales = shopsales;
  }

  public long getRoleid() {
    return roleid;
  }

  public void setRoleid(long roleid) {
    this.roleid = roleid;
  }

  public String getShopname() {
    return shopname;
  }

  public void setShopname(String shopname) {
    this.shopname = shopname;
  }

  public String getShoptel() {
    return shoptel;
  }

  public void setShoptel(String shoptel) {
    this.shoptel = shoptel;
  }

  public Time getOpentime() {
    return opentime;
  }

  public void setOpentime(Time opentime) {
    this.opentime = opentime;
  }

  public Time getEndtime() {
    return endtime;
  }

  public void setEndtime(Time endtime) {
    this.endtime = endtime;
  }

  public String getShoppwd() {
    return shoppwd;
  }

  public void setShoppwd(String shoppwd) {
    this.shoppwd = shoppwd;
  }

  public String getShopaddress() {
    return shopaddress;
  }

  public void setShopaddress(String shopaddress) {
    this.shopaddress = shopaddress;
  }

  public String getBuslicense() {
    return buslicense;
  }

  public void setBuslicense(String buslicense) {
    this.buslicense = buslicense;
  }

  public String getFoodlicense() {
    return foodlicense;
  }

  public void setFoodlicense(String foodlicense) {
    this.foodlicense = foodlicense;
  }

  public String getShopinfo() {
    return shopinfo;
  }

  public void setShopinfo(String shopinfo) {
    this.shopinfo = shopinfo;
  }

  public String getShopstate() {
    return shopstate;
  }

  public void setShopstate(String shopstate) {
    this.shopstate = shopstate;
  }

  public String getShopAuditstate() {
    return shopAuditstate;
  }

  public void setShopAuditstate(String shopAuditstate) {
    this.shopAuditstate = shopAuditstate;
  }

  public double getIncome() {
    return income;
  }

  public void setIncome(double income) {
    this.income = income;
  }

  public double getShopscore() {
    return shopscore;
  }

  public void setShopscore(double shopscore) {
    this.shopscore = shopscore;
  }

  public String getLegalpersonname() {
    return legalpersonname;
  }

  public void setLegalpersonname(String legalpersonname) {
    this.legalpersonname = legalpersonname;
  }

  public String getLegalpersonid() {
    return legalpersonid;
  }

  public void setLegalpersonid(String legalpersonid) {
    this.legalpersonid = legalpersonid;
  }

  public String getTypename() {
    return typename;
  }

  public void setTypename(String typename) {
    this.typename = typename;
  }

  public String getTypeimg() {
    return typeimg;
  }

  public void setTypeimg(String typeimg) {
    this.typeimg = typeimg;
  }

  public long getGoodsid() {
    return goodsid;
  }

  public void setGoodsid(long goodsid) {
    this.goodsid = goodsid;
  }


  public long getShopid() {
    return shopid;
  }

  public void setShopid(long shopid) {
    this.shopid = shopid;
  }


  public long getTypeid() {
    return typeid;
  }

  public void setTypeid(long typeid) {
    this.typeid = typeid;
  }


  public long getShopgoodstypeid() {
    return shopgoodstypeid;
  }

  public void setShopgoodstypeid(long shopgoodstypeid) {
    this.shopgoodstypeid = shopgoodstypeid;
  }


  public String getGoodsname() {
    return goodsname;
  }

  public void setGoodsname(String goodsname) {
    this.goodsname = goodsname;
  }


  public long getGoodsnum() {
    return goodsnum;
  }

  public void setGoodsnum(long goodsnum) {
    this.goodsnum = goodsnum;
  }


  public double getGoodsprice() {
    return goodsprice;
  }

  public void setGoodsprice(double goodsprice) {
    this.goodsprice = goodsprice;
  }


  public String getGoodsdescribe() {
    return goodsdescribe;
  }

  public void setGoodsdescribe(String goodsdescribe) {
    this.goodsdescribe = goodsdescribe;
  }


  public String getGoodsimg() {
    return goodsimg;
  }

  public void setGoodsimg(String goodsimg) {
    this.goodsimg = goodsimg;
  }


  public String getGoodsstate() {
    return goodsstate;
  }

  public void setGoodsstate(String goodsstate) {
    this.goodsstate = goodsstate;
  }


  public String getAuditstate() {
    return auditstate;
  }

  public void setAuditstate(String auditstate) {
    this.auditstate = auditstate;
  }


  public long getMonsales() {
    return monsales;
  }

  public void setMonsales(long monsales) {
    this.monsales = monsales;
  }
}
