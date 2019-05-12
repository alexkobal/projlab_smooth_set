import java.awt.*;

/**
 * RegularTile
 * <p>
 * Non-breakable type of Tile. Under non-moving objects it is guaranteed to be a RegularTile.
 *
 */
public class RegularTile extends Tile
{
	@Override
	public void invokeDraw(Graphics g) {
		View.getInstance().draw(this, g);
	}

	@Override
	public String toString(){
		return "r";
	}
}


