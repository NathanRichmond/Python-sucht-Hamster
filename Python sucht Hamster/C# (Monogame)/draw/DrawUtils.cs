using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Python3.draw
{
    public static class DrawUtils
    {
        /// <summary>
        /// Determines the upper-left coordinates of a string based on its alignment's coordinates.
        /// </summary>
        /// <param name="targetX">X coordinate of the alignment</param>
        /// <param name="targetY">Y coordinate of the alignment</param>
        public static Vector2 StringPos(string text, SpriteFont font, float targetX, float targetY, Align alignment)
        {
            float x = targetX;
            float y = targetY;

            float width = font.MeasureString(text).X;
            float height = font.MeasureString(text).Y;

            switch (alignment)
            {
                case Align.Center:
                    x = targetX - width / 2;
                    y = targetY - height / 2;
                    break;
                case Align.Right:
                    x = targetX - width;
                    y = targetY - height;
                    break;
            }

            return new Vector2(x, y);
        }

        public enum Align { Center, Right }



        /// <summary>
        /// Will draw a border (hollow rectangle) of the given 'thicknessOfBorder' (in pixels)
        /// of the specified color.
        ///
        /// By Sean Colombo, from http://bluelinegamestudios.com/blog
        /// </summary>
        /// <param name="rectangleToDraw"></param>
        /// <param name="thicknessOfBorder"></param>
        public static void DrawBorder(SpriteBatch spriteBatch, Texture2D pixel, Rectangle rectangleToDraw,
                                      int thicknessOfBorder, Color borderColor)
        {
            // Draw top line
            spriteBatch.Draw(pixel, new Rectangle(rectangleToDraw.X, rectangleToDraw.Y, rectangleToDraw.Width, thicknessOfBorder), borderColor);

            // Draw left line
            spriteBatch.Draw(pixel, new Rectangle(rectangleToDraw.X, rectangleToDraw.Y, thicknessOfBorder, rectangleToDraw.Height), borderColor);

            // Draw right line
            spriteBatch.Draw(pixel, new Rectangle((rectangleToDraw.X + rectangleToDraw.Width - thicknessOfBorder),
                                            rectangleToDraw.Y,
                                            thicknessOfBorder,
                                            rectangleToDraw.Height), borderColor);
            // Draw bottom line
            spriteBatch.Draw(pixel, new Rectangle(rectangleToDraw.X,
                                            rectangleToDraw.Y + rectangleToDraw.Height - thicknessOfBorder,
                                            rectangleToDraw.Width,
                                            thicknessOfBorder), borderColor);
        }
    }
}
