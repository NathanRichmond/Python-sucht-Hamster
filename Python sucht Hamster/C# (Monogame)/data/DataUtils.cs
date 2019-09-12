using Python3.chars;
using Python3.game;
using Python3.tiles;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Python3.data
{
    static class DataUtils
    {
        public static Random r = new Random();

        /// <summary>
        /// Determines whether a point is within the borders of a Button.
        /// </summary>
        /// <param name="button">The Button in question</param>
        /// <param name="x">The x coordinate of the point</param>
        /// <param name="y">The y coordinate of the point</param>
        /// <returns>A bool.</returns>
        public static bool CollisionBtn(Button button, int x, int y)
        {
            return x < button.X + button.Width
                && x > button.X
                && y < button.Y + button.Height
                && y > button.Y;
        }

        /// <summary>
        /// Generates two random integers that are within the boundaries of the grid size.
        /// </summary>
        /// <returns>The two integers in an array.</returns>
        public static int[] GenRandomCoords()
        {
            int randX = r.Next(Grid.Size[0]);
            int randY = r.Next(Grid.Size[1]);

            return new int[] { randX, randY }; // return array with coords
        }

        /// <summary>
        /// Generates a random Direction.
        /// </summary>
        public static Dir GenRandomDir()
        {
            Dir dir = Dir.North; // default value
            int dirInt = r.Next(4);
            switch (dirInt)
            {
                case 0:
                    dir = Dir.North;
                    break;
                case 1:
                    dir = Dir.East;
                    break;
                case 2:
                    dir = Dir.South;
                    break;
                case 3:
                    dir = Dir.West;
                    break;
            }
            return dir;
        }

        /// <summary>
        /// Converts a direction into the respective integer.
        /// </summary>
        public static int Convert(Dir dir)
        {
            switch (dir)
            {
                case Dir.North:
                    return 0;
                case Dir.East:
                    return 1;
                case Dir.South:
                    return 2;
                case Dir.West:
                    return 3;
                default:
                    return 0;
            }
        }

        /// <summary>
        /// Converts an integer into the respective direction.
        /// </summary>
        public static Dir Convert(int dir)
        {
            switch (dir)
            {
                case 0:
                    return Dir.North;
                case 1:
                    return Dir.East;
                case 2:
                    return Dir.South;
                case 3:
                    return Dir.West;
                default:
                    return Dir.North;
            }
        }

        /*
         * Round input to a multiple of tile size.
         * Round down if difference < (half of tile size), round up otherwise.
         * Might need improving! (rounding)
         */
        public static int RoundToTile(int input)
        {
            int result = 0;
            int halfOfTile = (int)Math.Round((double)(GameMain.Tile / 2));
            if (input % GameMain.Tile <= halfOfTile)
            {
                result = input - (input % GameMain.Tile);
            }
            else
            {
                result = input + (GameMain.Tile - (input % GameMain.Tile));
            }
            return result;
        }

        /// <summary>
        /// Get a specific Tile by its grid coordinates.
        /// </summary>
        /// <returns>The found Tile.</returns>
        public static Tile GetTile(int gridX, int gridY)
        {
            // cycle through all Tiles
            for (int i = 0; i < Grid.Size[0]; i++)
            {
                for (int j = 0; j < Grid.Size[1]; j++)
                {
                    Tile t = Grid.GameGrid[i, j];
                    if (t.GridX == gridX && t.GridY == gridY)
                    {
                        return t;
                    }
                }
            }
            return null;
        }

        /// <summary>
        /// Determine the size of the area around the player the enemy won't enter, based on grid size.
        /// </summary>
        public static int CalcPlayerMargin()
        {
            // Square root of product of (grid width / factor) and (grid height / factor)
            return (int)Math.Sqrt((Level.Gridsize[0] - 1) / 2.5 * (Level.Gridsize[1] - 1) / 2.5);
        }

        /// <summary>
        /// Check whether the coordinates are still within the grid.
        /// </summary>
        public static bool WithinGrid(int gridX, int gridY)
        {
            return gridX >= 0 && gridX < Grid.Size[0] && gridY >= 0 && gridY < Grid.Size[1];
        }

        /// <summary>
        /// Pick one out of a set, randomly
        /// </summary>
        /// <param name="values">The set from which to pick one from</param>
        public static int PickRandom(int[] values)
        {
            int rand = 0;

            // find maximum value in values
            int max = 0;
            for (int i = 0; i < values.Length; i++)
            {
                if (values[i] > max)
                {
                    max = values[i];
                }
            }

            bool repeat = true;
            do
            {
                rand = r.Next(max + 1); // generate random int 0-(max value of the array)

                for (int i = 0; i < values.Length; i++)
                { // cycle through the values
                    if (rand == values[i])
                    { // if rand equals a value
                        repeat = false; // then stop
                        break;
                    }
                }
            } while (repeat);

            return rand;
        }

        /// <summary>
        /// Returns an array of Directions, with the order of the Directions randomized.
        /// </summary>
        public static Dir[] GenRandomDirOrder()
        {
            Dir[] randDirOrder = new Dir[4]; // return array

            int[] allDirs = new int[4] { 0, 1, 2, 3 };

            // assign each direction a random "rank" (i.e., position in the return array)
            Dictionary<int, Dir> dirRanks = new Dictionary<int, Dir>();
            for (int i = 0; i < 4; i++)
            {
                Dir direction = Convert(i);
                int rank;
                do
                {
                    rank = PickRandom(allDirs); // generate random int
                } while (dirRanks.ContainsKey(rank)); // repeat if generated int is already taken
                dirRanks.Add(rank, direction);
            }

            // fill return array with information from the rank dictionary
            for (int i = 0; i < dirRanks.Count; i++)
            {
                randDirOrder[i] = dirRanks[i];
            }

            return randDirOrder;
        }

        /// <summary>
        /// Returns the Direction which corresponds to the passed coordinates.
        /// </summary>
        public static Dir GetDirByCoords(int oldX, int oldY, int newX, int newY)
        {
            int diffX = newX - oldX;
            int diffY = newY - oldY;

            if (diffX == 0 && diffY == -1)
            {
                return Dir.North;
            }
            else if (diffX == 1 && diffY == 0)
            {
                return Dir.East;
            }
            else if (diffX == 0 && diffY == 1)
            {
                return Dir.South;
            }
            else if (diffX == -1 && diffY == 0)
            {
                return Dir.West;
            }
            else
            {
                return Dir.North; // default
            }
        }
    }

}

