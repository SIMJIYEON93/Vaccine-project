package gui;

import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.V_CenterDao;
import dao.V_ConditionDao;
import dao.V_ReservationDao;
import dao.V_UserDao;
import vo.V_CenterVo;
import vo.V_ReservationVo;
import vo.V_UserVo;

public class GUIMain extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	// 윈도우를 구성하는 컴포넌트

	JTextField tf1, tf2;
	JLabel la1, la2;
	JButton b1, b2, b3, b4;

	V_UserDao v_user_dao = V_UserDao.getInstance();
	List<V_UserVo> list = new ArrayList<>();

	public GUIMain() { // main
		super("잔여 백신 예약 프로그램_사용자");

		la1 = new JLabel("사용자 ID ");
		la1.setBounds(50, 50, 60, 20);

		tf1 = new JTextField();
		tf1.setBounds(120, 50, 150, 20);

		la2 = new JLabel("생년월일");
		la2.setBounds(50, 75, 60, 20);

		tf2 = new JTextField();
		tf2.setBounds(120, 75, 150, 20);

		b1 = new JButton("로그인");
		b1.setBounds(270, 50, 90, 45);

		b2 = new JButton("회원 가입");
		b2.setBounds(50, 100, 90, 40);

		b3 = new JButton("사용자 조회");
		b3.setBounds(150, 100, 110, 40);

		b4 = new JButton("종료");
		b4.setBounds(270, 100, 90, 40);

		add(la1);
		add(la2);
		add(tf1);
		add(tf2);
		add(b1);
		add(b2);
		add(b3);
		add(b4);

		setLayout(null);
		setSize(430, 240);
		setResizable(false);
		setVisible(true);

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				V_UserDao v_user_dao = V_UserDao.getInstance();
				while (true) {
					if (v_user_dao.login_check(tf1.getText(), tf2.getText()) == false) {
						JOptionPane.showMessageDialog(b1, "없는 계정입니다");
						tf1.setText("");
						tf2.setText("");
						break;
					} else {
						new GUI_Frame(tf1.getText()); // 여기서 다른 프레임으로 넘가는거면 인자로 tf1 id문자열을 받으ㄴ걸 전달하면 되겠는데요?
						setVisible(false); // 창 안보이게 하기
						JOptionPane.showMessageDialog(b1, "로그인 완료");
						break;
					}
				}

			}
		});

		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_Frame2();
				setVisible(false); // 창 안보이게 하기
			}
		});

		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_Frame3();
				setVisible(false); // 창 안보이게 하기
			}
		});

		b4.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b4) {
			JOptionPane.showMessageDialog(this, "프로그램 종료");
			dispose();
			System.exit(0);
		}

	}

	public static void main(String[] args) {
		new GUIMain();
	}

}

class GUI_Frame extends JFrame { // 로그인 -> 예약
	JButton b1, b2, b3, b4;

	GUI_Frame(String id) { // 예약 타이틀
		super("예약"); // 타이틀

		b1 = new JButton("백신 예약");
		b1.setBounds(210, 75, 100, 40);

		b2 = new JButton("예약 조회");
		b2.setBounds(210, 125, 100, 40);

		b3 = new JButton("예약 수정");
		b3.setBounds(210, 175, 100, 40);

		b4 = new JButton("취소");
		b4.setBounds(210, 225, 100, 40);

		add(b1);
		add(b2);
		add(b3);
		add(b4);

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_Frame5(id);
				setVisible(false); // 창 안보이게 하기
			}
		});

		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_Frame6(id);
				setVisible(false); // 창 안보이게 하기
			}
		});

		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_Frame7(id);
				setVisible(false); // 창 안보이게 하기
			}
		});

		b4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUIMain();
				setVisible(false); // 창 안보이게 하기
			}
		});

		setLayout(null);
		setSize(500, 350);
		setResizable(false);
		setVisible(true);

	}

}

