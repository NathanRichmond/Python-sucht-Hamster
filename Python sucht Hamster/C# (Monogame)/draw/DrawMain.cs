using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Python3.chars;
using Python3.game;
using Python3.tiles;
using static Python3.draw.DrawUtils;

namespace Python3.draw
{
    class DrawMain
    {
        private SpriteBatch spriteBatch;
        private GameContent gameContent;
        private int screenWidth;
        private int screenHeight;

        private Texture2D pixel;

        private static Player p;
        private GameState gs;
        private int lvl;
        private Rectangle smGrid;

        private readonly bool drawPlayerMargin = false;

        public DrawMain(SpriteBatch spriteBatch, GameContent gameContent, int screenWidth, int screenHeight)
        {
            this.spriteBatch = spriteBatch;
            this.gameContent = gameContent;
            this.screenWidth = screenWidth;
            this.screenHeight = screenHeight;
            this.pixel = gameContent.TxtrGeneric;
        }

        public void Draw()
        {
            p = Game2.p;
            gs = GameMain.Gamestate;
            lvl = Game2.LevelNr;
            smGrid = GameMain.SmGrid;

            /*
		    * START MENU SCREEN
		    */
            if (gs == GameState.Startmenu)
                DrawStartMenu();

            /*
             * TUTORIAL MENU SCREEN
             */
            if (gs == GameState.Tutorialmenu)
                DrawTutorialMenu();

            /*
             * ANLEITUNGS SCREEN
             */
            if (gs == GameState.Manual || gs == GameState.Anleitung)
                DrawManual();

            /*
             * INGAME ELEMENTS
             */
            if (gs == GameState.Ingame || gs == GameState.Pause || gs == GameState.Victory || gs == GameState.Defeat)
            {
                /*
                 * Note: Draw order is important! Example: Special Tiles are drawn before
                 * Characters because Characters should be in front of Special Tiles.
                 */

                /*
                 * BACKGROUND
                 */
                DrawBackground();

                /*
                 * BACKGROUND LAYER: Grid
                 */
                DrawGrid();

                /*
                 * GRID CONTENTS: Tiles (incl. characters and Special Tiles)
                 */
                DrawTileContent();

                /*
                 * GRID CONTENTS: Special Tiles (to be deleted, include in drawTile())
                 */
                //drawSpecialTile();

                /*
                 * GRID CONTENTS: Wall (to be deleted, include in drawTile())
                 */
                //drawWall();

                /*
                 * GAME ELEMENTS: GameTimer
                 */
                if (lvl != 101 && lvl != 102) // no timer in tut1 & tut2
                {
                    DrawGameTimer();
                }

                // Extrabilder für die Tutorials
                if ((lvl == 101 || lvl == 102) && !Game2.IsBehindTheGame)
                {
                    DrawPfeiltasten();
                }

                /*
                 * GAME ELEMENTS: Ingame Buttons
                 */
                DrawIngameButtons();

                /*
                 * GAME ELEMENTS: Level Title
                 */
                if (lvl < 100) // only for regular, not for tutlvls
                    DrawLvlTitle();

                /*
                 * GAME ELEMENTS: Special Tile Activated Info
                 */
                /*
                if (Game2.SpecialTiles)
                    drawSpecialTileInfo();
                */

                /*
                 * GAME ELEMENTS: Hamster Count (for Hamsterinflation)
                 */
                if (Level.Hamsterinflation)
                    DrawHamstercount();

                /*
                 * BEHIND THE GAME
                 */
                if (Game2.IsBehindTheGame && lvl > 100)
                    DrawBehindTheGame();
            }

            /*
             * GAME END SCREENS
             */
            if (gs == GameState.Victory || gs == GameState.Defeat)
                DrawGameendScreen();

            /*
             * PAUSE SCREEN
             */
            if (gs == GameState.Pause)
                DrawPauseScreen();

        }

        private void DrawPauseScreen()
        {
            // Gray overlay
            Rectangle gridRect = new Rectangle(Grid.X, Grid.Y, Grid.Width, Grid.Height);
            float opacity = 0.5f;
            pixel.SetData(new Color[] { Color.White });
            spriteBatch.Draw(pixel, gridRect, Color.Black * opacity);

            int x = Grid.X + Grid.Width / 2;
            int y = Grid.Y + Grid.Height / 4;
            string text = "PAUSE";
            spriteBatch.DrawString(gameContent.LabelFont, text, StringPos(text, gameContent.LabelFont, x, y, Align.Center), Color.White);
        }

