package test_main;

import java.util.Scanner;

import dao.V_CenterDao;
import vo.V_CenterVo;

public class V_CenterMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		V_CenterDao v_center_dao = V_CenterDao.getInstance();

		int s_num;
		String c_address, c_name, vaccine;
		boolean result = true;
		String menu;
		// 잔여백신 --count

		while (result) {
			System.out.println("[1.전체 리스트 조회 / 2.내역검색 / 3.시설명으로 검색 / 4.주소,백신 으로 검색 / 5.종료]");
			System.out.println("메뉴 선택 해주세요");
			menu = sc.next();
			switch (menu) {
			// 전체 리스트 조회
			case "1":
				System.out.println("전체 리스트를 조회 합니다");
				for (V_CenterVo vo : v_center_dao.getList())
					System.out.println(vo);
				break;

			case "2":
				System.out.println("내역 검색을 선택하셨습니다.");
				System.out.print("연번을 입력해주세요 >>> ");
				s_num = sc.nextInt();
				// 연번 존재여부
				if (v_center_dao.s_num_check(s_num)) {
					System.out.println("없는 연번입니다.");
				} else {
					// 연번으로 내역검색
					System.out.println(v_center_dao.getCustom(s_num));
				}
				//sc.nextLine();
				break;

			case "3":
				System.out.println("병원 이름 입력해주세요");
				c_name = sc.next();
				// 병원 이름으로 검색
				for (V_CenterVo vo : v_center_dao.search_by_name(c_name))
					System.out.println(vo);
				break;

			case "4":
				System.out.println("주소를 입력해 주세요(신주소)");
				c_address = sc.next();
				System.out.println("백신 이름을 입력해주세요");
				vaccine = sc.next();
				// 주소와 백신 종류로 검색
				for (V_CenterVo vo : v_center_dao.search_by_address(c_address))
					System.out.println(vo);
				//sc.nextLine();
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