class GUI_Frame2 extends JFrame {
	JTextField tf1, tf2, tf3, tf4, tf5;
	JLabel la1, la2, la3, la4, la5, la6, la7;
	JButton b1, b2;

	GUI_Frame2() { // 회원가입 화면
		super("회원 가입"); // 타이틀
		la1 = new JLabel("사용자 ID ");
		la1.setBounds(50, 50, 100, 20);

		tf1 = new JTextField();
		tf1.setBounds(150, 50, 150, 20);

		la2 = new JLabel("생년월일(6자리)");
		la2.setBounds(50, 75, 100, 20);

		tf2 = new JTextField();
		tf2.setBounds(150, 75, 150, 20);

		la3 = new JLabel("성별(M/F)");
		la3.setBounds(50, 100, 100, 20);

		tf3 = new JTextField();
		tf3.setBounds(150, 100, 150, 20);

		la4 = new JLabel("전화번호('-'포함)");
		la4.setBounds(50, 125, 100, 20);

		tf4 = new JTextField();
		tf4.setBounds(150, 125, 150, 20);

		la5 = new JLabel("국적");
		la5.setBounds(50, 150, 100, 20);

		tf5 = new JTextField();
		tf5.setBounds(150, 150, 150, 20);

		la6 = new JLabel("내국인(L)/외국인(F)");
		la6.setBounds(50, 175, 150, 20);

		b1 = new JButton("확인");
		b1.setBounds(50, 220, 90, 40);

		b2 = new JButton("취소");
		b2.setBounds(150, 220, 90, 40);

		add(la1);
		add(la2);
		add(la3);
		add(la4);
		add(la5);
		add(la6);
		add(tf1);
		add(tf2);
		add(tf3);
		add(tf4);
		add(tf5);
		add(b1);
		add(b2);

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 회원가입 완료
				V_UserDao v_user_dao = V_UserDao.getInstance();
				V_UserVo vo;
				vo = new V_UserVo(tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(), tf5.getText());
				while (true) {
					if (v_user_dao.u_id_check(tf1.getText()) == false) {
						JOptionPane.showMessageDialog(b1, "중복된 계정입니다.");
						tf1.setText("");
						tf2.setText("");
						tf3.setText("");
						tf4.setText("");
						tf5.setText("");
						break;

					} else {
						if(tf1.getText().equals("")) {
							JOptionPane.showMessageDialog(b1, "필수 입력값입니다.");
							break;
						}
						v_user_dao.insert_user(vo);
						new GUIMain();
						setVisible(false); // 창 안보이게 하기
						JOptionPane.showMessageDialog(b1, "회원 가입 완료.");
						break;
					}
				}

			}
		});

		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUIMain();
				setVisible(false); // 창 안보이게 하기
			}
		});

		setLayout(null);
		setSize(400, 350);
		setResizable(false);
		setVisible(true);

	}
}

class GUI_Frame3 extends JFrame {
	DefaultTableModel dm;
	JTable jt;
	String[] cols = { "ID", "생년월일", "성별", "전화번호", "내국인/외국인" };

