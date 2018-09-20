package kr.or.kosta.chat.client;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kr.or.kosta.chat.common.Protocol;

public class LoginPanel extends Panel implements ActionListener{
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	JLabel nickNameL, emptyL1, emptyL2;
	JTextField nickNameTF;
	JButton duplicateB, loginB, exitB;
	
	JJ_ChatUI frame;
	
	Font font;
	
	Color buttonColor;

	static boolean isCheck = false;
	
	public LoginPanel() {}
	
	public LoginPanel(JJ_ChatUI frame) {
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		font = new Font(Font.DIALOG, Font.PLAIN, 14);
		buttonColor = new Color(255, 204, 000);
		
		nickNameL = new JLabel("닉네임");
		nickNameL.setFont(font);
		nickNameTF = new JTextField(30);
		nickNameTF.setFont(font);
		
		duplicateB = new JButton("중복체크");
		duplicateB.setFont(font);
		loginB = new JButton("입장");
		loginB.setFont(font);
		exitB = new JButton("종료");
		exitB.setFont(font);
		
		exitB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	
		loginB.addActionListener(this);
		this.frame = frame;
		init();
		setContents();	
	}
	public void init() {
		isCheck = false;
		nickNameTF.setEditable(true);
		nickNameTF.setText("");
	}
	
	public void setContents() {
		
		setLayout(gridBagLayout);
		add(new Label(" "),	0, 0, 1, 1, 0, 0, 1);		
		
		add(nickNameL,  	0, 1, 1, 1, 0, 0, 0);
		add(nickNameTF, 	1, 1, 1, 1, 0, 0, 1);
		add(duplicateB,   	2, 1, 1, 1, 0, 0, 0);
		Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER,30,40));
		buttonPanel.add(loginB);
		buttonPanel.add(exitB);
		add(buttonPanel, 	1, 2, 1, 1, 0, 0, 0);
	}
	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weigthx,
			double weighty, int fill) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weigthx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.anchor = gridBagConstraints.WEST;

		gridBagConstraints.insets = new Insets(5, 5, 5, 5); // top, left, bottom, right

		switch (fill) {
		case 1:
			gridBagConstraints.fill = gridBagConstraints.BOTH;
			break;
		case 2:
			gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
			break;
		case 3:
			gridBagConstraints.fill = gridBagConstraints.VERTICAL;
			break;
		default:
			gridBagConstraints.fill = gridBagConstraints.NONE;
			break;
		}

		gridBagLayout.setConstraints(component, gridBagConstraints);

		add(component);
	}
	
	/**
	 * 닉네임 중복 확인 물어보기
	 */
	public void cs_checkNickName() {
		String nickName = nickNameTF.getText();
		frame.sendMessage(Protocol.CS_LOGIN+Protocol.DELEMETER+nickName);
	}
	
	
	/** 
	 * 닉네임 중복확인 결과에 따라 처리
	 * @param message 닉네임 중복확인 결과 메시지
	 */
	public void sc_checkNickName(String message) {
		if(!(message.equalsIgnoreCase("SUCCESS"))){
			JOptionPane.showMessageDialog(null, "닉네임이 이미 존재합니다.", "중복체크", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int isUsernickName = JOptionPane.showConfirmDialog(null, "사용가능한 닉네임입니다.\n사용하시겠습니까?", "닉네임 사용", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(isUsernickName == 0) {
			isCheck = true;
			nickNameTF.setEditable(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.setKey_nickName(nickNameTF.getText());
		frame.sendMessage(Protocol.CS_ENTER+Protocol.DELEMETER+"Login");
		frame.pack();
		frame.setCenter();
		frame.changeCard("waiting");
	}
	
	public void eventRegist() {
		
		nickNameTF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nickNameTF.getText();
				if(name.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요","입력 필수" , JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					frame.connect();
					cs_checkNickName();
				}
			}
		});
		duplicateB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nickNameTF.getText();
				if(name.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요","입력 필수" , JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					//연결은 한 번만 해야 하니까 체크 필수
					frame.connect();
					cs_checkNickName();
				}
			}
		});
		
		loginB.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!(isCheck)) {
					JOptionPane.showMessageDialog(null, "닉네임 중복확인을 눌러주세요", "중복체크", JOptionPane.ERROR_MESSAGE);
				}else {
					loginB.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
						}
					});
				}
			}; 
		});
	}
}
