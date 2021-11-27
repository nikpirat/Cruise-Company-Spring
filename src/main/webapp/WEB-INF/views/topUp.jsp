<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05.11.2021
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/WEB-INF/styles/topUp.css" %>
    </style>
</head>
<body>
<div class='container'>

    <form class='modal' action="/topUp" method="post">
        <header class='header'>
            <h1>TOP UP BALANCE</h1>

        </header>
        <div class="active-card">
            <div class="card-holder">
                <div class="card-icon"> Icon </div>
                <span> Visa Credit Card </span>
                <span> **** **** **** 1234 </span>
            </div>
        </div>
        <div class='content'>
            <div class='form'>
                <div class='form-row'>
                    <div class='input-group'>
                        <label class='amount'> Amount </label>
                        <div class="amount-wrap">
                            <input placeholder='' name="amount" type='text'>
                            <select name="">
                                <option value="USD" selected="selected">USD </option>
                                <option value="EUR">Euro</option>
                            </select>
                        </div>
                    </div>
                </div>
<%--                <div class='form-row'>--%>
<%--                    <div class='input-group'>--%>
<%--                        <label>Account Statement</label>--%>
<%--                        <input maxlength='16' placeholder='' >--%>
<%--                    </div>--%>
<%--                </div>--%>

            </div>
            <footer class='footer'>
                <button class='button confirm' type="submit">TOP UP BALANCE</button>
                <button class='button cancel' href="/user"> CANCEL</button>
            </footer>
        </div>
    </form>
</div>

</body>
</html>
