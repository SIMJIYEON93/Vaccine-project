package test_main;

import java.sql.Date;
import java.util.Scanner;

import dao.V_ConditionDao;
import dao.V_UserDao;
import vo.V_CenterVo;
import vo.V_ConditionVo;

public class V_CenterMain_Center {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V_ConditionDao v_condition_dao = V_ConditionDao.getInstance();
		V_UserDao v_user_dao = V_UserDao.getInstance();
		
		String u_id, vaccine, inoc_f, inoc_s;
		int s_num;
		Date inoc_date;
		boolean result = true;
		String menu;

		while (result) {
			System.out.println("[1.예약 전체 조회 / 2.특정병원 예약자 확인 / 3.특정환자 조회 / 4.접종 증명서 출력 / 5.종료]");
			System.out.println("메뉴 선택 해주세요");
			menu = sc.next();
			switch (menu) {
			// 전체 리스트 조회
			case "1":
				System.out.println("예약 전체 조회합니다");
				for (V_ConditionVo vo : v_condition_dao.getList())
					System.out.println(vo);
				break;

			case "2":
				System.out.println("특정병원 예약자 확인");
				System.out.print("아이디을 입력해주세요 >>> ");
				u_id = sc.next();
				// 연번 존재여부
				if (v_user_dao.u_id_check(u_id)) {
					System.out.println("없는 연번입니다.");
				} else {
					// 연번으로 내역검색
					System.out.println(v_condition_dao.getV_Condition(u_id));
				}
				//sc.nextLine();
				break;

			case "3":
				System.out.println("특정환자 조회");
				System.out.print("아이디을 입력해주세요 >>> ");
				u_id = sc.next();
				// 연번 존재여부
				if (v_user_dao.u_id_check(u_id)) {
					System.out.println("없는 아이디입니다.");
				} else {
					// 연번으로 내역검색
					System.out.println(v_condition_dao.getV_Condition(u_id));
				}
				//sc.nextLine();
				break;

			case "4":
				System.out.println("접종 확인서 출력");
				System.out.print("아이디을 입력해주세요 >>> ");
				u_id = sc.next();
				v_condition_dao.certificate(u_id);
				break;
				
			case "5":
				System.out.println("종료합니다.");
				result = false;
				break;

			default:
				System.out.println("잘못 선택하셨습니다");
				break;
			}
		}
		sc.close();
	}

}
