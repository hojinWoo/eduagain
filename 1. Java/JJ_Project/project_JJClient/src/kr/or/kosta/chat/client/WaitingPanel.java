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
import java.awt.Image;
import java.awt.Insets;
import java.awt.List;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;

import kr.or.kosta.chat.common.Protocol;

public class WaitingPanel extends Panel implements ActionListener {

	JLabel waitingL, userL;
	JLabel jLabel; //임시
	
	JButton addRoomB, enterB, searchRoomUserB,searchWaitUserB, logoutB, exitB, enterRoomB, sendMsgB;
	
	JTextField waitingTF, userTF, sendMsgTF;
	
	Choice sendAllC;
	List roomList; 
	List waitingList, userList;
	JTextArea chattingTA;
	
	Image settingIcon;
	
	Choice settingC;
	
	AddRoomFrame addRoomFrame;
	
	PopupMenu pMenu; 
	MenuItem whisperUserItem;
	
	Font font;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	JJ_ChatUI frame;
	
	String sc_nickName, sc_roomUserInfo, toUserWisper, roomName;
	
	java.util.List<String> waitingUserList, roomUserList;
	
	JTable jTable;
	JScrollPane scrollPane;
	String[] header;
	java.util.List<String[]> roomInfoL;
	Object[][] roomListInfoO = null;
	DefaultTableModel tableModel;
	DefaultTableCellRenderer tScheduleCellRenderer;
	TableColumnModel tcmSchedule;
	TableCellEditor tableEditor;
	
	public WaitingPanel() {}
	
	public WaitingPanel(JJ_ChatUI frame) {
		
		font = new Font(Font.DIALOG, Font.PLAIN, 17);
		
		waitingL = new JLabel("대기실 유저리스트");
		waitingL.setFont(font);
		userL = new JLabel("해당방 유저리스트");
		userL.setFont(font);
		
		addRoomB = new JButton("방 만들기");
		addRoomB.setFont(font);
		enterB = new JButton("입장");
		enterB.setFont(font);
		searchRoomUserB = new JButton("대화방 닉 검색");
		searchRoomUserB.setFont(font);
		searchWaitUserB = new JButton("대기실 닉 검색");
		searchWaitUserB.setFont(font);
		enterRoomB = new JButton("입장");
		enterRoomB.setFont(font);
		logoutB = new JButton("로그아웃");
		logoutB.setFont(font);
		exitB = new JButton("종료");
		exitB.setFont(font);
		sendMsgB = new JButton("전송");
		sendMsgB.setFont(font);
	
		waitingTF = new JTextField(20);
		waitingTF.setFont(font);
		userTF = new JTextField(20);
		userTF.setFont(font);
		sendMsgTF = new JTextField(50);
		sendMsgTF.setFont(font);
		chattingTA = new JTextArea(10,70); // 크기 조절 필요
		chattingTA.setFont(font);
		chattingTA.setBackground(new Color(137, 168, 183));
		
		waitingList = new List(10, false);
		userList = new List(10, false);
		
		waitingList.setFont(font);
		userList.setFont(font);
		
		sendAllC = new Choice();
		sendAllC.add("전체");
		sendAllC.setFont(font);
		roomInfoL = new ArrayList<>();
		header = new String[]{"방 번호", "방 제목", "방장 이름", "(현재인원/최대인원)"};
		tableModel = new DefaultTableModel(roomListInfoO, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				//편집 불가
				return false;
			};
		};
		
		jLabel = new JLabel("");
		jLabel.setFont(font);
		
		jTable = new JTable(tableModel);
		jTable.setFont(font);
		
		jTable.setRowHeight(20);
		scrollPane = new JScrollPane(jTable);
		scrollPane.setBackground(new Color(139, 214, 190));
		jTable.setAutoCreateRowSorter(true);
		
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		
		addRoomFrame = new AddRoomFrame("방 만들기", this);
		
		waitingUserList= new ArrayList<>();
		
		pMenu = new PopupMenu();
		whisperUserItem = new MenuItem("귓속말하기");
		
		pMenu.add(whisperUserItem);
		pMenu.setFont(font);
		
		this.frame = frame;
		
