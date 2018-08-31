import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Event 처리
 * @author hojin
 *
 */
public class ExitHandler extends WindowAdapter {

	ChatFrame frame;
	
	public ExitHandler(ChatFrame frame) {
		this.frame = frame;
	}
	
	//Adpater 클래스를 기존 인터페이스 중 한 개씩 만들어 놓았기 떄문에
	//Override는 한 개만 사용 가능
	
	@Override
	public void windowClosing(WindowEvent e) {
		frame.finish();
	}
	
}
