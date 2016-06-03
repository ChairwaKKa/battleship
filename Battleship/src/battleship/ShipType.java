package battleship;

/**
 * Auswahl aller möglicher Schiffstypen und deren Größenangabe
 *
 * @author oliver2
 *
 */
public enum ShipType {
	 Submarine(2), Destroyer(3), Cruiser(4), Battleship(5);

	private final int size; // Trefferpunkte des jeweiligen Schiffes

	ShipType(int shipSize)
	{
		this.size = shipSize;
	}

	/**
	 * Gibt die Länge / Größe des Schiffes wieder
	 *
	 * @return
	 * 		Schiffslänge
	 */
	public int getSize()
	{
		return size;
	}

}