        private void DrawGameendScreen()
        {

            // Gray overlay
            Rectangle gridRect = new Rectangle(Grid.X, Grid.Y, Grid.Width, Grid.Height);
            float opacity = 0.7f;
            pixel.SetData(new Color[] { Color.White });
            spriteBatch.Draw(pixel, gridRect, Color.Black * opacity);

            /*
             * GIF and text
             */
            int w = (int)(screenWidth / 10.6666666666667); // width of GIF rectangle, equal to int h
            int h = screenHeight / 6; // height of GIF rectangle, equal to int w
            int threshold = w; // if not enough space for GIF, then no GIF

            if (Grid.Width <= threshold || Grid.Height <= threshold) // no space for GIF?
            { // then center text
                w = 0;
                h = 0;
            }

            // position of rectangle: centered on grid
            int x = Grid.X + (Grid.Width / 2 - w / 2);
            int y = Grid.Y + (Grid.Height / 2 - h / 2);

            int wRect = w / 2;
            int hRect = h / 2;
            int xRect = x + wRect / 2;
            int yRect = y + hRect / 3;
            Rectangle posRect = new Rectangle(xRect, yRect, wRect, hRect);

            SpriteFont font = gameContent.LabelFont;

            // variables that depend on gamestate:
            Texture2D gif = pixel;
            string text = "";
            string mainText = "";
            if (gs == GameState.Victory)
            {
                gif = gameContent.ImgVictory;
                text = "Hamster platt,\nPython satt.";
                mainText = "SIEG!";
            }
            else if (gs == GameState.Defeat)
            {
                gif = gameContent.ImgDefeat;
                text = "Brr! Du bist\nerfroren.";
                mainText = "NIEDERLAGE!";
            }

            // Actual drawing starts here
            if (Grid.Width > threshold && Grid.Height > threshold)
            {
                // GIF:
                //spriteBatch.Draw(gif, posRect, Color.White);

                // Defeat text:
                int xText = x + wRect;
                int yText = y + 3 * hRect / 2;
                spriteBatch.DrawString(font, text, StringPos(text, font, xText, yText, Align.Center), Color.White);
            }
            else // center text when there's little space
            {
                // g.setTextBaseline(VPos.CENTER);
            }

            x += wRect;
            SpriteFont mainFont = font;
            spriteBatch.DrawString(mainFont, mainText, StringPos(mainText, font, x, y, Align.Center), Color.White);

            if (gs == GameState.Victory)
                DrawVictoryButton(); // Button: "Next Level"

        }

        private void DrawVictoryButton()
        {
            Button b = GameMain.BtnVictory;
            Rectangle btnRect = new Rectangle(b.X, b.Y, b.Width, b.Height);

            if (Game2.LevelNr + 1 <= GameMain.NLvls || (Game2.LevelNr > 100 && Game2.LevelNr < 108))
            {
                DrawBorder(spriteBatch, pixel, btnRect, 1, Color.White); // Button border
                spriteBatch.Draw(pixel, btnRect, Color.White * 0.8f); // Button background: white with 20% opacity
                DrawButtonHover(b); // Button hover effect
                spriteBatch.Draw(gameContent.ImgBtnNext, btnRect, Color.White); // Button image
            }

            if (Game2.LevelNr > 100 && Game2.LevelNr < 108) // if victory is in a Tutorial level
            {
                // draw box with text "zum nächsten Level" underneath the button
                int x = b.X + b.Height / 6; // x coordinate of box
                int y = b.Y + b.Height + b.Height / 6; // y coordinate of box
                int w = (int)(screenWidth / 5.7); // width of box
                int h = 2 * b.Height / 3; // height of box
                Rectangle boxRect = new Rectangle(x, y, w, h);

                DrawBorder(spriteBatch, pixel, boxRect, 1, Color.White); // Box border
                spriteBatch.Draw(pixel, btnRect, Color.Black * 0.2f); // Box background: black with 80% opacity

                // Box text
                string text = "zum nächsten Tutorial";
                SpriteFont font = gameContent.LabelFont;
                spriteBatch.DrawString(font, text, StringPos(text, font, x + w / 2, y + h / 2, Align.Center), Color.White);
            }
        }

        private void DrawBehindTheGame()
        {
            // TODO: Implement
        }

        private void DrawHamstercount()
        {
            // TODO: Implement
        }

        private void DrawLvlTitle()
        {
            int width = (int)(screenWidth / 10.25);
            int height = (int)(screenHeight / 12.25);
            int x = (screenWidth / 2) - width / 2;
            int gridy = (screenHeight / 2 - 275 / 2) / 2 + 90;
            int y = gridy / 2 - height / 2;
            Texture2D lvlTitle = gameContent.ImgLvltitle01;

            switch (Game2.LevelNr)
            {
                case 1:
                    lvlTitle = gameContent.ImgLvltitle01;
                    break;
                case 2:
                    lvlTitle = gameContent.ImgLvltitle02;
                    break;
                case 3:
                    lvlTitle = gameContent.ImgLvltitle03;
                    break;
                case 4:
                    lvlTitle = gameContent.ImgLvltitle04;
                    break;
                case 5:
                    lvlTitle = gameContent.ImgLvltitle05;
                    break;
                case 6:
                    lvlTitle = gameContent.ImgLvltitle06;
                    break;
                case 7:
                    lvlTitle = gameContent.ImgLvltitle07;
                    break;
                case 8:
                    lvlTitle = gameContent.ImgLvltitle08;
                    break;
                case 9:
                    lvlTitle = gameContent.ImgLvltitle09;
                    break;
                case 10:
                    lvlTitle = gameContent.ImgLvltitle10;
                    break;
                case 11:
                    lvlTitle = gameContent.ImgLvltitle11;
                    break;
                case 12:
                    lvlTitle = gameContent.ImgLvltitle12;
                    break;
            }

            spriteBatch.Draw(lvlTitle, new Rectangle(x, y, width, height), Color.White);
        }

