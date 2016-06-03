package battleship;

/**
 * Auswahl aller m�glicher Schiffstypen und deren Gr��enangabe
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
	 * Gibt die L�nge / Gr��e des Schiffes wieder
	 *
	 * @return
	 * 		Schiffsl�nge
	 */
	public int getSize()
	{
		return size;
	}

}
