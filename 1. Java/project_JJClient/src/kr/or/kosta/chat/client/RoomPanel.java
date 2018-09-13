package kr.or.kosta.chat.client;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.or.kosta.chat.common.Protocol;

public class RoomPanel extends Panel implements ActionListener {

	JJ_ChatUI frame;

	JLabel waitingL, userL, writeMsgL, roomInfoL, jLabel;

	JButton searchRoomUserB, searchWaitUserB, sendMsgB, outRoomB;

	JTextField waitingTF, userTF, sendMsgTF;

	Choice sendAllC;
	
	JTextArea chattingTA;
	
	Font font;

	List waitingList, userList;

	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	PopupMenu pMenu;
	MenuItem whisperUserItem, inviteUserItem;

	String sc_nickName, sc_roomUserInfo, toUserMenuchoice, toRoomUserWisper;

	java.util.List<String> waitingUserList, roomUserList;

	public RoomPanel() {
	}

	public RoomPanel(JJ_ChatUI frame) {

		font = new Font(Font.DIALOG, Font.PLAIN, 17);
		
		waitingL = new JLabel("대기실 유저리스트");
		waitingL.setFont(font);
		userL = new JLabel("해당방 유저리스트");
		userL.setFont(font);
		writeMsgL = new JLabel("메시지 입력");
		writeMsgL.setFont(font);
		roomInfoL = new JLabel();
		roomInfoL.setSize(200, 10);
		roomInfoL.setFont(font);
		

		searchRoomUserB = new JButton("대화방 닉 검색");
		searchRoomUserB.setFont(font);
		searchWaitUserB = new JButton("대기실 닉 검색");
		searchWaitUserB.setFont(font);
		sendMsgB = new JButton("전송");
		sendMsgB.setFont(font);
		outRoomB = new JButton("방 나가기");
		outRoomB.setFont(font);

		waitingTF = new JTextField(20);
		waitingTF.setFont(font);
		userTF = new JTextField(20);
		userTF.setFont(font);
		sendMsgTF = new JTextField(50);
		sendMsgTF.setFont(font);
		
		jLabel = new JLabel();
		jLabel.setFont(font);

		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();

		chattingTA = new JTextArea("", 25, 70); // 크기 조절 필요
		chattingTA.setBackground(new Color(137, 168, 183));
		chattingTA.setFont(font);
		waitingList = new List(10, false);
		waitingList.setFont(font);
		userList = new List(10, false);
		userList.setFont(font);

		sendAllC = new Choice();
		sendAllC.add("전체");
		sendAllC.setFont(font);
//		sendAllC.add("귓속말"); //방에 있는 사람에게만 귓속말
		
		waitingUserList = new ArrayList<>();

		pMenu = new PopupMenu();
		whisperUserItem = new MenuItem("귓속말하기");
		inviteUserItem = new MenuItem("초대하기");
		pMenu.add(whisperUserItem);
		pMenu.add(inviteUserItem);
		pMenu.setFont(font);
		
		this.frame = frame;
		setContents();
	}

	public void setContents() {
		setLayout(gridBagLayout);

		Panel WestP = new Panel(new BorderLayout(10, 10));
		Panel EastP = new Panel(new BorderLayout(10, 10));

		Panel menuP = new Panel(new BorderLayout(10, 10));
		Panel menuWestP = new Panel(new FlowLayout());
		Panel menwEastP = new Panel(new FlowLayout());
		menuWestP.add(roomInfoL, FlowLayout.LEFT);
		menwEastP.add(outRoomB);
		menuP.add(menuWestP, BorderLayout.WEST);
		menuP.add(menwEastP, BorderLayout.EAST);
		
		Panel textP = new Panel(new FlowLayout());
		textP.add(chattingTA);

		Panel writeP = new Panel(new FlowLayout(FlowLayout.CENTER));
		writeP.add(sendAllC);
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

		WestP.add(menuP, BorderLayout.NORTH);
		WestP.add(textP, BorderLayout.CENTER);
		WestP.add(writeP, BorderLayout.SOUTH);
		EastP.add(userP, BorderLayout.NORTH);
		EastP.add(waitingP, BorderLayout.CENTER);
		
		add(WestP, 	0, 0, 1, 1, 3, 0, 1);
		add(EastP, 	1, 0, 1, 1, 1, 0, 1);
		add(pMenu);
	}
	
