
public class Unit {
	
	//초기화 할 필요 없음
	private Weapon weapon;
	private String name;
	
	public Unit() {}
	public Unit(String name) {
		//Aggregation 관계인 경우 Weapon도 입력 받아와서 초기화 해야한다. this.weapon = weapon;
		//Composition 관계인 경우 외부에서 전달이 아닌 생성을 해야 한다. weapon = new Weapon(); 이 경우는 class가 아니니까 불가.
		this.name = name;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void attack() {
		weapon.attack();
	}
	
	public static void main(String[] args) {
		Unit unit = new Unit("SCV");
		
		Weapon weapon = new Gun();
		
		unit.setWeapon(weapon);
		unit.attack();
		
		unit.setWeapon(new Sword());
		unit.attack();
	}
	
}
