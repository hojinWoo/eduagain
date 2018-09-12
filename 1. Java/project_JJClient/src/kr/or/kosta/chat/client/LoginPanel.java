package kr.or.kosta.chat.client;

import java.awt.Button;
import java.awt.Component;
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

import javax.swing.JOptionPane;

import kr.or.kosta.chat.common.Protocol;

public class LoginPanel extends Panel implements ActionListener{
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	Label nickNameL, emptyL1, emptyL2;
	TextField nickNameTF;
	Button duplicateB, loginB, exitB;
	
	JJ_ChatUI frame;
	
	static boolean isCheck = true;
	
	public LoginPanel() {}
	
	public LoginPanel(JJ_ChatUI frame) {
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		nickNameL = new Label("닉네임");
		nickNameTF = new TextField(10);
		duplicateB = new Button("중복체크");
		loginB = new Button("입장");
		exitB = new Button("종료");
		
		exitB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		emptyL1 = new Label();
		emptyL1.setSize(400, 200);
		emptyL2 = new Label();
		emptyL2.setSize(400, 200);
		
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
		add(emptyL1,		0, 0, 1, 1, 0, 0, 1);		
		
		add(nickNameL,  	0, 1, 1, 1, 0, 0, 0);
		add(nickNameTF, 	1, 1, 1, 1, 0, 0, 2);
		add(duplicateB,   	2, 1, 1, 1, 0, 0, 0);
		add(new Label(""),	3, 1, 1, 1, 0, 0, 0);
		
		Panel buttonPanel = new Panel();
		buttonPanel.add(loginB);
		buttonPanel.add(exitB);
		add(new Label(""),	0, 2, 1, 1, 0, 0, 0);
		add(buttonPanel, 	1, 2, 1, 1, 0, 0, 0);
		add(new Label(""),	2, 2, 1, 1, 0, 0, 0);
		add(new Label(""),	3, 2, 1, 1, 0, 0, 0);
		
		add(emptyL2,		0, 3, 1, 1, 0, 0, 1);		

		
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

		gridBagConstraints.insets = new Insets(5, 5, 5, 5); // margin

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
	
	public void cs_checkNickName() {
		String nickName = nickNameTF.getText();
		frame.sendMessage(Protocol.CS_LOGIN+Protocol.DELEMETER+nickName);
	}
	
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
