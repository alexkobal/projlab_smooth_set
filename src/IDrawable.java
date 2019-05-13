import java.awt.*;

/**
 * IDrawable
 * <p>
 *     An interface for objects that can be drawn
 * </p>
 */
public interface IDrawable
{
    /**
     * invokeDraw
     * <p>
     *     Meant to call the proper draw function in the view
     * </p>
     * @param g graphics object
     */
    public void invokeDraw(Graphics g);
}
