package kr.or.kosta.chat.client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import kr.or.kosta.chat.common.Protocol;

public class WaitingPanel extends Panel implements ActionListener{

//	GridBagLayout gridBagLayout;
//	GridBagConstraints gridBagConstraints;
	
	GridLayout gridLayout;
	
	Label waitingL, userL;
	
	Button addRoomB, enterB, searchRoomUserB,searchWaitUserB, logoutB, exitB, enterRoomB;
	
	TextField waitingTF, userTF;
//	Choice setting;
	List roomList; 
	List waitingList, userList;
	
	AddRoomFrame addRoomFrame;
	
	PopupMenu pMenu; 
	MenuItem whisperUserItem;
	
//	MenuBar menuBar;
//	Menu settingRoomMenu, settingMenu;
//	MenuItem addRoomItem, logoutItem, exitItem;
	
	JJ_ChatUI frame;
	
	String sc_nickName, sc_roomUserInfo, toUserWisper;
	
	java.util.List<String> waitingUserList, roomUserList;
	
	
	public WaitingPanel() {}
	
	public WaitingPanel(JJ_ChatUI frame) {
		
		waitingL = new Label("대기실 유저리스트");
		userL = new Label("해당방 유저리스트");
		
		addRoomB = new Button("방 만들기");
		enterB = new Button("입장");
		searchRoomUserB = new Button("대화방 닉 검색");
		searchWaitUserB = new Button("대기실 닉 검색");
		enterRoomB = new Button("입장");
		logoutB = new Button("로그아웃");
		exitB = new Button("종료");
	
		waitingTF = new TextField(20);
		userTF = new TextField(20);
		
//		setting = new Choice();
		roomList = new List(20, false);
		
		roomList.setSize(300, 300);
		waitingList = new List(10, false);
		userList = new List(10, false);
		
		gridLayout = new GridLayout(1, 2, 10,0);
		
//		gridBagLayout = new GridBagLayout();
	
		addRoomFrame = new AddRoomFrame("방 만들기", this);
		
		waitingUserList= new ArrayList<>();
		
		pMenu = new PopupMenu();
		whisperUserItem = new MenuItem("귓속말하기");
		
		pMenu.add(whisperUserItem);
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
		
		Panel menuP = new Panel(new FlowLayout(FlowLayout.RIGHT));
		menuP.add(enterRoomB);
		menuP.add(addRoomB);
		menuP.add(logoutB);
		menuP.add(exitB);
		
		Panel roomP = new Panel(new BorderLayout(10, 10));
		roomP.add(roomList, BorderLayout.CENTER);

		Panel userP = new Panel(new BorderLayout(10, 10));
		Panel northUserP = new Panel(new BorderLayout(10, 10));
		Panel sumNorthUserP = new Panel(new GridLayout(2, 1, 10, 0));
		Panel userGridP = new Panel(new FlowLayout(FlowLayout.LEFT));
		northUserP.add(userL, BorderLayout.WEST);
		userGridP.add(userTF);
		userGridP.add(searchRoomUserB);
		sumNorthUserP.add(northUserP);
		sumNorthUserP.add(userGridP);
//		userP.add(northUserP, BorderLayout.NORTH);
//		userP.add(userGridP, BorderLayout.CENTER);
		userP.add(sumNorthUserP, BorderLayout.NORTH);
		userP.add(userList, BorderLayout.CENTER);
		
		Panel waitingP = new Panel(new BorderLayout(10, 10));
		Panel northWaitingP = new Panel(new BorderLayout(10, 10));
		Panel sumNorthWaitingP = new Panel(new GridLayout(2, 1, 10, 0));
		Panel waitingGridP = new Panel(new FlowLayout(FlowLayout.LEFT));
		northWaitingP.add(waitingL, BorderLayout.WEST);
		waitingGridP.add(waitingTF);
		waitingGridP.add(searchWaitUserB);
		sumNorthWaitingP.add(northWaitingP);
		sumNorthWaitingP.add(waitingGridP);
//		waitingP.add(northWaitingP, BorderLayout.NORTH);
//		waitingP.add(waitingGridP, BorderLayout.CENTER);
		waitingP.add(sumNorthWaitingP, BorderLayout.NORTH);
		waitingP.add(waitingList, BorderLayout.CENTER);
		
		Panel WestP = new Panel(new BorderLayout(10, 10));
		Panel EastP = new Panel(new BorderLayout(10, 10));
		
		WestP.add(menuP, BorderLayout.NORTH);
		WestP.add(roomP, BorderLayout.CENTER);
		EastP.add(userP, BorderLayout.NORTH);
		EastP.add(waitingP, BorderLayout.CENTER);
		
		add(WestP);
		add(EastP);
		add(pMenu);
	}
	
	public void sc_getWaiting(String sc_nickNammes) {
		this.sc_nickName = sc_nickNammes;
		String[] nickname = sc_nickNammes.substring(1,sc_nickNammes.length()-1).replaceAll(" ", "").split(",");
		Arrays.sort(nickname, String.CASE_INSENSITIVE_ORDER); //order nickname
		waitingList.removeAll();
		for (String temp : nickname) {
			waitingUserList.add(temp);
			waitingList.add(temp);
		}
	}
	
