package battleship;

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

	public void alreadySunk(Ocean oc)
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

	public boolean getIsDestroyed()
	{
		return this.isDestroyed;
	}

	public void setIsDestroyed()
	{
		this.isDestroyed = true;
	}

	public void setReverse (boolean r)
	{
		this.reverse = r;
	}

	public boolean getReverse()
	{
		return this.reverse;
	}

}
