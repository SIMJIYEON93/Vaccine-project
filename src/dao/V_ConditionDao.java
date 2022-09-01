package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.jdbc.OracleConnectionUtil;
import vo.V_ConditionVo;
import vo.V_ReservationVo;
import vo.V_UserVo;

public class V_ConditionDao {

	private static V_ConditionDao v_condtion_dao = new V_ConditionDao();

	public V_ConditionDao() {
	}

	public static V_ConditionDao getInstance() {
		return v_condtion_dao;
	}

	// v_condition 전체 조회 -> 모든회원 접종 상태 확인
	public List<V_ConditionVo> getList() {
		String sql = "SELECT * FROM V_CONDITION";
		Connection conn = OracleConnectionUtil.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		V_ConditionVo vo;
		List<V_ConditionVo> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new V_ConditionVo(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getDate(6));
				list.add(vo);
			}
			return list;

		} catch (SQLException e) {
			System.out.println("V_CONDITION List 조회에 문제가 있습니다. : " + e.getMessage());
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

	public boolean u_id_check(String u_id) {
		Connection conn = OracleConnectionUtil.connect();
		String sql = "SELECT * FROM V_CONDITION WHERE U_ID = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql); // sql 쿼리 전달
			pstmt.setString(1, u_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return false; // 중복값이면 false 리턴
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
				conn.close();
			} catch (SQLException e) {
				System.out.println("close 오류 : " + e.getMessage());
				// e.printStackTrace();
			}
		}

		return false;
	}

	// u_id로 내역 검색 -> u_id 현재 상태 조회
	public V_ConditionVo getV_Condition(String u_id) {
		Connection conn = OracleConnectionUtil.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM V_CONDITION WHERE U_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, u_id);
			rs = pstmt.executeQuery();
			V_ConditionVo vo = null;

			if (rs.next()) {
				vo = new V_ConditionVo(rs.getString("U_ID"), rs.getInt("S_NUM"), rs.getString("VACCINE"),
						rs.getString("INOC_F"), rs.getString("INOC_S"), rs.getDate("INOC_DATE"));
			}
			return vo;

		} catch (SQLException e) {
			System.out.println("SQL 실행에 오류가 발생했습니다. : " + e.getMessage());
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

	public void inoc_f_insert(V_ReservationVo vo, Date now_date) {

		if (vo.getInoc_date().before(now_date)) {
			Connection conn = OracleConnectionUtil.connect();
			PreparedStatement pstmt = null;
			// 1차접종 X -> 1차접종 O , 접종날짜 최신화
			String sql = "INSERT INTO V_CONDITION VALUES (?,?,?,'O','X',?)";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getU_id());
				pstmt.setInt(2, vo.getS_num());
				pstmt.setString(3, vo.getVaccine());
				pstmt.setDate(4, vo.getInoc_date());
				pstmt.execute();
			} catch (SQLException e) {
				System.out.println("SQL 실행에 오류가 발생했습니다. : " + e.getMessage());
			} finally {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				OracleConnectionUtil.close(conn);
			}
		}
	}

	public void inoc_s_update(String u_id, Date inoc_date, Date now_date) { // 1,2차 접종 변경시 return true

		if (inoc_date.before(now_date)) {
			Connection conn = OracleConnectionUtil.connect();
			PreparedStatement pstmt = null;

			String sql = "UPDATE V_CONDITION SET INOC_S = ?, INOC_DATE = ? WHERE U_ID = ?";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "O");
				pstmt.setDate(2, inoc_date);
				pstmt.setString(3, u_id);
				pstmt.execute();
			} catch (SQLException e) {
				System.out.println("SQL 실행에 오류가 발생했습니다. : " + e.getMessage());
			} finally {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				OracleConnectionUtil.close(conn);
			}
		}
	}

	// 접종 증명서 출력(txt파일)
	public void certificate(String u_id) {
		String fname = "D:\\JCP0716\\data\\result.txt";
		File file = new File(fname);

		V_ConditionVo vo = getV_Condition(u_id);

		PrintWriter fpw;
		try {
			fpw = new PrintWriter(file);
			fpw.print(vo);
			fpw.close();
			System.out.println("정상 출력되었습니다. 확인해주세요.");
		} catch (FileNotFoundException e) {
			System.out.println("파일 경로명/파일명 확인하세요.");
		}
	}
}
