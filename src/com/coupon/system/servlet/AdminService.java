package com.coupon.system.servlet;

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
import datamodel.bean.objects.Company;
import datamodel.bean.objects.Customer;

@Path("/AdminService")
public class AdminService
{
  @GET
  @Path("/getCompany")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String getCompany(@QueryParam("companyID") int id)
  {
    StringBuilder res = new StringBuilder();
    Company company = null;
    try
    {
      company = new AdminFacade().getCompany(id);
      if (company != null) {
        res.append("{ ").append("\"id\" : \"" + company.getId() + "\"").append(" , \"name\" : \"" + company.getCompName() + "\"").append(" , \"email\" : \"" + company.getEmail() + "\"").append(" , \"errorMessage\" : \"\"").append(" }");
      } else {
        res.append("{ ").append("\"id\" : \"\"").append(" , \"name\" : \"\"").append(" , \"email\" : \"\"").append(" , \"errorMessage\" : \"company with id=" + id + " doesn't exsit in the system \"").append(" }");
      }
    }
    catch (CouponsGenericException e)
    {
      res.append("{ ").append("\"id\" : \"\"").append(" , \"name\" : \"\"").append(" , \"email\" : \"\"").append(" , \"errorMessage\" : \"General Error occure in the system\"").append(" }");
      
      return new String(res);
    }
    System.out.println("Response from server = " + res);
    return new String(res);
  }
  
  @POST
  @Path("/createCompany")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String createCompany(@QueryParam("companyID") int compID, @QueryParam("companyName") String compName, @QueryParam("companyPassword") String compPassword, @QueryParam("companyEmail") String compEmail)
  {
    Company company = new Company();
    company.setId(compID);
    company.setCompName(compName);
    company.setPassword(compPassword);
    company.setEmail(compEmail);
    StringBuilder res = new StringBuilder();
    try
    {
      new AdminFacade().createCompany(company);
      res.append("{ ")
        .append("\"responseMessage\" : \"New company with id=+" + compID + "created successfully\"")
        .append(" }");
    }
    catch (CouponsGenericException e)
    {
      System.err.println(e.getMessage());
    }
    return new String(res);
  }
  
