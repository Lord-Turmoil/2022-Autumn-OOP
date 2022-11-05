package top.ts.oop.lab05.team;

public class Team {
	public static void main(String[] args) {
		SpiralAbyss spiralAbyss = new SpiralAbyss(3);

		spiralAbyss.addPartner(new Person("Alpha", "Alphawave ready!"));
		spiralAbyss.addPartner(new Person("Beta", "Beta here!"));
		spiralAbyss.addPartner(new Person("Gamma", "Gamma is not Grammar!"));
		spiralAbyss.addPartner(new Person("Delta", "Delta squad ready!")) ;

		spiralAbyss.listPartners();
		spiralAbyss.play();
	}
}
