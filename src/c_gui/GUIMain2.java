package c_gui;

import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class GUIMain2 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	// 윈도우를 구성하는 컴포넌트

	JButton b1, b2, b3;
	JLabel la1;
	JTextField tf1;

	V_UserDao v_user_dao = V_UserDao.getInstance();
	List<V_UserVo> list = new ArrayList<>();

	public GUIMain2() { // main
		super("잔여 백신 예약 프로그램_관제");

		b1 = new JButton("예약 현황 조회");
		b1.setBounds(75, 30, 150, 40);

		la1 = new JLabel("사용자 ID");
		la1.setBounds(75, 80, 70, 20);

		tf1 = new JTextField();
		tf1.setBounds(155, 80, 70, 20);

		b2 = new JButton("접종 내역 출력");
		b2.setBounds(75, 110, 150, 40);

		b3 = new JButton("종료");
		b3.setBounds(120, 160, 60, 40);

		add(b1);
		add(b2);
		add(b3);
		add(la1);
		add(tf1);

		setLayout(null);
		setSize(300, 280);
		setResizable(false);
		setVisible(true);

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GUI_Frame();
				setVisible(false);

			}

		});

		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(b2, "접종 증명서 출력 완료");
				V_ConditionDao v_condition_dao = V_ConditionDao.getInstance();
				V_UserDao v_user_dao = V_UserDao.getInstance();
				while (true) {
					if (v_user_dao.u_id_check(tf1.getText())) {
						JOptionPane.showMessageDialog(b2, "없는 사용자 입니다.");
						break;
					}else {
						v_condition_dao.certificate(tf1.getText());
						JOptionPane.showMessageDialog(b2, "출력 완료");
						break;
					}
				}
				new GUIMain2();
				setVisible(false);

			}

		});

		b3.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b3) {
			JOptionPane.showMessageDialog(this, "프로그램 종료");
			dispose();
			System.exit(0);
		}

	}

	public static void main(String[] args) {
		new GUIMain2();
	}

}

class GUI_Frame extends JFrame {
	DefaultTableModel dm;
	JTable jt;
	String[] cols = { "ID", "연번", "백신종류", "접종날짜", "내국인/외국인" };

	GUI_Frame() {
		super("예약 현황 조회");
		V_ReservationDao v_reservation_dao = V_ReservationDao.getInstance();
		List<V_ReservationVo> list = new ArrayList<>();
		dm = new DefaultTableModel(null, cols);
		jt = new JTable(dm);
		for (V_ReservationVo vo : v_reservation_dao.getList())
			list.add(vo);
		String find = "";
		String data[] = new String[5];

		for (int i = 0; i < list.size(); i++) {
			V_ReservationVo temp = list.get(i);
			boolean flag;
			if (find.equals(""))
				flag = true;
			else
				flag = temp.getU_id().equals(find);

			if (flag) {
				data[0] = temp.getU_id();
				data[1] = String.valueOf(temp.getS_num());
				data[2] = temp.getVaccine();
				data[3] = String.valueOf(temp.getInoc_date());
				data[4] = temp.getU_nation();
				dm.addRow(data);
			}
		}

		Container ctn = getContentPane();
		JScrollPane jsp = new JScrollPane(jt);

		JLabel la = new JLabel("ID 조회");
		la.setBounds(10, 10, 100, 30);

		JTextField tf = new JTextField();
		tf.setBounds(120, 10, 200, 30);

		JButton b1 = new JButton("검색");
		b1.setBounds(420, 10, 60, 30);

		JButton b2 = new JButton("종료");
		b2.setBounds(490, 10, 60, 30);

		String[] temp = { "연번", "ID" };
		JComboBox<String> jc = new JComboBox<>(temp);
		jc.setBounds(330, 10, 80, 30);

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
					else if (jc.getSelectedIndex() == 0)
						flag = temp.getU_id().contains(tf.getText());
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
				new GUIMain2();
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
		add(jc);

		pack();

		setBounds(500, 50, 600, 600);
		setVisible(true);

	}

}
