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
    class EnemyAI
    {
        private Player p = Game2.p;
        private Enemy e;

        private int x; // x coordinate after move
        private int y; // y coordinate after move
        private Tile t; // tile after move

        public EnemyAI(Enemy enemy) => e = enemy;

        public void AI_Move()
        {
            MakeSemiSensibleMove();
        }

        private void SetCoords(Dir direction)
        {
            x = e.X;
            y = e.Y;
            switch (direction)
            {
                case Dir.North:
                    y--;
                    break;
                case Dir.East:
                    x++;
                    break;
                case Dir.South:
                    y++;
                    break;
                case Dir.West:
                    x--;
                    break;
            }
        }

        private bool IsValidMove()
        {
            if (DataUtils.WithinGrid(x, y))
            {
                t = DataUtils.GetTile(x, y);
                if (!t.CheckContent(typeof(Wall))
                    && !t.CheckContent(typeof(Player))
                    && !t.CheckContent(typeof(BabyhamsterTwo))
                    && !t.CheckContent(typeof(BabyhamsterThree))
                    && !t.CheckContent(typeof(BabyhamsterFour)))
                {
                    return true;
                }
            }
            return false;
        }

        private void MakeSemiSensibleMove()
        {
            /*
            // old function start
            if (DataUtils.WithinGrid(x, y))
            {

                if (NearPlayer() && NearPlayerAfterMove())
                { // if leaving pM with the generated direction isn't possible
                    LeavePlayerMargin();
                }
                else
                {
                    if (NearPlayerAfterMove())
                    { // always try to leave playerMargin
                        MakeRandomMove();
                    }
                }
            }
            // old function end
            */

            if (!NearPlayer())
            {
                /*
                 * Enemy is not near Player.
                 * Thus, Enemy makes a random move, but avoids the playerMargin, if possible.
                 */
                AvoidPlayerMargin();
            }
            else
            {
                /*
                 * Enemy is near Player.
                 * Thus, Enemy attemps to leave the playerMargin (depending on its position relative to the Player), if possible.
                 */
                LeavePlayerMargin();
            }
        }

        private void AvoidPlayerMargin()
        {
            bool stuck = false;

            Dir[] randDirs = DataUtils.GenRandomDirOrder(); // generate random Dirs order

            List<Dir> invalidDirs = new List<Dir>(); // all Dirs which are not validMove
            List<Dir> nearPlayerAfterMoveDirs = new List<Dir>(); // all Dirs which are validMove but nearPlayerAfterMove

            for (int i = 0; i < 4; i++)
            { // cycle through the randDirs and add them to the Lists above if necessary
                Dir randDir = randDirs[i];
                SetCoords(randDir);
                if (!IsValidMove())
                {
                    invalidDirs.Add(randDir);
                }
                else if (NearPlayerAfterMove())
                {
                    nearPlayerAfterMoveDirs.Add(randDir);
                }
            }

            if (invalidDirs.Count < 4)
            { // if not all Dirs are invalid
                bool foundValidDir = false;
                // then try to get one which is validMove && !nearPlayerAfterMove
                for (int i = 0; i < 4; i++)
                { // cycle through the randDirs
                    Dir randDir = randDirs[i];
                    SetCoords(randDir);
                    foundValidDir = !invalidDirs.Contains(randDir) && !nearPlayerAfterMoveDirs.Contains(randDir); // if Dir is validMove && !nearPlayerAfterMove
                    if (foundValidDir) break;
                }

                if (!foundValidDir)
                { // no Dir is validMove && !nearPlayerAfterMove?
                  // then pick a Dir from the ones which are at least validMove (means entering the playerMargin, but that's inevitable at this point)

                    for (int i = 0; i < 4; i++)
                    { // cycle through the randDirs
                        Dir randDir = randDirs[i];
                        SetCoords(randDir);
                        if (!invalidDirs.Contains(randDir)) break; // skip the invalid Dirs
                    }
                }

                // Enemy has determined a valid Dir and set coords for that, can now make the real move
            }
            else // all Dirs are invalid -> stuck
            {
                stuck = true;
            }

            MakeMove(stuck);

        }

        private void LeavePlayerMargin()
        {
            // Detect position relative to player
            bool north = e.Y < p.Y;
            bool east = e.X > p.X;
            bool south = e.Y > p.Y;
            bool west = e.X < p.X;

            int pos = 0;
            /*
             * int pos:
             * 0 = North. 1 = North-east. 2 = East. 3 = South-east. 4 = South.
             * 5 = South-west. 6 = West. 7 = North-west.
             */

            if (north && !east && !west) // pos = 0
            {
                Dir[] prefDirs = new Dir[4] { Dir.North, Dir.East, Dir.West, Dir.South };
                LeavePlayerMargin1(prefDirs);
            }
            else if (north && east) // pos = 1
            {
                Dir[] prefDirs = new Dir[4] { Dir.North, Dir.East, Dir.West, Dir.South };
                LeavePlayerMargin2(prefDirs);
            }
            else if (east && !north && !south) // pos = 2
            {
                Dir[] prefDirs = new Dir[4] { Dir.East, Dir.North, Dir.South, Dir.West };
                LeavePlayerMargin1(prefDirs);
            }
            else if (south && east) // pos = 3
            {
                Dir[] prefDirs = new Dir[4] { Dir.South, Dir.East, Dir.North, Dir.West };
                LeavePlayerMargin2(prefDirs);
            }
            else if (south && !east && !west) // pos = 4
            {
                Dir[] prefDirs = new Dir[4] { Dir.South, Dir.East, Dir.West, Dir.North };
                LeavePlayerMargin1(prefDirs);
            }
            else if (south && west) // pos = 5
            {
                Dir[] prefDirs = new Dir[4] { Dir.South, Dir.West, Dir.East, Dir.North };
                LeavePlayerMargin2(prefDirs);
            }
            else if (west && !north && !south) // pos = 6
            {
                Dir[] prefDirs = new Dir[4] { Dir.West, Dir.North, Dir.South, Dir.East };
                LeavePlayerMargin1(prefDirs);
            }
            else if (north && west) // pos = 7
            {
                Dir[] prefDirs = new Dir[4] { Dir.North, Dir.West, Dir.South, Dir.East };
                LeavePlayerMargin2(prefDirs);
            }

            /*
             * Generate random move number that won't move enemy towards player.
             * Parameters: dir1 (preferred direction), dir2, dir3 (pick one random from these).
             * If pos == 1 || 3 || 5 || 7, then dir1 = 4 (don't care)
             */
            /*
           switch (pos)
           {
               case 0: // North
                   LeavePlayerMargin(0, 1, 3); // pref. dir N, else E/W
                   break;
               case 1: // North-east
                   LeavePlayerMargin(4, 0, 1); // N/E
                   break;
               case 2: // East
                   LeavePlayerMargin(1, 0, 2); // pref. dir E, else N/S
                   break;
               case 3: // South-east
                   LeavePlayerMargin(4, 1, 2); // E/S
                   break;
               case 4: // South
                   LeavePlayerMargin(2, 1, 3); // pref. dir S, else E/W
                   break;
               case 5: // South-west
                   LeavePlayerMargin(4, 2, 3); // S/W
                   break;
               case 6: // West
                   LeavePlayerMargin(3, 0, 2); // pref. dir W, else N/S
                   break;
               case 7: // North-west
                   LeavePlayerMargin(4, 0, 3); // W/N
                   break;
           }
           */
        }

        /// <summary>
        /// Cycles through the passed array, treats second and third Direction equally (randomizes which one of those is checked first)
        /// </summary>
        /// <param name="prefDirs">The array with the Directions ordered by preference: preferredDir, altDir1, altDir2, badDir.</param>
        private void LeavePlayerMargin1(Dir[] prefDirs)
        {
            bool stuck = false;
            bool checkBadDir = false;

            SetCoords(prefDirs[0]);
            if (!IsValidMove()) // preferred dir is invalid, try next two
            {
                if (DataUtils.r.Next(2) == 0) // randomize which one is checked first
                {
                    SetCoords(prefDirs[1]);
                    if (!IsValidMove()) // is invalid, try next now
                    {
                        SetCoords(prefDirs[2]);
                        if (!IsValidMove()) // both are invalid
                        {
                            checkBadDir = true;
                        }
                    }
                }
                else
                {
                    SetCoords(prefDirs[2]);
                    if (!IsValidMove()) // is invalid, try next now
                    {
                        SetCoords(prefDirs[1]);
                        if (!IsValidMove()) // both are invalid
                        {
                            checkBadDir = true;
                        }
                    }
                }

                if (checkBadDir) // second and third are invalid
                {
                    SetCoords(prefDirs[3]);
                    if (!IsValidMove()) // is also invalid
                    {
                        stuck = true;
                    }
                }
            }

            MakeMove(stuck);
        }

        /// <summary>
        /// Cycles through the passed array, treats first and second and third and fourth Directions equally (randomizes which one of those is checked first)
        /// </summary>
        /// <param name="prefDirs">The array with the Directions ordered by preference: preferredDir1, preferredDir2, badDir1, badDir2.</param>
        private void LeavePlayerMargin2(Dir[] prefDirs)
        {
            bool stuck = false;
            bool checkBadDirs = false;

            if (DataUtils.r.Next(2) == 0) // randomize which one is checked first
            {
                SetCoords(prefDirs[0]);
                if (!IsValidMove()) // is invalid, try next now
                {
                    SetCoords(prefDirs[1]);
                    if (!IsValidMove()) // both are invalid
                    {
                        checkBadDirs = true;
                    }
                }
            }
            else
            {
                SetCoords(prefDirs[1]);
                if (!IsValidMove()) // is invalid, try next now
                {
                    SetCoords(prefDirs[0]);
                    if (!IsValidMove()) // both are invalid
                    {
                        checkBadDirs = true;
                    }
                }
            }

            if (checkBadDirs) // first and second are invalid
            {
                if (DataUtils.r.Next(2) == 0) // randomize which one is checked first
                {
                    SetCoords(prefDirs[2]);
                    if (!IsValidMove()) // is invalid, try next now
                    {
                        SetCoords(prefDirs[3]);
                        if (!IsValidMove()) // both are invalid
                        {
                            stuck = true;
                        }
                    }
                }
                else
                {
                    SetCoords(prefDirs[3]);
                    if (!IsValidMove()) // is invalid, try next now
                    {
                        SetCoords(prefDirs[2]);
                        if (!IsValidMove()) // both are invalid
                        {
                            stuck = true;
                        }
                    }
                }
            }

            MakeMove(stuck);
        }

        private void MakeMove(bool stuck)
        {
            if (stuck)
            {
                // all Dirs are invalid, can't move – just make a random turn
                Dir randDir;
                do
                {
                    randDir = DataUtils.GenRandomDir();
                } while (e.FaceDirection == randDir); // make a real turn, i.e., do not keep the current FaceDirection
                e.FaceDirection = randDir; // 
            }
            else
            {
                // Enemy has determined a valid Dir and set coords for that, can now make the real move
                e.XAfterMove = x;
                e.YAfterMove = y;
                e.TileAfterMove = DataUtils.GetTile(x, y);
                e.Move();
            }
        }


        // check if coordinates are within playerMargin
        private bool NearPlayer(int ex, int ey)
        {
            bool x = ex >= (p.X - Level.PlayerMargin) && ex <= (p.X + Level.PlayerMargin);
            bool y = ey >= (p.Y - Level.PlayerMargin) && ey <= (p.Y + Level.PlayerMargin);
            return x && y;
        }

        private bool NearPlayer() => NearPlayer(e.X, e.Y);

        private bool NearPlayerAfterMove() => NearPlayer(x, y);

    }
}
