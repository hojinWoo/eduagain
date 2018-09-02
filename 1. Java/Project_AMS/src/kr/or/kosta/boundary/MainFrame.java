package kr.or.kosta.boundary;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
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
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Enumeration;
import java.util.List;

import kr.or.kosta.entity.Account;
import kr.or.kosta.entity.AccountManager;
import kr.or.kosta.entity.MinusAccount;

public class MainFrame extends Frame {
	
	AccountManager manager;
	
	Label kindAccountL; //계좌종류
	Label numAccountL; 	//계좌번호
	Label nameAccountL;	//예금주명
	Label passwordL;	//비밀번호
	Label depositL;		//입금금액
	Label minusMoneyL;	//대출금액
	Label listAccoutnL;	//계좌목록
	Label unitMoney;	//단위 : 원
	
	Button searchAccountBt, deleteAccountBt, searchNameBt, registerAccountBt, searchAllBt;
	
	TextField numAccounTF, nameAccountTF, passwordTF, depositTF, minusMoneyTF;
	
	TextArea showListTA;
	
	Choice kindAccountCB;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public MainFrame() {
		
	}
	
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
		deleteAccountBt = new Button("삭제");
		searchNameBt = new Button("검색");
		registerAccountBt = new Button("신규등록");
		registerAccountBt.setEnabled(false);
		searchAllBt = new Button("전체조회");
		
		numAccounTF = new TextField("****-****-****");
		nameAccountTF = new TextField();
		passwordTF = new TextField();
		passwordTF.setEchoChar('*');
		depositTF = new TextField();
		minusMoneyTF = new TextField();
		
		showListTA = new TextArea();
		
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
	public void setAll(){
		setContents();
		eventRegist();
		pack();
	}
	
