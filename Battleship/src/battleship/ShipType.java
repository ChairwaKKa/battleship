package battleship;

public enum ShipType {
	 Submarine(2), Destroyer(3), Cruiser(4), Battleship(5);

	private final int size; // Trefferpunkte des jeweiligen Schiffes

	ShipType(int shipSize)
	{
		this.size = shipSize;
	}

	public int getSize()
	{
		return size;
	}

}
