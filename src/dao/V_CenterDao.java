package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.jdbc.OracleConnectionUtil;
import vo.V_CenterVo;

public class V_CenterDao {

	private static V_CenterDao v_center_dao = new V_CenterDao();

	private V_CenterDao() {
	}

	public static V_CenterDao getInstance() {
		return v_center_dao;
	}

	// V_CenterVo 전체 조회
	public List<V_CenterVo> getList() {
		String sql = "SELECT * FROM V_CENTER WHERE V_COUNT > 0";
		Connection conn = OracleConnectionUtil.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		V_CenterVo vo;
		List<V_CenterVo> list = new ArrayList<V_CenterVo>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new V_CenterVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6));
				list.add(vo);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("V_CENTER List 조회에 문제가 있습니다. : " + e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("Close 오류 : " + e.getMessage());
			}
			OracleConnectionUtil.close(conn);
		}
		return null;
	}

	// S_NUM 존재 여부 확인
	public boolean s_num_check(int s_num) {
		Connection conn = OracleConnectionUtil.connect();
		String sql = "SELECT * FROM V_CENTER WHERE S_NUM = ? AND V_COUNT > 0";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql); // sql 쿼리 전달
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return false; 
			} else
				return true;

		} catch (SQLException e) {
			System.out.println("SQL 실행에 오류가 발생했습니다. : " + e.getMessage());
			// e.printStackTrace();
		} finally { // try 안에서 return 할때 finally 실행하고 리턴합니다.
					// 익셉션 발생하여 catch 실행하고 finally 실행.
			try {
				rs.close(); // 꼭 닫기
				pstmt.close(); // 꼭 닫기
			} catch (SQLException e) {
				System.out.println("close 오류 : " + e.getMessage());
				// e.printStackTrace();
			}
		}

		return false;
	}

	// S_NUM로 내역 검색
	public V_CenterVo getCustom(int s_num) {
		Connection conn = OracleConnectionUtil.connect();
		String sql = "SELECT * FROM V_CENTER WHERE S_NUM = ? AND V_COUNT > 0";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String c_name, c_address, c_num, vaccine;
		int v_count;

		V_CenterVo vo = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();

			if (rs.next()) { // where 검색컬럼이 PK,unique 일 때 -> while x , if 문으로
				// 테이블 컬럼과 CustomVo 타입 객체와 매핑 -> 자동으로 할 때가 옵니다.
				s_num = rs.getInt("S_NUM");
				c_name = rs.getString("C_NAME");
				c_address = rs.getString("C_ADDRESS");
				c_num = rs.getString("C_NUM");
				vaccine = rs.getString("VACCINE");
				v_count = rs.getInt("V_COUNT");
				vo = new V_CenterVo(s_num, c_name, c_address, c_num, vaccine, v_count); // 객체 생성하고 초기화
			}

		} catch (SQLException e) {
			System.out.println("SQL 실행에 오류가 발생했습니다. : " + e.getMessage());
			// e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
			}
			OracleConnectionUtil.close(conn);
		}

		return vo;
	}

	// C_ADDRESS, VACCINE 조건으로 조회
	public List<V_CenterVo> search_by_address(String c_address) {
		Connection conn = OracleConnectionUtil.connect();
		String sql = "SELECT * FROM V_CENTER WHERE C_ADDRESS LIKE ? AND V_COUNT > 0";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		V_CenterVo vo;
		List<V_CenterVo> list = new ArrayList<V_CenterVo>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + c_address + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new V_CenterVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6));
				list.add(vo);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("SQL 실행에 오류가 발생했습니다. : " + e.getMessage());
			// e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
			}
			OracleConnectionUtil.close(conn);
		}

		return null;
	}

	// C_NAME 조건으로 조회
	public List<V_CenterVo> search_by_name(String c_name) {
		Connection conn = OracleConnectionUtil.connect();
		String sql = "SELECT * FROM V_CENTER WHERE C_NAME LIKE ? AND V_COUNT > 0";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		V_CenterVo vo;
		List<V_CenterVo> list = new ArrayList<V_CenterVo>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + c_name + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new V_CenterVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6));
				list.add(vo);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("SQL 실행에 오류가 발생했습니다. : " + e.getMessage());
			// e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
			}
			OracleConnectionUtil.close(conn);
		}

		return null;
	}

	// 잔여백신수 --count
	public void v_count_update(int num) {
		V_CenterVo vo = getCustom(num);
		Connection conn = OracleConnectionUtil.connect();
		String sql = "UPDATE V_CENTER SET V_COUNT = V_COUNT - 1 WHERE S_NUM = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getS_num());
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("SQL 실행에 오류가 발생했습니다. : " + e.getMessage());
		} finally {
			OracleConnectionUtil.close(conn);
		}

	}
}