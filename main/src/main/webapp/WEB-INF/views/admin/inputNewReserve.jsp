<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/views/common/adminheader.jsp" %>


<section id="new-container">

	<h2>���� �ű� ���</h2>
	<form action="<%=request.getContextPath() %>/reserve/inputnewendreserve.do" method="post">
		<table>
			<tr>
				<th>�̸�</th>
				<th>
					<input type="text" placeholder="�̸��� �Է��ϼ���" name="guestName">
				</th>
				<th>����Ÿ��</th>
				<th>
					<select name="roomSelect">
						<optgroup label="standard">
							<option value="standard01">standard01</option>
							<option value="standard02">standard02</option>
							<option value="standard03">standard03</option>
							<option value="standard04">standard04</option>
							<option value="standard05">standard05</option>
							<option value="standard06">standard06</option>
							<option value="standard07">standard07</option>
							<option value="standard08">standard08</option>
							<option value="standard09">standard09</option>
							<option value="standard10">standard10</option>
						</optgroup>
						<optgroup label="deluxe">
							<option value="deluxe01">deluxe01</option>
							<option value="deluxe02">deluxe02</option>
							<option value="deluxe03">deluxe03</option>
							<option value="deluxe04">deluxe04</option>
							<option value="deluxe05">deluxe05</option>
							<option value="deluxe06">deluxe06</option>
							<option value="deluxe07">deluxe07</option>
							<option value="deluxe08">deluxe08</option>
							<option value="deluxe09">deluxe09</option>
							<option value="deluxe10">deluxe10</option>
						</optgroup>
						<optgroup label="suite">
							<option value="suite01">suite01</option>
							<option value="suite02">suite02</option>
							<option value="suite03">suite03</option>
							<option value="suite04">suite04</option>
							<option value="suite05">suite05</option>
							<option value="suite06">suite06</option>
							<option value="suite07">suite07</option>
							<option value="suite08">suite08</option>
							<option value="suite09">suite09</option>
							<option value="suite10">suite10</option>
						</optgroup>
					</select>
				</th>	
			</tr>
			<tr>
				<th>�Խǳ�¥</th>
				<th>
					<input type="date" name="checkInDate">
				</th>
				<th>��ǳ�¥</th>
				<th>
					<input type="date" name="checkOutDate">
				</th>
			</tr>
			<tr>
				<th>����ó</th>
				<th>
					<input type="text" name="phoneNumber">
				</th>
				<th>�̸���</th>
				<th>
					<input type="text" name="email">
				</th>
			</tr>
			<tr>
				<th>�ο���
					<select>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
					</select>
				</th>
			</tr>
			<tr>
				<td>�ּ�</td>
			</tr>
			<tr>	
				<td>
					<input type="text" placeholder="�ּҸ� �Է��ϼ���" name="address">	
				</td>
			</tr>
			<tr>
				<td>�޸����</td>
			</tr>
			<tr>
				<textarea name="memo" cols="100" rows="10" style="resize:none;">����û����</textarea>
			</tr>
			<tr>
				<th>
					<input type="submit" value="���">
				</th>
			</tr>	
		</table>
	</form>
	
	
		
	<style>
	section#new-container table{
		margin:0 auto;
	}
	section#new-container table th {
		padding:0 10px; text-align:right;
	}
	section#new-container table td {
		padding:0 10px; text-align:left;
	}
	section#new-container {
		text-align:center;
		}
	section#new-container input{
		margin:3px;
		}
	</style>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>