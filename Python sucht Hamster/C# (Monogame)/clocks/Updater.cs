using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Python3.chars;
using Python3.game;
using Python3.tiles;

namespace Python3.clocks
{
    public static class Updater
    {
        // all timers
        public static List<Timer> Timers = new List<Timer>();

        // time while in game
        private static long currentTimeMillis = 0;

        // gamestate cache
        private static GameState gs = GameState.Tutorialmenu;

        // debug: firstkeypressingame cache
        private static bool firstKeyPress = Game2.IsFirstKeyPressInGame;


        private static Player p = Game2.p;
        private static List<Enemy> enemies = Game2.enemies;


        public static void Update(GameTime gameTime)
        {
            currentTimeMillis = (long)gameTime.TotalGameTime.TotalMilliseconds;


            /*
             * Update timers
             */
            if (Timers.Count > 0) UpdateTimers();

            /*
             * Update GameState (only for debugging)
             */
            if (GameMain.Gamestate != gs) UpdateGameState();

            /*
             * Update other in-game elements
             */
            if (gs == GameState.Ingame || gs == GameState.Pause)
            {
                p = Game2.p;
                enemies = Game2.enemies;

                // Activate special tiles, in case someone is atop one
                ActivateSpecialTiles();

                // Check if all enemies are dead and win game if yes 
                WinGame();

                // Debug: Notify about Game2.IsFirstKeyPressInGame changes
                if (firstKeyPress != Game2.IsFirstKeyPressInGame)
                {
                    Console.Write("IsFirstKeyPressInGame = " + Game2.IsFirstKeyPressInGame + " (game is ");
                    if (Game2.IsFirstKeyPressInGame) Console.Write("not ");
                    Console.WriteLine("running)");
                    firstKeyPress = Game2.IsFirstKeyPressInGame;
                }

            }
        }

        private static void UpdateTimers()
        {
            for (int i = 0; i < Timers.Count; i++)
            {
                Timer t = Timers[i];
                if (t.Running)
                {
                    t.UpdateTime(currentTimeMillis);
                }
            }
        }

        private static void UpdateGameState()
        {
            GameState gsNew = GameMain.Gamestate;
            Console.WriteLine("Switched Gamestate from {0} to {1}.", gs, gsNew); // just for debugging

            gs = gsNew;
        }

        private static void WinGame()
        {
            if (Game2.enemies.Count <= 0)
            {
                GameMain.Gamestate = GameState.Victory;

                if (Game2.LevelNr < 100 && Game2.LevelNr >= Game2.MaxLevelAvailable) // only for regular levels (not for Tutorial levels) and only if this is the highest level won until now
                {
                    Game2.MaxLevelAvailable = Game2.LevelNr + 1;
                }
            }
        }

        private static void ActivateSpecialTiles()
        {
            /*
             * Player
             */
            for (int i = 0; i < p.Tile.Content.Count; i++)
            {
                // switch doesn't work because we're comparing to Types
                if (p.Tile.Content[i].GetType() == typeof(Korn))
                {
                    var korns = Tiles.korns.Where(x => x.Tile == p.Tile); // this is LINQ
                    foreach (var korn in korns)
                    {
                        korn.Activate();
                    }
                }
                else if (p.Tile.Content[i].GetType() == typeof(BabyhamsterTwo))
                {
                    var babyhamsterTwos = Tiles.babyhamsterTwos.Where(x => x.Tile == p.Tile);
                    foreach (var babyhamsterTwo in babyhamsterTwos)
                    {
                        babyhamsterTwo.Activate();
                    }
                }
                else if (p.Tile.Content[i].GetType() == typeof(BabyhamsterThree))
                {
                    var babyhamsterThrees = Tiles.babyhamsterThrees.Where(x => x.Tile == p.Tile);
                    foreach (var babyhamsterThree in babyhamsterThrees)
                    {
                        babyhamsterThree.Activate();
                    }
                }
                else if (p.Tile.Content[i].GetType() == typeof(BabyhamsterFour))
                {
                    var babyhamsterFours = Tiles.babyhamsterFours.Where(x => x.Tile == p.Tile);
                    foreach (var babyhamsterFour in babyhamsterFours)
                    {
                        babyhamsterFour.Activate();
                    }
                }
                else if (p.Tile.Content[i].GetType() == typeof(Hourglass))
                {
                    var hourglasses = Tiles.hourglasses.Where(x => x.Tile == p.Tile);
                    foreach (var hourglass in hourglasses)
                    {
                        hourglass.Activate(p);
                    }
                }
                else if (p.Tile.Content[i].GetType() == typeof(Hammer))
                {
                    var hammers = Tiles.hammers.Where(x => x.Tile == p.Tile);
                    foreach (var hammer in hammers)
                    {
                        hammer.Activate(p);
                    }
                }
            }

            /*
             * Enemy
             */
            for (int i = 0; i < enemies.Count; i++)
            {
                Enemy e = enemies[i];

                for (int j = 0; j < e.Tile.Content.Count; j++)
                {
                    // switch doesn't work because we're comparing to Types
                    if (e.Tile.Content[j].GetType() == typeof(Korn))
                    {
                        var korns = Tiles.korns.Where(x => x.Tile == e.Tile);
                        foreach (var korn in korns)
                        {
                            korn.Activate(e);
                        }
                    }
                    else if (e.Tile.Content[j].GetType() == typeof(BabyhamsterTwo))
                    {
                        var babyhamsterTwos = Tiles.babyhamsterTwos.Where(x => x.Tile == e.Tile);
                        foreach (var babyhamsterTwo in babyhamsterTwos)
                        {
                            babyhamsterTwo.Activate();
                        }
                    }
                    else if (e.Tile.Content[j].GetType() == typeof(BabyhamsterThree))
                    {
                        var babyhamsterThrees = Tiles.babyhamsterThrees.Where(x => x.Tile == e.Tile);
                        foreach (var babyhamsterThree in babyhamsterThrees)
                        {
                            babyhamsterThree.Activate();
                        }
                    }
                    else if (e.Tile.Content[j].GetType() == typeof(BabyhamsterFour))
                    {
                        var babyhamsterFours = Tiles.babyhamsterFours.Where(x => x.Tile == e.Tile);
                        foreach (var babyhamsterFour in babyhamsterFours)
                        {
                            babyhamsterFour.Activate();
                        }
                    }
                    else if (e.Tile.Content[j].GetType() == typeof(Hourglass))
                    {
                        var hourglasses = Tiles.hourglasses.Where(x => x.Tile == e.Tile);
                        foreach (var hourglass in hourglasses)
                        {
                            hourglass.Activate(e);
                        }
                    }
                    else if (e.Tile.Content[j].GetType() == typeof(Hammer))
                    {
                        var hammers = Tiles.hammers.Where(x => x.Tile == e.Tile);
                        foreach (var hammer in hammers)
                        {
                            hammer.Activate(e);
                        }
                    }
                }
            }

        }

    }
}
