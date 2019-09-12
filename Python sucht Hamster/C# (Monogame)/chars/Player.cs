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
    class Player : Char
    {
        private int steps;

        public Player()
        {
            SetValidSpawn(); // spawns at random position
            Init();
        }

        public Player(int gridX, int gridY)
        {
            X = gridX;
            Y = gridY;
            Init();
        }

        private void SetValidSpawn()
        {
            /*
             * Generate random spawn point. No collision check needed whatsoever because Player spawns first.
             */
            X = DataUtils.GenRandomCoords()[0];
            Y = DataUtils.GenRandomCoords()[1];
        }

        private void Init()
        {
            FaceDirection = Dir.North;
            Tile = DataUtils.GetTile(X, Y);
            Tile.Content.Add(this);
        }

        public void Move(Dir direction)
        {
            if (GameMain.Gamestate == GameState.Ingame)
            {
                FaceDirection = direction; // turn
                SetCoordsAfterMove(direction);

                if (IsValidMove())
                {
                    X = XAfterMove; // move
                    Y = YAfterMove; // move

                    Tile.Content.Remove(this);
                    Tile = TileAfterMove;
                    Tile.Content.Add(this);

                    Tile.KillEnemies();

                    steps++;
                }
            }
        }

        private void SetCoordsAfterMove(Dir direction)
        {
            XAfterMove = X;
            YAfterMove = Y;

            switch (direction)
            {
                case Dir.North: // move upwards
                    YAfterMove = Y - 1;
                    break;
                case Dir.East: // move to the right
                    XAfterMove = X + 1;
                    break;
                case Dir.South: // move downwards
                    YAfterMove = Y + 1;
                    break;
                case Dir.West: // move to the left
                    XAfterMove = X - 1;
                    break;
            }

        }

        private bool IsValidMove()
        {
            bool valid = false;

            TileAfterMove = DataUtils.GetTile(XAfterMove, YAfterMove);

            if (TileAfterMove != null)
            {
                valid = !TileAfterMove.CheckContent(typeof(Wall)) /*&& !TileAfterMove.CheckContent(typeof(Egg)*/; // no collision with Wall/Egg
            }
            return valid;
        }

        public void PlaceEgg()
        {
            /*
            //		System.out.println("Entered function Player.placeEgg().");
            //		System.out.println("SpecialTile_Creation.getEggAmount(): " + SpecialTile_Creation.getEggAmount());
            //		System.out.println("Game.getnEgg(): " + Game.getnEgg());
            if (SpecialTile_Creation.getEggAmount() < Game.getnEgg())
            {
                //			System.out.println(SpecialTile_Creation.getEggAmount() + " <= " + Game.getnEgg());
                SpecialTile_Creation.specialtiles.add(new SpecialTile(X, Y, "egg"));
            }
            */
        }
    }
 
}
