using Python3.clocks;
using Python3.data;
using Python3.game;
using Python3.tiles;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Python3.chars
{
    class Enemy : Char
    {
        public int Index; // for testing purposes
        public double Speed; // amount of moves Enemy can make within one second
        public bool Alive;
        public bool SpeedBoosted;
        public Enemy_Movement em;
        public EnemyAI ai;
        //public ST_BoostEnemy sb;

        public Enemy()
        {
            SetValidSpawn(); // Spawn at random position
            Init();
        }

        public Enemy(int gridX, int gridY)
        {
            X = gridX;
            Y = gridY;
            Init();
        }

        private void SetValidSpawn()
        {
            /*
             * Generate random spawn point. Don't spawn inside Player.
             */
            int xSpawn = DataUtils.GenRandomCoords()[0];
            int ySpawn = DataUtils.GenRandomCoords()[1];
            Tile tSpawn = DataUtils.GetTile(xSpawn, ySpawn);

            if (!tSpawn.CheckContent(typeof(Player)) && !tSpawn.CheckContent(typeof(Enemy)))
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
            Index = Game2.enemies.Count + 1;
            FaceDirection = DataUtils.GenRandomDir();
            Speed = Level.ESpeed;
            Alive = true;
            SpeedBoosted = false;
            Tile = DataUtils.GetTile(X, Y);
            Tile.Content.Add(this);
        }

        public void Move()
        {
            if (GameMain.Gamestate == GameState.Ingame && Alive)
            {
                FaceDirection = DataUtils.GetDirByCoords(X, Y, XAfterMove, YAfterMove); // turn

                X = XAfterMove; // move
                Y = YAfterMove; // move

                Tile.Content.Remove(this);
                Tile = TileAfterMove;
                Tile.Content.Add(this);

                if (Tile.CheckContent(typeof(Player))) Tile.KillEnemies();
            }
        }

        public void KillEnemy()
        {
            Alive = false;
            Game2.Hamstercount++;
            Game2.enemies.Remove(this);
            Tile.Content.Remove(this);
        }
    }
}