	private void setContents() {
		setLayout(gridBagLayout);
		
		add(kindAccountL,		0,0,1,1,0,0,0);
		add(kindAccountCB,		1,0,1,1,0,0,0);
		
		add(numAccountL, 		0,1,1,1,0,0,0);
		add(numAccounTF, 		1,1,1,1,0,0,2);
		add(searchAccountBt, 	2,1,1,1,0,0,2);
		add(deleteAccountBt, 	3,1,1,1,0,0,0);
		
		add(nameAccountL,		0,2,1,1,0,0,0);
		add(nameAccountTF,		1,2,1,1,0,0,2);
		add(searchNameBt,		2,2,1,1,0,0,2);
		
		add(passwordL,			0,3,1,1,0,0,0);
		add(passwordTF,			1,3,1,1,0,0,2);
		add(depositL,			2,3,1,1,0,0,0);
		add(depositTF,			3,3,1,1,0,0,2);
		
		add(minusMoneyL,		0,4,1,1,0,0,0);
		add(minusMoneyTF,		1,4,1,1,0,0,2);
		add(registerAccountBt,	2,4,1,1,0,0,0);
		add(searchAllBt	,		3,4,1,1,0,0,0);

		add(listAccoutnL,		0,5,1,1,0,0,0);
		add(unitMoney	,		3,5,1,1,0,0,0);
		
		add(showListTA, 		0,6,4,1,0,0,0);
	}
	
	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weigthx, double weighty, int fill) {
		gridBagConstraints.gridx = gridx; 
		gridBagConstraints.gridy = gridy; 
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weigthx;
		gridBagConstraints.weighty = weighty;
		
		gridBagConstraints.insets = new Insets(5, 5, 5, 5); //margin 주기
		
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
	public void finish() {
		setVisible(false);
		dispose();
		System.exit(0);
	}
	
	private void print(int num) {
		if(num == 1){
			showListTA.setText("-------------------------------------------------------------------\n");
			showListTA.append(String.format("계좌종류\t%-18s%-10s%-15s%-15s\n", "계좌번호", "예금주명", "현재잔액", "대출금액"));
			showListTA.append("===================================================================\n");
		}else if(num == 2){
			showListTA.append("-------------------------------------------------------------------\n");
		}
	}

	private void setBlank() {
		numAccounTF.setText("****-****-****");
		nameAccountTF.setText("");
		passwordTF.setText("");
		depositTF.setText("");
		minusMoneyTF.setText("");
	}
	
	private boolean checkAccountNum(String accountNum){
		if (accountNum.equals("") || accountNum.equals("****-****-****")) {
			showListTA.setText("계좌번호를 입력해주세요");
			return false;
		}else if(!(accountNum.matches("\\d{4}-\\d{4}-\\d{4}"))){ //****-****-****형식에 맞게 입력하도록 설정
			showListTA.setText("계좌번호가 형식에 맞지 않습니다.");
			return false;
		}
		return true;
		
	}
	
	public void eventRegist() {
		
		//종료 처리 이벤트
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		
		//계좌번호 조회 
		searchAccountBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String accountNum = numAccounTF.getText();
				if (!checkAccountNum(accountNum)){} 
				else {
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
		
		//계좌번호 삭제
		deleteAccountBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String accountNum = numAccounTF.getText();
				if (!checkAccountNum(accountNum)){}
				else {
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
		
		//이름 검색
		searchNameBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String accountOwner = nameAccountTF.getText();
				if (accountOwner.equals("")) {
					showListTA.setText("예금주명을 입력해주세요");
				}else {
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
		
		//신규 등록
		registerAccountBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String accountNum = numAccounTF.getText();
				if (!checkAccountNum(accountNum)){}
				String accountName = nameAccountTF.getText();
				if(accountName.equals("")) {
					showListTA.append("예금주명을 입력해주세요\n");
				}
				
				int password = 0;
				long depositMoney = 0;
				long minusMoney = 0;
				try {
					password = Integer.parseInt(passwordTF.getText());
					if (kindAccountCB.getSelectedIndex() == 1) {
						try {
							depositMoney = Long.parseLong(depositTF.getText());
						} catch (NumberFormatException e2) {
							depositMoney = 0; //입금금액이 없어도 계좌 생성은 가능 (0원)
						}finally {
							boolean isNew = manager.add(new Account(accountNum, accountName, password, depositMoney));
							if(isNew){
								showListTA.setText("입출금계좌가 생성되었습니다.");
							}else{
								showListTA.setText("중복된 계좌가 존재합니다..");
							}
						}

					} else if (kindAccountCB.getSelectedIndex() == 2) {
						String deposit = depositTF.getText();
						if (deposit.equals("")) {
							deposit = "0";
						}
						try {
							depositMoney = Long.parseLong(deposit);
						} catch (NumberFormatException e2) {
						}
						try {
							minusMoney = Long.parseLong(minusMoneyTF.getText());
							boolean isNew = manager.add(new MinusAccount(accountNum, accountName, password, depositMoney, minusMoney));
							if(isNew){
								showListTA.setText("마이너스계좌가 생성되었습니다.");
							}else{
								showListTA.setText("중복된 계좌번호가 존재합니다..");
							}
						} catch (NumberFormatException e2) {
							showListTA.setText("대출금액을 입력해주세요");
						}
					}
				} catch (NumberFormatException e2) {
					showListTA.append("비밀번호를 입력해주세요");
				}

				setBlank();
			}
		});
		
		//전체조회
		searchAllBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				print(1);
				
				//Override된 객체를 사용해서 숫자(0(전체), 1(입출금), 2(마이나스))를 통해 원하는 list를 return받아서 출력한다.
				List searchList = manager.list(kindAccountCB.getSelectedIndex());
				if(!searchList.isEmpty()) {
					for (Object object : searchList) {
						showListTA.append(object+"\n");
					}
				}
				print(2);
				setBlank();
			}
		});
		
		
		//계좌 종류 선택
		kindAccountCB.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(kindAccountCB.getSelectedIndex() == 0){
					depositTF.setEditable(true);
					minusMoneyTF.setEditable(true);
					registerAccountBt.setEnabled(false);
				}else if(kindAccountCB.getSelectedIndex() == 1) {
					depositTF.setEditable(true);
					minusMoneyTF.setEditable(false);
					registerAccountBt.setEnabled(true);
				}else if(kindAccountCB.getSelectedIndex() == 2){
					depositTF.setEditable(true);
					minusMoneyTF.setEditable(true);
					registerAccountBt.setEnabled(true);
				}
			}
		});
		
		/*마우스가 클릭 될 때 초기화 되도록 설정*/
		numAccounTF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				numAccounTF.setText("");
			}
		});
		
		/*비밀번호 *로 표시, 키보드로 숫자만 입력받에 하기*/
		passwordTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar(); 
				if(!(Character.isDigit(c)) && (c != '\b') ) {
					e.consume();
				}
			}
		});
		/*키보드로 숫자만 입력받에 하기*/
		depositTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar(); 
				if(!(Character.isDigit(c)) && (c != '\b') ) {
					e.consume();
				}
			}
		});
		/*키보드로 숫자만 입력받에 하기*/
		minusMoneyTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char c = e.getKeyChar(); 
				if(!(Character.isDigit(c)) && (c != '\b') ) {
					e.consume();
				}
			}
		});
		
	}


	public void setAccountManager(AccountManager accountManager) {
		manager = accountManager;
		
		//테스트를 위해 미리 넣어놓는 예제 데이터
		manager.add(new Account("1111-2222-3333", "우호진", 1111, 100000));
		manager.add(new Account("3333-2222-6666", "박지성", 1112, 400000));
		manager.add(new Account("4444-7777-1111", "김연아", 1113, 300000));
		manager.add(new Account("6666-4444-2222", "손흥민", 1114, 200000));
		manager.add(new Account("4466-2244-1233", "박지성", 1115, 100000));
		manager.add(new MinusAccount("1231-1234-2222", "백종현", 4321, 0, 1000000));
		manager.add(new MinusAccount("1231-1234-2241", "김기정", 1234, 0, 100000));
	}
}