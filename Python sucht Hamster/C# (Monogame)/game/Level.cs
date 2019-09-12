using Python3.data;
using Python3.tiles;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Python3.game
{
    public class Level
    {
        public static int[] ESpawn = new int[2]; // coordinates of manually placed enemy
        public static int[] PSpawn = new int[2]; // coordinates of manually placed player

        public static int[] Gridsize = new int[2]; // widthxheight of the grid, in tiles
        public static int NEnemy; // number of Enemies
        public static double ESpeed; // speed of Enemies, i.e., moves per second. When set to 0, it's not really 0 (because long isn't infinite) but it would take the Enemies 293.274.701 years to make a move
        public static double GameDuration; // time in seconds until game is lost
        public static int PlayerMargin; // area (in tiles) around the player which the enemy won't enter

        public static bool Hamsterinflation; // activate Inflation?

        public static int NWall; // number of Walls
        public static List<int[]> WallSpawn = new List<int[]>(); // coordinates of manually placed Walls

        public static int NKorn; // number of Korn Tiles
        public static List<int[]> KornSpawn = new List<int[]>(); // coordinates of manually placed Korns
        public static double KornDuration; // duration of Korn effect in seconds. Minimum is 0.0167, everything lower than that has no effect
        public static double KornBoost; // factor by which Enemy base speed is increased

        public static int NBabyhamsterTwo; // number of Babyhamster Tiles with two hamsters
        public static List<int[]> BabyhamsterTwoSpawn = new List<int[]>(); // coordinates of manually placed BabyhamsterTwos
        public static int NBabyhamsterThree; // number of Babyhamster tiles with three hamsters
        public static List<int[]> BabyhamsterThreeSpawn = new List<int[]>(); // coordinates of manually placed BabyhamsterThrees
        public static int NBabyhamsterFour; // number of Babyhamster tiles with four hamsters
        public static List<int[]> BabyhamsterFourSpawn = new List<int[]>(); // coordinates of manually placed BabyhamsterFours

        public static int NHourglass; // number of Hourglass Tiles
        public static List<int[]> HourglassSpawn = new List<int[]>(); // coordinates of manually placed Hourglasses
        public static double HourglassEDuration; // duration of Hourglass effect (activated by Enemy) in seconds
        public static double HourglassEFactor; // factor by which time is increased
        public static double HourglassPDuration; // duration of Hourglass effect (activated by Player) in seconds
        public static double HourglassPFactor; // factor. Needs to be 0<factor<1 to have it slow down the time

        public static int NHammer; // number of Hammer Tiles.
        public static List<int[]> HammerSpawn = new List<int[]>(); // coordinates of manually placed Hammers

        public static int NEgg; // number of Eggs available to the Player

        public Level(int level)
        {
            ResetToDefaultProperties();
            ApplySpecificProperties(level);
        }

        private void ResetToDefaultProperties()
        {
            Gridsize[0] = 10;
            Gridsize[1] = 10;
            NEnemy = 1;
            ESpeed = 2.5;
            GameDuration = 10;
            PlayerMargin = -1;
            Hamsterinflation = false;
            NWall = 0;
            NKorn = 0;
            KornDuration = 1;
            KornBoost = 2;
            NBabyhamsterTwo = 0;
            NBabyhamsterThree = 0;
            NBabyhamsterFour = 0;
            NHourglass = 0;
            HourglassEDuration = 0.5;
            HourglassEFactor = 2;
            HourglassPDuration = 0.5;
            HourglassPFactor = 0.5;
            NHammer = 0;
            NEgg = 0;
        }

        private void ApplySpecificProperties(int level)
        {
            switch (level)
            {
                case 1:
                    Level1();
                    break;
                case 2:
                    Level2();
                    break;
                case 3:
                    Level3();
                    break;
                case 4:
                    Level4();
                    break;
                case 5:
                    Level5();
                    break;
                case 6:
                    Level6();
                    break;
                case 7:
                    Level7();
                    break;
                case 8:
                    Level8();
                    break;
                case 9:
                    Level9();
                    break;
                case 10:
                    Level10();
                    break;
                case 11:
                    Level11();
                    break;
                case 12:
                    Level12();
                    break;

                case 101:
                    Level101();
                    break;
                case 102:
                    Level102();
                    break;
                case 103:
                    Level103();
                    break;
                case 104:
                    Level104();
                    break;
                case 105:
                    Level105();
                    break;
                case 106:
                    Level106();
                    break;
                case 107:
                    Level107();
                    break;
                case 108:
                    Level108();
                    break;
            }

            if (PlayerMargin == -1) // only calculate if not already set to a specific value
            {
                PlayerMargin = DataUtils.CalcPlayerMargin();
            }
        }


        /*
         * Properties for the individual Levels:
         */
        private static void Level1() // "Die Jagd beginnt..."
        {
            GameDuration = 300;
            NEgg = 3;
        }

        private static void Level2() // "Um Mauern schlängeln"
        {
            ESpeed = 5.5;
            GameDuration = 8.5;
            NWall = 12;
        }

        private static void Level3() // "Wie eine Fliege"
        {
            Gridsize[0] = 8;
            Gridsize[1] = 5;
            ESpeed = 16;
            GameDuration = 6;
            NWall = 15;
            NKorn = 2;
            KornDuration = 2;
            KornBoost = 3;
        }

        private static void Level4() // "Im Labyrinth"
        {
            Gridsize[0] = 32;
            Gridsize[1] = 20;
            ESpeed = 6.6;
            GameDuration = 13;
            NWall = 250;
        }

        private static void Level5() // "Chronismus"
        {
            Gridsize[0] = 10;
            Gridsize[1] = 10;
            ESpeed = 5;
            GameDuration = 10;
            NHourglass = 50;
            HourglassEDuration = 1;
            HourglassEFactor = 10;
            HourglassPDuration = 1;
            HourglassPFactor = 0.3;
        }

        private static void Level6() // "Im Silo"
        {
            Gridsize[0] = 32;
            Gridsize[1] = 20;
            NEnemy = 5;
            ESpeed = 4;
            GameDuration = 35;
            PlayerMargin = 4;
            NKorn = 550;
            KornDuration = 0.175;
            KornBoost = 5;
        }

        private static void Level7() // "Familientreffen"
        {
            Gridsize[0] = 15;
            Gridsize[1] = 8;
            NEnemy = 30;
            ESpeed = 1.5;
            GameDuration = 18;
            NWall = 10;
        }

        private static void Level8() // "Zugemauert"
        {
            Gridsize[0] = 32;
            Gridsize[1] = 20;
            GameDuration = 20;
            PlayerMargin = 4;
            NWall = 200;
            NKorn = 125;
            KornDuration = 0.75;
            KornBoost = 5;
            NHammer = 100;
        }

        private static void Level9() // "Das volle Programm"
        {
            Gridsize[0] = 32;
            Gridsize[1] = 20;
            ESpeed = 4;
            GameDuration = 20;
            NWall = 125;
            NKorn = 100;
            KornBoost = 3;
            NBabyhamsterTwo = 30;
            NBabyhamsterThree = 25;
            NBabyhamsterFour = 20;
            NHourglass = 50;
            HourglassEDuration = 1;
            HourglassEFactor = 3;
            HourglassPDuration = 1;
            HourglassPFactor = 0.5;
            NHammer = 75;
        }

        private static void Level10() // "Baustelle"
        {
            Gridsize[0] = 20;
            Gridsize[1] = 20;
            GameDuration = 99;
            NWall = 25;
            NHammer = 140;
        }

        private static void Level11() // "Im Hamsternest"
        {
            Gridsize[0] = 32;
            Gridsize[1] = 20;
            NEnemy = 10;
            GameDuration = 40;
            NWall = 50;
            NKorn = 20;
            KornBoost = 3;
            NBabyhamsterTwo = 120;
            NBabyhamsterThree = 120;
            NBabyhamsterFour = 100;
        }

        private static void Level12() // "Hamsterinflation"
        {
            Gridsize[0] = 20;
            Gridsize[1] = 20;
            ESpeed = 15;
            GameDuration = 15;
            Hamsterinflation = true;
        }

        private static void Level101()
        {
            Gridsize[0] = 15;
            Gridsize[1] = 1;
            ESpeed = 0;
            PSpawn[0] = 0;
            PSpawn[1] = 0;
            ESpawn[0] = 14;
            ESpawn[1] = 0;
        }

        private static void Level102()
        {
            PSpawn[0] = 0;
            PSpawn[1] = 0;
            ESpawn[0] = 9;
            ESpawn[1] = 9;
        }

        private static void Level103()
        {
            PSpawn[0] = 0;
            PSpawn[1] = 0;
            ESpawn[0] = 9;
            ESpawn[1] = 9;
        }

        private static void Level104()
        {
            Gridsize[0] = 8;
            Gridsize[1] = 5;
            PSpawn[0] = 1;
            PSpawn[1] = 2;
            ESpawn[0] = 6;
            ESpawn[1] = 2;
            WallSpawn.Add(new int[] { 4, 1 });
            WallSpawn.Add(new int[] { 4, 2 });
            WallSpawn.Add(new int[] { 4, 3 });
        }

        private static void Level105()
        {
            Gridsize[0] = 8;
            Gridsize[1] = 8;
            PSpawn[0] = 1;
            PSpawn[1] = 0;
            ESpawn[0] = 4;
            ESpawn[1] = 0;
            ESpeed = 4;
            for (int i = 2; i <= 5; i++)
            {
                WallSpawn.Add(new int[] { 2, i });
                WallSpawn.Add(new int[] { 5, i });
            }
            WallSpawn.Add(new int[] { 3, 2 });
            WallSpawn.Add(new int[] { 3, 5 });
            WallSpawn.Add(new int[] { 4, 2 });
            WallSpawn.Add(new int[] { 4, 5 });
            KornBoost = 2;
            KornSpawn.Add(new int[] { 3, 0 });
            KornSpawn.Add(new int[] { 3, 1 });
            KornSpawn.Add(new int[] { 5, 0 });
            KornSpawn.Add(new int[] { 5, 1 });
            KornSpawn.Add(new int[] { 7, 5 });
            KornSpawn.Add(new int[] { 0, 6 });
        }

        private static void Level106()
        {
            Gridsize[0] = 15;
            Gridsize[1] = 8;
            PSpawn[0] = 0;
            PSpawn[1] = 7;
            ESpawn[0] = 14;
            ESpawn[1] = 2;
            GameDuration = 20;
            for (int i = 0; i <= 7; i++)
            {
                if (i != 2 && i != 3)
                    WallSpawn.Add(new int[] { 3, i });
            }
            for (int i = 0; i <= 7; i++)
            {
                if (i != 5)
                    WallSpawn.Add(new int[] { 7, i });
            }
            WallSpawn.Add(new int[] { 10, 0 });
            for (int i = 3; i <= 7; i++)
            {
                WallSpawn.Add(new int[] { 10, i });
            }
            HourglassEFactor = 3;
            HourglassEDuration = 2;
            HourglassPFactor = 0.25;
            HourglassPDuration = 1.75;
            HourglassSpawn.Add(new int[] { 3, 2 });
            HourglassSpawn.Add(new int[] { 3, 3 });
            HourglassSpawn.Add(new int[] { 7, 5 });
            HourglassSpawn.Add(new int[] { 10, 1 });
            HourglassSpawn.Add(new int[] { 10, 2 });
            HourglassSpawn.Add(new int[] { 13, 2 });
            HourglassSpawn.Add(new int[] { 14, 1 });
            HourglassSpawn.Add(new int[] { 14, 3 });
            // playerMargin: 12 tiles
        }

        private static void Level107()
        {
            Gridsize[0] = 14;
            Gridsize[1] = 3;
            PSpawn[0] = 0;
            PSpawn[1] = 2;
            ESpawn[0] = 11;
            ESpawn[1] = 2;
            for (int i = 1; i <= 5; i++)
            {
                WallSpawn.Add(new int[] { i, 1 });
                WallSpawn.Add(new int[] { i, 2 });
            }
            WallSpawn.Add(new int[] { 7, 0 });
            for (int i = 7; i <= 13; i++)
            {
                WallSpawn.Add(new int[] { i, 1 });
            }
            BabyhamsterTwoSpawn.Add(new int[] { 7, 2 });
            BabyhamsterTwoSpawn.Add(new int[] { 10, 2 });
            BabyhamsterTwoSpawn.Add(new int[] { 11, 0 });
            BabyhamsterThreeSpawn.Add(new int[] { 1, 0 });
            BabyhamsterThreeSpawn.Add(new int[] { 13, 2 });
            BabyhamsterFourSpawn.Add(new int[] { 9, 0 });
        }

        private static void Level108()
        {
            Gridsize[0] = 12;
            Gridsize[1] = 5;
            PSpawn[0] = 0;
            PSpawn[1] = 2;
            ESpawn[0] = 9;
            ESpawn[1] = 2;
            for (int y = 0; y < 5; y++)
            {
                if (y != 2)
                { // leave space at y=2
                    for (int x = 0; x < 5; x++)
                    {
                        WallSpawn.Add(new int[] { x, y });
                    }
                }
            }
            for (int i = 0; i <= 4; i++)
            {
                WallSpawn.Add(new int[] { 8, i });
            }
            HammerSpawn.Add(new int[] { 5, 2 });
            HammerSpawn.Add(new int[] { 6, 0 });
            HammerSpawn.Add(new int[] { 11, 2 });
        }


    }
}