        private void DrawIngameButtons()
        {
            for (int i = 0; i < GameMain.BtnsIngame.Length; i++)
            {
                Button b = GameMain.BtnsIngame[i];
                Rectangle btnRect = new Rectangle(b.X, b.Y, b.Width, b.Height);

                pixel.SetData(new Color[] { Color.White });

                /*
                 * Button background
                 */
                Color bgColor = Color.White * 0.8f; // white with 20% opacity
                spriteBatch.Draw(pixel, btnRect, bgColor);

                /*
                 * Button hover background
                 */
                if (b.IsHover)
                {
                    bool flagDraw = true;
                    Color bgHoverColor = Color.Black * 0.2f; // black with 80% opacity
                    if (i == 1 && gs != GameState.Ingame && gs != GameState.Pause) // for pause Button: only when ingame/pause
                        flagDraw = false;
                    if (flagDraw)
                        spriteBatch.Draw(pixel, btnRect, bgHoverColor);
                }

                /*
                 * Button image
                 */
                switch (i)
                {
                    case 0:
                        spriteBatch.Draw(gameContent.ImgBtnExit, btnRect, Color.White);
                        if (gs == GameState.Victory && Game2.LevelNr == 108) // last Tutorial level
                        {
                            // draw box with text "zurück zum Menü" underneath the button
                            /*
                              int x = b.getX() + 1 * b.getHeight() / 6; // x coordinate of box
                              int y = b.getY() + b.getHeight() + 1 * b.getHeight() / 6; // y coordinate of box
                              int w = 240; // width of box
                              int h = 2 * b.getHeight() / 3; // height of box
                              g.setStroke(Color.WHITE);
                              g.setLineWidth(1);
                              g.strokeRect(x, y, w, h);
                              g.setFill(new Color(0, 0, 0, 0.2));
                              g.fillRect(x, y, w, h);
                              g.setTextAlign(TextAlignment.CENTER);
                              g.setTextBaseline(VPos.CENTER);
                              g.setFont(new Font("Constantia",
                                      ((Gui.getWidth() * Gui.getHeight()) / 25521.230769230769230769230769231) / 2));
                              g.setFill(Color.WHITE);
                              g.fillText("zurück zum Menü", x + w / 2, y + h / 2);
                              */
                        }
                        break;
                    case 1:
                        switch (gs)
                        {
                            case GameState.Ingame:
                                spriteBatch.Draw(gameContent.ImgBtnPause, btnRect, Color.White);
                                break;
                            case GameState.Pause:
                                spriteBatch.Draw(gameContent.ImgBtnPlay, btnRect, Color.White);
                                break;
                            default:
                                spriteBatch.Draw(gameContent.ImgBtnPause, btnRect, Color.White);
                                pixel.SetData(new Color[] { Color.White });
                                spriteBatch.Draw(pixel, btnRect, Color.Black * 0.4f); // dark layer over the button (signifying "disabled")
                                break;
                        }
                        break;
                    case 2:
                        spriteBatch.Draw(gameContent.ImgBtnRestart, btnRect, Color.White);
                        break;
                }

                /*
                 * Button border
                 */
                Color borderColor = Color.White;
                if (b.IsHover)
                    borderColor = Color.Black * 0.3f;
                if (i == 1 && gs != GameState.Ingame && gs != GameState.Pause) // for pause Button: only when ingame/pause
                    borderColor = Color.Black * 0.25f; // black with 75% opacity
                DrawBorder(spriteBatch, pixel, btnRect, 1, borderColor);

            }
        }

        private void DrawPfeiltasten()
        {
            // TODO: Implement
        }

        private void DrawTileContent()
        {
            // player over enemy over special tile, but wall over everything
            DrawSpecialTile();
            DrawEnemy();
            DrawPlayer();
            DrawWall();
        }

        private void DrawPlayer()
        {
            Rectangle tileRect = new Rectangle(p.Tile.X, p.Tile.Y, GameMain.Tile, GameMain.Tile);
            Texture2D img = gameContent.ImgPlayer0_0; // default image
            PythonSkin skin = GameMain.PythonSkin;

            switch (p.FaceDirection)
            {
                case Dir.North:
                    switch (skin)
                    {
                        default:
                            img = gameContent.ImgPlayer0_0;
                            break;
                        case PythonSkin.Bloody:
                            img = gameContent.ImgPlayer1_0;
                            break;
                        case PythonSkin.Pokemon:
                            img = gameContent.ImgPlayer2_0;
                            break;
                    }
                    break;
                case Dir.East:
                    switch (skin)
                    {
                        default:
                            img = gameContent.ImgPlayer0_1;
                            break;
                        case PythonSkin.Bloody:
                            img = gameContent.ImgPlayer1_1;
                            break;
                        case PythonSkin.Pokemon:
                            img = gameContent.ImgPlayer2_1;
                            break;
                    }
                    break;
                case Dir.South:
                    switch (skin)
                    {
                        default:
                            img = gameContent.ImgPlayer0_2;
                            break;
                        case PythonSkin.Bloody:
                            img = gameContent.ImgPlayer1_2;
                            break;
                        case PythonSkin.Pokemon:
                            img = gameContent.ImgPlayer2_2;
                            break;
                    }
                    break;
                case Dir.West:
                    switch (skin)
                    {
                        default:
                            img = gameContent.ImgPlayer0_3;
                            break;
                        case PythonSkin.Bloody:
                            img = gameContent.ImgPlayer1_3;
                            break;
                        case PythonSkin.Pokemon:
                            img = gameContent.ImgPlayer2_3;
                            break;
                    }
                    break;
            }
            spriteBatch.Draw(img, tileRect, Color.White);

            // draw playerMargin:

            if (drawPlayerMargin)
            {
                int pm = Level.PlayerMargin * GameMain.Tile;
                Rectangle pmRect = new Rectangle(p.Tile.X - pm, p.Tile.Y - pm, pm * 2 + GameMain.Tile, pm * 2 + GameMain.Tile);
                DrawUtils.DrawBorder(spriteBatch, pixel, pmRect, 1, Color.Red); 
            }

        }

