using Python3.chars;
using Python3.game;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Python3.tiles
{
    /// <summary>
    /// This is the actual (functional) Tile class.
    /// </summary>
    class Tile
    {
        public int X { get; set; }
        public int Y { get; set; }
        public int GridX { get; set; } // coordinates within the grid
        public int GridY { get; set; }

        /*
         * Content: List of objects which are currently positioned on this tile (e.g. player, enemy, wall, etc.)
         * Will be monitored and updated by Updater.cs in order to ensure only one type of object is on the tile at once.
         */
        public List<object> Content = new List<object>();


        public Tile(int gridX, int gridY)
        {
            Init(gridX, gridY);
        }

        private void Init(int gridX, int gridY)
        {
            GridX = gridX;
            GridY = gridY;
            X = gridX * (GameMain.Tile - 1) + Grid.X;
            Y = gridY * (GameMain.Tile - 1) + Grid.Y;
        }

        /// <summary>
        /// Checks whether the current Content of the Tile contains an instance of the specified Type.
        /// </summary>
        /// <param name="type">The Type to check against. Make sure to wrap it in typeof(Type)!</param>
        public bool CheckContent(Type type)
        {
            for (int i = 0; i < Content.Count; i++)
            {
                if (Content[i].GetType() == type)
                {
                    return true;
                }
            }
            return false;
        }

        /// <summary>
        /// Checks whether the Content property of the Tile is empty.
        /// </summary>
        /// <returns></returns>
        public bool NoContent() => Content.Count == 0;

        /// <summary>
        /// Checks whether the Content of the Tile is a Special Tile.
        /// </summary>
        public bool ContentIsSpecialTile()
        {
            bool flag = false;
            for (int i = 0; i < Content.Count; i++)
            {
                flag = Content[i].GetType() == typeof(Korn)
                       || Content[i].GetType() == typeof(BabyhamsterTwo)
                       || Content[i].GetType() == typeof(BabyhamsterThree)
                       || Content[i].GetType() == typeof(BabyhamsterFour)
                       || Content[i].GetType() == typeof(Hourglass)
                       || Content[i].GetType() == typeof(Hammer);
                if (flag) break;
            }
            return flag;
        }

        /// <summary>
        /// Clears the entire content of the tile.
        /// </summary>
        public void RemoveAllContent()
        {
            Content.Clear();
        }

        /// <summary>
        /// Kills all enemies which are currently on the Tile.
        /// </summary>
        public void KillEnemies()
        {
            // determine enemies to be killed
            List<Enemy> enemiesToBeKilled = new List<Enemy>();
            for (int j = 0; j < Game2.enemies.Count; j++)
            {
                if (Content.Contains(Game2.enemies[j])) enemiesToBeKilled.Add(Game2.enemies[j]);
            }
            // kill enemies
            for (int j = 0; j < enemiesToBeKilled.Count; j++)
            {
                enemiesToBeKilled[j].KillEnemy();
            }
        }

    }
}
