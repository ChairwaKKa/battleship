package battleship;

import javax.swing.JOptionPane;



public class RunGame {

	public static void main(String[] args) {

		int size 			=  0;
		int minSize 		=  5;
		int maxSize 		= 20;
		String input 		= "";
		boolean validInput	= true;
		boolean gameEnd		= false;


		// Die Eingabe wird validiert und die Feldgr��e ausgegeben
		do
		{
			validInput = true;
			input = JOptionPane.showInputDialog("Geben Sie bitte die Spielfeldgr��e ein (min "+ minSize+", max " + maxSize +").");
			InputValidity iv = new InputValidity (minSize, maxSize, input);
			validInput = iv.validInteger();
			if (validInput)
			{
				size = iv.getInteger();
			}
		} while (!validInput);


		// Mit der Angabe der Feldgr��e wird der Ozean erzeugt
		Ocean oc = new Ocean(size);
		oc.initFieldTypes();
		System.out.print("Der Ozean wurde mit Wasser gef�llt..\n");
		Fleet fleet = new Fleet(size);
		fleet.setFleet(oc);
		System.out.print("Ganze "+ fleet.getNumberOfShips() +" wurden auf dem Ozean verteilt.\nOh ha!\n");
		oc.showOcean();

		int x = 0, y = 0, shootCounter = 0, numberOfTries = 0;
		do
		{
			gameEnd = false;

			do
			{
				input = JOptionPane.showInputDialog("Geben Sie bitte die Zielkoordinate ein!");
				InputValidity iv = new InputValidity(input);
				validInput = iv.validInputPattern(size);
				x = iv.getX();
				y = iv.getY();

			} while (!validInput);

			// Es wird nur auf ein Zielfeld geschossen, welches noch nicht beschossen wurde

			if (!oc.ocean[x][y].alreadyGotHit())
			{
				oc.ocean[x][y].shootField();
				fleet.checkFleet(oc);				
				oc.showOcean();

				shootCounter ++;
				numberOfTries = shootCounter;
				System.out.print("Es sind nur noch " + fleet.getNumberOfShips() + " Schiffe auf dem Feld!");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Auf dieses Feld wurde bereits geschossen.");
			}

			// Es wird nach dem Schuss gepr�ft, ob die gesamte Flotte zerst�rt wurde

			if (fleet.isDefeted())
			{
				gameEnd = true;
				JOptionPane.showMessageDialog(null, "Sie haben gewonnen und " + numberOfTries + " Versuche ben�tigt!");
			}

		} while(!gameEnd);

	}

}
