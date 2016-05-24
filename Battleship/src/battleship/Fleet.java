package battleship;

import java.util.ArrayList;

import battleship.Ship;
import battleship.GameLogic;
import battleship.ShipPart;

public class Fleet {
	private int placeableShipParts = 0;
	private int totalNumberOfShips = 0;
	private int errorCounter =0;

	public ArrayList<Ship> fleet = new ArrayList<Ship>();

	Fleet(int fieldSize)
	{
		placeableShipParts = (int) (0.3 *  fieldSize * fieldSize);

//System.out.print("Frei platzierbare Schiffsteile: " + placeableShipParts + "\n");
		do
		{
			if (placeableShipParts >= ShipType.Battleship.getSize())
			{
				fleet.add(totalNumberOfShips, new Ship(ShipType.Battleship));
				totalNumberOfShips ++;
				placeableShipParts -= ShipType.Battleship.getSize();
			}

			for (int i = 0; i<2 ; i++)
			{
				if (placeableShipParts >= ShipType.Cruiser.getSize())
				{
					fleet.add(totalNumberOfShips, new Ship(ShipType.Cruiser));
					totalNumberOfShips ++;
					placeableShipParts -= ShipType.Cruiser.getSize();
				}
			}

			for (int i = 0; i<3 ; i++)
			{
				if (placeableShipParts >= ShipType.Destroyer.getSize())
				{
					fleet.add(totalNumberOfShips, new Ship(ShipType.Destroyer));
					totalNumberOfShips ++;
					placeableShipParts -= ShipType.Destroyer.getSize();
				}
			}

			for (int i = 0; i< 4; i++)	// Es wird versucht vier Submarines zur Flotte hinzuzufügen
			{
				if (placeableShipParts >= ShipType.Submarine.getSize())
				{
					fleet.add(totalNumberOfShips, new Ship(ShipType.Submarine));
					totalNumberOfShips ++;
					placeableShipParts -= ShipType.Submarine.getSize();
				}
			}

		} while (placeableShipParts >  ShipType.Submarine.getSize());
//System.out.print("Es wurden " + totalNumberOfShips + " Schiffe der Flotte hinzugefügt.\nEs sind noch "+placeableShipParts + " freie Felder übrig.\n" );
	}

	public void setFleet (Ocean oc)
	{
		boolean orientation = true;								// true -> horizontal aufs Feld; false -> vertikal aufs Feld
		boolean shipCanBePLacedOnField = false;
		boolean reverse = false;
		int anchorX = 0, anchorY = 0;							// Ankerpunkte des Schiffes

		for (Ship ship : fleet)									// Es wird durch die Flotte durchiteriert und jedes Schiff aufs Feld gesetzt
		{
			reverse			= false;
			orientation 	= GameLogic.generateRandomBool();		// Für jedes Schiff wird die Orientierung ein mal gewürfelt
			int shipSize 	= ship.getShipLength();
			int shipID		= ship.getShipID();
			do
			{
				anchorX = GameLogic.generateRandomInt();
				anchorY = GameLogic.generateRandomInt();

				shipCanBePLacedOnField 	= GameLogic.shipCanBeSetOnField(anchorX, anchorY, oc, orientation, shipSize);

			} while (!shipCanBePLacedOnField);					// Solange das Schiff nicht platziert werden kann, soll ein neuer Ankerpunkt gesucht werden.

			ship.setAnchorX(anchorX);
			ship.setAnchorY(anchorY);
	/*
	 * Wenn ein Schiff platziert werden kann, wird es mit den zuletzt gewürfelten Werten auf den Ozean gesetzt
	 */
			if (orientation == GameLogic.horizontal)
			{
				try
				{
					for (int i = 0; i < shipSize; i++)
					{
						oc.ocean[anchorX][anchorY + i] = new ShipPart(anchorX, anchorY+i, shipID);			// Schiffsteile werden mit der jeweiligen Schiffsnummer erzeugt
						oc.ocean[anchorX][anchorY + i].setFieldType(FieldType.ShipPart);
					}
				}catch (Exception e)
				{
					for (int i = 0; i < shipSize; i++)
					{
						oc.ocean[anchorX][anchorY - i] = new ShipPart(anchorX, anchorY+i, shipID);			// Schiffsteile werden mit der jeweiligen Schiffsnummer erzeugt
						oc.ocean[anchorX][anchorY - i].setFieldType(FieldType.ShipPart);
					}
					reverse = true;
				}

			}


			if (orientation == GameLogic.vertical)
			{
				try
				{
					for (int j = 0; j < shipSize; j++)
					{
						oc.ocean[anchorX + j][anchorY] = new ShipPart(anchorX + j,anchorY, shipID);			// Schiffsteile werden mit der jeweiligen Schiffsnummer erzeugt
						oc.ocean[anchorX + j][anchorY].setFieldType(FieldType.ShipPart);
					}
				} catch (Exception e)
				{
					for (int j = 0; j < shipSize; j++)
					{
						oc.ocean[anchorX - j][anchorY] = new ShipPart(anchorX + j,anchorY, shipID);			// Schiffsteile werden mit der jeweiligen Schiffsnummer erzeugt
						oc.ocean[anchorX - j][anchorY].setFieldType(FieldType.ShipPart);
					}
					reverse = true;
				}

			}
	/*
	 * Wenn ein Schiff platziert worden ist, werden Randfelder um es herumgesetzt
	 */
			GameLogic.setShipBorder(oc, orientation, anchorX, anchorY, shipSize, reverse);
if (ship.getShipID() == this.totalNumberOfShips)
{
	errorCounter ++;
	System.out.print("Durchlauf Nummer: "+ errorCounter + "\n");
}
		}
	}

	public int getNumberOfShips()
	{
		return totalNumberOfShips;
	}

}