        private void DrawEnemy()
        {
            for (int i = 0; i < Game2.enemies.Count; i++)
            {
                Enemy e = Game2.enemies[i];
                Rectangle tileRect = new Rectangle(e.Tile.X, e.Tile.Y, GameMain.Tile, GameMain.Tile);

                if (e.Alive)
                {
                    Texture2D img = gameContent.ImgEnemy0_0; // default image
                    bool upgraded = e.SpeedBoosted; // is Hamster upgraded?
                    HamsterSkin skin = GameMain.HamsterSkin; // Hamster skin

                    switch (e.FaceDirection)
                    {
                        case Dir.North:
                            switch (skin)
                            {
                                default:
                                    img = upgraded ? gameContent.ImgEnemy0u_0 : gameContent.ImgEnemy0_0;
                                    break;
                                case HamsterSkin.Candy:
                                    img = upgraded ? gameContent.ImgEnemy1u_0 : gameContent.ImgEnemy1_0;
                                    break;
                                case HamsterSkin.Pokemon:
                                    img = upgraded ? gameContent.ImgEnemy2u_0 : gameContent.ImgEnemy2_0;
                                    break;
                            }
                            break;
                        case Dir.East:
                            switch (skin)
                            {
                                default:
                                    img = upgraded ? gameContent.ImgEnemy0u_1 : gameContent.ImgEnemy0_1;
                                    break;
                                case HamsterSkin.Candy:
                                    img = upgraded ? gameContent.ImgEnemy1u_1 : gameContent.ImgEnemy1_1;
                                    break;
                                case HamsterSkin.Pokemon:
                                    img = upgraded ? gameContent.ImgEnemy2u_1 : gameContent.ImgEnemy2_1;
                                    break;
                            }
                            break;
                        case Dir.South:
                            switch (skin)
                            {
                                default:
                                    img = upgraded ? gameContent.ImgEnemy0u_2 : gameContent.ImgEnemy0_2;
                                    break;
                                case HamsterSkin.Candy:
                                    img = upgraded ? gameContent.ImgEnemy1u_2 : gameContent.ImgEnemy1_2;
                                    break;
                                case HamsterSkin.Pokemon:
                                    img = upgraded ? gameContent.ImgEnemy2u_2 : gameContent.ImgEnemy2_2;
                                    break;
                            }
                            break;
                        case Dir.West:
                            switch (skin)
                            {
                                default:
                                    img = upgraded ? gameContent.ImgEnemy0u_3 : gameContent.ImgEnemy0_3;
                                    break;
                                case HamsterSkin.Candy:
                                    img = upgraded ? gameContent.ImgEnemy1u_3 : gameContent.ImgEnemy1_3;
                                    break;
                                case HamsterSkin.Pokemon:
                                    img = upgraded ? gameContent.ImgEnemy2u_3 : gameContent.ImgEnemy2_3;
                                    break;
                            }
                            break;
                    }
                    spriteBatch.Draw(img, tileRect, Color.White);
                }
                else
                {
                    // error, couldn't draw Enemy
                    Console.WriteLine("Couldn't draw enemy.");
                    /*
                    g.setFill(new Color(0, 0, 0, 0.4));
                    g.fillRect(t.getX(), t.getY(), e.getWidth(), e.getHeight());

                    g.setTextAlign(TextAlignment.CENTER);
                    g.setTextBaseline(VPos.CENTER);
                    g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 33177.6) / 2));
                    g.setFill(Color.WHITE);
                    g.fillText("Couldn't draw Enemy.", t.getX() + (e.getWidth() / 2), t.getY() + (e.getHeight() / 2));
                    */
                }
                /*
                 * Draw index of Enemy
                 */
                //			g.setFill(new Color(0, 0, 0, 0.4));
                //			g.fillRect(t.getX(), t.getY(), e.getWidth(), e.getHeight());
                //
                //			g.setTextAlign(TextAlignment.CENTER);
                //			g.setTextBaseline(VPos.CENTER);
                //			g.setFont(new Font("Constantia", ((Gui.getWidth() * Gui.getHeight()) / 33177.6) / 2));
                //			g.setFill(Color.WHITE);
                //			g.fillText("" + e.index, t.getX() + (e.getWidth()/2), t.getY() + (e.getHeight()/2));

                /*
                 * Draw boosted information of Enemy (for debugging)
                 */
                /*
                if (e.SpeedBoosted)
                {
                    int x = (int)(Grid.X - (screenWidth / 6.4));
                    int y = Grid.Y;
                    int width = (int)(screenWidth / 7.68);
                    int height = (int)(screenHeight / 21.6);

                    Rectangle enemyRect = new Rectangle(x, y, width, height);
                    pixel.SetData(new Color[] { Color.White });
                    spriteBatch.Draw(pixel, enemyRect, Color.Black * 0.2f);

                    spriteBatch.DrawString(gameContent.LabelFont, e.em.NSpeedBoost.ToString(), new Vector2(x, y), Color.White);
                }
                */

            }

        }

