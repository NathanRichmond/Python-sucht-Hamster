using Microsoft.Xna.Framework.Input;
using Python3.game;
using static Python3.data.DataUtils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Xna.Framework;
using Python3.chars;

namespace Python3.data
{
    public static class KeyHandler
    {
        private static bool MousePressedCache = false;
        private static List<Keys> KeyPressedCache = new List<Keys>();

        private static Player p;

        public static void Handle(GameTime gameTime)
        {
            HandleMouseAction();
            HandleKeyAction();
        }

        private static void HandleMouseAction()
        {
            HandleMouseMoved();

            if (!MousePressedCache)
            {
                if (Mouse.GetState().LeftButton == ButtonState.Pressed) // if left mouse button was pressed
                {
                    MousePressedCache = true;
                    HandleMousePressed();
                }
            }
            else
            {
                if (Mouse.GetState().LeftButton == ButtonState.Released) // if left mouse button was released
                {
                    MousePressedCache = false;
                }
            }
        }

        private static void HandleMouseMoved()
        {
            int x = Mouse.GetState().X;
            int y = Mouse.GetState().Y;
            GameState gs = GameMain.Gamestate;

            switch (gs)
            {
                case GameState.Startmenu:
                    /*
                     * START MENU Buttons
                     */
                    for (int i = 0; i < GameMain.BtnsStartmenu.Length; i++)
                    {
                        GameMain.BtnsStartmenu[i].IsHover = CollisionBtn(GameMain.BtnsStartmenu[i], x, y);
                    }
                    for (int i = 0; i < GameMain.BtnsLevelSelect.Length; i++)
                    {
                        GameMain.BtnsLevelSelect[i].IsHover = CollisionBtn(GameMain.BtnsLevelSelect[i], x, y);
                    }
                    break;
                case GameState.Tutorialmenu:
                    /*
                     * TUTORIAL MENU Buttons
                     */
                    for (int i = 0; i < GameMain.BtnsTutorialmenu.Length; i++)
                    {
                        GameMain.BtnsTutorialmenu[i].IsHover = CollisionBtn(GameMain.BtnsTutorialmenu[i], x, y);
                    }
                    break;
                case GameState.Anleitung:
                    /*
                     * ANLEITUNG Buttons
                     */
                    for (int i = 0; i < GameMain.BtnsAnleitung.Length; i++)
                    {
                        GameMain.BtnsAnleitung[i].IsHover = CollisionBtn(GameMain.BtnsAnleitung[i], x, y);
                    }
                    break;
                case GameState.Manual:
                    /*
                     * MANUAL Buttons
                     */
                    for (int i = 0; i < GameMain.BtnsManual.Length; i++)
                    {
                        GameMain.BtnsManual[i].IsHover = CollisionBtn(GameMain.BtnsManual[i], x, y);
                    }
                    break;
                default:
                    /*
                     * INGAME Buttons
                     */
                    for (int i = 0; i < GameMain.BtnsIngame.Length; i++)
                    {
                        GameMain.BtnsIngame[i].IsHover = CollisionBtn(GameMain.BtnsIngame[i], x, y);
                        if (i == 1 && gs != GameState.Ingame && gs != GameState.Pause) // for pause Button: only when ingame/pause
                        {
                            GameMain.BtnsIngame[i].IsHover = false;
                        }
                    }

                    /*
                     * VICTORY Button
                     */
                    if (Game2.LevelNr + 1 <= GameMain.NLvls || Game2.LevelNr > 100)
                    {
                        GameMain.BtnVictory.IsHover = CollisionBtn(GameMain.BtnVictory, x, y);
                    }

                    break;
            }
        }

