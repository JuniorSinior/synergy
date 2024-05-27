<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="main.com.web.admin.reserve.dto.Member" %>
<% 
	Member m=(Member) request.getAttribute("member");
	Integer roomPeopleNo=(m!=null) ? m.getRoomPeopleNo() : null;
	String roomType=(m!= null) ? m.getRoomType() : null;
	String bedType=(m!=null) ? m.getBedType() : null;
	String location=(m!=null) ? m.getLocation() : null;
%>   
<%@ include file="/WEB-INF/views/common/adminheader.jsp"%>

<section id="update-container">

	<h2>예약 정보 수정</h2>
	<form action="<%=request.getContextPath()%>/reserve/updateendreserve.do" method="post">
	<table>예약번호 : <input type="text" name="reserveNo" value="<%=m.getReserveNo()%>" style="border: none;" readonly>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" id="memberId" name="memberId" placeholder="아이디를 입력하세요" value="<%=m.getMemberId()%>">
				</td>
				<th>가격</th>
				<th>
					<input type="text" id="payPrice" name="payPrice" value="<%=m.getPayPrice() %>" readonly>
				</th>
				<th>예약날짜</th>
				<th>
					<input type="date" id="reserveDate" name="reserveDate" value="<%=m.getReserveDate() %>"  readonly>
				</th>
			</tr>
			<tr>
				<th>이름</th>
				<th>
					<input type="text" placeholder="이름을 입력하세요" name="memberName" value="<%=m.getMemberName() %>">
				</th>
				<th>연락처</th>
				<th>
					<input type="text" placeholder="(-)제외하고 입력해주세요"name="memberPhone" value="<%=m.getMemberPhone() %>">
				</th>
				<th>예약수정날짜</th>
				<th>
					<input type="date" id="updateReserveDate" name="updateReserveDate" value="<%=m.getUpdateReserveDate()%>" readonly>
				</th>
			</tr>
			<tr>
				<th>인원수</th>
			    <th>
			        <select name="roomPeopleNo">
			        	<
			            <option value="1" <%= (roomPeopleNo != null && roomPeopleNo == 1) ? "selected" : "" %>>1인</option>
				        <option value="2" <%= (roomPeopleNo != null && roomPeopleNo == 2) ? "selected" : "" %>>2인</option>
				        <option value="3" <%= (roomPeopleNo != null && roomPeopleNo == 3) ? "selected" : "" %>>3인</option>
				        <option value="4" <%= (roomPeopleNo != null && roomPeopleNo == 4) ? "selected" : "" %>>4인</option> </select>
			    </th>
			    <th>객실타입</th>
			    <th>
				    <select name="roomType">
				        <option value="standard" <%= "standard".equals(roomType) ? "selected" : "" %>>standard</option>
				        <option value="deluxe" <%= "deluxe".equals(roomType) ? "selected" : "" %>>deluxe</option>
				        <option value="suite" <%= "suite".equals(roomType) ? "selected" : "" %>>suite</option>
				    </select>
				</th>
				<th>베드타입</th>
				<th>
				    <select name="bedType">
				        <option value="트윈" <%= "트윈".equals(bedType) ? "selected" : "" %>>트윈</option>
				        <option value="더블" <%= "더블".equals(bedType) ? "selected" : "" %>>더블</option>
				    </select>
				</th>
			</tr>
			<tr>
				<th>지역</th>
				<th>
					<select name="location">
						<option value="서울" <%="서울".equals(m.getLocation()) ? "selected" : "" %>>서울</option>
						<option value="부산" <%="부산".equals(m.getLocation()) ? "selected" : "" %>>부산</option>
						<option value="제주" <%="제주".equals(m.getLocation()) ? "selected" : "" %>>제주</option>
					</select>
				</th>
				<th>입실날짜</th>
				<th>
					<input type="date" name="checkInDate" value="<%=m.getCheckInDate()%>">
				</th>
				<th>퇴실날짜</th>
				<th>
					<input type="date" name="checkOutDate" value="<%=m.getCheckOutDate()%>">
				</th>
			</tr>
			<tr>
				<th >주소</th>	
				<td colspan="5">
					<input type="text" placeholder="주소를 입력하세요" name="memberAddress" style="width: 95%;" value="<%=m.getMemberAddress()%>">	
				</td>
			</tr>
			<tr>
				<td>메모사항</td>
				<td colspan="5"><textarea name="requstMemo" cols="128" rows="10" placeholder="고객요청사항을 적으세요" style="resize:none;" value="<%=m.getRequestMemo()%>"></textarea></td>
			</tr>
		</table>
			
			<input type="submit" value="수정">
			
	</form>
</section>	

	<script>
	   //입실날짜부터 선택해서 퇴실날짜 선택가능하게
	    const checkInDateInput = document.querySelector('input[name="checkInDate"]');
		const checkOutDateInput = document.querySelector('input[name="checkOutDate"]');
		
		checkInDateInput.min = new Date().toISOString().split('T')[0];
		
		checkInDateInput.addEventListener('change', function() {
		    checkOutDateInput.min = checkInDateInput.value;
		});
		    
				
		roomTypeSelect.addEventListener('change', function() {
		    const roomType = roomTypeSelect.value;
		    const price = getPayPrice(roomType);
		    payPriceInput.value = price;
		});
		
		function getPayPrice(roomType){
		    if (roomType === 'standard') {
		        return '100000';
		    } else if (roomType === 'deluxe') {
		        return '200000';
		    } else if (roomType === 'suite') {
		        return '300000';
		    }
		}
	
	document.getElementById('reserveDate').valueAsDate = new Date();
	document.getElementById('updateReserveDate').valueAsDate = new Date();
	</script>

	<style>
     body {
         background-color: lightgray;
         margin: 0;
         padding: 0;
     }
     #update-container {
         width: 60%;
         margin: 2rem auto;
         background-color: white;
         box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
         border-radius: 8px;
         overflow: hidden;
     }
     h2 {
         background-color: lightgray;
         margin: 10px;
         padding: 1rem;
         text-align: center;
     }
     form {
         padding: 1rem;
     }
     table {
         width: 100%;
         border-collapse: collapse;
         margin-bottom: 30px;
         
     }
     th, td {
         padding: 0.75rem;
         text-align: left;
         background-color: lightgray;
         border: 1px solid black;
         text-align: center;
     }
     
     td input[type="text"],
     th input[type="text"], 
     td input[type="date"],
     th input[type="date"],
     td select, 
     th select{
     	 width: 180px;
         border: 1px solid lightgray;
         border-radius: 10px;
         height: 30px;
         text-align: center;
     }
     
     
     textarea {
         resize: none;
         border-radius: 10px;
         text-align: center;
     }
     input[type="submit"] {
         display: block;
         width: 100%;
         padding: 1rem;
         background-color: lightgray;
         border: none;
         color: black;
         border-radius: 4px;
         font-size: 1rem;
         cursor: pointer;
         
     }
     input[type="submit"]:hover {
         background-color: lightblue;
     }
</style>





<%@ include file="/WEB-INF/views/common/adminfooter.jsp"%>