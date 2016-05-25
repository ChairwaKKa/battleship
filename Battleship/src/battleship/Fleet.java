package battleship;

import java.util.ArrayList;

import battleship.Ship;
import battleship.GameLogic;
import battleship.ShipPart;

public class Fleet {
	private int placeableShipParts = 0;
	private int totalNumberOfShips = 0;

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

	public void checkFleet(Ocean oc)
	{
		for (int i = 0; i < this.fleet.size(); i++)
		{
			this.fleet.get(i).alreadySunk(oc);
			if (this.fleet.get(i).getIsDestroyed())
			{
				changeVisualForSunkShips(oc, this.fleet.get(i));
				this.fleet.remove(i);
			}
		}
	}

	public void changeVisualForSunkShips(Ocean oc, Ship ship)
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

	public boolean isDefeted()
	{
		return (this.fleet.size() == 0);
	}

	public int getNumberOfShips()
	{
		return this.fleet.size();
	}

}