        private static void HandleMousePressed()
        {
            int x = Mouse.GetState().X; // x coord of mouse position where clicked
            int y = Mouse.GetState().Y; // y coord of mouse position where clicked
            GameState gs = GameMain.Gamestate;

            switch (gs)
            {
                case GameState.Startmenu:

                    // Cross button
                    if (CollisionBtn(GameMain.BtnsStartmenu[0], x, y)) GameMain.self.Exit();

                    // Hamster skins button
                    if (CollisionBtn(GameMain.BtnsStartmenu[1], x, y))
                    {
                        switch (GameMain.HamsterSkin)
                        {
                            case HamsterSkin.Default:
                                GameMain.HamsterSkin = HamsterSkin.Candy; // select next skin
                                break;
                            case HamsterSkin.Candy:
                                GameMain.HamsterSkin = HamsterSkin.Pokemon;
                                break;
                            case HamsterSkin.Pokemon:
                                GameMain.HamsterSkin = HamsterSkin.Default;
                                break;
                            default:
                                break;
                        }
                    }

                    // Python skins button
                    if (CollisionBtn(GameMain.BtnsStartmenu[2], x, y))
                    {
                        switch (GameMain.PythonSkin)
                        {
                            case PythonSkin.Default:
                                GameMain.PythonSkin = PythonSkin.Bloody;
                                break;
                            case PythonSkin.Bloody:
                                GameMain.PythonSkin = PythonSkin.Pokemon;
                                break;
                            case PythonSkin.Pokemon:
                                GameMain.PythonSkin = PythonSkin.Default;
                                break;
                            default:
                                break;
                        }
                    }

                    // Tutorial button
                    if (CollisionBtn(GameMain.BtnsStartmenu[3], x, y))
                    {
                        GameMain.Gamestate = GameState.Tutorialmenu;
                    }

                    // Level select buttons
                    for (int i = 0; i < GameMain.NLvls; i++)
                    {
                        if (CollisionBtn(GameMain.BtnsLevelSelect[i], x, y))
                        {
                            if (Game2.MaxLevelAvailable >= i + 1) // only if level was unlocked already
                            {
                                Game2.LevelNr = i + 1; // i+1 because i starts at 0 but levels start at 1
                                Game2.StartNewGame();
                            }
                        }
                    }
                    break;

                case GameState.Tutorialmenu:

                    // Cross button
                    if (CollisionBtn(GameMain.BtnsStartmenu[0], x, y))
                        GameMain.self.Exit();

                    // Tutorial level select buttons
                    for (int i = 0; i < 7; i++)
                    {
                        if (CollisionBtn(GameMain.BtnsTutorialmenu[i], x, y))
                        {
                            Game2.LevelNr = i == 0 ? 101 : i + 102; // Ex.: Array index [3] - tutlvl 105 (because 102 has no button in array)
                            Game2.StartNewGame();
                        }
                    }

                    // Button question mark
                    if (CollisionBtn(GameMain.BtnsTutorialmenu[7], x, y))
                        Game2.IsBehindTheGame = false;

                    // Button "Start Level 1"
                    if (CollisionBtn(GameMain.BtnsTutorialmenu[8], x, y))
                    {
                        Game2.LevelNr = 1;
                        Game2.StartNewGame();
                    }

                    // Button compiler
                    if (CollisionBtn(GameMain.BtnsTutorialmenu[9], x, y))
                        Game2.IsBehindTheGame = true;

                    // Button "Textual Manual"
                    if (CollisionBtn(GameMain.BtnsTutorialmenu[10], x, y))
                    {
                        GameMain.Gamestate = Game2.IsAltManual == true ? GameState.Manual : GameState.Anleitung;
                    }

                    // Button "Back to startmenu"
                    if (CollisionBtn(GameMain.BtnsTutorialmenu[11], x, y))
                    {
                        GameMain.Gamestate = GameState.Startmenu;
                    }
                    break;

                case GameState.Anleitung:

                    if (CollisionBtn(GameMain.BtnsAnleitung[1], x, y))
                    {
                        Game2.LevelNr = 1;
                        Game2.StartNewGame();
                    }
                    break;

                case GameState.Manual:

                    if (CollisionBtn(GameMain.BtnsManual[1], x, y))
                        Game2.Manualpage = 1;

                    if (CollisionBtn(GameMain.BtnsManual[2], x, y))
                    {
                        Game2.LevelNr = 1;
                        Game2.StartNewGame();
                    }
                    break;

                default:

                    // Button Stop
                    if (CollisionBtn(GameMain.BtnsIngame[0], x, y))
                    {
                        Game2.StopGame();
                        GameMain.Gamestate = Game2.LevelNr < 100 ? GameState.Startmenu : GameState.Tutorialmenu; // return to Tutorial menu when in a Tutorial level
                    }

                    // Button Pause
                    if (CollisionBtn(GameMain.BtnsIngame[1], x, y))
                    {
                        switch (gs) // Pause button only clickable when gamestate == Ingame || Pause
                        {
                            case GameState.Ingame:
                                GameMain.Gamestate = GameState.Pause;
                                break;
                            case GameState.Pause:
                                GameMain.Gamestate = GameState.Ingame;
                                break;
                        }
                    }

                    // Button Restart
                    if (CollisionBtn(GameMain.BtnsIngame[2], x, y))
                        Game2.RestartLevel();

                    // (Victory) Button Next Level
                    if (gs == GameState.Victory && (Game2.LevelNr + 1 <= GameMain.NLvls || Game2.LevelNr > 100))
                    {
                        if (CollisionBtn(GameMain.BtnVictory, x, y))
                        {
                            Game2.LevelNr += 1;
                            Game2.StartNewGame();
                        }
                    }

                    break;
            }

        }

