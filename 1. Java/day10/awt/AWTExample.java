package awt;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;


public class AWTExample {
	public static void main(String[] args) {
		//AWT는 UTF는-8을 지원나지 않는다.
		Frame frame = new Frame("처음으로 만든 Frame");
		frame.setSize(1200, 600);
		frame.setVisible(true);
		
		Button button = new Button("AWT button");
		Button button1 = new Button("AWT button1");
		Button button2 = new Button("AWT button2");
		
		frame.setLayout(new FlowLayout());
		frame.add(button);
		frame.add(button1);
		frame.add(button2);
		
//		frame.setResizable(false); //화면 크기 변경 불가능
		
		Label label = new Label("Awt Label"); //테두리 없이 글자만
		frame.add(label);
		
		TextField textField = new TextField("ID", 15); //숫자는 크기
		frame.add(textField);
		
		TextArea textArea = new TextArea(10, 15); //rows, columns
		frame.add(textArea);

		Checkbox checkbox = new Checkbox("Man", true);
		frame.add(checkbox);
		
		CheckboxGroup cg = new CheckboxGroup();
		Checkbox checkbox1 = new Checkbox("Man", true, cg);
		Checkbox checkbox2 = new Checkbox("Woman", false, cg);
		frame.add(checkbox1);
		frame.add(checkbox2);
		
		Choice selectBox = new Choice();
		selectBox.add("aa");
		selectBox.add("bbbb");
		selectBox.add("123");
		selectBox.add("aasdasd");
		frame.add(selectBox);
	}
}