	GUI_Frame3() { // 사용자 조회
		super("사용자 조회"); // 타이틀
		// 아이디로 조회
		V_UserDao v_user_dao = V_UserDao.getInstance();
		List<V_UserVo> list = new ArrayList<>();
		dm = new DefaultTableModel(null, cols);
		jt = new JTable(dm);
		for (V_UserVo vo : v_user_dao.getList())
			list.add(vo);
		String[] data = new String[5];

		Container ctn = getContentPane();
		JScrollPane jsp = new JScrollPane(jt);

		JLabel la = new JLabel("전화번호('-'포함)");
		la.setBounds(20, 10, 100, 30);

		JTextField tf = new JTextField();
		tf.setBounds(130, 10, 200, 30);

		JButton b1 = new JButton("검색");
		b1.setBounds(350, 10, 60, 30);

		JButton b2 = new JButton("종료");
		b2.setBounds(420, 10, 60, 30);

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dm = new DefaultTableModel(null, cols);
				jt.setModel(dm);
				JOptionPane.showMessageDialog(b1, "검색 완료");
				for (int k = dm.getRowCount() - 1; k >= 0; k--)
					dm.removeRow(k);

				String find = tf.getText();
				for (int i = 0; i < list.size(); i++) {
					V_UserVo temp = list.get(i);
					boolean flag;
					if (find.equals(""))
						flag = true;
					else
						flag = temp.getP_num().equals(tf.getText());

					if (flag) {
						data[0] = temp.getU_id();
						data[1] = temp.getU_birth();
						data[2] = temp.getU_gender();
						data[3] = temp.getP_num();
						data[4] = temp.getU_nation();
						dm.addRow(data);
					}
				}

			}
		});

		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUIMain();
				setVisible(false); // 창 안보이게 하기
			}
		});

		jsp.setBounds(10, 60, 550, 500);
		ctn.setLayout(null);
		ctn.add(b1);
		ctn.add(b2);
		ctn.add(la);
		ctn.add(tf);
		ctn.add(jsp);

		pack();

		setBounds(500, 50, 600, 600);
		setVisible(true);

	}

}

class GUI_Frame5 extends JFrame {
	private static final long serialVersionUID = 1L; // 임의 버전
	JLabel la1, la2, la3;
	JTextField tf1, tf2;
	JButton b1, b2, b3;

	GUI_Frame5(String id) {
		super("백신 예약");
		la1 = new JLabel("병원 코드 ");
		la1.setBounds(50, 50, 100, 20);

		tf1 = new JTextField();
		tf1.setBounds(150, 50, 150, 20);

		la2 = new JLabel("접종 날짜 ");
		la2.setBounds(50, 80, 100, 20);

		tf2 = new JTextField();
		tf2.setBounds(150, 80, 150, 20);

		la3 = new JLabel("날짜 형식은 YYYY-MM-DD 입니다 ");
		la3.setBounds(120, 20, 200, 20);

		b1 = new JButton("예약");
		b1.setBounds(50, 110, 90, 40);

		b2 = new JButton("접종 기관 검색");
		b2.setBounds(150, 110, 130, 40);

		b3 = new JButton("취소");
		b3.setBounds(290, 110, 90, 40);

		add(la1);
		add(la2);
		add(la3);
		add(tf1);
		add(tf2);
		add(b1);
		add(b2);
		add(b3);

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				V_CenterDao v_center_dao = V_CenterDao.getInstance();
				V_UserDao v_user_dao = V_UserDao.getInstance();
				V_ReservationDao v_reservation_dao = V_ReservationDao.getInstance();

				V_ReservationVo vo1;
				vo1 = new V_ReservationVo(id, Integer.parseInt(tf1.getText()),
						v_center_dao.getCustom(Integer.parseInt(tf1.getText())).getVaccine(),
						Date.valueOf(tf2.getText()), v_user_dao.getV_User(id).getU_nation());
				// 다른 화면에서 입력받은 텍스트필드를 여기로 가져와서 그것을 넣어주는 상황
				while (true) {
					if (v_center_dao.s_num_check(Integer.parseInt(tf1.getText()))) {
						JOptionPane.showMessageDialog(b1, "없는 병원 코드입니다");
						tf1.setText("");
						break;
					} else {
						if(tf1.getText().equals("")) {
							JOptionPane.showMessageDialog(b1, "필수 입력값입니다.");
							break;
						}
						v_reservation_dao.insert_reservation(vo1);
						JOptionPane.showMessageDialog(b1, "예약 완료");
						new GUI_Frame(id);
						setVisible(false); // 창 안보이게 하기
						break;
					}
				}

			}
		});

		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_Frame8(id);
				setVisible(false); // 창 안보이게 하기
			}
		});

		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_Frame(id);
				setVisible(false); // 창 안보이게 하기
			}
		});

		setLayout(null);
		setSize(430, 200);
		setResizable(false);
		setVisible(true);
	}
}