	public void sc_getRoomInfo(String sc_rooms) {
		String[] room = sc_rooms.substring(1, sc_rooms.length()-1).replaceAll(" ", "").split(",");
		Arrays.sort(room, String.CASE_INSENSITIVE_ORDER); //order room
		roomList.removeAll();
		for (String temp : room) {
			String[] roomInfo = temp.split(Protocol.DELEMETER2);
			roomList.add(String.format("%-5s%-20s%-20s%-5s", roomInfo[0], roomInfo[1], roomInfo[2], roomInfo[3]));
		}
	}
	
	public void sc_getRoomUserInfo(String sc_roomUserInfo) {
		this.sc_roomUserInfo = sc_roomUserInfo;
		String[] roomUser = sc_roomUserInfo.substring(1, sc_roomUserInfo.length()-1).replaceAll(" ", "").split(",");
		Arrays.sort(roomUser, String.CASE_INSENSITIVE_ORDER);
		userList.removeAll();
		roomUserList = new ArrayList<>();
		for (String temp : roomUser) {
			roomUserList.add(temp);
			userList.add(temp);
		}
	}
	
	
	public void cs_checkRoomName(String name) {
		//방이름 중복체크 확인 
		frame.sendMessage(Protocol.CS_ROOM_NAME+Protocol.DELEMETER+name);
	}
	
	public void sc_checkRoomName(String msg) {
		//방이름 확인 여부 받음
		addRoomFrame.sc_CheckName(msg);
	}
	public void sc_checkCreateRoom(String msg) {
		//방 만들기 확인
		addRoomFrame.sc_checkCreate(msg);
	}
	
	public void createRoom(String name, String owner, String capacity) {
		//방만들려고 메시지 보낼 것
		frame.sendMessage(Protocol.CS_ROOM_CREATE+ Protocol.DELEMETER+name+Protocol.DELEMETER2+owner+Protocol.DELEMETER2+capacity);
		System.out.println("방 만들어줘!!");
	}
	
	public void changeCardPanel(int num) {
		if(num==0) {
			//logout

			
			frame.setSize(300, 200);
			frame.setCenter();
			frame.getLoginPanel().init();
			frame.changeCard("login");
			return;
		}else if(num==2) {
			frame.pack();
			frame.setCenter();
			frame.changeCard("room");
			return;
		}
	}
	
	
	public void searchUserList() {
		//대화방 닉네임 검색
		String tempRoomUser = userTF.getText();
//		System.out.println(tempRoomUser);
		if(tempRoomUser.trim().equals("")) {
			sc_getRoomUserInfo(sc_roomUserInfo);
		}
		userList.removeAll();
		for (String string : roomUserList) {
			System.out.println(string);
			if(string.contains(tempRoomUser)) {
				userList.add(string);
			}
		}
	}
	
	public void searchRoomUserList() {
		//대기실 닉네임 검색
		String tempNickName = waitingTF.getText();
		if(tempNickName.trim().equals("")) {
			sc_getWaiting(sc_nickName);
			return;
		}
		waitingList.removeAll();
		for (String string : waitingUserList) {
			if(string.contains(tempNickName)) {
				waitingList.add(string);
			}
		}
	}
	
	//방입장하기
	public void sc_enterRoom() {
		changeCardPanel(2);
	}
	
	public String getNickName() {
//		System.out.println(frame.getKey_nickName());
		return frame.getKey_nickName();
	}
	
	//귓속말 보내기
	public void sendWhisper(String ToUser) {
		System.out.println(ToUser + "에게 귓속말");
	}
	
	public void eventRegist() {
		//방 입장
		enterRoomB.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String roomName = roomList.getSelectedItem().split("\\s+")[1];
				frame.sendMessage(Protocol.CS_REQUEST_ENTER_CHATROOM + Protocol.DELEMETER + frame.getKey_nickName() + Protocol.DELEMETER + roomName);
			}
		});
		
		searchRoomUserB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchUserList();
			}
		});
		
		userTF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchUserList();
			}
		});
		
		searchWaitUserB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchRoomUserList();
			}
		});
		
		waitingTF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchRoomUserList();
			}
		});
		
		roomList.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectRoomName = roomList.getSelectedItem().split("\\s+")[1];
				frame.sendMessage(Protocol.CS_GETROOM_INFO+Protocol.DELEMETER+selectRoomName);
			}
		});
		
		userList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toUserWisper = userList.getSelectedItem();
				if(userList.getSelectedIndex()>=0) {
					if(e.getModifiers() == InputEvent.BUTTON3_MASK) {
						pMenu.show(e.getComponent().getParent(), e.getX()+5, e.getY()+80);
					}
				}
			}
		});
		
		waitingList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toUserWisper = waitingList.getSelectedItem();
				//오른쪽 버튼
				if(waitingList.getSelectedIndex()>=0) {
					if(e.getModifiers() == InputEvent.BUTTON3_MASK) {
						pMenu.show(e.getComponent().getParent(), e.getX()+5, e.getY()+80);
					}
				}
			}
		});
		pMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("귓속말하기")) {
					sendWhisper(toUserWisper);
				}
			}
		});
		
		
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
				frame.sendMessage(Protocol.CS_LOGOUT + Protocol.DELEMETER + frame.getKey_nickName()); //로그아웃
				changeCardPanel(0);
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