        private void DrawWall()
        {
            for (int i = 0; i < Tiles.walls.Count; i++)
            {
                Wall w = Tiles.walls[i];
                Rectangle tileRect = new Rectangle(w.Tile.X + 1, w.Tile.Y + 1, GameMain.Tile - 2, GameMain.Tile - 2);
                spriteBatch.Draw(gameContent.ImgWall, tileRect, Color.White);
            }
        }

        private void DrawSpecialTile()
        {
            Rectangle tileRect = new Rectangle(0, 0, GameMain.Tile, GameMain.Tile);

            for (int i = 0; i < Tiles.korns.Count; i++)
            {
                Korn k = Tiles.korns[i];
                if (k.Alive)
                {
                    tileRect.X = k.Tile.X;
                    tileRect.Y = k.Tile.Y;
                    spriteBatch.Draw(gameContent.ImgSpecialtile_korn, tileRect, Color.White);
                }
            }
            for (int i = 0; i < Tiles.babyhamsterTwos.Count; i++)
            {
                BabyhamsterTwo b = Tiles.babyhamsterTwos[i];
                if (b.Alive)
                {
                    tileRect.X = b.Tile.X;
                    tileRect.Y = b.Tile.Y;
                    spriteBatch.Draw(gameContent.ImgSpecialtile_babyhamsterTwo, tileRect, Color.White);
                }
            }
            for (int i = 0; i < Tiles.babyhamsterThrees.Count; i++)
            {
                BabyhamsterThree b = Tiles.babyhamsterThrees[i];
                if (b.Alive)
                {
                    tileRect.X = b.Tile.X;
                    tileRect.Y = b.Tile.Y;
                    spriteBatch.Draw(gameContent.ImgSpecialtile_babyhamsterThree, tileRect, Color.White);
                }
            }
            for (int i = 0; i < Tiles.babyhamsterFours.Count; i++)
            {
                BabyhamsterFour b = Tiles.babyhamsterFours[i];
                if (b.Alive)
                {
                    tileRect.X = b.Tile.X;
                    tileRect.Y = b.Tile.Y;
                    spriteBatch.Draw(gameContent.ImgSpecialtile_babyhamsterFour, tileRect, Color.White);
                }
            }
            for (int i = 0; i < Tiles.hourglasses.Count; i++)
            {
                Hourglass h = Tiles.hourglasses[i];
                if (h.Alive)
                {
                    tileRect.X = h.Tile.X;
                    tileRect.Y = h.Tile.Y;
                    spriteBatch.Draw(gameContent.ImgSpecialtile_hourglass, tileRect, Color.White);
                }
            }
            for (int i = 0; i < Tiles.hammers.Count; i++)
            {
                Hammer h = Tiles.hammers[i];
                if (h.Alive)
                {
                    tileRect.X = h.Tile.X;
                    tileRect.Y = h.Tile.Y;
                    spriteBatch.Draw(gameContent.ImgSpecialtile_hammer, tileRect, Color.White);
                }
            }
        }

        private void DrawGrid()
        {
            // Semi-transparent layer underneath the grid
            Rectangle gridRect = new Rectangle(Grid.X, Grid.Y, Grid.Width, Grid.Height);
            float opacity = 0.7f;
            pixel.SetData(new Color[] { Color.White });
            spriteBatch.Draw(pixel, gridRect, Color.White * opacity);

            // actual Grid
            for (int i = 0; i < Grid.Size[0]; i++)
            {
                for (int j = 0; j < Grid.Size[1]; j++)
                {
                    Tile t = Grid.GameGrid[i, j];
                    spriteBatch.Draw(gameContent.ImgGrid_01x01, new Rectangle(t.X, t.Y, GameMain.Tile, GameMain.Tile), Color.White);
                }
            }
        }

        private void DrawBackground()
        {
            Rectangle bgRect = new Rectangle(0, 0, GameMain.ScreenWidth, GameMain.ScreenHeight);

            // Plain blue background for when loading image below fails
            pixel.SetData(new Color[] { Color.CornflowerBlue });
            spriteBatch.Draw(pixel, bgRect, Color.White);

            spriteBatch.Draw(gameContent.ImgBgFeld, new Rectangle(0, 0, screenWidth, screenHeight), Color.White);

            // Semi-transparent layer to moderate the intense corn field
            float opacity = 0.1f;
            pixel.SetData(new Color[] { Color.White });
            spriteBatch.Draw(pixel, bgRect, Color.White * opacity);
        }

        private void DrawManual()
        {
            // TODO: Implement
        }

        private void DrawTutorialMenu()
        {
            // TODO: Implement
        }

