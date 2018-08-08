package com.coupon.system.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.codehaus.jackson.map.ObjectMapper;

import business.facade.layer.ifc.ClientType;
import business.facade.layer.ifc.CouponClientFacade;
import business.facade.layer.impl.AdminFacade;
import business.facade.layer.impl.CompanyFacade;
import business.facade.layer.impl.CustomerFacade;
import datamodel.bean.dao.exceptions.CouponsGenericException;
import datamodel.bean.objects.Coupon;
import datamodel.bean.objects.CouponType;

@Path("/CompanyService")
public class CompanyService
{
  @POST
  @Path("/createCoupon")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String createCoupon(@QueryParam("couponID") long id, @QueryParam("couponTittle") String title, @QueryParam("couponStartDate") String startDate, @QueryParam("couponEndDate") String endDate, @QueryParam("couponAmount") int amount, @QueryParam("couponType") String type, @QueryParam("couponMessage") String message, @QueryParam("couponPrice") double price, @QueryParam("couponImage") String image)
  {
    StringBuilder res = new StringBuilder();
    try
    {
      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
      java.sql.Date sDate = new java.sql.Date(sdf1.parse(startDate).getTime());
      java.sql.Date eDate = new java.sql.Date(sdf1.parse(endDate).getTime());
      Coupon coupon = new Coupon(id, title, sDate, eDate, amount, CouponType.valueOf(type), message, price, image);
      
      new CompanyFacade(1L).createCoupon(coupon);
      res.append("{ ")
        .append("\"responseMessage\" : \"New coupon with id=" + coupon.getId() + "created successfully\"")
        .append(" }");
    }
    catch (CouponsGenericException|ParseException e)
    {
      res.append("{ ").append("\"errorMessage\" : \"" + e.getMessage() + "\"").append(" }");
      
      System.out.println("Response from server = " + res);
      return new String(res);
    }
    System.out.println("Response from server = " + res);
    return new String(res);
  }
  
  @POST
  @Path("/removeCoupon")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String removeCoupon(@QueryParam("couponID") long id, @QueryParam("couponTittle") String title, @QueryParam("couponStartDate") String startDate, @QueryParam("couponEndDate") String endDate, @QueryParam("couponAmount") int amount, @QueryParam("couponType") String type, @QueryParam("couponMessage") String message, @QueryParam("couponPrice") double price, @QueryParam("couponImage") String image)
  {
    StringBuilder res = new StringBuilder();
    try
    {
      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
      Date sDate = new Date(sdf1.parse(startDate).getTime());
      Date eDate = new Date(sdf1.parse(endDate).getTime());
      Coupon coupon = new Coupon(id, title, sDate, eDate, amount, CouponType.valueOf(type), message, price, image);
      
      new CompanyFacade(1L).removeCoupon(coupon);
      res.append("{ ")
        .append("\"responseMessage\" : \"Coupon with ID " + id + " removed successfully in the system\"")
        .append(" }");
    }
    catch (CouponsGenericException|ParseException e)
    {
      System.err.println(e.getMessage());
      res.append("{ ")
        .append("\"responseMessage\" : \"" + e.getMessage() + "\"")
        .append(" }");
      
      System.out.println("Response from server = " + res);
      return new String(res);
    }
    System.out.println("Response from server = " + res);
    return new String(res);
  }
  
