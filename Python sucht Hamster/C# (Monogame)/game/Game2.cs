using Python3.chars;
using Python3.clocks;
using Python3.tiles;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Python3.game
{
    class Game2
    {
        public static Player p;
        public static List<Enemy> enemies = new List<Enemy>();

        public static int LevelNr;
        public static int MaxLevelAvailable = 12; // highest level that was won in current session
        public static bool IsBehindTheGame = false; // whether tutlevels show behind the game info

        public static bool IsFirstKeyPressInGame = true;

        public static int Hamstercount = 0; // number of devoured Hamsters
        public static int HamsterinflationCounter = 0; // counter for hamsterinflation()

        public static bool IsAltManual = false; // alternative, revamped manual -- not finished yet
        public static int Manualpage = 0; // manual page that is shown; for altmanual

        public static GameTimer_Clock tc; // GameTimer

        public static void StartNewGame()
        {
            Hamstercount = 0;
            HamsterinflationCounter = 0;
            Console.WriteLine("(Hamsterinflation) Reset HamsterinflationCounter to {0}.", HamsterinflationCounter);

            ResetGame(); // empty the grid

            new Level(LevelNr); // set level-specific variables

            new Grid(); // create the grid background and create all (functional, "invisible") tiles of the grid

            Spawn(); // generate new chars

            Tiles.GenerateSpecialTiles(); // generate Special Tiles

            new GameTimer(); // Zeitleiste

            GameMain.Gamestate = GameState.Ingame; // actually start the game (draw ingame elements etc.)
        }

        private static void ResetGame()
        {
            /*
             * Literally kill the existing chars
             */
            p = null;
            /*for (int i = 0; i < enemies.Count; i++)
            {
                enemies[i].em = null; // intended fix for hamsterinflation, doesn't work
            }*/
            enemies.Clear(); // Empty array of Enemies

            /*
             * Empty arrays of grid elements
             */
            Tiles.ClearAll();

            IsFirstKeyPressInGame = true;
        }

        public static void Spawn()
        {
            // Generate new chars

            if (LevelNr < 100)
            { // for regular levels: random placement
                p = new Player();
                for (int i = 0; i < Level.NEnemy; i++)
                {
                    enemies.Add(new Enemy());
                }
            }
            else
            { // for Tutorial levels: manual placement (coordinates are set in Level
              // properties)
                p = new Player(Level.PSpawn[0], Level.PSpawn[1]);
                enemies.Add(new Enemy(Level.ESpawn[0], Level.ESpawn[1]));
            }

        }

        public static void RestartLevel()
        {
            StopGame();
            StartNewGame();
        }

        public static void StopGame()
        {
            if (!IsFirstKeyPressInGame)
            {
                /*
                 * Cancel all clocks, in case they have already begun running
                 */

                if (LevelNr != 101 && LevelNr != 102) tc.Stop(); // stop GameTimer, but not in tutlvl1 & tutlvl2 (there is no GameTimer in these levels)

                for (int i = 0; i < enemies.Count; i++)
                {
                    Enemy e = enemies[i];
                    if (e.SpeedBoosted) e.em.SB_Timer.Stop();
                    e.em.Timer.Stop();
                }
            }
        }

        public static void StartClocks()
        {

            for (int i = 0; i < enemies.Count; i++)
            {
                Enemy e = enemies[i];
                e.em = new Enemy_Movement(e);
                e.em.Start(); // start movement of enemy
            }


            if (LevelNr != 101 && LevelNr != 102) // no timer in tut1 & tut2
            {
                // set timer count down according to level settings
                GameTimer.GameDuration = Level.GameDuration;

                // start timer count down
                tc = new GameTimer_Clock();
                tc.Start();
            }

            IsFirstKeyPressInGame = false;
        }

        public static void Hamsterinflation()
        {
            Console.WriteLine("There are {0} enemies.", enemies.Count);
            if (HamsterinflationCounter > enemies.Count)
            {
                Console.WriteLine("(Hamsterinflation) HamsterinflationCounter {0} > enemies.Count {1}.", HamsterinflationCounter, enemies.Count);
                if (enemies.Count < 64)
                { // don't spawn more than 63 Enemies
                    Enemy enemy = new Enemy();
                    //enemy.Speed = 10;
                    enemies.Add(enemy);
                    enemy.em = new Enemy_Movement(enemy);
                    enemy.em.Start();
                }
            }
            else
            {
                Console.WriteLine("(Hamsterinflation) HamsterinflationCounter {0} < enemies.Count {1}.", HamsterinflationCounter, enemies.Count);
            }
            HamsterinflationCounter++;
        }

    }
}
