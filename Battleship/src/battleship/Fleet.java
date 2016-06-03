package battleship;

/**
 * Eine Flotte setzt sich aus mehreren Schiffen (--> Ship) zusammen
 *
 * @author oliver2
 */
import java.util.ArrayList;

public class Fleet {
	private int placeableShipParts = 0;
	private int totalNumberOfShips = 0;

	public ArrayList<Ship> fleet = new ArrayList<Ship>();

	/**
	 * Die Flotte wird abhängig von der Spielfeldgröße erstellt.
	 * Die Schiffe sind unter --> ShipType definiert.
	 * 30% der Spielfelder sind Schiffsteile.
	 *
	 *
	 * @param fieldSize
	 * 		Spielfeldgröße
	 */

	Fleet(int fieldSize)
	{
		placeableShipParts = (int) (0.3 * fieldSize * fieldSize);

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
	}

	/**
	 * Die Schiffe der Flotte werden nacheinander auf das Spielfeld (Ocean) gelegt
	 *
	 * @param oc
	 * 		Spielfeld (Ocean)
	 */
	public void setFleet (Ocean oc)
	{
		boolean orientation = true;								// true -> horizontal aufs Feld; false -> vertikal aufs Feld
		boolean shipCanBePLacedOnField = false;
		boolean reverse = false;
		int anchorX = 0, anchorY = 0;							// Ankerpunkte des Schiffes

		for (Ship ship : this.fleet)									// Es wird durch die Flotte durchiteriert und jedes Schiff aufs Feld gesetzt
		{
			reverse			= false;
			int shipSize 	= ship.getShipLength();
			int shipID		= ship.getShipID();
			do
			{
				anchorX		= GameLogic.generateRandomInt();
				anchorY 	= GameLogic.generateRandomInt();
				orientation = GameLogic.generateRandomBool();

				shipCanBePLacedOnField 	= GameLogic.shipCanBeSetOnField(anchorX, anchorY, oc, orientation, shipSize, ship);

			} while (!shipCanBePLacedOnField);					// Solange das Schiff nicht platziert werden kann, soll ein neuer Ankerpunkt gesucht werden.

			ship.setAnchorX(anchorX);
			ship.setAnchorY(anchorY);
			reverse = ship.getReverse();
	/*
	 * Wenn ein Schiff platziert werden kann, wird es mit den zuletzt gewürfelten Werten auf den Ozean gesetzt
	 */
			if (orientation == GameLogic.horizontal)
			{
				if (!reverse)
				{
					for (int i = 0; i < shipSize; i++)
					{
						oc.ocean[anchorX][anchorY + i] = new ShipPart(anchorX, anchorY+i, shipID);			// Schiffsteile werden mit der jeweiligen Schiffsnummer erzeugt
						oc.ocean[anchorX][anchorY + i].setFieldType(FieldType.ShipPart);
					}
				}
				else if (reverse)
				{
					for (int i = 0; i < shipSize; i++)
					{
						oc.ocean[anchorX][anchorY - i] = new ShipPart(anchorX, anchorY+i, shipID);			// Schiffsteile werden mit der jeweiligen Schiffsnummer erzeugt
						oc.ocean[anchorX][anchorY - i].setFieldType(FieldType.ShipPart);
					}
				}

			}

			if (orientation == GameLogic.vertical)
			{
				if (!reverse)
				{
					for (int j = 0; j < shipSize; j++)
					{
						oc.ocean[anchorX + j][anchorY] = new ShipPart(anchorX + j,anchorY, shipID);			// Schiffsteile werden mit der jeweiligen Schiffsnummer erzeugt
						oc.ocean[anchorX + j][anchorY].setFieldType(FieldType.ShipPart);
					}
				} else if (reverse)
				{
					for (int j = 0; j < shipSize; j++)
					{
						oc.ocean[anchorX - j][anchorY] = new ShipPart(anchorX + j,anchorY, shipID);			// Schiffsteile werden mit der jeweiligen Schiffsnummer erzeugt
						oc.ocean[anchorX - j][anchorY].setFieldType(FieldType.ShipPart);
					}
				}
			}
	/*
	 * Wenn ein Schiff platziert worden ist, werden Randfelder um es herumgesetzt
	 */
			GameLogic.setShipBorder(oc, orientation, anchorX, anchorY, shipSize, reverse);
		}
	}

	/**
	 *Die Schiffe der Flotte werden nacheinander geprüft, ob sie bereits gesunken sind
	 *und wenn ja, werden sie sowohl als zerstört gesetzt, als auch aus der Flotte entfernt.
	 *
	 * @param oc
	 * 		Spielfeld (Ocean)
	 */
	public void checkFleet(Ocean oc)
	{
		for (Ship ship : this.fleet)
		{
			ship.checkIfAlreadySunken(oc);
			if (ship.getIsDestroyed())
			{
				changeVisualForSunkenShips(oc, ship);
				this.fleet.remove(ship.getShipID() -1);
			}
		}
	}

	/**
	 * Die Darstellung wird für ein zerstörtes Schiff geändert
	 *
	 * @param oc
	 * 		Spielfeld (Ocean)
	 *
	 * @param ship
	 * 		Schiff --> Ship --> ShipType
	 */
	public void changeVisualForSunkenShips(Ocean oc, Ship ship)
	{
		for (int x = 0; x < oc.ocean.length; x++)
			for (int y= 0; y < oc.ocean[x].length; y++)
			{
				if (ship.getShipID() == oc.ocean[x][y].getFieldID())
				{
					oc.ocean[x][y].setVisual(GameLogic.sinkShip);
				}
			}
	}

	/**
	 * Gibt wieder, ob die Flotte besiegt worden ist
	 *
	 * @return
	 * 		Besiegt oder nicht
	 */
	public boolean isDefeated()
	{
		return (this.fleet.size() == 0);
	}

	/**
	 * Gibt die aktuelle Anzahl an Schiffen wieder
	 *
	 * @return
	 * 		Anzahl der übrigen Schiffe
	 */
	public int getNumberOfShips()
	{
		return this.fleet.size();
	}

}