        private void DrawStartMenu()
        {
            spriteBatch.Draw(gameContent.ImgStartmenu, new Rectangle(0, 0, screenWidth, screenHeight), Color.White);

            DrawLevelSelection(); // draw Level Select buttons and level description

            // draw other buttons
            for (int i = 0; i < GameMain.BtnsStartmenu.Length; i++)
            {
                Button b = GameMain.BtnsStartmenu[i];
                Rectangle btnRect = new Rectangle(b.X, b.Y, b.Width, b.Height);

                switch (i)
                {
                    case 0: // Cross - Quit
                        btnRect.X += 3;
                        btnRect.Y += 3;
                        btnRect.Width -= 6;
                        btnRect.Height -= 6;
                        spriteBatch.Draw(gameContent.ImgBtnCross, btnRect, Color.White);
                        DrawButtonHover(b);
                        break;
                    case 1: // Hamster settings
                        Texture2D imgHamster = pixel;
                        switch (GameMain.HamsterSkin)
                        {
                            case HamsterSkin.Default:
                                imgHamster = gameContent.ImgEnemy0large_1;
                                break;
                            case HamsterSkin.Candy:
                                imgHamster = gameContent.ImgEnemy1large_1;
                                break;
                            case HamsterSkin.Pokemon:
                                imgHamster = gameContent.ImgEnemy2large_1;
                                break;
                        }
                        spriteBatch.Draw(imgHamster, btnRect, Color.White);
                        break;
                    case 2: // Python settings
                        Texture2D imgPython = pixel;
                        switch (GameMain.PythonSkin)
                        {
                            case PythonSkin.Default:
                                imgPython = gameContent.ImgPlayer0large_3;
                                break;
                            case PythonSkin.Bloody:
                                imgPython = gameContent.ImgPlayer1large_3;
                                break;
                            case PythonSkin.Pokemon:
                                imgPython = gameContent.ImgPlayer2large_3;
                                break;
                        }
                        spriteBatch.Draw(imgPython, btnRect, Color.White);
                        break;
                    case 3: // Question mark - Manual
                        btnRect.X += 3;
                        btnRect.Y += 3;
                        btnRect.Width -= 6;
                        btnRect.Height -= 6;
                        spriteBatch.Draw(gameContent.ImgBtnQuestionMark, btnRect, Color.White);
                        DrawButtonHover(b);
                        break;
                }
                if (b.IsHover)
                {
                    switch (i)
                    {
                        case 1: // if hovering over button 1 (Hamster skins)
                            string textHamster = "";
                            switch (GameMain.HamsterSkin)
                            {
                                case HamsterSkin.Default:
                                    textHamster = "Standard-Hamster";
                                    break;
                                case HamsterSkin.Candy:
                                    textHamster = "Zuckerwatte-Hamster";
                                    break;
                                case HamsterSkin.Pokemon:
                                    textHamster = "Pokemon-Hamster";
                                    break;
                            }
                            DrawButtonHover(b, textHamster);
                            break;
                        case 2: // if hovering over button 2 (Python skins)
                            string textPython = "";
                            switch (GameMain.PythonSkin)
                            {
                                case PythonSkin.Default:
                                    textPython = "Standard-Python";
                                    break;
                                case PythonSkin.Bloody:
                                    textPython = "Blutsauger-Python";
                                    break;
                                case PythonSkin.Pokemon:
                                    textPython = "Pokemon-Python";
                                    break;
                            }
                            DrawButtonHover(b, textPython);
                            break;
                    }
                }

            }
        }

        private void DrawLevelSelection()
        {
            Rectangle gridRect = new Rectangle(smGrid.X, smGrid.Y, smGrid.Width, smGrid.Height);

            // Semi-transparent layer underneath the grid
            float opacity = 0.7f;
            pixel.SetData(new Color[] { Color.White });
            spriteBatch.Draw(pixel, gridRect, Color.White * opacity);

            spriteBatch.Draw(gameContent.ImgGrid_12x04_large, gridRect, Color.White);

            for (int i = 0; i < GameMain.BtnsLevelSelect.Length; i++)
            {
                Button b = GameMain.BtnsLevelSelect[i];
                Rectangle btnRect = new Rectangle(b.X, b.Y, b.Width, b.Height);

                if (Game2.MaxLevelAvailable < i + 1) // if level is still unavailable
                {
                    /*
                     * Draw Wall image. Slight coordinate adjustments due to Wall image already
                     * having a transparent 1px border
                     */
                    //spriteBatch.Draw(gameContent.ImgWallLarge, new Rectangle(btnRect.X - 1, btnRect.Y - 1, btnRect.Width + 2, btnRect.Height + 2), Color.White);
                    spriteBatch.Draw(gameContent.ImgWallLarge, btnRect, Color.White);
                }
                else // if level is available, then draw respective Korn image
                {
                    Texture2D imgKorn = pixel;
                    switch (i + 1)
                    {
                        case 1:
                            imgKorn = gameContent.ImgKorn01;
                            break;
                        case 2:
                            imgKorn = gameContent.ImgKorn02;
                            break;
                        case 3:
                            imgKorn = gameContent.ImgKorn03;
                            break;
                        case 4:
                            imgKorn = gameContent.ImgKorn04;
                            break;
                        case 5:
                            imgKorn = gameContent.ImgKorn05;
                            break;
                        case 6:
                            imgKorn = gameContent.ImgKorn06;
                            break;
                        case 7:
                            imgKorn = gameContent.ImgKorn07;
                            break;
                        case 8:
                            imgKorn = gameContent.ImgKorn08;
                            break;
                        case 9:
                            imgKorn = gameContent.ImgKorn09;
                            break;
                        case 10:
                            imgKorn = gameContent.ImgKorn10;
                            break;
                        case 11:
                            imgKorn = gameContent.ImgKorn11;
                            break;
                        case 12:
                            imgKorn = gameContent.ImgKorn12;
                            break;
                    }
                    spriteBatch.Draw(imgKorn, btnRect, Color.White);

                    if (b.IsHover)
                    {
                        spriteBatch.Draw(pixel, btnRect, Color.Black * 0.2f);
                        DrawLvlDesc(i + 1);
                    }
                }
            }
        }

