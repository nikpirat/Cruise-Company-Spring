<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>manage users - Bootdey.com</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        <%@include file="/templates/styles/manageCruises.css" %>
    </style>

</head>
<body>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title text-uppercase mb-0">Manage Cruises</h5>
                </div>
                <div class="table-responsive">
                    <table class="table no-wrap user-table mb-0">
                        <thead>
                        <tr>
                            <th scope="col" class="border-0 text-uppercase font-medium pl-4">Name</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Passenger amount</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Route to</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Route from</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Travel start</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Travel End</th>
                            <th scope="col" class="border-0 text-uppercase font-medium">Manage</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userShips}" var="amount" varStatus="generalIndex">
                            <c:set var="index" value="${generalIndex.index}"/>
                            <c:forEach items="${userShips}" var="userShips" varStatus="userShipsIndex">
                                <c:if test="${userShipsIndex.index == index}">
                                    <tr>
                                    <td><c:out value="${userShips.name}"/></td>
                                    <td><c:out value="${userShips.passengerAmount}"/></td>
                                    <td><c:out value="${userShips.routeTo}"/></td>
                                    <td><c:out value="${userShips.routeFrom}"/></td>
                                    <td><c:out value="${userShips.travelStart}"/></td>
                                    <td><c:out value="${userShips.travelEnd}"/></td>
                                </c:if>
                            </c:forEach>
<%--                            <c:forEach items="${cruisesInfo}" var="cruisesInfo" varStatus="cruise">--%>
<%--                                <c:if test="${cruise.index == index}">--%>
<%--                                    <td><c:out value="${cruisesInfo.roomType}"/></td>--%>
<%--                                    <td><c:out value="${cruisesInfo.totalPrice}"/></td>--%>
<%--                                    <td><c:out value="${cruisesInfo.id}"/></td>--%>
<%--                                    </tr>--%>
<%--                                </c:if>--%>
<%--                            </c:forEach>--%>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

</script>
</body>
</html>
