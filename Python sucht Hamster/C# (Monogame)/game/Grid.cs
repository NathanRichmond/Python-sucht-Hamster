using Python3.tiles;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Python3.game
{
    class Grid
    {
        //public static List<Tile> GameGrid;
        //public static Dictionary<Tile, object> GameGrid;
        public static Tile[,] GameGrid { get; set; } // two-dimensional array containing all tiles
        public static int[] Size { get; set; } = new int[2]; // array with the width and height of the grid in tiles, e.g . { 10, 10 }
        public static int X { get; set; } // position in window
        public static int Y { get; set; }
        public static int Width { get; set; }
        public static int Height { get; set; }

        public Grid()
        {
            Size[0] = Level.Gridsize[0];
            Size[1] = Level.Gridsize[1];

            GameGrid = new Tile[Size[0], Size[1]];

            Width = Size[0] * (GameMain.Tile - 1);
            Height = Size[1] * (GameMain.Tile - 1);

            X = GameMain.ScreenWidth / 2 - Width / 2;
            Y = (GameMain.ScreenHeight / 2 - Height / 2) / 2 + GameMain.ScreenHeight / 8;

            CreateTiles();
        }

        private void CreateTiles()
        {
            for (int i = 0; i < (Size[0]); i++)
            {
                for (int j = 0; j < Size[1]; j++)
                {
                    GameGrid[i, j] = new Tile(i, j);
                }
            }
        }
    }
}
