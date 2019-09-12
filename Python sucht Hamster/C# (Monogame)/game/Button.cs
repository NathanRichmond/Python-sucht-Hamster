using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Python3.game
{
    public class Button
    {
        public int X, Y, Width, Height;
        public String Text;
        public bool IsHover;

        public Button(int x, int y, int width, int height)
        {
            X = x;
            Y = y;
            Width = width;
            Height = height;
        }

        /// <summary>
        /// Note: Button properties are of type int, hence the passsed doubles will be converted to int.
        /// </summary>
        public Button(double x, double y, double width, double height)
        {
            X = (int)x;
            Y = (int)y;
            Width = (int)width;
            Height = (int)height;
        }
    }
}
