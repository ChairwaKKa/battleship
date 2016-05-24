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


		// Die Eingabe wird validiert und die Feldgröße ausgegeben
		do
		{
			validInput = true;
			input = JOptionPane.showInputDialog("Geben Sie bitte die Spielfeldgröße ein (min "+ minSize+", max " + maxSize +").");
			InputValidity iv = new InputValidity (minSize, maxSize, input);
			validInput = iv.validInteger();
			if (validInput)
			{
				size = iv.getInteger();
			}
		} while (!validInput);


		// Mit der Angabe der Feldgröße wird der Ozean erzeugt
		Ocean oc = new Ocean(size);
		oc.initFieldTypes();
		System.out.print("Der Ozean wurde mit Wasser gefüllt..\n");
		Fleet fleet = new Fleet(size);
		fleet.setFleet(oc);
		System.out.print("Ganze "+ fleet.getNumberOfShips() +" wurden auf dem Ozean verteilt.\nOh ha!\n");
		oc.showOcean();

		int x =0, y =0;
		do
		{
			gameEnd = false;
			input = JOptionPane.showInputDialog("Zielkoordinaten:");
			try
			{
				x = Integer.parseInt(input.substring(0, 1));
			} catch (Exception e)
			{
				gameEnd = true;
				JOptionPane.showMessageDialog(null, "Spiel beendet");
			}

			try
			{
				y = Integer.parseInt(input.substring(1, 2));
			} catch (Exception e)
			{
				gameEnd = true;
				JOptionPane.showMessageDialog(null, "Spiel beendet");
			}

			if (oc.ocean[x][y].alreadyGotHit())
			{
				gameEnd = true;
				JOptionPane.showMessageDialog(null, "Spiel beendet");
			}
			else
			{
				oc.ocean[x][y].shootField();
				oc.showOcean();
			}

		} while(!gameEnd);

	}

}
