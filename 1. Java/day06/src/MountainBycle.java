
public class MountainBycle extends Bycle{
	
	private String frame;
	private boolean suspension;
	
	public MountainBycle() {
		
	}
	public MountainBycle(String frame, boolean suspension) {
		this.frame = frame;
		this.suspension = suspension;
	}
	
	public MountainBycle(int id, String brand, String frame, boolean suspension) {
		super(id, brand);
		this.frame = frame;
		this.suspension = suspension;
	}
	public String getFrame() {
		return frame;
	}
	public void setFrame(String frame) {
		this.frame = frame;
	}
	public boolean isSuspension() {
		return suspension;
	}
	public void setSuspension(boolean suspension) {
		this.suspension = suspension;
	}
	public void running() {
		System.out.println("산악자전거가 달린다");
	}
	
}