        private static void HandleKeyAction()
        {
            KeyboardState state = Keyboard.GetState(); // currrent keyboard state

            // cycle through all pressed keys
            for (int i = 0; i < state.GetPressedKeys().Length; i++)
            {
                Keys pressedKey = state.GetPressedKeys()[i];

                // start clocks whenever any key is pressed
                if (GameMain.Gamestate == GameState.Ingame)
                    Sc();

                // update the key cache
                if (!KeyPressedCache.Contains(pressedKey)) // if pressed key is pressed for the first time (and thus not already in cache)
                {
                    KeyPressedCache.Add(pressedKey); // add pressed key to key cache
                    HandleKeyAction(pressedKey);
                }
            }

            // cycle through key cache, remove all keys which are no longer pressed
            for (int i = 0; i < KeyPressedCache.Count; i++)
            {
                Keys k = KeyPressedCache[i];

                if (state.IsKeyUp(k))
                    KeyPressedCache.Remove(k);
                    
            }

        }

        /// <param name="k">The key which was pressed</param>
        private static void HandleKeyAction(Keys k)
        {
            /*
             * ESC: switch between pause and ingame
             */
            if (k == Keys.Escape)
            {
                switch (GameMain.Gamestate)
                {
                    case GameState.Pause:
                        GameMain.Gamestate = GameState.Ingame;
                        break;
                    case GameState.Ingame:
                        GameMain.Gamestate = GameState.Pause;
                        break;
                }
            }

            /*
             * Space Bar: Place an Egg
             */
            if (k == Keys.Space)
            {
                //Console.WriteLine("Space Bar pressed - initiating process:");
                //Console.WriteLine();
                //Console.WriteLine("--- PLACE AN EGG ---");
                if (GameMain.Gamestate == GameState.Ingame)
                {
                    /*
                    p = Game.p;
                    //Console.WriteLine("Successfully detected Player.");
                    p.placeEgg();
                    */
                }
            }

            /*
             * F1–F12: (Un)lock respective max levels (only in startmenu)
             */
            if (GameMain.Gamestate == GameState.Startmenu)
            {
                int maxLvl = Game2.MaxLevelAvailable;
                switch (k)
                {
                    case Keys.F1:
                        maxLvl = 1;
                        break;
                    case Keys.F2:
                        maxLvl = 2;
                        break;
                    case Keys.F3:
                        maxLvl = 3;
                        break;
                    case Keys.F4:
                        maxLvl = 4;
                        break;
                    case Keys.F5:
                        maxLvl = 5;
                        break;
                    case Keys.F6:
                        maxLvl = 6;
                        break;
                    case Keys.F7:
                        maxLvl = 7;
                        break;
                    case Keys.F8:
                        maxLvl = 8;
                        break;
                    case Keys.F9:
                        maxLvl = 9;
                        break;
                    case Keys.F10:
                        maxLvl = 10;
                        break;
                    case Keys.F11:
                        maxLvl = 11;
                        break;
                    case Keys.F12:
                        maxLvl = 12;
                        break;
                }
                Game2.MaxLevelAvailable = maxLvl;
            }

            /*
             * Arrow Keys: Move player
             */
            if (GameMain.Gamestate == GameState.Ingame)
            {
                p = Game2.p;
                switch (k)
                {
                    case Keys.Up:
                        //Console.WriteLine("Move Player North.");
                        p.Move(Dir.North);
                        break;
                    case Keys.Right:
                        //Console.WriteLine("Move Player East.");
                        p.Move(Dir.East);
                        break;
                    case Keys.Down:
                        //Console.WriteLine("Move Player South.");
                        p.Move(Dir.South);
                        break;
                    case Keys.Left:
                        //Console.WriteLine("Move Player West.");
                        p.Move(Dir.West);
                        break;
                }
            }
        }

        private static void Sc() // start clocks
        {
            if (Game2.IsFirstKeyPressInGame) Game2.StartClocks();
        }

    }

}
