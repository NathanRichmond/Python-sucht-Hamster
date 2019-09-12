using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Content;

namespace Python3.draw
{
    class GameContent
    {
        public Texture2D TxtrGeneric;

        // public SoundEffect exampleSound { get; set; }

        public SpriteFont LabelFont;

        /*
         * ============================= BACKGROUND =============================
         */
        public Texture2D ImgBgFeld;

        /*
         * ============================= GRID =============================
         */
        public Texture2D ImgGrid_01x01;
        public Texture2D ImgGrid_05x05;
        public Texture2D ImgGrid_05x10;
        public Texture2D ImgGrid_08x05;
        public Texture2D ImgGrid_08x08;
        public Texture2D ImgGrid_10x05;
        public Texture2D ImgGrid_10x10;
        public Texture2D ImgGrid_10x10_ntransp;
        public Texture2D ImgGrid_12x01_large;
        public Texture2D ImgGrid_12x02_large;
        public Texture2D ImgGrid_12x04;
        public Texture2D ImgGrid_12x04_large;
        public Texture2D ImgGrid_12x05;
        public Texture2D ImgGrid_14x03;
        public Texture2D ImgGrid_15x01;
        public Texture2D ImgGrid_15x08;
        public Texture2D ImgGrid_20x20;
        public Texture2D ImgGrid_32x20;
        public Texture2D ImgGrid_40x20;

        /*
         * ============================= CHARACTERS =============================
         * Variable names: i<entity><skinindex><possible modification>_<faceDirection>
         */
        /*
         * Hamster
         */
        // Default (Gui.hamsterSkin: 0)
        public Texture2D ImgEnemy0_0;
        public Texture2D ImgEnemy0_1;
        public Texture2D ImgEnemy0_2;
        public Texture2D ImgEnemy0_3;
        // Default upgraded
        public Texture2D ImgEnemy0u_0;
        public Texture2D ImgEnemy0u_1;
        public Texture2D ImgEnemy0u_2;
        public Texture2D ImgEnemy0u_3;

        // Candy (Gui.hamsterSkin: 1)
        public Texture2D ImgEnemy1_0;
        public Texture2D ImgEnemy1_1;
        public Texture2D ImgEnemy1_2;
        public Texture2D ImgEnemy1_3;
        // Candy upgraded
        public Texture2D ImgEnemy1u_0;
        public Texture2D ImgEnemy1u_1;
        public Texture2D ImgEnemy1u_2;
        public Texture2D ImgEnemy1u_3;

        // Pokemon (Gui.hamsterSkin: 2)
        public Texture2D ImgEnemy2_0;
        public Texture2D ImgEnemy2_1;
        public Texture2D ImgEnemy2_2;
        public Texture2D ImgEnemy2_3;
        // Pokemon upgraded
        public Texture2D ImgEnemy2u_0;
        public Texture2D ImgEnemy2u_1;
        public Texture2D ImgEnemy2u_2;
        public Texture2D ImgEnemy2u_3;

        /*
         * Python
         */
        // Default (Gui.pythonSkin: 0)
        public Texture2D ImgPlayer0_0;
        public Texture2D ImgPlayer0_1;
        public Texture2D ImgPlayer0_2;
        public Texture2D ImgPlayer0_3;

        // Bloody (Gui.pythonSkin: 1)
        public Texture2D ImgPlayer1_0;
        public Texture2D ImgPlayer1_1;
        public Texture2D ImgPlayer1_2;
        public Texture2D ImgPlayer1_3;

        // Pokemon (Gui.pythonSkin: 2)
        public Texture2D ImgPlayer2_0;
        public Texture2D ImgPlayer2_1;
        public Texture2D ImgPlayer2_2;
        public Texture2D ImgPlayer2_3;

        /*
         * Start Menu icons
         */
        public Texture2D ImgPlayer0large_3;
        public Texture2D ImgPlayer1large_3;
        public Texture2D ImgPlayer2large_3;
        public Texture2D ImgEnemy0large_1;
        public Texture2D ImgEnemy1large_1;
        public Texture2D ImgEnemy2large_1;

