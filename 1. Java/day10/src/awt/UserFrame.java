package awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.LayoutManager;

import javax.swing.border.Border;

public class UserFrame extends Frame{
//	String title; //실제로는 존재하기 때문에 필요
	
	Button ebutton, wbutton, sbutton, nbutton, cbutton; //동서남북 + 중앙
	
	public UserFrame() {
		this("noTitle");
	}

	public UserFrame(String title){
		super(title);
		this.ebutton = new Button("East");
		this.wbutton = new Button("West");
		this.sbutton = new Button("South");
		this.nbutton = new Button("North");
		this.cbutton = new Button("Center");
	}
	//화면 배치
	public void setContents() {
		//layout을 교체할 수 있다.
//		this.setLayout(new FlowLayout());
		
		//위에서 flowLayout으로 설정한 경우 borderlayout은 적용이 안된다.
		add(ebutton, BorderLayout.EAST);
		add(wbutton, BorderLayout.WEST);
		add(sbutton, BorderLayout.SOUTH);
		add(nbutton, BorderLayout.NORTH);
		add(cbutton, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		UserFrame frame = new UserFrame("This is Title..");
		frame.setSize(1000,800);
		frame.setContents();
		frame.setVisible(true);
	}
}
