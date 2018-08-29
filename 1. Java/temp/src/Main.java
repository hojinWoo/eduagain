import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		double dist = 0;
		double radius = 0;
		double minusradius = 0;
		for (int i = 0; i < test; i++) {
			int turret[] = new int[6];
			for (int j = 0; j < 6; j++) {
				turret[j] = sc.nextInt(); //x1, y1, r1, x2, y2, r2
			}
			dist = Math.pow(turret[4]-turret[1], 2)+Math.pow(turret[3]-turret[0], 2);
			radius = Math.pow(turret[5]+turret[2], 2);
			minusradius = Math.pow(Math.abs(turret[5] - turret[2]), 2);
			if(dist == 0 && minusradius == 0) {
				System.out.println(-1);
			}else if(dist<minusradius || (dist > radius && dist> minusradius)) {
				System.out.println(0);
			}else if(dist == minusradius || dist == radius) {
				System.out.println(1);
			}else if((dist < radius && dist> minusradius)) {
				System.out.println(2);
			}
		}
	}

}
