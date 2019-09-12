using Python3.data;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Python3.tiles
{
    /// <summary>
    /// Abstract class all Special Tiles inherit from. Contains basic properties.
    /// </summary>
    abstract class SpecialTile
    {
        public int X, Y; // coordinates within the grid
        public Tile Tile;
        public bool Alive;

        public SpecialTile()
        {
            SetValidSpawn(); // Spawn at random position
            Init();
        }

        public SpecialTile(int gridX, int gridY)
        {
            if (DataUtils.WithinGrid(gridX, gridY))
            {
                X = gridX;
                Y = gridY;
                Init();
            }
        }

        private void SetValidSpawn()
        {
            /*
             * Generate random spawn point. Only spawn on empty tiles.
             */
            int xSpawn = DataUtils.GenRandomCoords()[0];
            int ySpawn = DataUtils.GenRandomCoords()[1];
            Tile tSpawn = DataUtils.GetTile(xSpawn, ySpawn);

            if (tSpawn.NoContent())
            {
                X = xSpawn;
                Y = ySpawn;
            }
            else
            {
                SetValidSpawn();
            }
        }

        private void Init()
        {
            Alive = true;
            Tile = DataUtils.GetTile(X, Y);
            Tile.Content.Add(this);
        }

        public virtual void Activate()
        {
            Alive = false;
            Tile.Content.Remove(this);
        }
    }

    class Wall : SpecialTile
    {
        public Wall() : base() { }
        public Wall(int gridX, int gridY) : base(gridX, gridY) { }
    }

}
