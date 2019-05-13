import java.awt.*;

/**
 * RegularTile
 * <p>
 * Non-breakable type of Tile. Under non-moving objects it is guaranteed to be a RegularTile.
 *
 */
public class RegularTile extends Tile
{
	/**
	 * invokeDraw
	 * <p>
	 *     Implementation of IDrawable
	 * </p>
	 * @param g graphics object
	 */
	@Override
	public void invokeDraw(Graphics g) {
		View.getInstance().draw(this, g);
	}

	/**
	 * toString
	 * <p>
	 *     Overrides Object.toString() in case of readability
	 * </p>
	 * @return "r" string
	 */
	@Override
	public String toString(){
		return "r";
	}
}


