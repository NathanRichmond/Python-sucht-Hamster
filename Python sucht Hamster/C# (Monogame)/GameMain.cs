using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Python3.clocks;
using Python3.data;
using Python3.draw;
using Python3.game;
using System;

namespace Python3
{
    /// <summary>
    /// This is the main type for your game.
    /// </summary>
    public class GameMain : Game
    {
        public static GameMain self; // used for exiting the game

        GraphicsDeviceManager graphics; // used for interacting with DirectX
        SpriteBatch spriteBatch;
        GameContent gameContent;
        DrawMain dm;

        double dps = 0; // debug: draw rate

        public static int ScreenWidth { get; set; } = 0;
        public static int ScreenHeight { get; set; } = 0;

        // Custom properties
        public static GameState Gamestate { get; set; } = GameState.Startmenu; // state of the game, i.e. in-game, start menu, etc.
        public static int Tile { get; set; } // width & height of 1 grid tile
        public static int NLvls { get; set; } // number of levels
        public static PythonSkin PythonSkin { get; set; } = PythonSkin.Default; // appearance of Python
        public static int NPythonSkins { get; set; } // number of available Python skins (Default, Bloody, Pokemon)
        public static HamsterSkin HamsterSkin { get; set; } = HamsterSkin.Default; // appearance of Hamster
        public static int NHamsterSkins { get; set; } // number of available Hamster skins (Default, Candy, Pokemon)
        // Buttons
        public static Button[] BtnsStartmenu; // Skins, Go to Tutorial Menu, Exit
        public static Button[] BtnsLevelSelect; // Koerner for level selection in Start Menu
        public static Button[] BtnsTutorialmenu;
        public static Button[] BtnsAnleitung;
        public static Button[] BtnsManual;
        public static Button[] BtnsIngame; // Back to Menu, Pause, Restart
        public static Button BtnVictory; // Go to next Level
        // Startmenu grid
        public static Rectangle SmGrid;
        private int SmGridTile;
        private int SmGridTileBorderThickness;
        

        public GameMain()
        {
            self = this;

            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content"; // sets the root folder of all game assets
        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            // Main initialization

            /*
             * Window-related inits
             */
            ScreenWidth = GraphicsAdapter.DefaultAdapter.CurrentDisplayMode.Width;
            ScreenHeight = GraphicsAdapter.DefaultAdapter.CurrentDisplayMode.Height;

            // set window size to 1280x720 if screen is larger than that
            if (ScreenWidth >= 1280)
                ScreenWidth = 1280;
            if (ScreenHeight >= 720)
                ScreenHeight = 720;
            graphics.PreferredBackBufferWidth = ScreenWidth;
            graphics.PreferredBackBufferHeight = ScreenHeight;
            graphics.ApplyChanges();

            IsMouseVisible = true;

            /* 
             * Game-related inits
             */
            Gamestate = GameState.Startmenu;

            Tile = (int)Math.Round(ScreenWidth / 46.545454545454545454545454545455);

            NLvls = 12;

            PythonSkin = PythonSkin.Default;
            NPythonSkins = 3;
            HamsterSkin = HamsterSkin.Default;
            NHamsterSkins = 3;

            // Startmenu grid
            SmGrid.Width = (int)(ScreenWidth / 1.93);
            SmGrid.Height = (int)(ScreenHeight / 3.256875);
            SmGrid.X = ScreenWidth / 2 - SmGrid.Width / 2;
            SmGrid.Y = 3 * (ScreenHeight / 4) - SmGrid.Height / 2;

            SmGridTile = SmGrid.Width / 12; // = SmGrid.Height / 4 -- is equal (if not, adjust the factors above, where SmGrid.Width/Height are assigned, accordingly -- needs to be equal!)
            SmGridTileBorderThickness = SmGridTile / 31;

            // All buttons
            InitIngameButtons();
            InitStartMenuButtons();
            InitTutorialMenuButtons();
            InitAnleitungsbuttons();
            InitManualButtons();


            // base.Initialize() is always the last function!
            base.Initialize();
        }

        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// </summary>
        protected override void LoadContent()
        {
            // Create a new SpriteBatch, which can be used to draw textures.
            spriteBatch = new SpriteBatch(GraphicsDevice);

            // Load the entire game content here
            gameContent = new GameContent(Content, GraphicsDevice);

            // Create a new DrawMain, which will be used to draw the GameContent.
            dm = new DrawMain(spriteBatch, gameContent, ScreenWidth, ScreenHeight);
        }

