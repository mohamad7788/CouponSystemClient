package com.coupon.system.servlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import business.facade.layer.ifc.ClientType;
import business.facade.layer.ifc.CouponClientFacade;
import business.facade.layer.impl.AdminFacade;
import business.facade.layer.impl.CompanyFacade;
import business.facade.layer.impl.CustomerFacade;
import datamodel.bean.dao.exceptions.CouponsGenericException;
import datamodel.bean.objects.Coupon;
import datamodel.bean.objects.CouponType;

@Path("/CustomerService")
public class CustomerService
{
  @POST
  @Path("/purchaseCoupon")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String purchaseCoupon(@QueryParam("couponID") long id, @QueryParam("couponTittle") String title, @QueryParam("couponStartDate") String startDate, @QueryParam("couponEndDate") String endDate, @QueryParam("couponAmount") int amount, @QueryParam("couponType") String type, @QueryParam("couponMessage") String message, @QueryParam("couponPrice") double price, @QueryParam("couponImage") String image)
  {
    StringBuilder res = new StringBuilder();
    try
    {
      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
      java.sql.Date sDate = new java.sql.Date(sdf1.parse(startDate).getTime());
      java.sql.Date eDate = new java.sql.Date(sdf1.parse(endDate).getTime());
      Coupon coupon = new Coupon(id, title, sDate, eDate, amount, CouponType.valueOf(type), message, price, image);
      
      new CustomerFacade(1L).purchaseCoupon(coupon);
      res.append("{ ")
        .append("\"responseMessage\" : \"New coupon with id=" + coupon.getId() + " purchased successfully\"")
        .append(" }");
    }
    catch (ParseException|CouponsGenericException e)
    {
      res.append("{ ").append("\"errorMessage\" : \"" + e.getMessage() + "\"").append(" }");
      
      System.out.println("Response from server = " + res);
      return new String(res);
    }
    System.out.println("Response from server = " + res);
    return new String(res);
  }
  
  @GET
  @Path("/getAllPurchasedCoupon")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String getAllPurchasedCoupon()
  {
    StringBuilder response = new StringBuilder();
    Collection<Coupon> allCoupons = null;
    try
    {
      allCoupons = new CustomerFacade(1L).getAllPurchasedCoupon();
    }
    catch (CouponsGenericException e)
    {
      response.append("{ ").append("\"CouponList\" : \"\"").append(" , \"errorMessage\" : \"General Error occure in the system\"").append(" }");
      
      return new String(response);
    }
    if (allCoupons.isEmpty())
    {
      response.append("{ ").append("\"CouponList\" : \"\"").append(" , \"errorMessage\" : \"Coupon list is empty in the system\"").append(" }");
    }
    else
    {
      Iterator<Coupon> iterator = allCoupons.iterator();
      response.append("{ ")
        .append("\"CompanyList\" : \"");
      while (iterator.hasNext())
      {
        Coupon coupon = (Coupon)iterator.next();
        response.append(coupon)
          .append(" ");
      }
      response.append("\"").append(" , \"errorMessage\" : \"\"").append(" }");
    }
    System.out.println("Response from server = " + response);
    return new String(response);
  }
  
  @GET
  @Path("/getAllPurchasedCouponByType")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String getAllPurchasedCouponByType(@QueryParam("couponType") String type)
  {
    StringBuilder res = new StringBuilder();
    Collection<Coupon> allCoupons = null;
    try
    {
      allCoupons = new CustomerFacade(1L).getAllPurchasedCouponByType(CouponType.valueOf(type));
    }
    catch (CouponsGenericException e)
    {
      res.append("{ ").append("\"CouponList\" : \"\"").append(" , \"errorMessage\" : \"General Error occure in the system\"").append(" }");
      
      return new String(res);
    }
    if (allCoupons.isEmpty())
    {
      res.append("{ ").append("\"CouponList\" : \"\"").append(" , \"errorMessage\" : \"Coupon list is empty for type<" + type + "> in the system\"").append(" }");
    }
    else
    {
      Iterator<Coupon> iterator = allCoupons.iterator();
      res.append("{ ")
        .append("\"CompanyList\" : \"");
      while (iterator.hasNext())
      {
        Coupon coupon = (Coupon)iterator.next();
        res.append(coupon)
          .append(" ");
      }
      res.append("\"").append(" , \"errorMessage\" : \"\"").append(" }");
    }
    System.out.println("Response from server = " + res);
    return new String(res);
  }
  
  @GET
  @Path("/getAllPurchasedCouponByPrice")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String getAllPurchasedCouponByPrice(@QueryParam("couponPrice") double price)
  {
    StringBuilder res = new StringBuilder();
    Collection<Coupon> allCoupons = null;
    try
    {
      allCoupons = new CustomerFacade(1L).getAllPurchasedCouponByPrice(price);
    }
    catch (CouponsGenericException e)
    {
      res.append("{ ").append("\"CouponList\" : \"\"").append(" , \"errorMessage\" : \"General Error occure in the system\"").append(" }");
      
      return new String(res);
    }
    if (allCoupons.isEmpty())
    {
      res.append("{ ").append("\"CouponList\" : \"\"").append(" , \"errorMessage\" : \"Coupon list is empty bt price<" + price + "> in the system\"").append(" }");
    }
    else
    {
      Iterator<Coupon> iterator = allCoupons.iterator();
      res.append("{ ")
        .append("\"CompanyList\" : \"");
      while (iterator.hasNext())
      {
        Coupon coupon = (Coupon)iterator.next();
        res.append(coupon)
          .append(" ");
      }
      res.append("\"").append(" , \"errorMessage\" : \"\"").append(" }");
    }
    System.out.println("Response from server = " + res);
    return new String(res);
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
