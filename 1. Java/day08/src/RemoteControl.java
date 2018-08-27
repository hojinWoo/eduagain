
public interface RemoteControl {
	public static final int MAX_VOLUMN = 50;
	public static final int MIN_VOLUMN = 0;
	public static final int MAX_CHANNEL = 100;
	public static final int MIN_CHANNEL = 1;
	
	public abstract void tunrOn();
	public abstract void tunrOff();
	public abstract void volumnUp();
	public abstract void volumnDown();
	public abstract void setVolumn(int volumn);
	public abstract void setChannel(int channel);
}