        private void DrawLvlDesc(int level)
        {
            int x = smGrid.X;
            int y = (int)(smGrid.Y - (screenHeight / 9.6));
            string levelDesc = "Level " + level;
            switch (level)
            {
                case 1:
                    levelDesc = "Level " + level + ": Die Jagd beginnt...";
                    break;
                case 2:
                    levelDesc = "Level " + level + ": Um Mauern schlaengeln";
                    break;
                case 3:
                    levelDesc = "Level " + level + ": Wie eine Fliege";
                    break;
                case 4:
                    levelDesc = "Level " + level + ": Im Labyrinth";
                    break;
                case 5:
                    levelDesc = "Level " + level + ": Chronismus";
                    break;
                case 6:
                    levelDesc = "Level " + level + ": Im Silo";
                    break;
                case 7:
                    levelDesc = "Level " + level + ": Familientreffen";
                    break;
                case 8:
                    levelDesc = "Level " + level + ": Zugemauert";
                    break;
                case 9:
                    levelDesc = "Level " + level + ": Das volle Programm";
                    break;
                case 10:
                    levelDesc = "Level " + level + ": Baustelle";
                    break;
                case 11:
                    levelDesc = "Level " + level + ": Im Hamsternest";
                    break;
                case 12:
                    levelDesc = "Level " + level + ": Hamsterinflation";
                    break;
            }
            spriteBatch.DrawString(gameContent.LabelFont, levelDesc, new Vector2(x, y), Color.White);

        }

        private void DrawButtonHover(Button b, string hoverText, SpriteFont font)
        {
            // draw Layer over the Button that is hovered on
            if (b.IsHover)
            {
                Rectangle btnRect = new Rectangle(b.X, b.Y, b.Width, b.Height);
                pixel.SetData(new Color[] { Color.White });
                spriteBatch.Draw(pixel, btnRect, Color.Black * 0.2f);

                if (font == null) font = gameContent.LabelFont; // default font if not specified
                if (hoverText != null) spriteBatch.DrawString(font, hoverText, new Vector2(GameMain.SmGrid.X, GameMain.SmGrid.Y - (screenHeight / 9.5f)), Color.White);
            }
        }
        private void DrawButtonHover(Button b)
        {
            DrawButtonHover(b, null, null);
        }
        private void DrawButtonHover(Button b, string hoverText)
        {
            DrawButtonHover(b, hoverText, null);
        }

