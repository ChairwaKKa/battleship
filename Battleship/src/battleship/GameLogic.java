package battleship;

import java.util.Random;

public class GameLogic {

	// Konstanten für die Orientierung
	public final static boolean horizontal = true;
	public final static boolean vertical = false;

	// Konstanten für die Konsolensymbole
	public final static String water    = " ~ ";
	public final static String hitWater = " / ";
	public final static String hitShip  = " X ";
	public final static String sinkShip = " V ";
	public final static String space    = "   ";
	public final static String  test    = " S ";

	// Ein Random Objekt wird erzeugt
	public static Random random = new Random();

	// Höhe und Breite der Kästchen für die grafische Darstellung
	public static int height = 20;	// Höhe
	public static int width = 20;	// Breite

	// Es wird eine zufällige ganze Zahl ausgegeben
	public static int generateRandomInt()
	{
		return (random.nextInt((Ocean.getFieldSize()-2))+1);
	}

	// Es wird ein zufälliger boolscher Wert ausgegeben
	public static boolean generateRandomBool()
	{
		return random.nextBoolean();
	}

	/*
	 * Es wird geprüft ob mit den gegebenen Werten ein Schiff gesetzt werden kann
	 */
	public static boolean shipCanBeSetOnField(int x, int y, Ocean oc, boolean orientation, int shipSize)
	{
		boolean positionabel = true;

		if (orientation == horizontal)
		{
			if ((y + shipSize) > (Ocean.getFieldSize() -2))
			{
				positionabel = false;
			}
			else
			{
				for (int i = 0; i < shipSize; i++)
				{

					if (oc.ocean[x][y + i].getFieldType() == FieldType.Water)
					{
						positionabel = true;
					}
					else
					{
						return false;
					}
				}
			}

			if (!positionabel)
			{
				for (int i = 0; i < shipSize; i++)
				{
					if (oc.ocean[x][y - i].getFieldType() == FieldType.Water)
					{
						positionabel = true;
					}
					else
					{
						return false;
					}
				}
			}
		}

		else if (orientation == vertical)
		{
			if ((x + shipSize) > (Ocean.getFieldSize() -2))
			{
				positionabel = false;
			}
			else
			{
				for (int i = 0; i < shipSize; i++)
				{

					if (oc.ocean[x + i][y].getFieldType() == FieldType.Water)
					{
						positionabel = true;
					}
					else
					{
						return false;
					}
				}
			}

			if (!positionabel)
			{
				for (int i = 0; i < shipSize; i++)
				{
					if (oc.ocean[x -i][y].getFieldType() == FieldType.Water)
					{
						positionabel = true;
					}
					else
					{
						return false;
					}
				}
			}
		}

		else
		{
			positionabel = false;
		}

		return positionabel;
	}

	/*
	 * Es werden um ein gesetztes Schiff Randfelder gelegt
	 */

