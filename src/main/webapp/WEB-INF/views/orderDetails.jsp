<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<style>
    label {
        float: left;
        padding: 10px;
    }

</style>
<body>

<div class="container text-center">
    <c:if test="${chooseRoomType == true}">
        <div class="container text-center">
            <h1>You need to choose type of room</h1>
        </div>
        <p>
            <a href="/cabinet" class="btn btn-info btn-lg">
                <span class="glyphicon glyphicon-arrow-right"></span> Back
            </a>
        </p>
    </c:if>
    <c:if test="${notEnoughMoney == true}">
        <div class="text-danger">
            <h2> Not enough money </h2>
            <h3>Your current balance : ${user.balance} $</h3>
            <h3>Order price : ${orderPrice} $</h3>
            <div class="container text-center">
                <p>
                    <a href="/cabinet" class="btn btn-info btn-lg">
                        <span class="glyphicon glyphicon-arrow-right"></span> Back
                    </a>
                </p>
            </div>
        </div>
    </c:if>
</div>

<c:if test="${(notEnoughMoney != true) and (chooseRoomType != true)}">

<div class="container text-center">
    <h1>Balance : ${user.balance}</h1>
</div>

<div class="container text-center">
    <form action="/order" method="post" onsubmit="return validateRadioButton()">
            <c:forEach items="${orderedCruises}" var="order" varStatus="generalIndex">
                <div style="border: 1px black solid">
                    <h3>Cruise : </h3>
                    <c:set var="index" value="${generalIndex.index}"/>
                    <table class="table table-bordered">
                        <tr>
                            <th>Name</th>
                            <th>Passenger Amount</th>
                            <th>Route to</th>
                            <th>Route from</th>
                            <th>Date of start trip</th>
                            <th>Date of end trip</th>
                        </tr>
                        <c:forEach items="${orderedCruises}" var="orderedCruises" varStatus="orderedCruisesStatus">
                            <c:if test="${index == orderedCruisesStatus.index}">
                                <tbody>
                                <tr>
                                    <td><c:out value="${orderedCruises.name}"/></td>
                                    <td><c:out value="${orderedCruises.passengerAmount}"/></td>
                                    <td><c:out value="${orderedCruises.routeTo}"/></td>
                                    <td><c:out value="${orderedCruises.routeFrom}"/></td>
                                    <td><c:out value="${orderedCruises.travelStart}"/></td>
                                    <td><c:out value="${orderedCruises.travelEnd}"/></td>
                                </tr>
                                </tbody>
                                <input name="shipId" value="${orderedCruises.id}" type="hidden">
                            </c:if>
                        </c:forEach>
                    </table>
                    <div class="col-lg-offset-3">
                        <div class="col-lg-3 ">
                            <label>
                                <input type="radio" name="type${order.id}" id="PRESIDENT" value="PRESIDENT">
                                President : ${PRESIDENT + order.price} $
                            </label>
                        </div>
                        <div class="col-lg-3">
                            <label>
                                <input type="radio" name="type${order.id}" id="COMFORT" value="COMFORT">
                                Comfort : ${COMFORT + order.price} $
                            </label>
                        </div>
                        <div class="col-lg-3">
                            <label>
                                <input type="radio" name="type${order.id}" id="STANDART" value="STANDART">
                                Standart : ${STANDART + order.price} $
                            </label>
                        </div>
                    </div>
                </div>
                <hr/>
                <hr/>
            </c:forEach>
            <div class="container col-md-6 btn-padding-size-margin">
                <div class="col-md-3" style="float: left">
                    <input id="order" class=" btn btn-success btn-lg" type="submit"
                           value="Submit">
                </div>
                <div class="col-md-3">
                    <p>
                        <a href="/cabinet" class="btn btn-info btn-lg">
                            <span class="glyphicon glyphicon-arrow-right"></span> Back
                        </a>
                    </p>
                </div>
            </div>
        </c:if>
    </form>
</div>

<script>
    function validateRadioButton() {
        if (document.getElementById('PRESIDENT').checked === false &&
            document.getElementById('COMFORT').checked === false &&
            document.getElementById('STANDART').checked === false) {
        }
    }


</script>
</body>
</html>