	/**
	 * @param component		component 등록
	 * @param gridx			x좌표
	 * @param gridy			y좌표
	 * @param gridwidth		x의 차지 개수
	 * @param gridheight	y의 차지 개수
	 * @param weigthx		x의 margin 비율
	 * @param weighty		y의 margin 비율
	 * @param fill			수직, 수평, 전체 등 칸 전체를 채울 조건 여부 설정
	 */
	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weigthx,
			double weighty, int fill) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weigthx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.anchor = gridBagConstraints.WEST; // 왼쪽에서 시작

		gridBagConstraints.insets = new Insets(5, 5, 5, 5); // margin 주기

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

	/** 대기실에 있는 사람들 띄우기*/
	public void sc_getWaiting(String sc_nickNammes) {
		this.sc_nickName = sc_nickNammes;
		String[] nickname = sc_nickNammes.substring(1,sc_nickNammes.length()-1).replaceAll(" ", "").split(",");
		Arrays.sort(nickname, String.CASE_INSENSITIVE_ORDER); //order nickname
		waitingList.removeAll();
		waitingList.add(frame.getKey_nickName() + "(본인)");
		for (String temp : nickname) {
			waitingUserList.add(temp);
			if(!temp.equals(frame.getKey_nickName())) {
				waitingList.add(temp);
			}
		}
	}

	/** 해당 방에 있는 유저 띄우기 */
	public void sc_getRoomUserInfo(String sc_roomUserInfo) {
		this.sc_roomUserInfo = sc_roomUserInfo;
		String[] roomUser = sc_roomUserInfo.substring(1, sc_roomUserInfo.length() - 1).replaceAll(" ", "").split(",");
		Arrays.sort(roomUser, String.CASE_INSENSITIVE_ORDER);
		userList.removeAll();
		userList.add(frame.getKey_nickName() + "(본인)");
		roomUserList = new ArrayList<>();
		for (String temp : roomUser) {
			roomUserList.add(temp);
			if(!temp.equals(frame.getKey_nickName())) {
				userList.add(temp);
			}
		}
	}

	public void searchUserList() {
		// 대화방 닉네임 검색
		String tempRoomUser = userTF.getText();
		if (tempRoomUser.trim().equals("")) {
			sc_getRoomUserInfo(sc_roomUserInfo);
			return;
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
			System.out.println("빈칸");
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
	
	//방 제목 보이기
	public void setRoomName(String roomname) {
		roomInfoL.setText(roomname + " chat Room");
		roomInfoL.setSize(50, 10);
	}

	public void changeCardPanel() {
		// 방 나가기 한 경우
		frame.pack();
		frame.setCenter();
		frame.changeCard("waiting");

	}

	// 귓속말 보내기
	public void sendWhisper(String receiver) {
		if(frame.getKey_nickName().equals(receiver)) {
			jLabel.setText("자기 자신에게는 보낼 수 없습니다.");
			JOptionPane.showMessageDialog(null, jLabel, "귓속말", JOptionPane.ERROR_MESSAGE);
			return;
		}
		jLabel.setText("메시지를 입력해주세요");
		String msg = JOptionPane.showInputDialog(null, jLabel);
		if(msg != null) {
			frame.sendMessage(Protocol.CS_SECRET_CHAT + Protocol.DELEMETER + frame.getKey_nickName() + Protocol.DELEMETER + receiver + Protocol.DELEMETER + msg);
		}
	}

	
	// 방 안에서 귓속말하기(특정 유저 선택)
	public void wisperMessageInRomm(String reciever) {
		sendAllC.select(1);
		System.out.println(sendAllC.getSelectedIndex() == 1);
	}
	
	//채팅하기
	public void uploadMessage(String sender, String msg, String time) {
		chattingTA.append("["+time+"] "+sender+" : "+msg+"\n");
	}
	//귓속말받기
	public void uploadWisperMsg(String sender, String msg, String time) {
		chattingTA.append("[" + time + "] <" + sender + "님이 보낸 귓속말> : " + msg + "\n");
	}
	
	//채팅 초대하기
	public void inviteUser(String receiver) {
		frame.sendMessage(Protocol.CS_INVITE + Protocol.DELEMETER+ frame.getKey_nickName() +Protocol.DELEMETER + receiver);
	}
	
	//방 나가기 버튼 누른 경우
	public void leaveCurrentRoom() {
		jLabel.setText("방을 나가시겠습니까?");
		int isLeaveRoom = JOptionPane.showConfirmDialog(null, jLabel, "방 나가기",JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE);
		if(isLeaveRoom == 0) {
			frame.sendMessage(Protocol.CS_LEAVEROOM + Protocol.DELEMETER + frame.getKey_nickName());
		}
	}
	
	//방 나가기 확인
	public void sc_leaveRoom() {
		changeCardPanel();
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
		
		outRoomB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				leaveCurrentRoom();
			}
		});
		
		//방에 있는 사람에게 귓속말하기
		userList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toRoomUserWisper = userList.getSelectedItem();
				// 오른쪽 버튼
				if (userList.getSelectedIndex() >= 0) {
					if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
						pMenu.show(e.getComponent().getParent(), e.getX() + 5, e.getY() + 80);
						pMenu.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand().equals("귓속말하기")) {
									sendWhisper(toRoomUserWisper);
								}
							}
						});
					}
				}
			}
		});

		//대기실 사람에게 귓속말하기
		waitingList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				toUserMenuchoice = waitingList.getSelectedItem();
				// 오른쪽 버튼
				if (waitingList.getSelectedIndex() >= 0) {
					if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
						pMenu.show(e.getComponent().getParent(), e.getX() + 5, e.getY() + 80);
						pMenu.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (e.getActionCommand().equals("귓속말하기")) {
									sendWhisper(toUserMenuchoice);
								}
								if(e.getActionCommand().equals("초대하기")) {
									inviteUser(toUserMenuchoice);
								}
								
							}
						});
					}
				}
			}
		});	
	}
	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