        /*
         * ============================= SPECIAL TILES =============================
         */
        // Wall
        public Texture2D ImgWall;
        public Texture2D ImgWallLarge;
        // Korn
        public Texture2D ImgSpecialtile_korn;
        // Babyhamster
        public Texture2D ImgSpecialtile_babyhamsterTwo;
        public Texture2D ImgSpecialtile_babyhamsterThree;
        public Texture2D ImgSpecialtile_babyhamsterFour;
        // Hourglass
        public Texture2D ImgSpecialtile_hourglass;
        // Hammer
        public Texture2D ImgSpecialtile_hammer;
        // Eggs
        public Texture2D ImgSpecialtile_egg;
        public Texture2D ImgSpecialtile_egg2;

        /*
         * ============================= LEVEL TITLE =============================
         */
        public Texture2D ImgLvltitle01;
        public Texture2D ImgLvltitle02;
        public Texture2D ImgLvltitle03;
        public Texture2D ImgLvltitle04;
        public Texture2D ImgLvltitle05;
        public Texture2D ImgLvltitle06;
        public Texture2D ImgLvltitle07;
        public Texture2D ImgLvltitle08;
        public Texture2D ImgLvltitle09;
        public Texture2D ImgLvltitle10;
        public Texture2D ImgLvltitle11;
        public Texture2D ImgLvltitle12;

        /*
         * ============================= BUTTONS =============================
         */
        /*
         * Ingame Buttons
         */
        public Texture2D ImgBtnPause;
        public Texture2D ImgBtnPlay;
        public Texture2D ImgBtnRestart;
        public Texture2D ImgBtnExit;
        public Texture2D ImgBtnNext;

        /*
         * Koerner for Level Select
         */
        public Texture2D ImgKorn01;
        public Texture2D ImgKorn02;
        public Texture2D ImgKorn03;
        public Texture2D ImgKorn04;
        public Texture2D ImgKorn05;
        public Texture2D ImgKorn06;
        public Texture2D ImgKorn07;
        public Texture2D ImgKorn08;
        public Texture2D ImgKorn09;
        public Texture2D ImgKorn10;
        public Texture2D ImgKorn11;
        public Texture2D ImgKorn12;

        /*
         * Cross Button
         */
        public Texture2D ImgBtnCross;

        /*
         * Question mark button
         */
        public Texture2D ImgBtnQuestionMark;

        /*
         * House button
         */
        public Texture2D ImgBtnHouse;

        /*
         * Manual Buttons
         */
        public Texture2D ImgBtnKeys;
        public Texture2D ImgBtnBabyhamster;
        public Texture2D ImgBtnZeitleiste;
        public Texture2D ImgBtnClipboard;
        public Texture2D ImgBtnCompiler;
        //public Texture2D ImgBtnBack;

        /*
         * Anleitung Buttons
         */
        public Texture2D ImgLeft;
        public Texture2D ImgRight;
        public Texture2D ImgUp;
        public Texture2D ImgDown;

        /*
         * ============================= SCREENS =============================
         */
        /*
         * Start Menu Screen
         */
        public Texture2D ImgStartmenu;

        /*
         * Tutorial Menu Screen
         */
        public Texture2D ImgTutorialmenu;

        /*
         * Victory GIF
         */
        public Texture2D ImgVictory;

        /*
         * Defeat GIF
         */
        public Texture2D ImgDefeat;

        /*
         * ============================= ANLEITUNG IMAGES =============================
         */
        public Texture2D ImgManual0;
        public Texture2D ImgPicture;
        public Texture2D ImgDownKey;
        public Texture2D ImgUpKey;
        public Texture2D ImgRightKey;
        public Texture2D ImgLeftKey;