        private void DrawGameTimer()
        {
            // Long switch statement below, collapse it!
            int w = (int)(screenWidth / 20.5);
            int h = (int)(screenHeight / 3.5);
            int x = (int)(Grid.X + Grid.Width + (screenWidth / 30.75));
            int y = Grid.Y + Grid.Height / 2 - h / 2; // is vertically centered with grid

            Texture2D imgGameTimer = pixel;

            switch (GameTimer.GameTime)
            {
                case 1:
                    imgGameTimer = gameContent.ImgGametimer01;
                    break;
                case 2:
                    imgGameTimer = gameContent.ImgGametimer02;
                    break;
                case 3:
                    imgGameTimer = gameContent.ImgGametimer03;
                    break;
                case 4:
                    imgGameTimer = gameContent.ImgGametimer04;
                    break;
                case 5:
                    imgGameTimer = gameContent.ImgGametimer05;
                    break;
                case 6:
                    imgGameTimer = gameContent.ImgGametimer06;
                    break;
                case 7:
                    imgGameTimer = gameContent.ImgGametimer07;
                    break;
                case 8:
                    imgGameTimer = gameContent.ImgGametimer08;
                    break;
                case 9:
                    imgGameTimer = gameContent.ImgGametimer09;
                    break;
                case 10:
                    imgGameTimer = gameContent.ImgGametimer10;
                    break;
                case 11:
                    imgGameTimer = gameContent.ImgGametimer11;
                    break;
                case 12:
                    imgGameTimer = gameContent.ImgGametimer12;
                    break;
                case 13:
                    imgGameTimer = gameContent.ImgGametimer13;
                    break;
                case 14:
                    imgGameTimer = gameContent.ImgGametimer14;
                    break;
                case 15:
                    imgGameTimer = gameContent.ImgGametimer15;
                    break;
                case 16:
                    imgGameTimer = gameContent.ImgGametimer16;
                    break;
                case 17:
                    imgGameTimer = gameContent.ImgGametimer17;
                    break;
                case 18:
                    imgGameTimer = gameContent.ImgGametimer18;
                    break;
                case 19:
                    imgGameTimer = gameContent.ImgGametimer19;
                    break;
                case 20:
                    imgGameTimer = gameContent.ImgGametimer20;
                    break;
                case 21:
                    imgGameTimer = gameContent.ImgGametimer21;
                    break;
                case 22:
                    imgGameTimer = gameContent.ImgGametimer22;
                    break;
                case 23:
                    imgGameTimer = gameContent.ImgGametimer23;
                    break;
                case 24:
                    imgGameTimer = gameContent.ImgGametimer24;
                    break;
                case 25:
                    imgGameTimer = gameContent.ImgGametimer25;
                    break;
                case 26:
                    imgGameTimer = gameContent.ImgGametimer26;
                    break;
                case 27:
                    imgGameTimer = gameContent.ImgGametimer27;
                    break;
                case 28:
                    imgGameTimer = gameContent.ImgGametimer28;
                    break;
                case 29:
                    imgGameTimer = gameContent.ImgGametimer29;
                    break;
                case 30:
                    imgGameTimer = gameContent.ImgGametimer30;
                    break;
                case 31:
                    imgGameTimer = gameContent.ImgGametimer31;
                    break;
                case 32:
                    imgGameTimer = gameContent.ImgGametimer32;
                    break;
                case 33:
                    imgGameTimer = gameContent.ImgGametimer33;
                    break;
                case 34:
                    imgGameTimer = gameContent.ImgGametimer34;
                    break;
                case 35:
                    imgGameTimer = gameContent.ImgGametimer35;
                    break;
                case 36:
                    imgGameTimer = gameContent.ImgGametimer36;
                    break;
                case 37:
                    imgGameTimer = gameContent.ImgGametimer37;
                    break;
                case 38:
                    imgGameTimer = gameContent.ImgGametimer38;
                    break;
                case 39:
                    imgGameTimer = gameContent.ImgGametimer39;
                    break;
                case 40:
                    imgGameTimer = gameContent.ImgGametimer40;
                    break;
                case 41:
                    imgGameTimer = gameContent.ImgGametimer41;
                    break;
                case 42:
                    imgGameTimer = gameContent.ImgGametimer42;
                    break;
                case 43:
                    imgGameTimer = gameContent.ImgGametimer43;
                    break;
                case 44:
                    imgGameTimer = gameContent.ImgGametimer44;
                    break;
                case 45:
                    imgGameTimer = gameContent.ImgGametimer45;
                    break;
                case 46:
                    imgGameTimer = gameContent.ImgGametimer46;
                    break;
                case 47:
                    imgGameTimer = gameContent.ImgGametimer47;
                    break;
                case 48:
                    imgGameTimer = gameContent.ImgGametimer48;
                    break;
                case 49:
                    imgGameTimer = gameContent.ImgGametimer49;
                    break;
                case 50:
                    imgGameTimer = gameContent.ImgGametimer50;
                    break;
                case 51:
                    imgGameTimer = gameContent.ImgGametimer51;
                    break;
                case 52:
                    imgGameTimer = gameContent.ImgGametimer52;
                    break;
                case 53:
                    imgGameTimer = gameContent.ImgGametimer53;
                    break;
                case 54:
                    imgGameTimer = gameContent.ImgGametimer54;
                    break;
                case 55:
                    imgGameTimer = gameContent.ImgGametimer55;
                    break;
                case 56:
                    imgGameTimer = gameContent.ImgGametimer56;
                    break;
                case 57:
                    imgGameTimer = gameContent.ImgGametimer57;
                    break;
                case 58:
                    imgGameTimer = gameContent.ImgGametimer58;
                    break;
                case 59:
                    imgGameTimer = gameContent.ImgGametimer59;
                    break;
                case 60:
                    imgGameTimer = gameContent.ImgGametimer60;
                    break;
                case 61:
                    imgGameTimer = gameContent.ImgGametimer61;
                    break;
                case 62:
                    imgGameTimer = gameContent.ImgGametimer62;
                    break;
                case 63:
                    imgGameTimer = gameContent.ImgGametimer63;
                    break;
                case 64:
                    imgGameTimer = gameContent.ImgGametimer64;
                    break;
                case 65:
                    imgGameTimer = gameContent.ImgGametimer65;
                    break;
                case 66:
                    imgGameTimer = gameContent.ImgGametimer66;
                    break;
                case 67:
                    imgGameTimer = gameContent.ImgGametimer67;
                    break;
                case 68:
                    imgGameTimer = gameContent.ImgGametimer68;
                    break;
                case 69:
                    imgGameTimer = gameContent.ImgGametimer69;
                    break;
                case 70:
                    imgGameTimer = gameContent.ImgGametimer70;
                    break;
                case 71:
                    imgGameTimer = gameContent.ImgGametimer71;
                    break;
                case 72:
                    imgGameTimer = gameContent.ImgGametimer72;
                    break;
                case 73:
                    imgGameTimer = gameContent.ImgGametimer73;
                    break;
                case 74:
                    imgGameTimer = gameContent.ImgGametimer74;
                    break;
                case 75:
                    imgGameTimer = gameContent.ImgGametimer75;
                    break;
                case 76:
                    imgGameTimer = gameContent.ImgGametimer76;
                    break;
                case 77:
                    imgGameTimer = gameContent.ImgGametimer77;
                    break;
                case 78:
                    imgGameTimer = gameContent.ImgGametimer78;
                    break;
                case 79:
                    imgGameTimer = gameContent.ImgGametimer79;
                    break;
                case 80:
                    imgGameTimer = gameContent.ImgGametimer80;
                    break;
                case 81:
                    imgGameTimer = gameContent.ImgGametimer81;
                    break;
                case 82:
                    imgGameTimer = gameContent.ImgGametimer82;
                    break;
                case 83:
                    imgGameTimer = gameContent.ImgGametimer83;
                    break;
                case 84:
                    imgGameTimer = gameContent.ImgGametimer84;
                    break;
                case 85:
                    imgGameTimer = gameContent.ImgGametimer85;
                    break;
                case 86:
                    imgGameTimer = gameContent.ImgGametimer86;
                    break;
                case 87:
                    imgGameTimer = gameContent.ImgGametimer87;
                    break;
                case 88:
                    imgGameTimer = gameContent.ImgGametimer88;
                    break;
                case 89:
                    imgGameTimer = gameContent.ImgGametimer89;
                    break;
            }

            spriteBatch.Draw(imgGameTimer, new Rectangle(x, y, w, h), Color.White);
        }

    }
}
