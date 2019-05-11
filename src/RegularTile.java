/**
 * RegularTile
 * <p>
 * Non-breakable type of Tile. Under non-moving objects it is guaranteed to be a RegularTile.
 *
 */
public class RegularTile extends Tile
{
	@Override
	public String toString(){
		return "r";
	}

	@Override
	public void invokeDraw() {
		View.draw(this);
	}
}