        /*
         * ============================= GAME TIMER =============================
         */
        public Texture2D ImgGametimer01;
        public Texture2D ImgGametimer02;
        public Texture2D ImgGametimer03;
        public Texture2D ImgGametimer04;
        public Texture2D ImgGametimer05;
        public Texture2D ImgGametimer06;
        public Texture2D ImgGametimer07;
        public Texture2D ImgGametimer08;
        public Texture2D ImgGametimer09;
        public Texture2D ImgGametimer10;
        public Texture2D ImgGametimer11;
        public Texture2D ImgGametimer12;
        public Texture2D ImgGametimer13;
        public Texture2D ImgGametimer14;
        public Texture2D ImgGametimer15;
        public Texture2D ImgGametimer16;
        public Texture2D ImgGametimer17;
        public Texture2D ImgGametimer18;
        public Texture2D ImgGametimer19;
        public Texture2D ImgGametimer20;
        public Texture2D ImgGametimer21;
        public Texture2D ImgGametimer22;
        public Texture2D ImgGametimer23;
        public Texture2D ImgGametimer24;
        public Texture2D ImgGametimer25;
        public Texture2D ImgGametimer26;
        public Texture2D ImgGametimer27;
        public Texture2D ImgGametimer28;
        public Texture2D ImgGametimer29;
        public Texture2D ImgGametimer30;
        public Texture2D ImgGametimer31;
        public Texture2D ImgGametimer32;
        public Texture2D ImgGametimer33;
        public Texture2D ImgGametimer34;
        public Texture2D ImgGametimer35;
        public Texture2D ImgGametimer36;
        public Texture2D ImgGametimer37;
        public Texture2D ImgGametimer38;
        public Texture2D ImgGametimer39;
        public Texture2D ImgGametimer40;
        public Texture2D ImgGametimer41;
        public Texture2D ImgGametimer42;
        public Texture2D ImgGametimer43;
        public Texture2D ImgGametimer44;
        public Texture2D ImgGametimer45;
        public Texture2D ImgGametimer46;
        public Texture2D ImgGametimer47;
        public Texture2D ImgGametimer48;
        public Texture2D ImgGametimer49;
        public Texture2D ImgGametimer50;
        public Texture2D ImgGametimer51;
        public Texture2D ImgGametimer52;
        public Texture2D ImgGametimer53;
        public Texture2D ImgGametimer54;
        public Texture2D ImgGametimer55;
        public Texture2D ImgGametimer56;
        public Texture2D ImgGametimer57;
        public Texture2D ImgGametimer58;
        public Texture2D ImgGametimer59;
        public Texture2D ImgGametimer60;
        public Texture2D ImgGametimer61;
        public Texture2D ImgGametimer62;
        public Texture2D ImgGametimer63;
        public Texture2D ImgGametimer64;
        public Texture2D ImgGametimer65;
        public Texture2D ImgGametimer66;
        public Texture2D ImgGametimer67;
        public Texture2D ImgGametimer68;
        public Texture2D ImgGametimer69;
        public Texture2D ImgGametimer70;
        public Texture2D ImgGametimer71;
        public Texture2D ImgGametimer72;
        public Texture2D ImgGametimer73;
        public Texture2D ImgGametimer74;
        public Texture2D ImgGametimer75;
        public Texture2D ImgGametimer76;
        public Texture2D ImgGametimer77;
        public Texture2D ImgGametimer78;
        public Texture2D ImgGametimer79;
        public Texture2D ImgGametimer80;
        public Texture2D ImgGametimer81;
        public Texture2D ImgGametimer82;
        public Texture2D ImgGametimer83;
        public Texture2D ImgGametimer84;
        public Texture2D ImgGametimer85;
        public Texture2D ImgGametimer86;
        public Texture2D ImgGametimer87;
        public Texture2D ImgGametimer88;
        public Texture2D ImgGametimer89;

    
        /*
         * ----- Load from file path -----
         */
        