		setContents();
	}
	
	public void setContents() {
		setLayout(gridBagLayout);
		
		Panel menuP = new Panel(new FlowLayout(FlowLayout.RIGHT));
		menuP.add(enterRoomB);
		menuP.add(addRoomB);
		menuP.add(logoutB);
		menuP.add(exitB);
		
		Panel roomListandChatting = new Panel(new BorderLayout());
		Panel roomP = new Panel(new BorderLayout(10, 10));
		roomP.add(scrollPane, BorderLayout.CENTER);
		
		Panel chatP = new Panel(new FlowLayout(FlowLayout.LEFT));
		chatP.add(chattingTA);

		roomListandChatting.add(roomP, BorderLayout.CENTER);
		roomListandChatting.add(chatP, BorderLayout.SOUTH);
		
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

		
		Panel writeP = new Panel(new FlowLayout(FlowLayout.CENTER));
		writeP.add(sendAllC);
		writeP.add(sendMsgTF);
		writeP.add(sendMsgB);
		
		Panel tempP = new Panel(new FlowLayout());
		tempP.add(new JLabel("  "));
		
		Panel WestP = new Panel(new BorderLayout(10, 10));
		Panel EastP = new Panel(new BorderLayout(10, 10));
		
		WestP.add(menuP, BorderLayout.NORTH);
		WestP.add(roomListandChatting, BorderLayout.CENTER);
		EastP.add(tempP, BorderLayout.NORTH);
		EastP.add(userP, BorderLayout.CENTER);
		EastP.add(waitingP, BorderLayout.SOUTH);
		
		add(WestP, 	0, 0, 1, 1, 3, 0, 1);
		add(EastP, 	1, 0, 1, 1, 1, 0, 1);
		add(writeP,	0, 1, 2, 1, 1, 1, 1);
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
	
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	/** 대기실에 있는 사람들 띄우기*/
	public void sc_getWaiting(String sc_nickNammes) {
		this.sc_nickName = sc_nickNammes;
		String[] nickname = sc_nickNammes.substring(1,sc_nickNammes.length()-1).replaceAll(" ", "").split(",");
		Arrays.sort(nickname, String.CASE_INSENSITIVE_ORDER); //order nickname
		waitingList.removeAll();
		waitingUserList = new ArrayList<>();
		waitingList.add(frame.getKey_nickName() + "(본인)");
		for (String temp : nickname) {
			waitingUserList.add(temp);
			if(!temp.equals(frame.getKey_nickName())) {
				waitingList.add(temp);
			}
		}
	}
	
	/**존재하는 방 목록 띄우기 */
	public void sc_getRoomInfo(String sc_rooms) {
		String[] room = sc_rooms.substring(1, sc_rooms.length()-1).replaceAll(" ", "").split(",");
		Arrays.sort(room, String.CASE_INSENSITIVE_ORDER); //order room
		String[] roomInfo = null;
		//init table
		for (int i = tableModel.getRowCount()-1; i >=0 ; i--) {
			tableModel.removeRow(i);
		}
		//방 목록 보여주기
		for (String string : room) {
			roomInfo = string.split(Protocol.DELEMETER2);
			tableModel.addRow(new String[]{roomInfo[0], roomInfo[1], roomInfo[2], "("+roomInfo[4]+"/"+roomInfo[3]+")"});
			//이벤트 처리
		}
		//가운데 정렬
		tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		tcmSchedule = jTable.getColumnModel();
		
		int[] widths = {20, 100, 50, 50};
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
//			jTable.getColumnModel().getColumn(i).setPreferredWidth(widths[i]); //열 크기 조절 실패...
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		
//		jTable.getColumn("방 번호").setPreferredWidth(5);
//		jTable.getColumn("방 제목").setPreferredWidth(40);
//		jTable.getColumn("방장 이름").setPreferredWidth(30);
//		jTable.getColumn("(현재인원/최대인원)").setPreferredWidth(10);
//		jTable.getColumnModel().getColumn(0).setPreferredWidth(5);
//		jTable.getColumnModel().getColumn(1).setPreferredWidth(40);
//		jTable.getColumnModel().getColumn(2).setPreferredWidth(30);
//		jTable.getColumnModel().getColumn(3).setPreferredWidth(10);
//		tcmSchedule.getColumn(0).setPreferredWidth(5);
//		tcmSchedule.getColumn(1).setPreferredWidth(40);
//		tcmSchedule.getColumn(2).setPreferredWidth(30);
//		tcmSchedule.getColumn(3).setPreferredWidth(10);

	}
	
	/** 해당 방에 있는 유저 띄우기 */
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
	
	//방 입장 거부
	public void sc_failEnterRoom() {
		jLabel.setText("방 인원이 초과되었습니다");
		JOptionPane.showMessageDialog(null, jLabel, "불가", JOptionPane.ERROR_MESSAGE);
	}
	
	//화면 전환
	public void changeCardPanel(int num) {
		if(num==0) {
			//logout 누른 경우
			frame.setSize(400, 500);
			frame.setCenter();
			frame.getLoginPanel().init();
			frame.changeCard("login");
			return;
		}else if(num==2) {
			//채팅방으로 들어간 경우
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
			if(!sc_roomUserInfo.equals("")) {
				sc_getRoomUserInfo(sc_roomUserInfo);
			}
			return;
		}
		userList.removeAll();
		for (String roomUser : roomUserList) {
			if(roomUser.contains(tempRoomUser)) {
				userList.add(roomUser);
				System.out.println(roomUser);
			}
		}
	}
	
	public void searchRoomUserList() {
		//대기실 닉네임 검색
		String tempNickName = waitingTF.getText();
		if(tempNickName.trim().equals("")) {
			System.out.println("빈칸!!!!!!!!!!!");
			sc_getWaiting(sc_nickName);
			return;
		}
		waitingList.removeAll();
		for (String waitUser : waitingUserList) {
			if(waitUser.contains(tempNickName)) {
				waitingList.add(waitUser);
				System.out.println(waitUser);
			}
		}
	}
	
	//채팅하기 출력
	public void uploadMessage(String sender, String msg, String time) {
		chattingTA.append("["+time+"] "+sender + " : " + msg + "\n");
	}
	
	//귓속말받기
	public void uploadWisperMsg(String sender, String msg, String time) {
		chattingTA.append("["+time+"] <"+sender + "님이 보낸 귓속말> : " + msg + "\n");
	}
	
	//방입장하기
	public void sc_enterRoom() {
		changeCardPanel(2);
	}
	
	//client의 닉네임 불러오기
	public String getNickName() {
		return frame.getKey_nickName();
	}
	
	// 귓속말 보내기
	public void sendWhisper(String ToUser) {
		if(frame.getKey_nickName().equals(ToUser)) {
			jLabel.setText("자기 자신에게는 보낼 수 없습니다");
			JOptionPane.showMessageDialog(null, jLabel, "귓속말", JOptionPane.ERROR_MESSAGE);
			return;
		}
		jLabel.setText("메시지를 입력해주세요");
		
		String msg = JOptionPane.showInputDialog(null, jLabel);
		if (msg != null) {
			frame.sendMessage(Protocol.CS_SECRET_CHAT + Protocol.DELEMETER + frame.getKey_nickName()
					+ Protocol.DELEMETER + ToUser + Protocol.DELEMETER + msg);
		}
	}
	
	public void eventRegist() {
		
		//전체채팅
		sendMsgTF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 채팅 보내기
				frame.sendMessage(Protocol.CS_ALL_CHAT + Protocol.DELEMETER + frame.getKey_nickName()
						+ Protocol.DELEMETER + sendMsgTF.getText());
				sendMsgTF.setText("");
			}
		});
		
		//전체채팅
		sendMsgB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 채팅 보내기
				frame.sendMessage(Protocol.CS_ALL_CHAT + Protocol.DELEMETER + frame.getKey_nickName()
						+ Protocol.DELEMETER + sendMsgTF.getText());
				sendMsgTF.setText("");
			}
		});
		
		
		//방 입장
		enterRoomB.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				roomName = (String) jTable.getValueAt(jTable.getSelectedRow(), 1);
				frame.sendMessage(Protocol.CS_REQUEST_ENTER_CHATROOM + Protocol.DELEMETER + frame.getKey_nickName() + Protocol.DELEMETER + roomName);
			}
		});
		
		//방에 참여한 인원 검색
		searchRoomUserB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchUserList();
			}
		});
		
		//방에 참여한 인원 검색
		userTF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchUserList();
			}
		});
		
		//대기실 유저 검색
		searchWaitUserB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchRoomUserList();
			}
		});
		
		//대기실 유저 검색
		waitingTF.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchRoomUserList();
			}
		});
		
		//방에 참여한 인원 요청
		jTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String selectRoomName = (String) jTable.getValueAt(jTable.getSelectedRow(), 1);
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
		
		//방 만들기
		addRoomB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addRoomFrame.init();
				addRoomFrame.setSize(400, 500);
				addRoomFrame.eventRegist();
				addRoomFrame.setCenter();
				addRoomFrame.setVisible(true);
				
			}
		});
		
		//로그아웃
		logoutB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.sendMessage(Protocol.CS_LOGOUT + Protocol.DELEMETER + frame.getKey_nickName()); //로그아웃
				changeCardPanel(0);
			}
		});
		
		//종료
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
