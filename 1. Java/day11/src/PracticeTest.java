import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.SpringLayout.Constraints;

public class PracticeTest extends Panel{
	
	Label receiveL, fileL, titleL;
	TextField receivetTf, fileTf, titleTf;
	Button findBt, sendBt, cancelBt;
	TextArea messageTA;
	
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	
	public PracticeTest() {
		
		receiveL = new Label("받는사람");
		fileL = new Label("첨부파일");
		titleL = new Label("    제목");
		
		receivetTf = new TextField();
		fileTf = new TextField();
		titleTf = new TextField();
		
		findBt = new Button("찾기");
		sendBt = new Button("보내기");
		cancelBt = new Button("취소");
		
		messageTA = new TextArea();
		
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
	}
	
	public void setContents() {
		setLayout(gridBagLayout);
		
		add(receiveL,		 0,0,1,1,0,0,0);
		add(receivetTf,		 1,0,1,1,1,0,2);
//		add(new Label(" "),  2,0,1,1,1,0,0);
		
		add(fileL,  0,1,1,1,0,0,0);
		add(fileTf, 1,1,1,1,1,0,2);
		add(findBt, 2,1,1,1,2,0,0);
		
		add(titleL,  0,2,1,1,0,0,0);
		add(titleTf, 1,2,2,1,1,0,2);
		
		add(messageTA, 0,3,3,1,1,0,1);
		
		Panel panel = new Panel();
		panel.add(sendBt, BorderLayout.WEST);
		panel.add(cancelBt, BorderLayout.EAST);
		
		add(panel, 0,4,0,1,1,0,0);
		
	}
	
	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weigthx, double weighty, int fill) {
		gridBagConstraints.gridx = gridx; 
		gridBagConstraints.gridy = gridy; 
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weigthx;
		gridBagConstraints.weighty = weighty;
		
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
			break;
		}
		
		
		gridBagLayout.setConstraints(component, gridBagConstraints);
		
		add(component); 
	}
	
	public static void main(String[] args) {
		Frame frame = new Frame("Grid example");
		PracticeTest panel = new PracticeTest();
		panel.setContents();
		frame.add(panel);
		frame.setSize(500,600);
		frame.setVisible(true);
	}
	
	
	
}
