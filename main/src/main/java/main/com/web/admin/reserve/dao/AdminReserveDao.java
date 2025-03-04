package main.com.web.admin.reserve.dao;

import static main.com.web.admin.reserve.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import main.com.web.admin.reserve.dto.Member;

public class AdminReserveDao {

	private Properties sql=new Properties();
	
	{
		String path=AdminReserveDao.class
				.getResource("/sql/admin/sql_admin.properties").getPath();
		try (FileReader fr=new FileReader(path)){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	 
	public List<Member> selectMemberAll(Connection conn,int cPage,int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> members=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberAll"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(AdminReserveDao.getMember(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return members;
	}
	
	public int selectMemberAllCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberAllCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public List<Member> searchMember(Connection conn, 
			String type, String keyword, String location, int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> members=new ArrayList<>();
		try {
			String sql=this.sql.getProperty("selectSearchMember");
			if (type.equals("roomType")) {
	            sql = sql.replace("#COL", "UPPER(roomType)");
	        } else {
	            sql = sql.replace("#COL", type);
	        }
			pstmt=conn.prepareStatement(sql);
			if(type.equals("memberName") || type.equals("reserveNo") || type.equals("roomType")) {
				pstmt.setString(1, "%"+keyword.toUpperCase()+"%");
				pstmt.setString(2, location);
			}
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(AdminReserveDao.getMember(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return members;
	}
	
	public int searchMemberCount(Connection conn,String type, String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=this.sql.getProperty("searchMemberCount");
		sql=sql.replace("#COL", type);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,type.equals("memberName")?"%"+keyword+"%":keyword);
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
			
		}catch(SQLException e) {
			
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public List<Member> selectMemberByLocation(Connection conn, String location, int cPage, int numPerpage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> members=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberByLocation"));
			pstmt.setString(1,location);
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(AdminReserveDao.getMember(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return members;
	}
	
	public int selectMemberCountByLocation(Connection conn, String location) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberCountByLocation"));
			pstmt.setString(1, location);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	
	public int insertNewMember(Connection conn, Member m) {
	    PreparedStatement pstmt = null;
	    PreparedStatement checkPstmt=null;
	    ResultSet rs=null;
	    int result = 0;
	    try {
	    	 checkPstmt = conn.prepareStatement(sql.getProperty("idDuplicateCheck"));
	         checkPstmt.setString(1, m.getMemberId());
	         rs = checkPstmt.executeQuery();
	    	if(rs.next() && rs.getInt(1) == 0) {
	    	
		        pstmt = conn.prepareStatement(sql.getProperty("insertNewMember"));
		              
		        pstmt.setString(1, m.getLocation());       // LOCATION
		        pstmt.setString(2, m.getMemberId());       // MEMBERID
		        pstmt.setString(3, m.getMemberName());     // MEMBERNAME
		        pstmt.setString(4, m.getRoomType());       // ROOMTYPE
		        pstmt.setString(5, m.getBedType());        // BEDTYPE
		        pstmt.setDate(6, m.getCheckInDate());      // CHECKINDATE
		        pstmt.setDate(7, m.getCheckOutDate());     // CHECKOUTDATE
		        pstmt.setString(8, m.getMemberPhone());    // MEMBERPHONE
		        pstmt.setInt(9, m.getPayPrice());         // PAYPRICE
		        pstmt.setInt(10, m.getRoomPeopleNo());     // ROOMPEOPLENO
		        pstmt.setString(11, m.getMemberAddress()); // MEMBERADDRESS
		        pstmt.setString(12, m.getRequestMemo()); // REQUESTMEMO
		        pstmt.setDate(13, m.getUpdateReserveDate()); //UPDATERESERVEDATE
		        result = pstmt.executeUpdate();
	    	}
	    	else {
	    		 System.out.println("아이디 중복됌");
	             result = -1;
	    	}
	    } catch(SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(pstmt);
	        close(rs);
	        close(checkPstmt);
	    }
	    return result;
	}
	
	
	public Member selectByReserveNo(Connection conn, String reserveNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectByReserveNo"));
			pstmt.setString(1, reserveNo);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=getMember(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
	
	public int updateReserve(Connection conn, Member m) {
		PreparedStatement pstmt = null;
	    int result = 0;
	    try {
		        pstmt = conn.prepareStatement(sql.getProperty("updateReserve"));
		              
		        pstmt.setString(1, m.getReserveNo());
		        pstmt.setString(2, m.getLocation());       // LOCATION
		        pstmt.setString(3, m.getMemberId());       // MEMBERID
		        pstmt.setString(4, m.getMemberName());     // MEMBERNAME
		        pstmt.setString(5, m.getRoomType());       // ROOMTYPE
		        pstmt.setString(6, m.getBedType());        // BEDTYPE
		        pstmt.setDate(7, m.getCheckInDate());      // CHECKINDATE
		        pstmt.setDate(8, m.getCheckOutDate());     // CHECKOUTDATE
		        pstmt.setString(9, m.getMemberPhone());    // MEMBERPHONE
		        pstmt.setInt(10, m.getPayPrice());         // PAYPRICE
		        pstmt.setInt(11, m.getRoomPeopleNo());     // ROOMPEOPLENO
		        pstmt.setString(12, m.getMemberAddress()); // MEMBERADDRESS
		        pstmt.setDate(13, m.getReserveDate());
		        pstmt.setDate(14, m.getUpdateReserveDate()); //UPDATERESERVEDATE
		        pstmt.setString(15, m.getRequestMemo()); // REQUESTMEMO
		        pstmt.setString(16, m.getReserveNo());
		        result = pstmt.executeUpdate();
	    } catch(SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(pstmt);
	    }
	    return result;
	}
	    
	
	
	
	public static Member getMember(ResultSet rs) throws SQLException{
		return Member.builder()
				.reserveNo(rs.getString("RESERVENO"))
				.location(rs.getString("LOCATION"))
				.memberId(rs.getString("MEMBERID"))
				.memberName(rs.getString("MEMBERNAME"))
				.roomType(rs.getString("ROOMTYPE"))
				.bedType(rs.getString("BEDTYPE"))
				.checkInDate(rs.getDate("CHECKINDATE"))
				.checkOutDate(rs.getDate("CHECKOUTDATE"))
				.memberPhone(rs.getString("MEMBERPHONE"))
				.payPrice(rs.getInt("PAYPRICE"))
				.roomPeopleNo(rs.getInt("ROOMPEOPLENO"))
				.updateReserveDate(rs.getDate("UPDATERESERVEDATE"))
				.memberAddress(rs.getString("MEMBERADDRESS"))
				.requestMemo(rs.getString("requestMemo"))
				.reserveDate(rs.getDate("RESERVEDATE"))
				.build();
	}
	
	public int deleteReserve(Connection conn, String reserveNo) {
		PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(sql.getProperty("deleteReserve"));
            pstmt.setString(1, reserveNo);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
}