class GUI_Frame6 extends JFrame {
	DefaultTableModel dm;
	JTable jt;
	String[] cols = { "ID", "병원 코드", "백신종류", "접종날짜", "내국인/외국인" };

	GUI_Frame6(String id) {
		super("예약 조회");
		V_ReservationDao v_reservation_dao = V_ReservationDao.getInstance();
		List<V_ReservationVo> list = new ArrayList<>();
		dm = new DefaultTableModel(null, cols);
		jt = new JTable(dm);
		for (V_ReservationVo vo : v_reservation_dao.getList())
			list.add(vo);

		String data[] = new String[5];

		Container ctn = getContentPane();
		JScrollPane jsp = new JScrollPane(jt);

		JLabel la = new JLabel("ID 조회");
		la.setBounds(10, 10, 100, 30);

		JTextField tf = new JTextField();
		tf.setBounds(120, 10, 200, 30);

		JButton b1 = new JButton("검색");
		b1.setBounds(350, 10, 60, 30);

		JButton b2 = new JButton("종료");
		b2.setBounds(420, 10, 60, 30);

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dm = new DefaultTableModel(null, cols);
				jt.setModel(dm);
				for (int k = dm.getRowCount() - 1; k >= 0; k--)
					dm.removeRow(k);

				String find = tf.getText();
				for (int i = 0; i < list.size(); i++) {
					V_ReservationVo temp = list.get(i);
					boolean flag;
					if (find.equals(""))
						flag = true;
					else
						flag = temp.getU_id().equals(tf.getText());

					if (flag) {
						data[0] = temp.getU_id();
						data[1] = String.valueOf(temp.getS_num());
						data[2] = temp.getVaccine();
						data[3] = String.valueOf(temp.getInoc_date());
						data[4] = temp.getU_nation();
						dm.addRow(data);
					}
				}

			}
		});

		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				V_ReservationDao v_reservation_dao = V_ReservationDao.getInstance();
				V_ConditionDao v_condition_dao = V_ConditionDao.getInstance();

				String to = "2021-09-15";
				Date now_date = Date.valueOf(to);

				if (v_reservation_dao.id_select() != null) {
					for (V_ReservationVo vo : v_reservation_dao.getList()) {
						if (v_condition_dao.u_id_check(vo.getU_id())) // 1차접종 해야하는사람
							v_condition_dao.inoc_f_insert(vo, now_date);
						else
							v_condition_dao.inoc_s_update(vo.getU_id(), vo.getInoc_date(), now_date);
					}
					v_reservation_dao.delete_inoc_date(now_date);
				}
				new GUI_Frame(id);
				setVisible(false); // 창 안보이게 하기

			}
		});

		jsp.setBounds(10, 60, 550, 500);
		ctn.setLayout(null);
		ctn.add(b1);
		ctn.add(b2);
		ctn.add(la);
		ctn.add(tf);
		ctn.add(jsp);

		pack();

		setBounds(500, 50, 600, 600);
		setVisible(true);

	}

}

class GUI_Frame7 extends JFrame {
	JTextField tf1;
	JLabel la1, la2;
	JButton b1, b2;

