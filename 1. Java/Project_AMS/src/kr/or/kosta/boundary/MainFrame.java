package kr.or.kosta.boundary;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import kr.or.kosta.entity.Account;
import kr.or.kosta.entity.AccountManager;
import kr.or.kosta.entity.MinusAccount;

public class MainFrame extends Frame {

	AccountManager manager; // AccountManager와 연결하기 위해 등록

	Label kindAccountL; // 계좌종류
	Label numAccountL; // 계좌번호
	Label nameAccountL; // 예금주명
	Label passwordL; // 비밀번호
	Label depositL; // 입금금액
	Label minusMoneyL; // 대출금액
	Label listAccoutnL; // 계좌목록
	Label unitMoney; // 단위 : 원

	Button searchAccountBt, deleteAccountBt, searchNameBt, registerAccountBt, searchAllBt;

	TextField numAccounTF, nameAccountTF, passwordTF, depositTF, minusMoneyTF;

	TextArea showListTA;

	Choice kindAccountCB;

	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;

	/**
	 * 디폴트 생성자
	 */
	public MainFrame() {
		super();
	}

	/**
	 * @param string
	 *            Frame의 title을 원할 시 사용
	 */
	public MainFrame(String string) {
		super(string);
		kindAccountL = new Label("계좌종류");
		numAccountL = new Label("계좌번호");
		nameAccountL = new Label("예금주명");
		passwordL = new Label("비밀번호");
		depositL = new Label("입금금액");
		minusMoneyL = new Label("대출금액");
		listAccoutnL = new Label("계좌목록");
		unitMoney = new Label("(단위 : 원)");

		searchAccountBt = new Button("조회");
		searchAccountBt.setBackground(Color.LIGHT_GRAY);
		deleteAccountBt = new Button("삭제");
		deleteAccountBt.setBackground(Color.LIGHT_GRAY);
		searchNameBt = new Button("검색");
		searchNameBt.setBackground(Color.LIGHT_GRAY);
		registerAccountBt = new Button("신규등록");
		registerAccountBt.setBackground(Color.LIGHT_GRAY);
		registerAccountBt.setEnabled(false); // 초기에 전체 계좌종류이기 때문에 비활성화
		searchAllBt = new Button("전체조회");
		searchAllBt.setBackground(Color.LIGHT_GRAY);

		numAccounTF = new TextField("****-****-****");
		nameAccountTF = new TextField();
		passwordTF = new TextField();
		passwordTF.setEchoChar('*');
		depositTF = new TextField();
		minusMoneyTF = new TextField();

		showListTA = new TextArea();
		showListTA.setBackground(Color.LIGHT_GRAY);

		kindAccountCB = new Choice();
		kindAccountCB.add("전체");
		kindAccountCB.add("입출금계좌");
		kindAccountCB.add("마이너스계좌");

		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();

	}

	/**
	 * setContents, eventRegist, pack 설정
	 */
	public void setAll() {
		setContents();
		eventRegist();
		pack();
	}

