<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="base.jsp"/>
<h1>와이파이 정보 구하기</h1>
<jsp:include page="inc_menu.jsp"/>

<form action="/NearbyWifiServlet" class="location-form">
    <label>LAT:</label>
    <label>
        <input id="latitude-input" name="latitude-input"  step="any" type="number" value="0.0"/>
    </label>
    <label>, LNT:</label>
    <label>
        <input id="longitude-input" name ="longitude-input" step="any"   type="number" value="0.0"/>
    </label>
    <button id="myLocationButton" type="button" class="btn"  onclick="getLocation()">내 위치 가져오기</button>
    <button id="nearWifiButton" type="submit" class ="btn">근처 WIFI 정보 보기</button>
</form>
<c:if test="${not empty searchList}">
    <table class="table-list">
        <colgroup>
            <col style="width: 86px">
            <col style="width: 77px">
            <col style="width: 74px">
            <col style="width: 147px">
            <col style="width: 197px">
            <col style="width: 127px">
            <col style="width: 83px">
            <col style="width: 134px">
            <col style="width: 99px">
            <col style="width: 144px">
            <col style="width: 104px">
            <col style="width: 59px">
            <col style="width: 73px">
            <col style="width: 116px">
            <col style="width: 97px">
            <col style="width: 103px">
            <col style="width: 113px">
        </colgroup>
        <tr >
            <th >거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분 </th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        <tbody>
        <c:forEach items="${searchList}" var="wifi">
            <tr class="warning">
                <td><c:out value="${wifi.distance}"/></td>
                <td><c:out value="${wifi.mgrNo}"/></td>
                <td><c:out value="${wifi.wrdofc}"/></td>
                <td><c:out value="${wifi.mainNm}"/></td>
                <td><c:out value="${wifi.adres1}"/></td>
                <td><c:out value="${wifi.adres2}"/></td>
                <td><c:out value="${wifi.floor}"/></td>
                <td><c:out value="${wifi.ty}"/></td>
                <td><c:out value="${wifi.mby}"/></td>
                <td><c:out value="${wifi.svcSe}"/></td>
                <td><c:out value="${wifi.cmcwr}"/></td>
                <td><c:out value="${wifi.year}"/></td>
                <td><c:out value="${wifi.door}"/></td>
                <td><c:out value="${wifi.remars3}"/></td>
                <td><c:out value="${wifi.lat}"/></td>
                <td><c:out value="${wifi.lnt}"/></td>
                <td><c:out value="${wifi.dttm}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<%-- searchList가 비어있는 경우 --%>
<c:if test="${empty searchList}">
    <table class="table-list">
        <colgroup>
            <col style="width: 86px">
            <col style="width: 77px">
            <col style="width: 74px">
            <col style="width: 147px">
            <col style="width: 197px">
            <col style="width: 127px">
            <col style="width: 83px">
            <col style="width: 134px">
            <col style="width: 99px">
            <col style="width: 144px">
            <col style="width: 104px">
            <col style="width: 59px">
            <col style="width: 73px">
            <col style="width: 116px">
            <col style="width: 97px">
            <col style="width: 103px">
            <col style="width: 113px">
        </colgroup>
        <tr >
            <th >거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분 </th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        <tbody>
        <tr>
            <td rowspan='2' colspan='17' style="text-align: center;padding-top: 24px;padding-bottom: 24px;">위치 정보를 입력한 후에 조회해주세요.</td>
        </tr>
        </tbody>
    </table>
</c:if>

<script>
    function  getLocation(){
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (pos) {
                let latitude = pos.coords.latitude;
                let longitude = pos.coords.longitude;

                document.getElementById('latitude-input').value = latitude;
                document.getElementById('longitude-input').value = longitude;
            });
        } else {
            alert("Geolocation이 지원되지 않습니다.")
        }
    }
</script>