  @POST
  @Path("/updateCoupon")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String updateCoupon(@QueryParam("couponID") long id, @QueryParam("couponTittle") String title, @QueryParam("couponStartDate") String startDate, @QueryParam("couponEndDate") String endDate, @QueryParam("couponAmount") int amount, @QueryParam("couponType") String type, @QueryParam("couponMessage") String message, @QueryParam("couponPrice") double price, @QueryParam("couponImage") String image)
  {
    StringBuilder res = new StringBuilder();
    try
    {
      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
      Date sDate = new Date(sdf1.parse(startDate).getTime());
      Date eDate = new Date(sdf1.parse(endDate).getTime());
      Coupon coupon = new Coupon(id, title, sDate, eDate, amount, CouponType.valueOf(type), message, price, image);
      
      new CompanyFacade(1L).updateCoupon(coupon);
      res.append("{ ")
        .append("\"responseMessage\" : \"Coupon with ID " + id + " updated successfully in the system\"")
        .append(" }");
    }
    catch (CouponsGenericException|ParseException e)
    {
      System.err.println(e.getMessage());
      res.append("{ ")
        .append("\"responseMessage\" : \"" + e.getMessage() + "\"")
        .append(" }");
      
      System.out.println("Response from server = " + res);
      return new String(res);
    }
    System.out.println("Response from server = " + res);
    return new String(res);
  }
  
  @GET
  @Path("/getCoupon")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String getCoupon(@QueryParam("couponID") long id)
  {
    ObjectMapper objectMapper = new ObjectMapper();
    Coupon coupon = null;
    String jsonResponse = null;
    try
    {
      coupon = new CompanyFacade(1L).getCoupon(id);
      jsonResponse = objectMapper.writeValueAsString(coupon);
      System.out.println("Response from server = " + jsonResponse);
    }
    catch (CouponsGenericException|IOException localCouponsGenericException) {}
    return jsonResponse;
  }
  
  @GET
  @Path("/getAllCoupon")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String getAllCoupon()
  {
    Collection<Coupon> allCoupons = null;
    String jsonStringLiString = null;
    try
    {
      allCoupons = new CompanyFacade(1L).getAllCoupon();
      ObjectMapper objectMapper = new ObjectMapper();
      jsonStringLiString = objectMapper.writeValueAsString(allCoupons);
      System.out.println("Response from server = " + jsonStringLiString);
    }
    catch (CouponsGenericException|IOException localCouponsGenericException) {}
    return jsonStringLiString;
  }
  
  @GET
  @Path("/getCouponByType")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String getCouponByType(@QueryParam("couponType") String type)
  {
    Collection<Coupon> allCoupons = null;
    String jsonStringLiString = null;
    try
    {
      allCoupons = new CompanyFacade(1L).getCouponByType(CouponType.valueOf(type));
      ObjectMapper objectMapper = new ObjectMapper();
      jsonStringLiString = objectMapper.writeValueAsString(allCoupons);
      System.out.println("Response from server = " + jsonStringLiString);
    }
    catch (CouponsGenericException|IOException localCouponsGenericException) {}
    return jsonStringLiString;
  }
  
  @GET
  @Path("/login")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String login(@QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("clientType") String clientType)
  {
    ClientType type = ClientType.valueOf(clientType);
    CouponClientFacade couponClientFacade = new AdminFacade().login(username, password, type);
    if ((couponClientFacade instanceof AdminFacade))
    {
      AdminFacade adminFacade = (AdminFacade)couponClientFacade;
      adminFacade.login(username, password, type);
    }
    if ((couponClientFacade instanceof CompanyFacade))
    {
      CompanyFacade companyFacade = (CompanyFacade)couponClientFacade;
      try
      {
        companyFacade.login(username, password, type);
      }
      catch (CouponsGenericException e)
      {
        e.printStackTrace();
      }
    }
    if ((couponClientFacade instanceof CustomerFacade))
    {
      CustomerFacade customerFacade = (CustomerFacade)couponClientFacade;
      try
      {
        customerFacade.login(username, password, type);
      }
      catch (CouponsGenericException e)
      {
        e.printStackTrace();
      }
    }
    StringBuilder response = new StringBuilder();
    response.append("{ ")
      .append("\"username\" : \"" + username + "\"")
      .append(" , \"password\" : \"" + password + "\"")
      .append(" , \"clientType\" : \"" + clientType + "\"")
      .append(" , \"errorMessage\" : \"\"")
      .append(" }");
    return new String(response);
  }
}
