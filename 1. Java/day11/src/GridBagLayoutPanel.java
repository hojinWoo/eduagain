import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;

/**
 * Frame은 재사용성이 떨어지기 때문에(내부적으로 사용X, 다른 container에 넣을 수 없기 때문)
 * 상속받지 않고 주로 재사용성이 높은 Panel을 상속받는다. 그리고 Frame에 넣으면 되기 때문
 * @author hojin
 *
 */

public class GridBagLayoutPanel extends Panel {
	//생성자도 한 개만 필요(Default 생성자)
	Button button1;
	Button button2;
	Button button3;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public GridBagLayoutPanel() {
		button1 = new Button("Button1");
		button2 = new Button("Button2");
		button3 = new Button("Button3");
		gridBagLayout = new GridBagLayout(); //격자가 붙을 때마다 자동적으로 생성(동적)
		gridBagConstraints = new GridBagConstraints(); //무조건 필요!!
	}
	
	public void setContents() {
		setLayout(gridBagLayout);
		
//		gridBagConstraints.gridx = 0; //0부터 시작
//		gridBagConstraints.gridy = 0; //0부터 시작
//		gridBagConstraints.gridwidth = 1;
//		gridBagConstraints.gridheight = 1;
//		
//		gridBagConstraints.weightx = 1;
//		gridBagConstraints.weighty = 1; //weight를 주게 되면 여백을 더 차지하게 된다. 
//		
//		gridBagLayout.setConstraints(button1, gridBagConstraints);
//		
//		add(button1); //gridBagConstrains의 정보가 들어간다.
		
//		gridBagConstraints.gridx = 0; //0부터 시작
//		gridBagConstraints.gridy = 1; //0부터 시작
//		gridBagConstraints.gridwidth = 1;
//		gridBagConstraints.gridheight = 1;
//		gridBagConstraints.weightx = 0;
//		gridBagConstraints.weighty = 0;
//		gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
////		gridBagConstraints.insets  = new Insets(top, left, bottom, right);//margin
//		gridBagConstraints.insets  = new Insets(5, 5, 5, 5);//margin
//		
//		
//		gridBagLayout.setConstraints(button2, gridBagConstraints);
//		
//		add(button2); //gridBagConstrains의 정보가 들어간다.
		//method 만들어서 처리
		add(button1, 0, 0, 1, 1, 1, 1);
		add(button2, 0, 1, 1, 1, 0, 0);
		add(button3, 1, 0, 1, 1, 0, 1);
	}
	
	
	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weigthx, double weighty) {
		gridBagConstraints.gridx = gridx; 
		gridBagConstraints.gridy = gridy; 
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weigthx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.fill = gridBagConstraints.HORIZONTAL;
		
		gridBagLayout.setConstraints(component, gridBagConstraints);
		
		add(component); 
	}
	public static void main(String[] args) {
		Frame frame = new Frame("Grid example");
		GridBagLayoutPanel panel = new GridBagLayoutPanel();
		panel.setContents();
		frame.add(panel);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
}