        public GameContent(ContentManager Content, GraphicsDevice graphicsDevice)
        {
            // Images
            ImgBgFeld = Content.Load<Texture2D>("bg/feld");
            ImgGrid_01x01 = Content.Load<Texture2D>("bg/grid/grid_01x01");
            ImgGrid_05x05 = Content.Load<Texture2D>("bg/grid/grid_05x05");
            ImgGrid_05x10 = Content.Load<Texture2D>("bg/grid/grid_05x10");
            ImgGrid_08x05 = Content.Load<Texture2D>("bg/grid/grid_08x05");
            ImgGrid_08x08 = Content.Load<Texture2D>("bg/grid/grid_08x08");
            ImgGrid_10x05 = Content.Load<Texture2D>("bg/grid/grid_10x05");
            ImgGrid_10x10 = Content.Load<Texture2D>("bg/grid/grid_10x10");
            ImgGrid_10x10_ntransp = Content.Load<Texture2D>("bg/grid/grid_10x10_ntransp");
            ImgGrid_12x01_large = Content.Load<Texture2D>("bg/grid/grid_12x01_large");
            ImgGrid_12x02_large = Content.Load<Texture2D>("bg/grid/grid_12x02_large");
            ImgGrid_12x04 = Content.Load<Texture2D>("bg/grid/grid_12x04");
            ImgGrid_12x04_large = Content.Load<Texture2D>("bg/grid/grid_12x04_large");
            ImgGrid_12x05 = Content.Load<Texture2D>("bg/grid/grid_12x05");
            ImgGrid_14x03 = Content.Load<Texture2D>("bg/grid/grid_14x03");
            ImgGrid_15x01 = Content.Load<Texture2D>("bg/grid/grid_15x01");
            ImgGrid_15x08 = Content.Load<Texture2D>("bg/grid/grid_15x08");
            ImgGrid_20x20 = Content.Load<Texture2D>("bg/grid/grid_20x20");
            ImgGrid_32x20 = Content.Load<Texture2D>("bg/grid/grid_32x20");
            ImgGrid_40x20 = Content.Load<Texture2D>("bg/grid/grid_40x20");
            ImgEnemy0_0 = Content.Load<Texture2D>("chars/hamster/default_skin/default-hamster_0");
            ImgEnemy0_1 = Content.Load<Texture2D>("chars/hamster/default_skin/default-hamster_1");
            ImgEnemy0_2 = Content.Load<Texture2D>("chars/hamster/default_skin/default-hamster_2");
            ImgEnemy0_3 = Content.Load<Texture2D>("chars/hamster/default_skin/default-hamster_3");
            ImgEnemy0u_0 = Content.Load<Texture2D>("chars/hamster/default_skin/default-hamster-u_0");
            ImgEnemy0u_1 = Content.Load<Texture2D>("chars/hamster/default_skin/default-hamster-u_1");
            ImgEnemy0u_2 = Content.Load<Texture2D>("chars/hamster/default_skin/default-hamster-u_2");
            ImgEnemy0u_3 = Content.Load<Texture2D>("chars/hamster/default_skin/default-hamster-u_3");
            ImgEnemy1_0 = Content.Load<Texture2D>("chars/hamster/candy_skin/candy-hamster_0");
            ImgEnemy1_1 = Content.Load<Texture2D>("chars/hamster/candy_skin/candy-hamster_1");
            ImgEnemy1_2 = Content.Load<Texture2D>("chars/hamster/candy_skin/candy-hamster_2");
            ImgEnemy1_3 = Content.Load<Texture2D>("chars/hamster/candy_skin/candy-hamster_3");
            ImgEnemy1u_0 = Content.Load<Texture2D>("chars/hamster/candy_skin/candy-hamster-u_0");
            ImgEnemy1u_1 = Content.Load<Texture2D>("chars/hamster/candy_skin/candy-hamster-u_1");
            ImgEnemy1u_2 = Content.Load<Texture2D>("chars/hamster/candy_skin/candy-hamster-u_2");
            ImgEnemy1u_3 = Content.Load<Texture2D>("chars/hamster/candy_skin/candy-hamster-u_3");
            ImgEnemy2_0 = Content.Load<Texture2D>("chars/hamster/pokemon_skin/pokemon-hamster_0");
            ImgEnemy2_1 = Content.Load<Texture2D>("chars/hamster/pokemon_skin/pokemon-hamster_1");
            ImgEnemy2_2 = Content.Load<Texture2D>("chars/hamster/pokemon_skin/pokemon-hamster_2");
            ImgEnemy2_3 = Content.Load<Texture2D>("chars/hamster/pokemon_skin/pokemon-hamster_3");
            ImgEnemy2u_0 = Content.Load<Texture2D>("chars/hamster/pokemon_skin/pokemon-hamster-u_0");
            ImgEnemy2u_1 = Content.Load<Texture2D>("chars/hamster/pokemon_skin/pokemon-hamster-u_1");
            ImgEnemy2u_2 = Content.Load<Texture2D>("chars/hamster/pokemon_skin/pokemon-hamster-u_2");
            ImgEnemy2u_3 = Content.Load<Texture2D>("chars/hamster/pokemon_skin/pokemon-hamster-u_3");
            ImgPlayer0_0 = Content.Load<Texture2D>("chars/python/default_skin/default-python_0");
            ImgPlayer0_1 = Content.Load<Texture2D>("chars/python/default_skin/default-python_1");
            ImgPlayer0_2 = Content.Load<Texture2D>("chars/python/default_skin/default-python_2");
            ImgPlayer0_3 = Content.Load<Texture2D>("chars/python/default_skin/default-python_3");
            ImgPlayer1_0 = Content.Load<Texture2D>("chars/python/bloody_skin/bloody-python_0");
            ImgPlayer1_1 = Content.Load<Texture2D>("chars/python/bloody_skin/bloody-python_1");
            ImgPlayer1_2 = Content.Load<Texture2D>("chars/python/bloody_skin/bloody-python_2");
            ImgPlayer1_3 = Content.Load<Texture2D>("chars/python/bloody_skin/bloody-python_3");
            ImgPlayer2_0 = Content.Load<Texture2D>("chars/python/pokemon_skin/pokemon-python_0");
            ImgPlayer2_1 = Content.Load<Texture2D>("chars/python/pokemon_skin/pokemon-python_1");
            ImgPlayer2_2 = Content.Load<Texture2D>("chars/python/pokemon_skin/pokemon-python_2");
            ImgPlayer2_3 = Content.Load<Texture2D>("chars/python/pokemon_skin/pokemon-python_3");
            ImgPlayer0large_3 = Content.Load<Texture2D>("chars/python/default_skin/default-python-large_3");
            ImgPlayer1large_3 = Content.Load<Texture2D>("chars/python/bloody_skin/bloody-python-large_3");
            ImgPlayer2large_3 = Content.Load<Texture2D>("chars/python/pokemon_skin/pokemon-python-large_3");
            ImgEnemy0large_1 = Content.Load<Texture2D>("chars/hamster/default_skin/default-hamster-large_1");
            ImgEnemy1large_1 = Content.Load<Texture2D>("chars/hamster/candy_skin/candy-hamster-large_1");
            ImgEnemy2large_1 = Content.Load<Texture2D>("chars/hamster/pokemon_skin/pokemon-hamster-large_1");
            ImgWall = Content.Load<Texture2D>("game/wall");
            ImgWallLarge = Content.Load<Texture2D>("game/wall_large");
            ImgSpecialtile_korn = Content.Load<Texture2D>("game/specialtiles/korn");
            ImgSpecialtile_babyhamsterTwo = Content.Load<Texture2D>("game/specialtiles/babyhamster_two");
            ImgSpecialtile_babyhamsterThree = Content.Load<Texture2D>("game/specialtiles/babyhamster_three");
            ImgSpecialtile_babyhamsterFour = Content.Load<Texture2D>("game/specialtiles/babyhamster_four");
            ImgSpecialtile_hourglass = Content.Load<Texture2D>("game/specialtiles/hourglass");
            ImgSpecialtile_hammer = Content.Load<Texture2D>("game/specialtiles/hammer");
            //ImgSpecialtile_egg = Content.Load<Texture2D>("game/specialtiles/egg");
            //ImgSpecialtile_egg2 = Content.Load<Texture2D>("game/specialtiles/egg2");
            ImgLvltitle01 = Content.Load<Texture2D>("game/ui/lvl_title/level1");
            ImgLvltitle02 = Content.Load<Texture2D>("game/ui/lvl_title/level2");
            ImgLvltitle03 = Content.Load<Texture2D>("game/ui/lvl_title/level3");
            ImgLvltitle04 = Content.Load<Texture2D>("game/ui/lvl_title/level4");
            ImgLvltitle05 = Content.Load<Texture2D>("game/ui/lvl_title/level5");
            ImgLvltitle06 = Content.Load<Texture2D>("game/ui/lvl_title/level6");
            ImgLvltitle07 = Content.Load<Texture2D>("game/ui/lvl_title/level7");
            ImgLvltitle08 = Content.Load<Texture2D>("game/ui/lvl_title/level8");
            ImgLvltitle09 = Content.Load<Texture2D>("game/ui/lvl_title/level9");
            ImgLvltitle10 = Content.Load<Texture2D>("game/ui/lvl_title/level9");
            ImgLvltitle11 = Content.Load<Texture2D>("game/ui/lvl_title/level11");
            ImgLvltitle12 = Content.Load<Texture2D>("game/ui/lvl_title/level12");
            ImgBtnPause = Content.Load<Texture2D>("game/ui/buttons/buttons_ingame/button_pause");
            ImgBtnPlay = Content.Load<Texture2D>("game/ui/buttons/buttons_ingame/button_play");
            ImgBtnRestart = Content.Load<Texture2D>("game/ui/buttons/buttons_ingame/button_restart_circle");
            ImgBtnExit = Content.Load<Texture2D>("game/ui/buttons/buttons_ingame/button_stop");
            ImgBtnNext = Content.Load<Texture2D>("game/ui/buttons/buttons_ingame/button_next");
            ImgKorn01 = Content.Load<Texture2D>("game/ui/buttons/buttons_lvlselect/button_korn1");
            ImgKorn02 = Content.Load<Texture2D>("game/ui/buttons/buttons_lvlselect/button_korn2");
            ImgKorn03 = Content.Load<Texture2D>("game/ui/buttons/buttons_lvlselect/button_korn3");
            ImgKorn04 = Content.Load<Texture2D>("game/ui/buttons/buttons_lvlselect/button_korn4");
            ImgKorn05 = Content.Load<Texture2D>("game/ui/buttons/buttons_lvlselect/button_korn5");
            ImgKorn06 = Content.Load<Texture2D>("game/ui/buttons/buttons_lvlselect/button_korn6");
            ImgKorn07 = Content.Load<Texture2D>("game/ui/buttons/buttons_lvlselect/button_korn7");
            ImgKorn08 = Content.Load<Texture2D>("game/ui/buttons/buttons_lvlselect/button_korn8");
            ImgKorn09 = Content.Load<Texture2D>("game/ui/buttons/buttons_lvlselect/button_korn9");
            ImgKorn10 = Content.Load<Texture2D>("game/ui/buttons/buttons_lvlselect/button_korn10");
            ImgKorn11 = Content.Load<Texture2D>("game/ui/buttons/buttons_lvlselect/button_korn11");
            ImgKorn12 = Content.Load<Texture2D>("game/ui/buttons/buttons_lvlselect/button_korn12");
            ImgBtnCross = Content.Load<Texture2D>("game/ui/buttons/button_cross");
            ImgBtnQuestionMark = Content.Load<Texture2D>("game/ui/buttons/button_questionmark");
            ImgBtnHouse = Content.Load<Texture2D>("game/ui/buttons/button_house");
            ImgBtnKeys = Content.Load<Texture2D>("manual/300px-Arrow_keys[1]");
            ImgBtnBabyhamster = Content.Load<Texture2D>("game/ui/buttons/button_babyhamster");
            ImgBtnZeitleiste = Content.Load<Texture2D>("game/ui/buttons/button_zeitleiste");
            ImgBtnClipboard = Content.Load<Texture2D>("game/ui/buttons/button_clipboard");
            ImgBtnCompiler = Content.Load<Texture2D>("game/ui/buttons/button_compiler");
            //ImgBtnBack = Content.Load<Texture2D>("game/ui/buttons/button_back");
            ImgLeft = Content.Load<Texture2D>("manual/Left");
            ImgRight = Content.Load<Texture2D>("manual/Right");
            ImgUp = Content.Load<Texture2D>("manual/Oben");
            ImgDown = Content.Load<Texture2D>("manual/Unten");
            ImgStartmenu = Content.Load<Texture2D>("game/ui/startmenu");
            ImgTutorialmenu = Content.Load<Texture2D>("game/ui/tutorialmenuscreen");
            ImgVictory = Content.Load<Texture2D>("game/ui/mdified victory");
            ImgDefeat = Content.Load<Texture2D>("game/ui/defeat");
            ImgManual0 = Content.Load<Texture2D>("manual/manual0");
            ImgPicture = Content.Load<Texture2D>("manual/CutOut");
            ImgDownKey = Content.Load<Texture2D>("manual/downKey");
            ImgUpKey = Content.Load<Texture2D>("manual/upKey");
            ImgRightKey = Content.Load<Texture2D>("manual/rightKey");
            ImgLeftKey = Content.Load<Texture2D>("manual/leftKey");
            ImgGametimer01 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_01");
            ImgGametimer02 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_02");
            ImgGametimer03 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_03");
            ImgGametimer04 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_04");
            ImgGametimer05 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_05");
            ImgGametimer06 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_06");
            ImgGametimer07 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_07");
            ImgGametimer08 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_08");
            ImgGametimer09 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_09");
            ImgGametimer10 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_10");
            ImgGametimer11 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_11");
            ImgGametimer12 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_12");
            ImgGametimer13 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_13");
            ImgGametimer14 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_14");
            ImgGametimer15 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_15");
            ImgGametimer16 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_16");
            ImgGametimer17 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_17");
            ImgGametimer18 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_18");
            ImgGametimer19 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_19");
            ImgGametimer20 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_20");
            ImgGametimer21 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_21");
            ImgGametimer22 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_22");
            ImgGametimer23 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_23");
            ImgGametimer24 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_24");
            ImgGametimer25 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_25");
            ImgGametimer26 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_26");
            ImgGametimer27 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_27");
            ImgGametimer28 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_28");
            ImgGametimer29 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_29");
            ImgGametimer30 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_30");
            ImgGametimer31 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_31");
            ImgGametimer32 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_32");
            ImgGametimer33 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_33");
            ImgGametimer34 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_34");
            ImgGametimer35 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_35");
            ImgGametimer36 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_36");
            ImgGametimer37 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_37");
            ImgGametimer38 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_38");
            ImgGametimer39 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_39");
            ImgGametimer40 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_40");
            ImgGametimer41 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_41");
            ImgGametimer42 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_42");
            ImgGametimer43 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_43");
            ImgGametimer44 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_44");
            ImgGametimer45 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_45");
            ImgGametimer46 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_46");
            ImgGametimer47 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_47");
            ImgGametimer48 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_48");
            ImgGametimer49 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_49");
            ImgGametimer50 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_50");
            ImgGametimer51 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_51");
            ImgGametimer52 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_52");
            ImgGametimer53 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_53");
            ImgGametimer54 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_54");
            ImgGametimer55 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_55");
            ImgGametimer56 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_56");
            ImgGametimer57 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_57");
            ImgGametimer58 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_58");
            ImgGametimer59 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_59");
            ImgGametimer60 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_60");
            ImgGametimer61 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_61");
            ImgGametimer62 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_62");
            ImgGametimer63 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_63");
            ImgGametimer64 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_64");
            ImgGametimer65 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_65");
            ImgGametimer66 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_66");
            ImgGametimer67 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_67");
            ImgGametimer68 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_68");
            ImgGametimer69 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_69");
            ImgGametimer70 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_70");
            ImgGametimer71 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_71");
            ImgGametimer72 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_72");
            ImgGametimer73 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_73");
            ImgGametimer74 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_74");
            ImgGametimer75 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_75");
            ImgGametimer76 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_76");
            ImgGametimer77 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_77");
            ImgGametimer78 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_78");
            ImgGametimer79 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_79");
            ImgGametimer80 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_80");
            ImgGametimer81 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_81");
            ImgGametimer82 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_82");
            ImgGametimer83 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_83");
            ImgGametimer84 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_84");
            ImgGametimer85 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_85");
            ImgGametimer86 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_86");
            ImgGametimer87 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_87");
            ImgGametimer88 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_88");
            ImgGametimer89 = Content.Load<Texture2D>("game/gametimer/frames/gametimer_89");

            // Sounds
            // exampleSound = Content.Load<SoundEffect>("exampleSound");

            // Fonts
            LabelFont = Content.Load<SpriteFont>("Constantia20");

            //generic Texture for layers etc. ("pixel")
            TxtrGeneric = new Texture2D(graphicsDevice, 1, 1);

            

        }
    }
}
