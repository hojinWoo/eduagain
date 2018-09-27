/**
 * O-R Mapping 클래스
 * Domain 클래스
 * @author hojin
 *
 */
public class Department {
	private int deptartmentId;		//부서 번호 
	private String departmentName; 	//부서 이름
	private int managerId;			
	private int locationId;
	public Department() {
		super();
	}
	public Department(int deptartmentId, String departmentName, int managerId, int locationId) {
		super();
		this.deptartmentId = deptartmentId;
		this.departmentName = departmentName;
		this.managerId = managerId;
		this.locationId = locationId;
	}
	public int getDeptartmentId() {
		return deptartmentId;
	}
	public void setDeptartmentId(int deptartmentId) {
		this.deptartmentId = deptartmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	public void delete(int departmentId) {
		
	}
	
	@Override
	public String toString() {
		return "Department [deptartmentId=" + deptartmentId + ", departmentName=" + departmentName + ", managerId="
				+ managerId + ", locationId=" + locationId + "]";
	}
	
	
	
}
