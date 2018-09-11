package kr.or.kosta.chat.client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class AddRoomFrame extends Frame{
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
//	roomNum, roomName, roomOwner, roomCapacity
	
	Label roomNameL, roomOwnerL, roomCapacityL;
	TextField roomNameTF, roomOwnerTF, roomCapacityTF;
	Button checkNameB, createRoomB, cancelB; 
	
	WaitingPanel waitingPanel;
	
	boolean isCheck = false;
	
	public AddRoomFrame() {}
	
	public AddRoomFrame(String title, WaitingPanel waitingPanel) {
		super(title);
		this.waitingPanel = waitingPanel;
		
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		roomNameL = new Label("이름");
		roomOwnerL = new Label("방장");
		roomCapacityL = new Label("참여자 수");
		
		roomNameTF = new TextField(10);
		roomOwnerTF = new TextField(10);
		roomCapacityTF = new TextField(10);
		
		checkNameB = new Button("중복체크");
		createRoomB = new Button("방 추가");
		cancelB = new Button("취소");
		
		setContents();
	}
	
	public void setContents() {
		
		setLayout(gridBagLayout);
		Panel nameP = new Panel(new BorderLayout(40, 10));
		nameP.add(roomNameL, BorderLayout.WEST);
		nameP.add(roomNameTF, BorderLayout.CENTER);
		nameP.add(checkNameB, BorderLayout.EAST);
		
		Panel ownerP = new Panel(new BorderLayout(40, 10));
		ownerP.add(roomOwnerL, BorderLayout.WEST);
		ownerP.add(roomOwnerTF, BorderLayout.CENTER);
		
		Panel capacityP = new Panel(new BorderLayout(13, 10));
		capacityP.add(roomCapacityL, BorderLayout.WEST);
		capacityP.add(roomCapacityTF, BorderLayout.CENTER);
		
		Panel settingP = new Panel(new FlowLayout(FlowLayout.CENTER,20,10));
		settingP.add(new Label());
		settingP.add(createRoomB);
		settingP.add(cancelB);
		
		add(nameP, 		0,0,1,1,0,0,0);
		add(ownerP, 	0,1,1,1,0,0,0);
		add(capacityP, 	0,2,1,1,0,0,0);
		add(settingP, 	0,4,1,1,0,0,0);
		
		
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
	
	public void setCenter() {
		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = (dim.width - getSize().width)/2;
		int y = (dim.height - getSize().height)/2;
		setLocation(x, y);
	}
	
	public void finish() {
		setVisible(false);
		dispose();
	}
	
	public void cs_checkName() {
		String name = roomNameTF.getText();
		if(name.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "이름을 입력해주세요","입력 필수" , JOptionPane.ERROR_MESSAGE);
			return;
		}
		waitingPanel.cs_checkRoomName(name);
	}
	
	public void sc_CheckName(String message) {
		if(!(message.equalsIgnoreCase("SUCCESS"))){
			JOptionPane.showMessageDialog(null, "방이름이 이미 존재합니다.", "중복체크", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int isUsernickName = JOptionPane.showConfirmDialog(null, "사용가능한 방이름입니다.\n사용하시겠습니까?", "방이름 사용", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(isUsernickName == 0) {
			isCheck = true;
			roomNameTF.setEditable(false);
		}
	}
	
	public void createRoom() {
		String name = roomNameTF.getText();
		String owner = roomOwnerTF.getText();
		String capacity = roomCapacityTF.getText();
		waitingPanel.createRoom(name, owner, capacity);
		
		finish();
	}
	
	public void eventRegist() {
		
		roomNameTF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cs_checkName();
			}
		});
		
		checkNameB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cs_checkName();
			}
		});
		createRoomB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		cancelB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				finish();
			}
		});
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
	}
	
	
}
