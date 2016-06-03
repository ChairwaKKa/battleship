package battleship;

/**
 * Beschreibt die Eigenschaften und Methoden eines Schiffes
 *
 * @author oliver2
 *
 */
public class Ship {
	private int shipID = 0, anchorX = 0, anchorY = 0;
	private static int shipCounter 	= 0;
	private int shipLength			= 0;
	private boolean isDestroyed 	= false;
	private boolean orientation 	= GameLogic.horizontal;
	private ShipType type 			= ShipType.Destroyer;
	private boolean reverse			= false;



	Ship ()
	{
		this.shipID = 0;
		this.type = ShipType.Destroyer;
		this.orientation = GameLogic.random.nextBoolean(); 		// true -> horizontal, false -> vertikal
		this.anchorX = 0;
		this.anchorY = 0;
		this.shipLength = this.type.getSize();
		this.isDestroyed = false;
	}

	Ship (ShipType shiptype) 									// Konstruktor mit Ankerpunkt im Koordinatenursprung
	{
		Ship.shipCounter++;
		this.shipID += Ship.shipCounter;
		this.type = shiptype;
		this.shipLength = this.type.getSize();
		this.orientation = GameLogic.random.nextBoolean(); 		// true -> horizontal, false -> vertikal
		this.anchorX = 0;
		this.anchorY = 0;
		this.isDestroyed = false;

	}

	Ship (ShipType shiptype, boolean orientation, int x, int y) // Konstruktor mit variablem Ankerpunkt
	{
		Ship.shipCounter++;
		this.shipID += Ship.shipCounter;
		this.type = shiptype;
		this.shipLength = this.type.getSize();
		this.orientation = orientation; 						// true -> horizontal, false -> vertikal
		this.anchorX = x;
		this.anchorY = y;
		this.isDestroyed = false;
	}

	/**
	 * Prüft, ob die dem Schiff zugehörigen Schiffsteile getroffen wurden und markiert
	 * das Schiff als zerstört, wenn alle seine Teile bereits getroffen worden sind.
	 *
	 * @param oc
	 * 		Spielfeld (Ocean)
	 */
	public void checkIfAlreadySunken(Ocean oc)
	{
		int shipPartCounter = 0;

		for (int x = 0; x < oc.ocean.length ; x ++)
			for (int y = 0; y < oc.ocean[x].length; y++)
			{
				if ((this.shipID == oc.ocean[x][y].getFieldID()) && oc.ocean[x][y].alreadyGotHit())
				{
					shipPartCounter++;
				}
			}
		this.isDestroyed = (shipPartCounter == this.shipLength);
	}

	/**
	 * Gibt die Schiffs -ID wieder
	 *
	 * @return
	 * 		Schiff - ID
	 */
	public int getShipID()
	{
		return this.shipID;
	}

	/**
	 * Gibt die Länge des Schiffes wieder
	 *
	 * @return
	 * 		Schiffslänge
	 */
	public int getShipLength()
	{
		return this.shipLength;
	}

	/**
	 * Gibt die Orientierung des Schiffes wieder.
	 * true  -> horizontal
	 * false -> vertikal
	 *
	 * @return
	 * 		Orientierung
	 */
	public boolean getOrientation()
	{
		return this.orientation;
	}

	/**
	 * Gibt den Schiffstypen des Schiffes wieder --> ShipType
	 *
	 * @return
	 * 		Schiffstyp
	 */
	public ShipType getShipType()
	{
		return this.type;
	}

	/**
	 * Gibt den Wert des Ankerpunktes auf der X-Achse wieder
	 *
	 * @return
	 * 		Punkt auf der X-Achse
	 */
	public int getAnchorX()
	{
		return this.anchorX;
	}

	/**
	 * Setzt den Ankerpunkt auf der X -Achse neu
	 *
	 * @param x
	 * 		Neuer Punkt auf der X-Achse
	 */
	public void setAnchorX(int x)
	{
		this.anchorX = x;
	}

	/**
	 * Gibt den Wert des Ankerpunktes auf der Y-Achse wieder
	 *
	 * @return
	 * 		Punkt auf der Y-Achse
	 */
	public int getAnchorY()
	{
		return this.anchorY;
	}

	/**
	 * Setzt den Ankerpunkt auf der Y -Achse neu
	 *
	 * @param y
	 * 		Neuer Punkt auf der Y-Achse
	 */
	public void setAnchorY(int y)
	{
		this.anchorY = y;
	}

	/**
	 * Gibt wieder ob ein Schiff zerstört ist
	 *
	 * @return
	 * 		Schiff ist zerstört oder nicht
	 */
	public boolean getIsDestroyed()
	{
		return this.isDestroyed;
	}

	/**
	 * Markiert ein Schiff als zerstört
	 */
	public void setIsDestroyed()
	{
		this.isDestroyed = true;
	}

	/**
	 * Markiert ein Schiff so, dass es Spiegelverkehrt auf das Spielfeld (Ocean)
	 * gesetzt wird.
	 *
	 * @param r
	 * 		true  -> wird spiegelverkehrt gesetzt
	 * 		false -> wird normal gesetzt
	 */
	public void setReverse (boolean r)
	{
		this.reverse = r;
	}

	/**
	 * Gibt wieder, ob das Schiff spiegelverkehrt auf das Spielfeld
	 * gesetzt wird.
	 *
	 * @return
	 * 		true  -> wird spiegelverkehrt gesetzt
	 * 		false -> wird normal gesetzt
	 */
	public boolean getReverse()
	{
		return this.reverse;
	}

}