        /// <summary>
        /// UnloadContent will be called once per game and is the place to unload
        /// game-specific content.
        /// </summary>
        protected override void UnloadContent()
        {
            // TODO: Unload any non-ContentManager content here
        }

        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Update(GameTime gameTime)
        {
            /*
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed || Keyboard.GetState().IsKeyDown(Keys.Escape))
                Exit();
            */

            // All Update logic (i.e., stuff that is updated every game frame) happens here
            KeyHandler.Handle(gameTime);
            Updater.Update(gameTime);

            base.Update(gameTime);
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.CornflowerBlue); // default, blue screen

            // All drawing happens here
            spriteBatch.Begin();
            dm.Draw();
            spriteBatch.End();

            base.Draw(gameTime);

            double dpsNew = 1 / gameTime.ElapsedGameTime.TotalSeconds;

            if (dps != dpsNew)
            {
                Console.WriteLine("Draws per second: " + dpsNew);

                Window.Title = "Python sucht Hamster\t–\tFPS: " + Math.Round(dpsNew, 5);

                dps = dpsNew;
            }

        }

        private void InitIngameButtons()
        {
            BtnsIngame = new Button[3];

            int x = (int)(ScreenWidth / 30.72);
            int xFixed = x;
            int y = (int)(ScreenHeight / 28.8);
            int width = (int)(ScreenWidth / 25.6);
            int height = (int)(ScreenHeight / 14.4);
            int vertDist = ScreenHeight / 170; // vertical space between individual buttons

            for (int i = 0; i < BtnsIngame.Length; i++)
            {
                x = xFixed + (i * (width + vertDist));
                BtnsIngame[i] = new Button(x, y, width, height);

            }

            /*
             * Victory Button
             */
            x = xFixed + (BtnsIngame.Length * (width + vertDist));
            BtnVictory = new Button(x, y, width, height);

        }

        private void InitStartMenuButtons() {
            InitLvlSelectButtons();

            BtnsStartmenu = new Button[4];

            int x = SmGrid.X + SmGridTileBorderThickness;
            int y = SmGrid.Y + SmGridTileBorderThickness;
            int width = SmGridTile;
            int height = SmGridTile;

            BtnsStartmenu[0] = new Button(x + 11 * width, y, width, height); // Cross - Quit
            BtnsStartmenu[1] = new Button(x, y, width, height); // Hamster Skins
            BtnsStartmenu[2] = new Button(x + 2 * width, y, width, height); // Python Skins
            BtnsStartmenu[3] = new Button(x + 9 * width, y, width, height); // Question Mark - Manual
        }

        private void InitLvlSelectButtons()
        {
            BtnsLevelSelect = new Button[NLvls];

            int border = SmGridTileBorderThickness; // just shortening that long variable name

            int buttonX = SmGrid.X + border; // general offset into tile
            int buttonY = SmGrid.Y + 3 * SmGridTile + border; // on fourth row of grid
            int buttonWidth = SmGridTile - border;
            int buttonHeight = SmGridTile - border;
            /*
            // multi-line buttons, not needed currently; will probably need fix
            int buttonsPerLine = 12;
            int innerSpace = 4; // space between individual buttons: 1px until tile border, 2px tile border, 1px
                                // offset into tile
            int j = 0;
            
            for (int i = 0; i < NLvls; i++) {
                j = i % buttonsPerLine;
                if (j == 0 && i != 0) { // if end of line is reached
                    buttonY = (int) (buttonY + smGridTile); // apply line break
                }
                buttonX = (buttonWidth + innerSpace) * j + SmGrid.X + 3;
                BtnsLevelSelect[i] = new Button(buttonX, buttonY, buttonWidth, buttonHeight);
            }
            */
            for (int i = 0; i < NLvls; i++)
            {
                buttonX = SmGrid.X + border + (i * (buttonWidth + border));
                BtnsLevelSelect[i] = new Button(buttonX, buttonY, buttonWidth, buttonHeight);
            }

        }