	/**
	 * setContents
	 * layout 설정 및 모든 객체들의 등록 및 위치 조절 
	 */
	private void setContents() {
		setLayout(gridBagLayout);

		add(kindAccountL, 0, 0, 1, 1, 0, 0, 0);
		add(kindAccountCB, 1, 0, 1, 1, 0, 0, 0);

		add(numAccountL, 0, 1, 1, 1, 0, 0, 0);
		add(numAccounTF, 1, 1, 1, 1, 0, 0, 2);
		add(searchAccountBt, 2, 1, 1, 1, 0, 0, 0);
		add(deleteAccountBt, 3, 1, 1, 1, 0, 0, 0);

		add(nameAccountL, 0, 2, 1, 1, 0, 0, 0);
		add(nameAccountTF, 1, 2, 1, 1, 0, 0, 2);
		add(searchNameBt, 2, 2, 1, 1, 0, 0, 0);

		add(passwordL, 0, 3, 1, 1, 0, 0, 0);
		add(passwordTF, 1, 3, 1, 1, 0, 0, 2);
		add(depositL, 2, 3, 1, 1, 0, 0, 0);
		add(depositTF, 3, 3, 1, 1, 0, 0, 2);

		add(minusMoneyL, 0, 4, 1, 1, 0, 0, 0);
		add(minusMoneyTF, 1, 4, 1, 1, 0, 0, 2);
		add(registerAccountBt, 2, 4, 1, 1, 0, 0, 0);
		add(searchAllBt, 3, 4, 1, 1, 0, 0, 0);

		add(listAccoutnL, 0, 5, 1, 1, 0, 0, 0);
		add(unitMoney, 3, 5, 1, 1, 0, 0, 0);

		add(showListTA, 0, 6, 4, 1, 0, 0, 0);
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

	/**
	 *종료 버튼 눌렀을 경우 끌 수 있도록 method 생성 
	 */
	private void finish() {
		setVisible(false);
		dispose();
		System.exit(0);
	}
	
	
	/**
	 * @param num 1이면 위에 출력문, 2이면 아래 문장 출력
	 */
	private void print(int num) {
		if (num == 1) {
			showListTA.setText("-------------------------------------------------------------------\n");
			showListTA.append(String.format("계좌종류\t%-18s%-10s%-15s%-15s\n", "계좌번호", "예금주명", "현재잔액", "대출금액"));
			showListTA.append("===================================================================\n");
		} else if (num == 2) {
			showListTA.append("-------------------------------------------------------------------\n");
		}
	}

	
	/**
	 * 모든 textField 초기화하기
	 */
	private void setBlank() {
		numAccounTF.setText("****-****-****");
		nameAccountTF.setText("");
		passwordTF.setText("");
		depositTF.setText("");
		minusMoneyTF.setText("");
	}

	/**
	 * 입력받은 계좌번호 형식 확인하기 
	 * @param accountNum	계좌번호 입력받기
	 * @return			계좌번호가 공백이거나 초기 값이거나 형식에 맞지 않는다면 false, 조건이 성립한다면 true return
	 */
	private boolean checkAccountNum(String accountNum) {
		if (accountNum.equals("") || accountNum.equals("****-****-****")) {
			showListTA.setText("계좌번호를 입력해주세요");
			return false;
		} else if (!(accountNum.matches("\\d{4}-\\d{4}-\\d{4}"))) { // ****-****-****형식에
																	// 맞게 입력하도록
																	// 설정
			showListTA.setText("계좌번호가 형식에 맞지 않습니다.");
			return false;
		}
		return true;

	}

	/**
	 * 각종 모든 이벤트 등록하기
	 */
	public void eventRegist() {

		/*X 버튼 클릭 시 종료할 수 있도록 설정*/
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});

