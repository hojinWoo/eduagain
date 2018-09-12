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
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import kr.or.kosta.chat.common.Protocol;

public class RoomPanel extends Panel implements ActionListener {

	JJ_ChatUI frame;
	GridLayout gridLayout;

	Label waitingL, userL, writeMsg;

	Button searchRoomUserB, searchWaitUserB, sendMsgB;

	TextField waitingTF, userTF, sendMsgTF;

	TextArea chattingTA;

	List waitingList, userList;

	PopupMenu pMenu;
	MenuItem whisperUserItem, inviteUserItem;

	String sc_nickName, sc_roomUserInfo, toUserWisper;

	java.util.List<String> waitingUserList, roomUserList;

	public RoomPanel() {
	}

	public RoomPanel(JJ_ChatUI frame) {

		waitingL = new Label("대기실 유저리스트");
		userL = new Label("해당방 유저리스트");
		writeMsg = new Label("메시지 입력");

		searchRoomUserB = new Button("대화방 닉 검색");
		searchWaitUserB = new Button("대기실 닉 검색");
		sendMsgB = new Button("전송");

		waitingTF = new TextField(20);
		userTF = new TextField(20);
		sendMsgTF = new TextField(50);

		gridLayout = new GridLayout(1, 2, 10, 0);

		chattingTA = new TextArea("", 30, 60); // 크기 조절 필요

		waitingList = new List(10, false);
		userList = new List(10, false);

		waitingUserList = new ArrayList<>();

		pMenu = new PopupMenu();
		whisperUserItem = new MenuItem("귓속말하기");
		inviteUserItem = new MenuItem("초대하기");
		pMenu.add(whisperUserItem);
		pMenu.add(inviteUserItem);

		this.frame = frame;
		setContents();
	}

	public void setContents() {
		setLayout(gridLayout);

		Panel WestP = new Panel(new BorderLayout(10, 10));
		Panel EastP = new Panel(new BorderLayout(10, 10));

		Panel textP = new Panel(new FlowLayout());
		textP.add(chattingTA);

		Panel writeP = new Panel(new FlowLayout(FlowLayout.CENTER));
		writeP.add(writeMsg);
		writeP.add(sendMsgTF);
		writeP.add(sendMsgB);

		Panel userP = new Panel(new BorderLayout(10, 10));
		Panel northUserP = new Panel(new BorderLayout(10, 10));
		Panel sumNorthUserP = new Panel(new GridLayout(2, 1, 10, 0));
		Panel userGridP = new Panel(new FlowLayout(FlowLayout.LEFT));
		northUserP.add(userL, BorderLayout.WEST);
		userGridP.add(userTF);
		userGridP.add(searchRoomUserB);
		sumNorthUserP.add(northUserP);
		sumNorthUserP.add(userGridP);
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
		waitingP.add(sumNorthWaitingP, BorderLayout.NORTH);
		waitingP.add(waitingList, BorderLayout.CENTER);

		WestP.add(textP, BorderLayout.CENTER);
		WestP.add(writeP, BorderLayout.SOUTH);
		EastP.add(userP, BorderLayout.NORTH);
		EastP.add(waitingP, BorderLayout.CENTER);

		add(WestP);
		add(EastP);
		add(pMenu);
	}

	public void sc_getWaiting(String sc_nickNammes) {
		this.sc_nickName = sc_nickNammes;
		String[] nickname = sc_nickNammes.substring(1, sc_nickNammes.length() - 1).replaceAll(" ", "").split(",");
		Arrays.sort(nickname, String.CASE_INSENSITIVE_ORDER); // order nickname
		waitingList.removeAll();
		for (String temp : nickname) {
			waitingUserList.add(temp);
			waitingList.add(temp);
		}
	}

	public void sc_getRoomUserInfo(String sc_roomUserInfo) {
		this.sc_roomUserInfo = sc_roomUserInfo;
		String[] roomUser = sc_roomUserInfo.substring(1, sc_roomUserInfo.length() - 1).replaceAll(" ", "").split(",");
		Arrays.sort(roomUser, String.CASE_INSENSITIVE_ORDER);
		userList.removeAll();
		roomUserList = new ArrayList<>();
		for (String temp : roomUser) {
			roomUserList.add(temp);
			userList.add(temp);
		}
	}

	public void searchUserList() {
		// 대화방 닉네임 검색
		String tempRoomUser = userTF.getText();
		if (tempRoomUser.trim().equals("")) {
			sc_getRoomUserInfo(sc_roomUserInfo);
		}
		userList.removeAll();
		for (String string : roomUserList) {
			System.out.println(string);
			if (string.contains(tempRoomUser)) {
				userList.add(string);
			}
		}
	}

	public void searchRoomUserList() {
		// 대기실 닉네임 검색
		String tempNickName = waitingTF.getText();
		if (tempNickName.trim().equals("")) {
			sc_getWaiting(sc_nickName);
			return;
		}
		waitingList.removeAll();
		for (String string : waitingUserList) {
			if (string.contains(tempNickName)) {
				waitingList.add(string);
			}
		}
	}

	public void changeCardPanel() {
		// 방 나가기 한 경우
		frame.pack();
		frame.setCenter();
		frame.changeCard("waiting");

	}

	// 귓속말 보내기
	public void sendWhisper(String ToUser) {
		System.out.println(ToUser + "에게 귓속말");
	}
	
	public void uploadMessage(String sender, String msg) {
		chattingTA.append(sender+" : "+msg+"\n");
	}

	public void eventRegist() {

		sendMsgTF.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 채팅 보내기
				frame.sendMessage(Protocol.CS_ROOM_CHAT + Protocol.DELEMETER + frame.getKey_nickName()
						+ Protocol.DELEMETER + sendMsgTF.getText());
				sendMsgTF.setText("");
			}
		});

		sendMsgB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 채팅 보내기
				frame.sendMessage(Protocol.CS_ROOM_CHAT + Protocol.DELEMETER + frame.getKey_nickName()
						+ Protocol.DELEMETER + sendMsgTF.getText());
				sendMsgTF.setText("");
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

		waitingList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toUserWisper = waitingList.getSelectedItem();
				// 오른쪽 버튼
				if (waitingList.getSelectedIndex() >= 0) {
					if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
						pMenu.show(e.getComponent().getParent(), e.getX() + 5, e.getY() + 80);
					}
				}
			}
		});

		pMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("귓속말하기")) {
					sendWhisper(toUserWisper);
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
