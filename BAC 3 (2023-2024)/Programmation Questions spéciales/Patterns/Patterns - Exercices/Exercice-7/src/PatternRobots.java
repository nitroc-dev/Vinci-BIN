public class PatternRobots {
	
	public static void fight(Robot robot1, Robot robot2) {
		int tick1=robot1.getFreq();
		int tick2=robot2.getFreq();
		while(robot2.diffLife(0)>0 && robot1.diffLife(0)>0) {
			int tick=Math.min(tick1, tick2);
			tick1-=tick;
			tick2-=tick;
			if (tick1==0) {// robot 1 feu
				tick1=shoot(robot1,robot2);
			}
			if (tick2==0) {// robot 2 feu
				tick2=shoot(robot2,robot1);
			}
		}
	}
	
	private static int shoot(Robot robot1, Robot robot2) {
		int dmg=Math.max(0,robot1.getCanon()-robot2.getShield());
		int lost=robot2.diffLife(0)-robot2.diffLife(-dmg);
		System.out.println(robot1.getName()+" shoots for "+lost);
		if (robot2.diffLife(0)<=0) {
			System.out.println("Kabooom "+robot2.getName());
		}
		return robot1.getFreq();
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		/* un robot avec un canon de 10, un bouclier de 2, une fréquence de tir de 100
			et qui a reçu une amélioration de canon multipliant la puissance de ce dernier par 2.
		*/
		Robot robot1 = new RobotImpl.RobotBuilder().setCanon(10).setShield(2).setFreq(100).setName("Robot1").buildRobot();
		/* un robot avec un canon de 9, un bouclier de 3, une fréquence de tir de 90
			et qui a reçu une amélioration de bouclier multipliant ce dernier par 2
			et une amélioration de mitigation des dégats qui réduit les points de vue perdus par 2.
		*/
		Robot robot2 = new RobotImpl.RobotBuilder().setCanon(9).setShield(3).setFreq(90).setName("Robot2").buildRobot();

		Robot robot3 = new RobotDecorator(new RobotImpl.RobotBuilder().setCanon(9).setShield(3).setFreq(90).setName("Robot3").buildRobot());
		((RobotDecorator)robot3).setBonusCanon(2);
		((RobotDecorator)robot3).setBonusShield(2);
		((RobotDecorator)robot3).setBonusFreq(2);

		//fight(robot1, robot3);

		/*
		PicVert picVert = new PicVert();
		GrosseBerta grosseBerta = new GrosseBerta();
		Tank tank = new Tank();

		FlyweightRobot_factory flyweightRobotFactory = new FlyweightRobot_factory();
		flyweightRobotFactory.addRobot("pic-vert", picVert);
		flyweightRobotFactory.addRobot("grosse-berta", grosseBerta);
		flyweightRobotFactory.addRobot("tank", tank);

		Robot robot4 = flyweightRobotFactory.createRobot("pic-vert");
		Robot robot5 = flyweightRobotFactory.createRobot("grosse-berta");
		Robot robot6 = flyweightRobotFactory.createRobot("tank");
		Robot robot7 = flyweightRobotFactory.createRobot("pic-vert");

		fight(robot4, robot5);
		System.out.println("--------------------------------------------------");
		fight(robot6, robot7);
		*/

		FlyweightRobot_prototype flyweightRobotPrototype = new FlyweightRobot_prototype();

		Robot picVert = new PicVert().createRobot();
		Robot grosseBerta = new GrosseBerta().createRobot();
		Robot tank = new Tank().createRobot();

		flyweightRobotPrototype.addRobot("pic-vert", picVert);
		flyweightRobotPrototype.addRobot("grosse-berta", grosseBerta);
		flyweightRobotPrototype.addRobot("tank", tank);

		Robot robot4 = flyweightRobotPrototype.createRobot("pic-vert");
		Robot robot5 = flyweightRobotPrototype.createRobot("grosse-berta");
		Robot robot6 = flyweightRobotPrototype.createRobot("tank");
		Robot robot7 = flyweightRobotPrototype.createRobot("pic-vert");

		fight(robot4, robot5);
		System.out.println("--------------------------------------------------");
		fight(robot6, robot7);
	}
}