        private void InitTutorialMenuButtons() {
            BtnsTutorialmenu = new Button[12];

            BtnsTutorialmenu[0] = new Button(
                    (int) (SmGrid.X + 3 + 2 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (SmGrid.Y + 3 + 2 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (ScreenWidth / 24.774193548387096774193548387097),
                    (int) (ScreenHeight / 13.935483870967741935483870967742)); // Keys - tutlvl 101
            BtnsTutorialmenu[1] = new Button(
                    (int) (SmGrid.X + 3 + 3 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (SmGrid.Y + 3 + 3 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (ScreenWidth / 24.774193548387096774193548387097),
                    (int) (ScreenHeight / 13.935483870967741935483870967742)); // Zeitleiste - tutlvl 103
            BtnsTutorialmenu[2] = new Button(
                    (int) (SmGrid.X + 3 + 4 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (SmGrid.Y + 3 + 2 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (ScreenWidth / 24.774193548387096774193548387097),
                    (int) (ScreenHeight / 13.935483870967741935483870967742)); // Wall - tutlvl 104
            BtnsTutorialmenu[3] = new Button(
                    (int) (SmGrid.X + 3 + 5 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (SmGrid.Y + 3 + 3 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (ScreenWidth / 24.774193548387096774193548387097),
                    (int) (ScreenHeight / 13.935483870967741935483870967742)); // Korn - tutlvl 105
            BtnsTutorialmenu[4] = new Button(
                    (int) (SmGrid.X + 3 + 6 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (SmGrid.Y + 3 + 2 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (ScreenWidth / 24.774193548387096774193548387097),
                    (int) (ScreenHeight / 13.935483870967741935483870967742)); // Sanduhr - tutlvl 106
            BtnsTutorialmenu[5] = new Button(
                    (int) (SmGrid.X + 3 + 7 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (SmGrid.Y + 3 + 3 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (ScreenWidth / 24.774193548387096774193548387097),
                    (int) (ScreenHeight / 13.935483870967741935483870967742)); // Babyhamster - tutlvl 107
            BtnsTutorialmenu[6] = new Button(
                    (int) (SmGrid.X + 3 + 8 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (SmGrid.Y + 3 + 2 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (ScreenWidth / 24.774193548387096774193548387097),
                    (int) (ScreenHeight / 13.935483870967741935483870967742)); // Hammer - tutlvl 108
            BtnsTutorialmenu[7] = new Button(SmGrid.X + 3, SmGrid.Y + 3,
                    (int) (ScreenWidth / 24.774193548387096774193548387097),
                    (int) (ScreenHeight / 13.935483870967741935483870967742)); // Fragezeichen
            BtnsTutorialmenu[8] = new Button(
                    (int) (SmGrid.X + 3 + 10 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (SmGrid.Y + 3 + 2 * (ScreenHeight / 13.090909090909090909090909090909)),
                    (int) (ScreenWidth / 24.774193548387096774193548387097),
                    (int) (ScreenHeight / 13.935483870967741935483870967742)); // zu Level 1
            BtnsTutorialmenu[8].Text = "Level 1";
            BtnsTutorialmenu[9] = new Button(
                    (int) (SmGrid.X + 3 + 1 * (ScreenHeight / 13.090909090909090909090909090909)), SmGrid.Y + 3,
                    (int) (ScreenWidth / 24.774193548387096774193548387097),
                    (int) (ScreenHeight / 13.935483870967741935483870967742)); // Compiler
            BtnsTutorialmenu[10] = new Button(
                    (int) (SmGrid.X + 3 + 6 * (ScreenHeight / 13.090909090909090909090909090909)), SmGrid.Y + 3,
                    (int) (ScreenWidth / 24.774193548387096774193548387097),
                    (int) (ScreenHeight / 13.935483870967741935483870967742)); // Clipboard
            BtnsTutorialmenu[11] = new Button(
                    (int) (SmGrid.X + 3 + 9 * (ScreenHeight / 13.090909090909090909090909090909)), SmGrid.Y + 3,
                    (int) (ScreenWidth / 24.774193548387096774193548387097),
                    (int) (ScreenHeight / 13.935483870967741935483870967742)); // Back
        }

        private void InitAnleitungsbuttons() {
            BtnsAnleitung = new Button[2];

            BtnsAnleitung[0] = new Button(1, 1, ScreenWidth, ScreenHeight);
            BtnsAnleitung[0].Text = "An alle, die den Hamster-Simulator genauso hassen wie wir: \n"
                    + "Hier ist endlich die Gelegenheit deine ganze Wut und deinen Frust herauszulassen und Rache an dem Hamster zu nehmen. \n"
                    + "\n" + "Python-sucht-Hamster  ist ein Spiel nach dem „Hunt and Catch“ – Prinzip, dies bedeutet, \n"
                    + "dass der Spieler einem zufällig bewegtem Spielelement hinterherjagt und versucht dieses zu fangen.\n"
                    + "\n" + "Du bist eine Python:        und hast Hunger! \n" + "\n"
                    + "Dies ist der Hamster:       ,deine bevorzugte Beute.\n"
                    + "Deshalb ist es dein Ziel den Hamster zu jagen und ihn zu fressen. \n" + "\n"
                    + "Dein Jagdgebiet ist in kleinere Felder unterteilt. \n"
                    + " Jeweils du und der Hamster befinden sich auf einem Feld und \n"
                    + "könnt euch auf die vier angrenzenden Felder bewegen. \n"
                    + "Wenn du und der Hamster sich auf dem selben Feld befinden hast du gewonnen! \n"
                    + "Du steuerst die Python, indem du die Pfeiltasten drückst. \n" + "\n"
                    + "Damit die Python nach rechts läuft drücke:               \n" + "\n"
                    + "Links :                  Oben: \n" + "\n" + "Unten:                            \n" + "\n"
                    + "Pro Klicken bewegt sich die Python immer ein Feld auf dem Spielfeld in die jeweilige Richtung. \n"
                    + "Wie schnell du drückst ist dir überlassen, während die Geschwindigkeit mit der sich der Hamster immer ein Feld weiter bewegt gleich ist. \n"
                    + "Das wäre jetzt ja noch ziemlich einfach, deshalb gibt es einige Hindernisse, die dir im Weg stehen. \n"
                    + "Diese sind in den verschiedenen Level unterschiedlich. Für mehr Informationen bewege die Maus auf die Levelfelder im Auswahlmenü. \n"
                    + "Im ersten Level ist die Schwierigkeit, dass du den Hamster in einer bestimmten Zeit fangen musst. \n"
                    + "Pythons sind Kaltblüter, deshalb ist die Sonne sehr wichtig für sie. \n"
                    + "Ohne das wärmende Sonnenlicht erstarren sie. Die stylische Zeitleiste rechts von dem Spielfeld zeigt dir den Sonnenstand \n"
                    + "und läuft kontinuierlich ab. \n" + "Ist die Sonne untergegangen hast du verloren! \n" + "\n"
                    + "Hier kommst du zum ersten Level:  \n";

            BtnsAnleitung[1] = new Button((int)(ScreenWidth - (ScreenWidth / 3.3391304347826086956521739130435)),
                    (int)(ScreenHeight - (ScreenHeight / 17.28)), (int)(ScreenHeight / 10.24),
                    (int)(ScreenHeight / 24.685714285714285714285714285714));
            BtnsAnleitung[1].Text = "Level 1";

        }

        private static void InitManualButtons() {
            // button of the navigational grid
            BtnsManual = new Button[3];

            int w0 = 64, h0 = 64;
            BtnsManual[0] = new Button(Grid.X, Grid.Y, w0, h0);

            int w = 350;
            int h = 85;
            int x1 = ScreenWidth / 2 - w / 2;
            int y1 = 570;
            BtnsManual[1] = new Button(x1, y1, w, h);
            BtnsManual[1].Text = "Erfahre mehr darüber";
            int x2 = ScreenWidth / 2 - w / 2;
            int y2 = 735;
            BtnsManual[2] = new Button(x2, y2, w, h);
            BtnsManual[2].Text = "Leg gleich los!";
        }    
    }

    public enum PythonSkin { Default, Bloody, Pokemon }

    public enum HamsterSkin { Default, Candy, Pokemon }

    public enum GameState { Startmenu, Ingame, Pause, Victory, Defeat, Tutorialmenu, Anleitung, Manual }

}