		/* 계좌번호 조회 버튼 클릭 시 이벤트 */
		searchAccountBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String accountNum = numAccounTF.getText(); //계좌번호 확인
				if (!checkAccountNum(accountNum)) { //계좌번호 형식 체크
				} else {
					//입력받은 계좌번호 정보 가져오기
					Account account = manager.get(accountNum); 
					if (kindAccountCB.getSelectedIndex() == 0) {
						if (account == null) {
							showListTA.setText("계좌번호가 존재하지 않습니다.");
						} else {
							print(1);
							showListTA.append(account.toString() + "\n");
							print(2);
						}
					} else if (kindAccountCB.getSelectedIndex() == 1) {
						if (account == null || account instanceof MinusAccount) {
							showListTA.setText("계좌번호가 존재하지 않습니다.");
						} else {
							print(1);
							showListTA.append(account.toString() + "\n");
							print(2);
						}
					} else if (kindAccountCB.getSelectedIndex() == 2) {
						if (account == null || !(account instanceof MinusAccount)) {
							showListTA.setText("계좌번호가 존재하지 않습니다.");
						} else {
							print(1);
							showListTA.append(account.toString() + "\n");
							print(2);
						}
					}
				}
				setBlank();
			}
		});

		/* 계좌번호 삭제 버튼 클릭 시 이벤트 */
		deleteAccountBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String accountNum = numAccounTF.getText();
				if (!checkAccountNum(accountNum)) {
				} else {
					Account account = manager.get(accountNum);
					if (kindAccountCB.getSelectedIndex() == 0) {
						if (account.equals("")) {
							showListTA.setText("계좌번호가 존재하지 않습니다.");
						} else {
							manager.remove(accountNum);
							showListTA.setText("계좌가 삭제되었습니다");

						}
					} else if (kindAccountCB.getSelectedIndex() == 1) {
						if (account.equals("") || account instanceof MinusAccount) {
							showListTA.setText("계좌번호가 존재하지 않습니다.");
						} else {
							manager.remove(accountNum);
							showListTA.setText("계좌가 삭제되었습니다");
						}
					} else if (kindAccountCB.getSelectedIndex() == 2) {
						if (account.equals("") || !(account instanceof MinusAccount)) {
							showListTA.setText("계좌번호가 존재하지 않습니다.");
						} else {
							manager.remove(accountNum);
							showListTA.setText("계좌가 삭제되었습니다");
						}
					}
				}
				setBlank();
			}
		});

		/*예금주명 검색 버튼 클릭 시 이벤트*/
		searchNameBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//예금주 명 가져오기
				String accountOwner = nameAccountTF.getText();
				if (accountOwner.equals("")) {
					showListTA.setText("예금주명을 입력해주세요");
				} else {
					//이름이 일치하는 예금주 명 읽어오기
					List searchList = manager.search(accountOwner);
					if (kindAccountCB.getSelectedIndex() == 0) {
						if (searchList == null) {
							showListTA.setText("이름이 존재하지 않습니다");
						} else {
							print(1);
							if (!searchList.isEmpty()) {
								for (Object object : searchList) {
									showListTA.append(object + "\n");
								}
							}
							print(2);
						}
					} else if (kindAccountCB.getSelectedIndex() == 1) {
						if (searchList == null) {
							showListTA.setText("이름이 존재하지 않습니다");
						} else {
							print(1);
							if (!searchList.isEmpty()) {
								for (Object object : searchList) {
									if (!(object instanceof MinusAccount))
										showListTA.append(object + "\n");
								}
							}
							print(2);
						}
					} else if (kindAccountCB.getSelectedIndex() == 2) {
						if (searchList == null) {
							showListTA.setText("이름이 존재하지 않습니다");
						} else {
							print(1);
							if (!searchList.isEmpty()) {
								for (Object object : searchList) {
									if (object instanceof MinusAccount)
										showListTA.append(object + "\n");
								}
							}
							print(2);
						}
					}
				}
				setBlank();
			}
		});

		/*신규등록 버튼 클릭 시 이벤트*/
		registerAccountBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//계좌번호 확인
				String accountNum = numAccounTF.getText();
				if (!checkAccountNum(accountNum)) {
				}
				//예금주 확인
				String accountName = nameAccountTF.getText();
				if (accountName.equals("")) {
					showListTA.append("예금주명을 입력해주세요\n");
				}
				int password = 0;
				long depositMoney = 0;
				long minusMoney = 0;
				try {
					password = Integer.parseInt(passwordTF.getText());
					// 입출금 계좌 선택 시
					if (kindAccountCB.getSelectedIndex() == 1) {
						try {
							depositMoney = Long.parseLong(depositTF.getText());
						} catch (NumberFormatException e2) {
							depositMoney = 0; // 입금금액이 없어도 계좌 생성은 가능 (0원)
						} finally {
							boolean isNew = manager.add(new Account(accountNum, accountName, password, depositMoney));
							if (isNew) {
								showListTA.setText("입출금계좌가 생성되었습니다.");
							} else {
								//기존의 계좌번호가 존재 시 error
								showListTA.setText("중복된 계좌가 존재합니다..");
							}
						}
					}
					// 마이너스 계좌 선택 시
					else if (kindAccountCB.getSelectedIndex() == 2) {
						try {
							depositMoney = Long.parseLong(depositTF.getText());
						} catch (NumberFormatException e2) {
							depositMoney = 0; // 입금금액이 없어도 계좌 생성은 가능 (0원)
						}
						// 마이너스 계좌 확인
						try {
							minusMoney = Long.parseLong(minusMoneyTF.getText());
							// 중복된 계좌번호 체크 및 생성
							boolean isNew = manager
									.add(new MinusAccount(accountNum, accountName, password, depositMoney, minusMoney));
							if (isNew) {
								showListTA.setText("마이너스계좌가 생성되었습니다.");
							} else {
								showListTA.setText("중복된 계좌번호가 존재합니다..");
							}
						} catch (NumberFormatException e2) {
							// 마이너스 계좌를 선택 안한 경우 error
							showListTA.setText("대출금액을 입력해주세요");
						}
					}
				} catch (NumberFormatException e2) {
					// 비밀번호를 선택 안한 경우 error
					showListTA.append("비밀번호를 입력해주세요");
				}
				setBlank();
			}
		});

		/* 전체조회 버튼 */
		searchAllBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				print(1);
				// Override된 객체를 사용해서 숫자(0(전체), 1(입출금), 2(마이나스))를 통해 원하는 list를
				// return받아서 출력한다.
				List searchList = manager.list(kindAccountCB.getSelectedIndex());
				if (!searchList.isEmpty()) {
					for (Object object : searchList) {
						showListTA.append(object + "\n");
					}
				}
				print(2);
				setBlank();
			}
		});

		/* 계좌 종류 버튼 */
		kindAccountCB.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// 전체 계좌 종류 선택 시
				if (kindAccountCB.getSelectedIndex() == 0) {
					depositTF.setEditable(true);
					minusMoneyTF.setEditable(true);
					registerAccountBt.setEnabled(false);
				}
				// 입출금계좌 선택 시
				else if (kindAccountCB.getSelectedIndex() == 1) {
					depositTF.setEditable(true);
					minusMoneyTF.setEditable(false);
					registerAccountBt.setEnabled(true);
				}
				// 마이너스 계좌 선택 시
				else if (kindAccountCB.getSelectedIndex() == 2) {
					depositTF.setEditable(true);
					minusMoneyTF.setEditable(true);
					registerAccountBt.setEnabled(true);
				}
			}
		});

		/* 계좌번호 입력 창에 마우스가 클릭 될 때 내용이 초기화 되도록 설정 */
		numAccounTF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				numAccounTF.setText("");
			}
		});

		/* 비밀번호 *로 표시, 키보드로 숫자만 입력받에 하기 */
		passwordTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c)) && (c != '\b')) {
					e.consume();
				}
			}
		});
		/* 키보드로 숫자만 입력받에 하기 */
		depositTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!(Character.isDigit(c)) && (c != '\b')) {
					e.consume();
				}
			}
		});
		/* 키보드로 숫자만 입력받에 하기 */
		minusMoneyTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar();
				if (!(Character.isDigit(c)) && (c != '\b')) {
					e.consume();
				}
			}
		});

	}

	/**
	 * AMS에서 AccountManager를 받아와서 사용한다. (by Class Diagram)
	 * 
	 * @param accountManager
	 *            만들어진 AccountManager를 받아서 직접 초기화하는 데 사용하여 직접 setting한다.
	 */
	public void setAccountManager(AccountManager accountManager) {
		manager = accountManager;

		// 테스트를 위해 미리 넣어놓는 예제 데이터
		manager.add(new Account("1111-2222-3333", "우호진", 1111, 100000));
		manager.add(new Account("3333-2222-6666", "박지성", 1112, 400000));
		manager.add(new Account("4444-7777-1111", "김연아", 1113, 300000));
		manager.add(new Account("6666-4444-2222", "손흥민", 1114, 200000));
		manager.add(new Account("4466-2244-1233", "박지성", 1115, 100000));
		manager.add(new MinusAccount("1231-1234-2222", "백종현", 4321, 0, 1000000));
		manager.add(new MinusAccount("1231-1234-2241", "김기정", 1234, 0, 100000));
	}
}