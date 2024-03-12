<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="base.jsp"/>
<h1>위치히스토리 목록</h1>
<jsp:include page="inc_menu.jsp"/>
<br> <br>
<table class="table-list">
    <thead>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${selectAll}" var="history">
        <tr>
            <td><c:out value="${history.id}"/></td>
            <td><c:out value="${history.lat}"/></td>
            <td><c:out value="${history.lnt}"/></td>
            <td><c:out value="${history.date}"/></td>
            <td style="text-align: center"> <button type ="button" class="button">삭제</button></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>

    $(".button").click(function () {

            var checkBtn = $(this);
            var tr = checkBtn.parent().parent();
            var td = tr.children();
            var deleteIdnumber = td.eq(0).text();
            $.ajax({
                type:"POST",
                url: "http://localhost:8081/removeHistory?deleteIdnumber=" + deleteIdnumber
            }).done(function (){
                location.reload();
            })
        }
    )
</script>