	public static void setShipBorder(Ocean oc, boolean orientation, int anchorX, int anchorY, int shipSize, boolean reverse)
	{

		if (orientation == horizontal)
		{
			if (reverse)
			{
				if (oc.ocean[anchorX][anchorY -shipSize].getFieldType() == FieldType.Water)			// Setze Randfeld links, wenn dort Wasser ist
					oc.ocean[anchorX][anchorY -shipSize].setFieldType(FieldType.Border);

				if (oc.ocean[anchorX][anchorY +1].getFieldType() == FieldType.Water)	// Setze Randfeld rechts, wenn dort Wasser ist
					oc.ocean[anchorX][anchorY +1].setFieldType(FieldType.Border);

				for (int i = 0; i < shipSize; i++)
				{
					if (oc.ocean[anchorX -1][anchorY -i].getFieldType() == FieldType.Water)
						oc.ocean[anchorX -1][anchorY -i].setFieldType(FieldType.Border); 		// Setze Randfelder obeerhalb, wenn dort Wasser ist

					if (oc.ocean[anchorX +1][anchorY -i].getFieldType() == FieldType.Water)
						oc.ocean[anchorX +1][anchorY -i].setFieldType(FieldType.Border); 		// Setze Randfelder unterhalb, wenn dort Wasser ist
				}
			}
			else if (!reverse)
			{
				if (oc.ocean[anchorX][anchorY -1].getFieldType() == FieldType.Water)			// Setze Randfeld links, wenn dort Wasser ist
					oc.ocean[anchorX][anchorY -1].setFieldType(FieldType.Border);

				if (oc.ocean[anchorX][anchorY +shipSize].getFieldType() == FieldType.Water)	// Setze Randfeld rechts, wenn dort Wasser ist
					oc.ocean[anchorX][anchorY +shipSize].setFieldType(FieldType.Border);

				for (int i = 0; i < shipSize; i++)
				{
					if (oc.ocean[anchorX -1][anchorY +i].getFieldType() == FieldType.Water)
						oc.ocean[anchorX -1][anchorY +i].setFieldType(FieldType.Border); 		// Setze Randfelder obeerhalb, wenn dort Wasser ist

					if (oc.ocean[anchorX +1][anchorY +i].getFieldType() == FieldType.Water)
						oc.ocean[anchorX +1][anchorY +i].setFieldType(FieldType.Border); 		// Setze Randfelder unterhalb, wenn dort Wasser ist
				}
			}
		}
		else if (orientation == vertical)
		{

			if (reverse)
			{
				for (int i = 0; i < shipSize; i++)
				{
					if (oc.ocean[anchorX -shipSize][anchorY ].getFieldType() == FieldType.Water)			// Setze Randfeld oberhalb, wenn dort Wasser ist
						oc.ocean[anchorX -shipSize][anchorY ].setFieldType(FieldType.Border);

					if (oc.ocean[anchorX +1][anchorY].getFieldType() == FieldType.Water)	// Setze Randfeld unterhalb, wenn dort Wasser ist
						oc.ocean[anchorX +1][anchorY].setFieldType(FieldType.Border);

					if (oc.ocean[anchorX -i][anchorY -1].getFieldType() == FieldType.Water)
						oc.ocean[anchorX -i][anchorY -1].setFieldType(FieldType.Border); 		// Setze Randfelder links, wenn dort Wasser ist

					if (oc.ocean[anchorX -i][anchorY +1].getFieldType() == FieldType.Water)
						oc.ocean[anchorX -i][anchorY +1].setFieldType(FieldType.Border); 		// Setze Randfelder rechts, wenn dort Wasser ist
				}
			}
			else if (!reverse)
			{
				if (oc.ocean[anchorX -1][anchorY ].getFieldType() == FieldType.Water)			// Setze Randfeld oberhalb, wenn dort Wasser ist
					oc.ocean[anchorX -1][anchorY ].setFieldType(FieldType.Border);

				if (oc.ocean[anchorX +shipSize][anchorY].getFieldType() == FieldType.Water)	// Setze Randfeld unterhalb, wenn dort Wasser ist
					oc.ocean[anchorX +shipSize][anchorY].setFieldType(FieldType.Border);

				for (int i = 0; i < shipSize; i++)
				{
					if (oc.ocean[anchorX +i][anchorY -1].getFieldType() == FieldType.Water)
						oc.ocean[anchorX +i][anchorY -1].setFieldType(FieldType.Border); 		// Setze Randfelder links, wenn dort Wasser ist

					if (oc.ocean[anchorX +i][anchorY +1].getFieldType() == FieldType.Water)
						oc.ocean[anchorX +i][anchorY +1].setFieldType(FieldType.Border); 		// Setze Randfelder rechts, wenn dort Wasser ist
				}
			}

		}
		else
		{
			System.out.print("ERROR: GameLogic : setShipBorder");
		}



	}


}