	GUI_Frame7(String id) {
		super("예약 수정");
		la1 = new JLabel("접종 날짜 ");
		la1.setBounds(50, 50, 80, 20);

		tf1 = new JTextField();
		tf1.setBounds(140, 50, 150, 20);

		la2 = new JLabel("날짜 형식은 YYYY-MM-DD 입니다 ");
		la2.setBounds(50, 80, 250, 20);

		b1 = new JButton("확인");
		b1.setBounds(80, 100, 90, 40);

		b2 = new JButton("취소");
		b2.setBounds(180, 100, 90, 40);

		add(la1);
		add(la2);
		add(tf1);
		add(b1);
		add(b2);

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				V_ReservationDao v_reservation_dao = V_ReservationDao.getInstance();
				Date u_date = Date.valueOf(tf1.getText());
				v_reservation_dao.update_inoc_date(u_date, id);
				new GUI_Frame(id);
				setVisible(false); // 창 안보이게 하기
				JOptionPane.showMessageDialog(b1, "예약 수정 완료");

			}
		});

		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_Frame(id);
				setVisible(false); // 창 안보이게 하기
			}
		});

		setLayout(null);
		setSize(350, 200);
		setResizable(false);
		setVisible(true);

	}
}

class GUI_Frame8 extends JFrame {
	DefaultTableModel dm;
	JTable jt;
	String[] cols = { "병원 코드", "시설명", "주소", "병원전화번호", "백신종류", "잔여백신수" };

	GUI_Frame8(String id) {
		super("센터 조회");
		V_CenterDao v_center_dao = V_CenterDao.getInstance();
		List<V_CenterVo> list = new ArrayList<>();
		dm = new DefaultTableModel(null, cols);
		jt = new JTable(dm);
		for (V_CenterVo vo : v_center_dao.getList())
			list.add(vo);
		String find = "";
		String[] data = new String[6];

		for (int i = 0; i < list.size(); i++) {
			V_CenterVo temp = list.get(i);
			boolean flag;
			if (find.equals(""))
				flag = true;
			else
				flag = temp.getC_name().equals(find);

			if (flag) {
				data[0] = String.valueOf(temp.getS_num());
				data[1] = temp.getC_name();
				data[2] = temp.getC_address();
				data[3] = temp.getC_num();
				data[4] = temp.getVaccine();
				data[5] = String.valueOf(temp.getV_count());
				dm.addRow(data);
			}
		}

		Container ctn = getContentPane();
		JScrollPane jsp = new JScrollPane(jt);

		JLabel la = new JLabel("조회");
		la.setBounds(10, 10, 100, 30);

		JTextField tf = new JTextField();
		tf.setBounds(120, 10, 200, 30);

		String[] temp = { "시설명", "주소" };
		JComboBox<String> jc = new JComboBox<>(temp);
		jc.setBounds(330, 10, 100, 30);

		JButton b1 = new JButton("검색");
		b1.setBounds(440, 10, 60, 30);

		JButton b2 = new JButton("종료");
		b2.setBounds(510, 10, 60, 30);

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dm = new DefaultTableModel(null, cols);
				jt.setModel(dm);
				JOptionPane.showMessageDialog(b1, "검색 완료");
				for (int k = dm.getRowCount() - 1; k >= 0; k--)
					dm.removeRow(k);

				String find = tf.getText();
				for (int i = 0; i < list.size(); i++) {
					V_CenterVo temp = list.get(i);
					boolean flag;
					if (find.equals(""))
						flag = true;
					else if (jc.getSelectedIndex() == 0)
						flag = temp.getC_name().contains(find);
					else
						flag = temp.getC_address().contains(find);

					if (flag) {
						data[0] = String.valueOf(temp.getS_num());
						data[1] = temp.getC_name();
						data[2] = temp.getC_address();
						data[3] = temp.getC_num();
						data[4] = temp.getVaccine();
						data[5] = String.valueOf(temp.getV_count());
						dm.addRow(data);
					}
				}

			}
		});

		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_Frame5(id);
				setVisible(false); // 창 안보이게 하기
			}
		});

		jsp.setBounds(10, 60, 550, 500);
		ctn.setLayout(null);
		ctn.add(b1);
		ctn.add(b2);
		ctn.add(la);
		ctn.add(tf);
		ctn.add(jc);
		ctn.add(jsp);

		pack();

		setBounds(500, 50, 600, 600);
		setVisible(true);

	}

}
