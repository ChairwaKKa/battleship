package battleship;

public class Ship {
	private int shipID = 0,anchorX = 0, anchorY = 0;
	private static int shipCounter = 0;
	private int shipLength = 0;
	private boolean isDestroyed = false;
	private boolean orientation = GameLogic.horizontal;
	private ShipType type = ShipType.Destroyer;



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
		//System.out.print("\nEin \"" + this.type + "\" wurde mit der ID " + shipID + " erstellt\n");

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

	public int getShipID()
	{
		return this.shipID;
	}

	public int getShipLength()
	{
		return this.shipLength;
	}

	public boolean getOrientation()
	{
		return this.orientation;
	}

	public ShipType getShipType()
	{
		return this.type;
	}

	public int getAnchorX()
	{
		return this.anchorX;
	}

	public void setAnchorX(int x)
	{
		this.anchorX = x;
	}

	public int getAnchorY()
	{
		return this.anchorY;
	}

	public void setAnchorY(int y)
	{
		this.anchorY = y;
	}

	public boolean isItAWreck()
	{
		return this.isDestroyed;
	}

	public void sinkShip()
	{
		this.isDestroyed = true;
	}

}
