package battleship;

public class Test {

	public static void main(String[] args) {




		for (int i = 0; i < 100; i++)
		{
			System.out.println("FeldNr: " + (i+1));
			Ocean oc = new Ocean(20);
			oc.initFieldTypes();
			Fleet fleet = new Fleet (20);
			fleet.setFleet(oc);
			oc.showOcean();
		}

	}

}
