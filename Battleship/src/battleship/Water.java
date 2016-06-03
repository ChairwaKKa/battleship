package battleship;

/**
 * Beschreibt ein Wasserfeld
 *
 * @author oliver2
 *
 */
public class Water extends Field{
	protected FieldType ft = FieldType.Water;


	Water()
	{
		this.fieldID = 0;
		this.visual = GameLogic.water;
	}

	@Override
	public void shootField()
	{
		this.gotHit = true;
		this.visual = GameLogic.hitWater;

	}
}
