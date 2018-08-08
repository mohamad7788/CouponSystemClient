package com.coupon.system.servlet;

import datamodel.bean.objects.Coupon;
import datamodel.bean.objects.CouponType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.codehaus.jackson.map.ObjectMapper;

public class TestJson
{
  public static void main(String[] args)
  {
    try
    {
      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
      java.sql.Date sDate = new java.sql.Date(sdf1.parse("2017-07-01").getTime());
      java.sql.Date eDate = new java.sql.Date(sdf1.parse("2047-07-01").getTime());
      
      ArrayList<Coupon> list = new ArrayList();
      for (int i = 1; i < 5; i++) {
        list.add(new Coupon(i, "Coupons for example", sDate, eDate, 1, CouponType.ELECTRICITY, "This is my message", 1.0D, "HTTP URL for Image"));
      }
      Coupon coupon = new Coupon(1L, "Coupons for example", sDate, eDate, 1, CouponType.ELECTRICITY, "This is my message", 1.0D, "HTTP URL for Image");
      ObjectMapper objectMapper = new ObjectMapper();
      String jsonStringLiString = objectMapper.writeValueAsString(list);
      Object localObject = objectMapper.readValue(objectMapper.writeValueAsString(coupon), Coupon.class);
    }
    catch (Exception localException) {}
  }
}
