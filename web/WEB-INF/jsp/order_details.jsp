<%-- 
    Document   : index
    Created on : Sep 13, 2017, 11:08:17 AM
    Author     : cyclingbd007
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <spring:url value="/resources/css/bootstrap.css" var="bootstrapCSS"/>
        <spring:url value="/resources/css/font-awesome.css" var="fontawesomeCSS"/>
        <spring:url value="/resources/js/jquery.min.js" var="jqueryJS"/>
        <spring:url value="/resources/js/bootstrap.js" var="bootstrapJS"/>
        <spring:url value="/resources/js/angular.min.js" var="angularJS"/>
        <spring:url value="/resources/js/my_app.js" var="myAppJS"/>
        <link rel="stylesheet" type="text/css" href="${bootstrapCSS}"/>
        <link rel="stylesheet" type="text/css" href="${fontawesomeCSS}"/>
        <script src="${jqueryJS}"></script>
        <script src="${bootstrapJS}"></script>
        <script src="${angularJS}"></script>
        <script src="${myAppJS}"></script>
        <style type="text/css">
            .header, .message{
                margin-bottom: 20px;
            }
            th, td{
                text-align: center;
            }
        </style>
    </head>
    <body ng-controller="orderDetailsChartCtrl">

        <div class="container">

            <div class="col-md-12 header">
                <h1 align="center"><a href="<%= request.getContextPath()%>/">Spring Inventory</a></h1>
            </div>

            <div class="col-md-12 menu">
                <nav class="navbar navbar-inverse">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span> 
                            </button>
                        </div>
                        <div class="collapse navbar-collapse" id="myNavbar">
                            <ul class="nav navbar-nav">
                                <li><a href="<%= request.getContextPath()%>/"><i class="fa fa-home"></i> Home</a></li>
                                <li><a href="<%= request.getContextPath()%>/product"><i class="fa fa-paypal"></i> Insert Product</a></li>
                                <li><a href="<%= request.getContextPath()%>/customer"><i class="fa fa-user-plus"></i> Register Customer</a></li> 
                                <li class="active"><a href="<%= request.getContextPath()%>/order_details"><i class="fa fa-area-chart"></i> Order Details</a></li> 
                            </ul>
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>


            <div class="col-md-8">
                <table class="table table-bordered table-striped table-responsive">
                    <thead>
                        <tr>
                            <th colspan="3" style="color: red;">Order History</th>
                            <th colspan="4">
                                <input ng-model="searchText" type="text" class="form-control" placeholder="Searchy & Filter By Customer Name"/>
                            </th>
                        </tr>
                        <tr>
                            <th style="width: 10px;">S.NO.</th>
                            <th style="width: 10px;">Order ID</th>
                            <th>Customer Name</th>
                            <th style="width: 20px;">Order Type</th>
                            <th style="width: 100px;">Order Date</th>
                            <th style="width: 100px;">Amout (TK)</th>
                            <th style="width: 20px;">Display Order Details</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="order in orders | filter:searchText | limitTo:5">
                            <td>{{$index + 1}}</td>
                            <td>{{order.oid}}</td>
                            <td>{{order.cid}}</td>
                            <td>{{order.orderType}}</td>
                            <td>{{order.orderDate}}</td>
                            <td>{{order.total}}</td>
                            <td><button ng-click="findOd(order)" type="button" class="btn btn-default"><i class="fa fa-search-plus"></i></button></td>
                        </tr>
                    </tbody>
                </table>

            </div>
            <div class="col-md-4">
                <table class="table table-bordered table-responsive table-striped">
                    <thead>
                        <tr>
                            <th colspan="5">Order Details</th>
                        </tr>
                        <tr>
                            <th>ID</th>
                            <th>Product Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="od in orderDetails">
                            <td>{{od.odid}}</td>
                            <td>{{od.pid}}</td>
                            <td>{{od.price}}</td>
                            <td>{{od.qty}}</td>
                            <td>{{od.price * od.qty}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>


            <div class="col-md-12" style="text-align: center;">
                &copy; Zubayer Ahamed
                || <a href="https://www.youtube.com/channel/UC4vVj7lKO7H4FohB3lv9dzA" target="_blank">Youtube</a>  || <a href="http://www.facebook.com/zubayerahamed" target="_blank">Facebook</a>
            </div>

        </div>

    </body>
</html>
