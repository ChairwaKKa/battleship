package battleship;

/**
 * Schiffsteil eines Schiffes
 *
 * @author oliver2
 *
 */
public class ShipPart extends Field{
	protected FieldType ft = FieldType.ShipPart;


	ShipPart (int x, int y,int id)
	{
		this.x = x;
		this.y = y;
		this.fieldID = id;
		this.visual = GameLogic.water;
	}


	@Override
	public void shootField()
	{
		this.gotHit = true;
		this.visual = GameLogic.hitShip;

	}

}
