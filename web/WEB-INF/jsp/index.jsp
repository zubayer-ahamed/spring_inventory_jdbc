<%-- 
    Document   : index
    Created on : Sep 13, 2017, 11:08:17 AM
    Author     : cyclingbd007
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <spring:url value="/resources/css/bootstrap.css" var="bootstrapCSS"/>
        <spring:url value="/resources/css/font-awesome.css" var="fontawesomeCSS"/>
        <spring:url value="/resources/js/jquery.min.js" var="jqueryJS"/>
        <spring:url value="/resources/js/bootstrap.js" var="bootstrapJS"/>
        <link rel="stylesheet" type="text/css" href="${bootstrapCSS}"/>
        <link rel="stylesheet" type="text/css" href="${fontawesomeCSS}"/>
        <script src="${jqueryJS}"></script>
        <script src="${bootstrapJS}"></script>
        <style type="text/css">
            .header, .message{
                margin-bottom: 20px;
            }
            th, td{
                text-align: center;
            }
        </style>

    </head>
    <body>

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
                                <li class="active"><a href="<%= request.getContextPath()%>/"><i class="fa fa-home"></i> Home</a></li>
                                <li><a href="<%= request.getContextPath()%>/product"><i class="fa fa-paypal"></i> Insert Product</a></li>
                                <li><a href="<%= request.getContextPath()%>/customer"><i class="fa fa-user-plus"></i> Register Customer</a></li> 
                            </ul>
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>

            <div class="col-md-12 message">
                <c:if test="${sm != null}">
                    <div class="alert alert-success alert-dismissable fade in">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Success!</strong> ${sm}
                    </div>

                </c:if>
                <c:if test="${em != null}">
                    <div class="alert alert-danger alert-dismissable fade in">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Error!</strong> ${em}
                    </div>
                </c:if>


            </div>


            <fom name="orderForm">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-4">
                            Order No: <input type="text" class="form-control" value="${orderNo}"/>
                        </div>
                        <div class="col-md-4">
                            Order Type: 
                            <div class="radio">
                                <label>
                                    <input type="radio" name="orderType">Sell
                                </label> &nbsp; &nbsp; &nbsp;
                                <label>
                                    <input type="radio" name="orderType">Purchase
                                </label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            Order Date: <input type="date" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="col-md-12"  style="margin-top: 20px;">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <i class="fa fa-product-hunt"></i> Select Product
                                </div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label for="Product Name">Product Name:</label>
                                        <select name="pname" class="form-control" onChange="sendInfo()">
                                            <option>--- Select Product --</option>
                                            <c:forEach var="pRow" items="${products}">
                                                <option value="${pRow.pid}">${pRow.pname}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="price">Price: </label>
                                        <input type="text" class="form-control" id="price">
                                    </div>
                                    <div class="form-group">
                                        <label for="qty">Stock: </label>
                                        <input type="text" class="form-control" id="qty">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <i class="fa fa-user-circle"></i> Select Customer
                                </div>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label for="Customer Name">Customer Name:</label>
                                        <select class="form-control">
                                            <option>--- Select Customer --</option>
                                            <c:forEach var="cRow" items="${customers}">
                                                <option value="${cRow.cid}">${cRow.cname}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="phone">Phone: </label>
                                        <input type="text" class="form-control" id="phone">
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <i class="fa fa-sellsy"></i> Order Details
                                </div>
                                <div class="panel-body">
                                    Total: 

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </fom>

            <div class="col-md-12" style="text-align: center; margin-top: 60px;">
                &copy; Zubayer Ahamed
                || <a href="https://www.youtube.com/channel/UC4vVj7lKO7H4FohB3lv9dzA" target="_blank">Youtube</a>  || <a href="http://www.facebook.com/zubayerahamed" target="_blank">Facebook</a>
            </div>

        </div>

    </body>
</html>
