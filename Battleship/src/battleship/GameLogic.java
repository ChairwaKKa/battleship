package battleship;

/**
 * Verwaltung von Konstanten,
 * Generieren von Zufallswerten,
 * Pr�falgorithmen,
 * Setzen von Randfeldern
 *
 * @author oliver2
 */
import java.util.Random;

public class GameLogic {

	/**
	 * Konstanten f�r die Orientierung
	 */
	public final static boolean horizontal = true;
	public final static boolean vertical = false;

	/**
	 * Konstanten f�r die Konsolensymbole
	 */
	public final static String water    = " ~ ";
	public final static String hitWater = " / ";
	public final static String hitShip  = " X ";
	public final static String sinkShip = " V ";
	public final static String space    = "   ";

	/**
	 *  Ein Random Objekt wird erzeugt
	 */
	public static Random random = new Random();

	/**
	 *  H�he und Breite der K�stchen f�r die grafische Darstellung
	 */
	public static int height = 20;	// H�he
	public static int width = 20;	// Breite

	/**
	 *  Es wird eine zuf�llige ganze Zahl im Bereich von 1 bis Spielfeldgr��e erzeugt
	 *
	 * @return
	 * 		Zufallszahl
	 */
	public static int generateRandomInt()
	{
		return (random.nextInt((Ocean.size-2))+1);
	}

	/**
	 * Es wird eine zuf�llige Zahl von 0 bis 1 erzeugt
	 *
	 * @return
	 * 		0 oder 1
	 */
	public static boolean generateRandomBool()
	{
		return random.nextBoolean();
	}

	/**
	 * Pr�falgorithmus f�r ein Schiff (Ship), um es
	 * auf das Spielfeld (Ocean) zu setzen.
	 *
	 * @param x
	 * 		Ankerpunkt des Schiffes auf der X -Achse
	 *
	 * @param y
	 * 		Ankerpunkt des Schiffes auf der Y -Achse
	 *
	 * @param oc
	 * 		Spielfeld (Ocean)
	 *
	 * @param orientation
	 * 		Oreientierung
	 * 		true -> horizontal
	 * 		false-> vertikal
	 *
	 * @param shipSize
	 * 		Schiffsl�nge
	 *
	 * @param ship
	 * 		Schiff --> Ship --> ShipType
	 *
	 * @return
	 * 		Das Schiff kann gesetzt werden oder nicht
	 */
	public static boolean shipCanBeSetOnField(int x, int y, Ocean oc, boolean orientation, int shipSize, Ship ship)
	{
		boolean placeable	= false;
		boolean reverse		= false;		// gibt an, ob das Schiff in die andere Richtung gebaut wird

		if (orientation == horizontal)
		{
			if ((y + shipSize) > (Ocean.size -2))
			{
				placeable 	= false;
			}
			else
			{
				placeable	= true;

				for (int i = 0; i < shipSize; i++)
				{
					if (placeable && (oc.ocean[x][y + i].getFieldType() == FieldType.Water))
					{
						placeable = true;
					}
					else
					{
						placeable = false;
					}
				}
			}

			if (!placeable)
			{
				reverse = true;
				for (int i = 0; i < shipSize; i++)
				{
					if (reverse && (oc.ocean[x][y - i].getFieldType() == FieldType.Water))
					{
						placeable 	= true;
						reverse 	= true;
					}
					else
					{
						placeable	= false;
						reverse		= false;
					}
				}
			}
		}

		else if (orientation == vertical)
		{
			if ((x + shipSize) > (Ocean.size -2))
			{
				placeable 	= false;
			}
			else
			{
				placeable 	= true;

				for (int i = 0; i < shipSize; i++)
				{

					if (placeable && (oc.ocean[x + i][y].getFieldType() == FieldType.Water))
					{
						placeable = true;
					}
					else
					{
						placeable = false;
					}
				}
			}

			if (!placeable)
			{
				reverse = true;

				for (int i = 0; i < shipSize; i++)
				{
					if (reverse && (oc.ocean[x -i][y].getFieldType() == FieldType.Water))
					{
						placeable = true;
					}
					else
					{
						placeable 	= false;
						reverse		= false;
					}
				}
			}
		}

		ship.setReverse(reverse);
		return placeable;
	}

	/**
	 * Wenn ein Schiff auf das Spielfeld (Ocean) gesetzt wurde, werden
	 * um die Schiffsteile Randteile erzeugt.
	 *
	 * @param oc
	 * 		Spielfeld (Ocean)
	 *
	 * @param orientation
	 * 		Orientierung
	 * 		true -> horizontal
	 * 		false-> vertikal
	 *
	 * @param anchorX
	 * 		Ankerpunkt auf der X -Achse
	 *
	 * @param anchorY
	 * 		Ankerpunkt auf der Y -Achse
	 *
	 * @param shipSize
	 * 		Schiffsl�nge
	 *
	 * @param reverse
	 * 		true -> Schiff wird spiegelverkehrt gesetzt
	 * 		false-> Schiff wird normal gesetzt (nach rechts, bzw unten)
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
