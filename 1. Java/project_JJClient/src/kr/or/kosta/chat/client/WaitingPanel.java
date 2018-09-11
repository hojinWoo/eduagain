package kr.or.kosta.chat.client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import kr.or.kosta.chat.common.Protocol;

public class WaitingPanel extends Panel implements ActionListener{

//	GridBagLayout gridBagLayout;
//	GridBagConstraints gridBagConstraints;
	
	GridLayout gridLayout;
	
	Label waitingL, userL;
	
	Button addRoomB, enterB, searchRoomUserB,searchWaitUserB, logoutB, exitB;
	
	TextField waitingTF, userTF;
//	Choice setting;
	TextArea waitingTA, userTA;
	List roomList; 
	List waitingList, userList;
	
	AddRoomFrame addRoomFrame;
	
//	MenuBar menuBar;
//	Menu settingRoomMenu, settingMenu;
//	MenuItem addRoomItem, logoutItem, exitItem;
	
	JJ_ChatUI frame;
	
	public WaitingPanel() {}
	
	public WaitingPanel(JJ_ChatUI frame) {
		
		waitingL = new Label("대기실 유저리스트");
		userL = new Label("채팅방 유저리스트");
		
		addRoomB = new Button("방 만들기");
		enterB = new Button("입장");
		searchRoomUserB = new Button("대기실 닉 검색");
		searchWaitUserB = new Button("대화방 닉 검색");
		logoutB = new Button("로그아웃");
		exitB = new Button("종료");
	
		waitingTF = new TextField("대기실 유저");
		userTF = new TextField("대화방 유저");
		
		waitingTA = new TextArea();
		userTA = new TextArea();
//		setting = new Choice();
		roomList = new List();
		roomList.setSize(300, 300);
		waitingList = new List();
		userList = new List();
		
		gridLayout = new GridLayout(1, 2, 10,0);
		
//		gridBagLayout = new GridBagLayout();
	
		addRoomFrame = new AddRoomFrame("방 만들기", this);
//		menuBar = new MenuBar();
//		settingRoomMenu = new Menu("방 설정");
//		settingMenu = new Menu("환경설정");
//		addRoomItem = new MenuItem("방 추가");
//		logoutItem = new MenuItem("로그아웃");
//		exitItem = new MenuItem("종료");
//		
//		exitItem.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//		});
		this.frame = frame;
		
		setContents();
	}
	public void setContents() {
		setLayout(gridLayout);
//		frame.setMenuBar(menuBar);
//		menuBar.add(settingRoomMenu);
//		menuBar.add(settingMenu);
//		settingRoomMenu.add(addRoomItem);
//		settingMenu.add(logoutItem);
//		settingMenu.addSeparator();
//		settingMenu.add(exitItem);
//		setting.add(logoutButton);
		
		Panel menuP = new Panel(new GridBagLayout());
		menuP.add(addRoomB);
		menuP.add(logoutB);
		menuP.add(exitB);
		
		Panel roomP = new Panel(new BorderLayout(10, 10));
		roomP.add(roomList, BorderLayout.CENTER);

		Panel waitingP = new Panel(new BorderLayout(10, 10));
		Panel northWaitingP = new Panel(new BorderLayout(10, 10));
		Panel waitingGridP = new Panel(new FlowLayout());
		northWaitingP.add(waitingL, BorderLayout.WEST);
		waitingGridP.add(waitingTF);
		waitingGridP.add(searchWaitUserB);
		waitingP.add(northWaitingP, BorderLayout.NORTH);
		waitingP.add(waitingGridP, BorderLayout.CENTER);
		waitingP.add(waitingTA, BorderLayout.SOUTH);
		
		Panel userP = new Panel(new BorderLayout(10, 10));
		Panel northUserP = new Panel(new BorderLayout(10, 10));
		Panel userGridP = new Panel(new FlowLayout());
		northUserP.add(userL, BorderLayout.WEST);
		userGridP.add(userTF);
		userGridP.add(searchRoomUserB);
		userP.add(northUserP, BorderLayout.NORTH);
		userP.add(userGridP, BorderLayout.CENTER);
		userP.add(userTA, BorderLayout.SOUTH);
		
		Panel WestP = new Panel(new BorderLayout(10, 10));
		Panel EastP = new Panel(new BorderLayout(10, 10));
		
		WestP.add(menuP, BorderLayout.NORTH);
		WestP.add(roomP, BorderLayout.CENTER);
		EastP.add(waitingP, BorderLayout.NORTH);
		EastP.add(userP, BorderLayout.CENTER);
		
		add(WestP);
		add(EastP);
	}
	
	public void cs_checkRoomName(String name) {
		//방이름 중복체크 확인 
		frame.sendMessage(Protocol.CS_ROOM_NAME+Protocol.DELEMETER+name);
	}
	
	public void sc_checkRoomName() {
		//방이름 확인 여부 받음
	}
	
	public void createRoom(String name, String owner, String capacity) {
		//방만들려고 메시지 보낼 것
		frame.sendMessage(Protocol.CS_ROOM_CREATE+ Protocol.DELEMETER+name+Protocol.DELEMETER2+owner+Protocol.DELEMETER2+capacity);
		System.out.println("방 만들어줘!!");
	}
	
	
	public void eventRegist() {
		
		
		addRoomB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				addRoomFrame.setSize(400, 400);
				addRoomFrame.pack();
				addRoomFrame.eventRegist();
				addRoomFrame.setCenter();
				addRoomFrame.setVisible(true);
				
			}
		});
		
		logoutB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		exitB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.finish();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