  @POST
  @Path("/removeCompany")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String removeCompany(@QueryParam("companyID") int compID)
  {
    Company company = new Company();
    company.setId(compID);
    StringBuilder res = new StringBuilder();
    try
    {
      new AdminFacade().removeCompany(company);
      res.append("{ ")
        .append("\"responseMessage\" : \"Company with ID " + compID + " removed successfully in the system\"")
        .append(" }");
    }
    catch (CouponsGenericException e)
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
  @Path("/updateCompany")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String updateCompany(@QueryParam("companyID") int compID, @QueryParam("companyName") String compName, @QueryParam("companyPassword") String compPassword, @QueryParam("companyEmail") String compEmail)
  {
    Company company = new Company();
    company.setId(compID);
    company.setCompName(compName);
    company.setPassword(compPassword);
    company.setEmail(compEmail);
    StringBuilder res = new StringBuilder();
    try
    {
      new AdminFacade().updateCompany(company);
      res.append("{ ")
        .append("\"responseMessage\" : \"Company with id=" + compID + " updated successfully in the system \"")
        .append(" }");
    }
    catch (CouponsGenericException e)
    {
      System.err.println(e.getMessage());
      res.append("{ ")
        .append("\"responseMessage\" : \"" + e.getMessage() + "\"")
        .append(" }");
      
      System.out.println("Response from server = " + res);
      return new String(res);
    }
    return new String(res);
  }
  
  @GET
  @Path("/getCustomer")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String getCustomer(@QueryParam("customerID") int id)
  {
    StringBuilder res = new StringBuilder();
    Customer customer = null;
    try
    {
      customer = new AdminFacade().getCustomer(id);
      if (customer != null) {
        res.append("{ ").append("\"id\" : \"" + customer.getId() + "\"").append(" , \"name\" : \"" + customer.getCustName() + "\"").append(" , \"password\" : \"" + customer.getPassword() + "\"").append(" , \"couponsList\" : \"" + customer.getCoupons() + "\"").append(" , \"errorMessage\" : \"\"").append(" }");
      } else {
        res.append("{ ").append("\"id\" : \"\"").append(" , \"name\" : \"\"").append(" , \"password\" : \"\"").append(" , \"errorMessage\" : \"customer with id=" + id + " doesn't exsit in the system \"").append(" }");
      }
    }
    catch (CouponsGenericException e)
    {
      res.append("{ ").append("\"id\" : \"\"").append(" , \"name\" : \"\"").append(" , \"password\" : \"\"").append(" , \"errorMessage\" : \"General Error occure in the system\"").append(" }");
      
      return new String(res);
    }
    System.out.println("Response from server = " + res);
    return new String(res);
  }
  
  @POST
  @Path("/createCustomer")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String createCustomer(@QueryParam("customerID") long customerID, @QueryParam("customerName") String customerName, @QueryParam("customerPassword") String customerPassword)
  {
    Customer customer = new Customer();
    customer.setId(customerID);
    customer.setCustName(customerName);
    customer.setPassword(customerPassword);
    customer.setCoupons(null);
    StringBuilder res = new StringBuilder();
    try
    {
      new AdminFacade().createCustomer(customer);
      res.append("{ ")
        .append("\"responseMessage\" : \"New Customer with <id=" + customerID + "> created successfully\"")
        .append(" }");
    }
    catch (CouponsGenericException e)
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
  @Path("/removeCustomer")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String removeCustome(@QueryParam("customerID") long customerID)
  {
    Customer customer = new Customer();
    customer.setId(customerID);
    StringBuilder res = new StringBuilder();
    try
    {
      new AdminFacade().removeCustomer(customer);
      res.append("{ ")
        .append("\"responseMessage\" : \"Customer with ID " + customerID + " removed successfully in the system\"")
        .append(" }");
    }
    catch (CouponsGenericException e)
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
  @Path("/getAllCompanies")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String getAllCompanies()
  {
    StringBuilder response = new StringBuilder();
    Collection<Company> allCompanies = null;
    try
    {
      allCompanies = new AdminFacade().getAllCompanies();
    }
    catch (CouponsGenericException e)
    {
      response.append("{ ").append("\"CompanyList\" : \"\"").append(" , \"errorMessage\" : \"General Error occure in the system\"").append(" }");
      
      return new String(response);
    }
    if (allCompanies.isEmpty())
    {
      response.append("{ ").append("\"CompanyList\" : \"\"").append(" , \"errorMessage\" : \"Company list is empty in the system\"").append(" }");
    }
    else
    {
      Iterator<Company> iterator = allCompanies.iterator();
      response.append("{ ")
        .append("\"CompanyList\" : \"");
      while (iterator.hasNext())
      {
        Company company = (Company)iterator.next();
        response.append(company)
          .append(" ");
      }
      response.append("\"").append(" , \"errorMessage\" : \"\"").append(" }");
    }
    System.out.println("Response from server = " + response);
    return new String(response);
  }
  
  @POST
  @Path("/updateCustomer")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String updateCustomer(@QueryParam("customerID") long customerID, @QueryParam("customerName") String customerName, @QueryParam("customerPassword") String customerPassword)
  {
    Customer customer = new Customer();
    customer.setId(customerID);
    customer.setCustName(customerName);
    customer.setPassword(customerPassword);
    customer.setCoupons(null);
    StringBuilder res = new StringBuilder();
    try
    {
      new AdminFacade().updateCustomer(customer);
      res.append("{ ")
        .append("\"responseMessage\" : \"Customer with id=" + customerID + " updated successfully in the system \"")
        .append(" }");
    }
    catch (CouponsGenericException e)
    {
      System.err.println(e.getMessage());
      res.append("{ ")
        .append("\"responseMessage\" : \"" + e.getMessage() + "\"")
        .append(" }");
      
      System.out.println("Response from server = " + res);
      return new String(res);
    }
    return new String(res);
  }
  
  @GET
  @Path("/getAllCustomers")
  @Produces({"application/json"})
  @Consumes({"application/json"})
  public String getAllCustomers()
  {
    StringBuilder res = new StringBuilder();
    try
    {
      Collection<Customer> allCustomers = new AdminFacade().getAllCustomer();
      Iterator<Customer> iterator = allCustomers.iterator();
      if (allCustomers.isEmpty()) {
        res.append("{ ").append("\"CustomerList\" : \"\"").append(" , \"errorMessage\" : \"Customer list is empty in the system\"").append(" }");
      }
      res.append("{ ").append("\"CustomerList\" : \"");
      while (iterator.hasNext())
      {
        Customer customer = (Customer)iterator.next();
        res.append(customer);
        res.append(" ");
      }
      res.append("\"").append(" , \"errorMessage\" : \"\"").append(" }");
    }
    catch (CouponsGenericException e)
    {
      res.append("{ ").append("\"CustomerList\" : \"\"").append(" , \"errorMessage\" : \"General Error occure in the system\"").append(" }");
      
      return new String(res);
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
