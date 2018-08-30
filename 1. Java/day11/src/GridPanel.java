import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
/**
 * Frame은 재사용성이 떨어지기 때문에(내부적으로 사용X, 다른 container에 넣을 수 없기 때문)
 * 상속받지 않고 주로 재사용성이 높은 Panel을 상속받는다. 그리고 Frame에 넣으면 되기 때문
 * @author hojin
 *
 */
public class GridPanel extends Panel {
	//생성자도 한 개만 필요(Default 생성자)
	Button[] buttons;
	
	public GridPanel() {
		//간단한 거기 때문에 여기서 그냥 처리
		setLayout(new GridLayout(3,3)); //순차적으로 들어가기 때문에 단점이 있다. 
				
		buttons = new Button[9];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new Button("button " + i);
			add(buttons[i]);
		}
	}
	public static void main(String[] args) {
		Frame frame = new Frame("Grid example");
		GridPanel panel = new GridPanel();
		frame.add(panel);
		
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
}
