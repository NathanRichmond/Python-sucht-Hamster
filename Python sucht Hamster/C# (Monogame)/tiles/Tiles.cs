using Python3.chars;
using Python3.data;
using Python3.game;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Python3.tiles
{
    /// <summary>
    /// This is the class which merely creates and stores Special Tiles and isn't used for anything else.
    /// </summary>
    static class Tiles
    {

        public static List<Wall> walls = new List<Wall>(); // global array list with all Walls
        public static List<Korn> korns = new List<Korn>(); // global array list with all Korns
        public static List<BabyhamsterTwo> babyhamsterTwos = new List<BabyhamsterTwo>(); // global array list with all BabyhamsterTwos
        public static List<BabyhamsterThree> babyhamsterThrees = new List<BabyhamsterThree>(); // global array list with all BabyhamsterThrees
        public static List<BabyhamsterFour> babyhamsterFours = new List<BabyhamsterFour>(); // global array list with all BabyhamsterFours
        public static List<Hourglass> hourglasses = new List<Hourglass>(); // global array list with all Hourglasses
        public static List<Hammer> hammers = new List<Hammer>(); // global array list with all Hammers

        public static void GenerateSpecialTiles()
        {
            // reset
            ClearAll();

            int nSpecialTiles = Level.NWall + Level.WallSpawn.Count
                                + Level.NKorn + Level.KornSpawn.Count
                                + Level.NBabyhamsterTwo + Level.BabyhamsterTwoSpawn.Count
                                + Level.NBabyhamsterThree + Level.BabyhamsterThreeSpawn.Count
                                + Level.NBabyhamsterFour + Level.BabyhamsterFourSpawn.Count
                                + Level.NHourglass + Level.HourglassSpawn.Count
                                + Level.NHammer + Level.HammerSpawn.Count;

            int nAvailableTiles = (Grid.Size[0] * Grid.Size[1]) - 1 - Level.NEnemy;

            if (nSpecialTiles <= nAvailableTiles)
            {
                GenerateWalls();
                GenerateKorns();
                GenerateBabyhamsterTwos();
                GenerateBabyhamsterThrees();
                GenerateBabyhamsterFours();
                GenerateHourglasses();
                GenerateHammers();
            }
            else
            {
                Console.WriteLine("\n------------- LEVEL {0} PROPERTIES ERROR! -------------", Game2.LevelNr);
                Console.WriteLine("Too many Special Tiles for this grid size!");
                Console.WriteLine("Adjust Level{0}() in Level.cs:\nDecrease total number of Special Tiles by {1} or increase grid size!",
                                  Game2.LevelNr, nSpecialTiles - nAvailableTiles);
                Console.WriteLine("-------------------------------------------------------\n");
            }
            
        }

        private static void GenerateWalls()
        {
            // manually placed walls
            for (int i = 0; i < Level.WallSpawn.Count; i++)
            {
                int[] coords = Level.WallSpawn[i];
                walls.Add(new Wall(coords[0], coords[1]));
            }

            // randomly placed walls
            int nAlreadyPlacedWalls = walls.Count;
            for (int i = 0; i < (Level.NWall - nAlreadyPlacedWalls); i++)
            {
                walls.Add(new Wall());
            }
        }

        private static void GenerateKorns()
        {
            // manually placed korns
            for (int i = 0; i < Level.KornSpawn.Count; i++)
            {
                int[] coords = Level.KornSpawn[i];
                korns.Add(new Korn(coords[0], coords[1]));
            }

            // randomly placed korns
            int nAlreadyPlacedKorns = korns.Count;
            for (int i = 0; i < (Level.NKorn - nAlreadyPlacedKorns); i++)
            {
                korns.Add(new Korn());
            }
        }

        private static void GenerateBabyhamsterTwos()
        {
            // manually placed babyhamsterTwos
            for (int i = 0; i < Level.BabyhamsterTwoSpawn.Count; i++)
            {
                int[] coords = Level.BabyhamsterTwoSpawn[i];
                babyhamsterTwos.Add(new BabyhamsterTwo(coords[0], coords[1]));
            }

            // randomly placed babyhamsterTwos
            int nAlreadyPlacedBabyhamsterTwos = babyhamsterTwos.Count;
            for (int i = 0; i < (Level.NBabyhamsterTwo - nAlreadyPlacedBabyhamsterTwos); i++)
            {
                babyhamsterTwos.Add(new BabyhamsterTwo());
            }
        }

        private static void GenerateBabyhamsterThrees()
        {
            // manually placed babyhamsterThrees
            for (int i = 0; i < Level.BabyhamsterThreeSpawn.Count; i++)
            {
                int[] coords = Level.BabyhamsterThreeSpawn[i];
                babyhamsterThrees.Add(new BabyhamsterThree(coords[0], coords[1]));
            }

            // randomly placed babyhamsterThrees
            int nAlreadyPlacedBabyhamsterThrees = babyhamsterThrees.Count;
            for (int i = 0; i < (Level.NBabyhamsterThree - nAlreadyPlacedBabyhamsterThrees); i++)
            {
                babyhamsterThrees.Add(new BabyhamsterThree());
            }
        }

        private static void GenerateBabyhamsterFours()
        {
            // manually placed babyhamsterFours
            for (int i = 0; i < Level.BabyhamsterFourSpawn.Count; i++)
            {
                int[] coords = Level.BabyhamsterFourSpawn[i];
                babyhamsterFours.Add(new BabyhamsterFour(coords[0], coords[1]));
            }

            // randomly placed babyhamsterFours
            int nAlreadyPlacedBabyhamsterFours = babyhamsterFours.Count;
            for (int i = 0; i < (Level.NBabyhamsterFour - nAlreadyPlacedBabyhamsterFours); i++)
            {
                babyhamsterFours.Add(new BabyhamsterFour());
            }
        }

        private static void GenerateHourglasses()
        {
            // manually placed hourglasses
            for (int i = 0; i < Level.HourglassSpawn.Count; i++)
            {
                int[] coords = Level.HourglassSpawn[i];
                hourglasses.Add(new Hourglass(coords[0], coords[1]));
            }

            // randomly placed hourglasses
            int nAlreadyPlacedHourglasss = hourglasses.Count;
            for (int i = 0; i < (Level.NHourglass - nAlreadyPlacedHourglasss); i++)
            {
                hourglasses.Add(new Hourglass());
            }
        }

        private static void GenerateHammers()
        {
            // manually placed hammers
            for (int i = 0; i < Level.HammerSpawn.Count; i++)
            {
                int[] coords = Level.HammerSpawn[i];
                hammers.Add(new Hammer(coords[0], coords[1]));
            }

            // randomly placed hammers
            int nAlreadyPlacedHammers = hammers.Count;
            for (int i = 0; i < (Level.NHammer - nAlreadyPlacedHammers); i++)
            {
                hammers.Add(new Hammer());
            }
        }


        public static void ClearAll()
        {
            walls.Clear();
            korns.Clear();
            babyhamsterTwos.Clear();
            babyhamsterThrees.Clear();
            babyhamsterFours.Clear();
            hourglasses.Clear();
            hammers.Clear();
        }

        /// <summary>
        /// Add a Wall to the List Tiles.walls, but only do so if the passed coordinates are within the grid and the tile is free of player/enemies.
        /// Also, remove any existing content on the tile.
        /// </summary>
        public static void AddWallSafe(int x, int y)
        {
            if (DataUtils.WithinGrid(x, y))
            {
                Tile tile = DataUtils.GetTile(x, y);
                if (!(tile.CheckContent(typeof(Enemy)) || tile.CheckContent(typeof(Player))))
                {
                    tile.RemoveAllContent();
                    walls.Add(new Wall(x, y));
                }
            }
        }
    }
}
