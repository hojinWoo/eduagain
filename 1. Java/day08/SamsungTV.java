
public class SamsungTV implements RemoteControl, Browsable {

	private int currentChannel;
	private int currentVolumn;
	
	
	public SamsungTV() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SamsungTV(int currentChannel, int currentVolumn) {
		super();
		this.currentChannel = currentChannel;
		this.currentVolumn = currentVolumn;
	}

	@Override
	public void surfing(String url) {
		// TODO Auto-generated method stub
		System.out.println(url + "을 화면에 렌더링해서 출력");
	}

	@Override
	public void tunrOn() {
		// TODO Auto-generated method stub
		System.out.println("전원을 켭니다");
	}

	@Override
	public void tunrOff() {
		// TODO Auto-generated method stub
		System.out.println("전원을 끕니다");
	}

	@Override
	public void volumnUp() {
		// TODO Auto-generated method stub
		if(!(currentVolumn >= MAX_VOLUMN)) {
			currentVolumn++;
		}
	}

	@Override
	public void volumnDown() {
		// TODO Auto-generated method stub
		currentVolumn--;
	}

	@Override
	public void setVolumn(int volumn) {
		// TODO Auto-generated method stub
		currentVolumn = volumn;
	}

	@Override
	public void setChannel(int channel) {
		// TODO Auto-generated method stub
		currentChannel = channel;
	}

	public int getCurrentChannel() {
		return currentChannel;
	}

	public void setCurrentChannel(int currentChannel) {
		this.currentChannel = currentChannel;
	}

	public int getCurrentVolumn() {
		return currentVolumn;
	}

	public void setCurrentVolumn(int currentVolumn) {
		this.currentVolumn = currentVolumn;
	}

	public static void main(String[] args) {
		RemoteControl tv = new SamsungTV();
		tv.tunrOn();
		tv.setChannel(15);
		
		System.out.println(((SamsungTV)tv).getCurrentChannel());
		
		tv.volumnUp();
		tv.volumnUp();
		tv.volumnUp();
		tv.volumnUp();
		tv.volumnUp();
		tv.volumnUp();
		
		System.out.println(RemoteControl.MAX_CHANNEL);
		
		tv.tunrOff();
	}
}
