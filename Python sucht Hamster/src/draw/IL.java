package draw;

import javafx.scene.image.Image;

public class IL {

	/*
	 * ============================= BACKGROUND =============================
	 */
	public static Image bggras = new Image("file:rsc/bg/feld.png");

	/*
	 * ============================= GRID =============================
	 */
	public static Image igrid_ntransp = new Image("file:rsc/bg/grid/grid_10x10_ntransp.png"); // currently unused
	public static Image igrid_05x05 = new Image("file:rsc/bg/grid/grid_05x05.png");
	public static Image igrid_05x10 = new Image("file:rsc/bg/grid/grid_05x10.png");
	public static Image igrid_08x05 = new Image("file:rsc/bg/grid/grid_08x05.png");
	public static Image igrid_08x08 = new Image("file:rsc/bg/grid/grid_08x08.png");
	public static Image igrid_10x05 = new Image("file:rsc/bg/grid/grid_10x05.png");
	public static Image igrid_10x10 = new Image("file:rsc/bg/grid/grid_10x10.png");
	public static Image igrid_12x04 = new Image("file:rsc/bg/grid/grid_12x04.png");
	public static Image igrid_12x04_large = new Image("file:rsc/bg/grid/grid_12x04_large.png");
	public static Image igrid_12x05 = new Image("file:rsc/bg/grid/grid_12x05.png");
	public static Image igrid_14x03 = new Image("file:rsc/bg/grid/grid_14x03.png");
	public static Image igrid_15x01 = new Image("file:rsc/bg/grid/grid_15x01.png");
	public static Image igrid_15x08 = new Image("file:rsc/bg/grid/grid_15x08.png");
	public static Image igrid_20x20 = new Image("file:rsc/bg/grid/grid_20x20.png");
	public static Image igrid_32x20 = new Image("file:rsc/bg/grid/grid_32x20.png");
	public static Image igrid_40x20 = new Image("file:rsc/bg/grid/grid_40x20.png");

	/*
	 * ============================= CHARACTERS =============================
	 * Variable names: i<entity><skinindex><possible modification>_<faceDirection>
	 */
	/*
	 * Hamster
	 */
	// Default (Gui.hamsterSkin: 0)
	public static Image ienemy0_0 = new Image("file:rsc/chars/hamster/default_skin/default-hamster_0.png");
	public static Image ienemy0_1 = new Image("file:rsc/chars/hamster/default_skin/default-hamster_1.png");
	public static Image ienemy0_2 = new Image("file:rsc/chars/hamster/default_skin/default-hamster_2.png");
	public static Image ienemy0_3 = new Image("file:rsc/chars/hamster/default_skin/default-hamster_3.png");
	// Default upgraded
	public static Image ienemy0u_0 = new Image("file:rsc/chars/hamster/default_skin/default-hamster-u_0.png");
	public static Image ienemy0u_1 = new Image("file:rsc/chars/hamster/default_skin/default-hamster-u_1.png");
	public static Image ienemy0u_2 = new Image("file:rsc/chars/hamster/default_skin/default-hamster-u_2.png");
	public static Image ienemy0u_3 = new Image("file:rsc/chars/hamster/default_skin/default-hamster-u_3.png");

	// Candy (Gui.hamsterSkin: 1)
	public static Image ienemy1_0 = new Image("file:rsc/chars/hamster/candy_skin/candy-hamster_0.png");
	public static Image ienemy1_1 = new Image("file:rsc/chars/hamster/candy_skin/candy-hamster_1.png");
	public static Image ienemy1_2 = new Image("file:rsc/chars/hamster/candy_skin/candy-hamster_2.png");
	public static Image ienemy1_3 = new Image("file:rsc/chars/hamster/candy_skin/candy-hamster_3.png");
	// Candy upgraded
	public static Image ienemy1u_0 = new Image("file:rsc/chars/hamster/candy_skin/candy-hamster-u_0.png");
	public static Image ienemy1u_1 = new Image("file:rsc/chars/hamster/candy_skin/candy-hamster-u_1.png");
	public static Image ienemy1u_2 = new Image("file:rsc/chars/hamster/candy_skin/candy-hamster-u_2.png");
	public static Image ienemy1u_3 = new Image("file:rsc/chars/hamster/candy_skin/candy-hamster-u_3.png");

	// Pokemon (Gui.hamsterSkin: 2)
	public static Image ienemy2_0 = new Image("file:rsc/chars/hamster/pokemon_skin/pokemon-hamster_0.png");
	public static Image ienemy2_1 = new Image("file:rsc/chars/hamster/pokemon_skin/pokemon-hamster_1.png");
	public static Image ienemy2_2 = new Image("file:rsc/chars/hamster/pokemon_skin/pokemon-hamster_2.png");
	public static Image ienemy2_3 = new Image("file:rsc/chars/hamster/pokemon_skin/pokemon-hamster_3.png");
	// Pokemon upgraded
	public static Image ienemy2u_0 = new Image("file:rsc/chars/hamster/pokemon_skin/pokemon-hamster-u_0.png");
	public static Image ienemy2u_1 = new Image("file:rsc/chars/hamster/pokemon_skin/pokemon-hamster-u_1.png");
	public static Image ienemy2u_2 = new Image("file:rsc/chars/hamster/pokemon_skin/pokemon-hamster-u_2.png");
	public static Image ienemy2u_3 = new Image("file:rsc/chars/hamster/pokemon_skin/pokemon-hamster-u_3.png");

	/*
	 * Python
	 */
	// Default (Gui.pythonSkin: 0)
	public static Image iplayer0_0 = new Image("file:rsc/chars/python/default_skin/default-python_0.png");
	public static Image iplayer0_1 = new Image("file:rsc/chars/python/default_skin/default-python_1.png");
	public static Image iplayer0_2 = new Image("file:rsc/chars/python/default_skin/default-python_2.png");
	public static Image iplayer0_3 = new Image("file:rsc/chars/python/default_skin/default-python_3.png");

	// Bloody (Gui.pythonSkin: 1)
	public static Image iplayer1_0 = new Image("file:rsc/chars/python/bloody_skin/bloody-python_0.png");
	public static Image iplayer1_1 = new Image("file:rsc/chars/python/bloody_skin/bloody-python_1.png");
	public static Image iplayer1_2 = new Image("file:rsc/chars/python/bloody_skin/bloody-python_2.png");
	public static Image iplayer1_3 = new Image("file:rsc/chars/python/bloody_skin/bloody-python_3.png");

	// Pokemon (Gui.pythonSkin: 2)
	public static Image iplayer2_0 = new Image("file:rsc/chars/python/pokemon_skin/pokemon-python_0.png");
	public static Image iplayer2_1 = new Image("file:rsc/chars/python/pokemon_skin/pokemon-python_1.png");
	public static Image iplayer2_2 = new Image("file:rsc/chars/python/pokemon_skin/pokemon-python_2.png");
	public static Image iplayer2_3 = new Image("file:rsc/chars/python/pokemon_skin/pokemon-python_3.png");

	/*
	 * Start Menu icons
	 */
	public static Image iplayer0large_3 = new Image("file:rsc/chars/python/default_skin/default-python_3.png");
	public static Image iplayer1large_3 = new Image("file:rsc/chars/python/bloody_skin/bloody-python_3.png");
	public static Image iplayer2large_3 = new Image("file:rsc/chars/python/pokemon_skin/pokemon-python_3.png");
	public static Image ienemy0large_1 = new Image("file:rsc/chars/hamster/default_skin/default-hamster-large_1.png");
	public static Image ienemy1large_1 = new Image("file:rsc/chars/hamster/candy_skin/candy-hamster-large_1.png");
	public static Image ienemy2large_1 = new Image("file:rsc/chars/hamster/pokemon_skin/pokemon-hamster-large_1.png");

	/*
	 * ============================= SPECIAL TILES =============================
	 */
	/*
	 * Wall
	 */
	public static Image iwall = new Image("file:rsc/game/wall.png");
	public static Image iwalllarge = new Image("file:rsc/game/wall_large.png");

	/*
	 * Korn
	 */
	public static Image ispecialtile_korn = new Image("file:rsc/game/specialtiles/korn.png");

	/*
	 * Babyhamster
	 */
	public static Image ispecialtile_babyhamsterTwo = new Image("file:rsc/game/specialtiles/babyhamster_two.png");
	public static Image ispecialtile_babyhamsterThree = new Image("file:rsc/game/specialtiles/babyhamster_three.png");
	public static Image ispecialtile_babyhamsterFour = new Image("file:rsc/game/specialtiles/babyhamster_four.png");

	/*
	 * Hourglass
	 */
	public static Image ispecialtile_hourglass = new Image("file:rsc/game/specialtiles/hourglass.png");

	/*
	 * Hammer
	 */
	public static Image ispecialtile_hammer = new Image("file:rsc/game/specialtiles/hammer.png");

	/*
	 * ============================= LEVEL TITLE =============================
	 */
	public static Image ilvltitle1 = new Image("file:rsc/game/ui/lvl_title/level1.png");
	public static Image ilvltitle2 = new Image("file:rsc/game/ui/lvl_title/level2.png");
	public static Image ilvltitle3 = new Image("file:rsc/game/ui/lvl_title/level3.png");
	public static Image ilvltitle4 = new Image("file:rsc/game/ui/lvl_title/level4.png");
	public static Image ilvltitle5 = new Image("file:rsc/game/ui/lvl_title/level5.png");
	public static Image ilvltitle6 = new Image("file:rsc/game/ui/lvl_title/level6.png");
	public static Image ilvltitle7 = new Image("file:rsc/game/ui/lvl_title/level7.png");
	public static Image ilvltitle8 = new Image("file:rsc/game/ui/lvl_title/level8.png");
	public static Image ilvltitle9 = new Image("file:rsc/game/ui/lvl_title/level9.png");
	public static Image ilvltitle10 = new Image("file:rsc/game/ui/lvl_title/level10.png");
	public static Image ilvltitle11 = new Image("file:rsc/game/ui/lvl_title/level11.png");
	public static Image ilvltitle12 = new Image("file:rsc/game/ui/lvl_title/level12.png");

	/*
	 * ============================= BUTTONS =============================
	 */
	/*
	 * Ingame Buttons
	 */
	public static Image ibpause = new Image("file:rsc/game/ui/buttons/buttons_ingame/button_pause.png");
	public static Image ibplay = new Image("file:rsc/game/ui/buttons/buttons_ingame/button_play.png");
	public static Image ibrestart = new Image("file:rsc/game/ui/buttons/buttons_ingame/button_restart_circle.png");
	public static Image ibexit = new Image("file:rsc/game/ui/buttons/buttons_ingame/button_stop.png");
	public static Image ibnext = new Image("file:rsc/game/ui/buttons/buttons_ingame/button_next.png");

	/*
	 * Koerner for Level Select
	 */
	public static Image ikorn1 = new Image("file:rsc/game/ui/buttons/buttons_lvlselect/button_korn1.png");
	public static Image ikorn2 = new Image("file:rsc/game/ui/buttons/buttons_lvlselect/button_korn2.png");
	public static Image ikorn3 = new Image("file:rsc/game/ui/buttons/buttons_lvlselect/button_korn3.png");
	public static Image ikorn4 = new Image("file:rsc/game/ui/buttons/buttons_lvlselect/button_korn4.png");
	public static Image ikorn5 = new Image("file:rsc/game/ui/buttons/buttons_lvlselect/button_korn5.png");
	public static Image ikorn6 = new Image("file:rsc/game/ui/buttons/buttons_lvlselect/button_korn6.png");
	public static Image ikorn7 = new Image("file:rsc/game/ui/buttons/buttons_lvlselect/button_korn7.png");
	public static Image ikorn8 = new Image("file:rsc/game/ui/buttons/buttons_lvlselect/button_korn8.png");
	public static Image ikorn9 = new Image("file:rsc/game/ui/buttons/buttons_lvlselect/button_korn9.png");
	public static Image ikorn10 = new Image("file:rsc/game/ui/buttons/buttons_lvlselect/button_korn10.png");
	public static Image ikorn11 = new Image("file:rsc/game/ui/buttons/buttons_lvlselect/button_korn11.png");
	public static Image ikorn12 = new Image("file:rsc/game/ui/buttons/buttons_lvlselect/button_korn12.png");

	/*
	 * Cross Button
	 */
	public static Image ibcross = new Image("file:rsc/game/ui/buttons/button_cross.png");

	/*
	 * Question mark button
	 */
	public static Image ibquestionmark = new Image("file:rsc/game/ui/buttons/button_questionmark.png");

	/*
	 * Manual Buttons
	 */
	public static Image ikeys = new Image("file:rsc/manual/300px-Arrow_keys[1].jpg");
	public static Image ibbabyhamster = new Image("file:rsc/game/ui/buttons/button_babyhamster.png");
	public static Image ibzeitleiste = new Image("file:rsc/game/ui/buttons/button_zeitleiste.png");
	public static Image ibclipboard = new Image("file:rsc/game/ui/buttons/button_clipboard.png");
	public static Image ibcompiler = new Image("file:rsc/game/ui/buttons/button_compiler.png");

	/*
	 * ============================= SCREENS =============================
	 */
	/*
	 * Start Menu Screen
	 */
	public static Image istartmenu = new Image("file:rsc/game/ui/startmenu.png");
	public static Image ilvldesc1 = new Image("file:rsc/game/ui/lvl_description/leveldesc1.png");
	public static Image ilvldesc2 = new Image("file:rsc/game/ui/lvl_description/leveldesc2.png");
	public static Image ilvldesc3 = new Image("file:rsc/game/ui/lvl_description/leveldesc3.png");
	public static Image ilvldesc4 = new Image("file:rsc/game/ui/lvl_description/leveldesc4.png");
	public static Image ilvldesc5 = new Image("file:rsc/game/ui/lvl_description/leveldesc5.png");
	public static Image ilvldesc6 = new Image("file:rsc/game/ui/lvl_description/leveldesc6.png");
	public static Image ilvldesc7 = new Image("file:rsc/game/ui/lvl_description/leveldesc7.png");
	public static Image ilvldesc8 = new Image("file:rsc/game/ui/lvl_description/leveldesc8.png");
	public static Image ilvldesc9 = new Image("file:rsc/game/ui/lvl_description/leveldesc9.png");
	public static Image ilvldesc10 = new Image("file:rsc/game/ui/lvl_description/leveldesc10.png");
	public static Image ilvldesc11 = new Image("file:rsc/game/ui/lvl_description/leveldesc11.png");
	public static Image ilvldesc12 = new Image("file:rsc/game/ui/lvl_description/leveldesc12.png");

	/*
	 * Tutorial Menu Screen
	 */
	public static Image itutorialmenu = new Image("file:rsc/game/ui/tutorialmenuscreen.png");

	/*
	 * Victory Screen
	 */
	public static Image ivictory = new Image("file:rsc/game/ui/victoryscreen.png");

	/*
	 * Defeat Screen
	 */
	public static Image idefeat = new Image("file:rsc/game/ui/defeatscreen.png");

	/*
	 * ============================= GAME TIMER =============================
	 */
	public static Image igametimer01 = new Image("file:rsc/game/gametimer/frames/gametimer_01.png");
	public static Image igametimer02 = new Image("file:rsc/game/gametimer/frames/gametimer_02.png");
	public static Image igametimer03 = new Image("file:rsc/game/gametimer/frames/gametimer_03.png");
	public static Image igametimer04 = new Image("file:rsc/game/gametimer/frames/gametimer_04.png");
	public static Image igametimer05 = new Image("file:rsc/game/gametimer/frames/gametimer_05.png");
	public static Image igametimer06 = new Image("file:rsc/game/gametimer/frames/gametimer_06.png");
	public static Image igametimer07 = new Image("file:rsc/game/gametimer/frames/gametimer_07.png");
	public static Image igametimer08 = new Image("file:rsc/game/gametimer/frames/gametimer_08.png");
	public static Image igametimer09 = new Image("file:rsc/game/gametimer/frames/gametimer_09.png");
	public static Image igametimer10 = new Image("file:rsc/game/gametimer/frames/gametimer_10.png");
	public static Image igametimer11 = new Image("file:rsc/game/gametimer/frames/gametimer_11.png");
	public static Image igametimer12 = new Image("file:rsc/game/gametimer/frames/gametimer_12.png");
	public static Image igametimer13 = new Image("file:rsc/game/gametimer/frames/gametimer_13.png");
	public static Image igametimer14 = new Image("file:rsc/game/gametimer/frames/gametimer_14.png");
	public static Image igametimer15 = new Image("file:rsc/game/gametimer/frames/gametimer_15.png");
	public static Image igametimer16 = new Image("file:rsc/game/gametimer/frames/gametimer_16.png");
	public static Image igametimer17 = new Image("file:rsc/game/gametimer/frames/gametimer_17.png");
	public static Image igametimer18 = new Image("file:rsc/game/gametimer/frames/gametimer_18.png");
	public static Image igametimer19 = new Image("file:rsc/game/gametimer/frames/gametimer_19.png");
	public static Image igametimer20 = new Image("file:rsc/game/gametimer/frames/gametimer_20.png");
	public static Image igametimer21 = new Image("file:rsc/game/gametimer/frames/gametimer_21.png");
	public static Image igametimer22 = new Image("file:rsc/game/gametimer/frames/gametimer_22.png");
	public static Image igametimer23 = new Image("file:rsc/game/gametimer/frames/gametimer_23.png");
	public static Image igametimer24 = new Image("file:rsc/game/gametimer/frames/gametimer_24.png");
	public static Image igametimer25 = new Image("file:rsc/game/gametimer/frames/gametimer_25.png");
	public static Image igametimer26 = new Image("file:rsc/game/gametimer/frames/gametimer_26.png");
	public static Image igametimer27 = new Image("file:rsc/game/gametimer/frames/gametimer_27.png");
	public static Image igametimer28 = new Image("file:rsc/game/gametimer/frames/gametimer_28.png");
	public static Image igametimer29 = new Image("file:rsc/game/gametimer/frames/gametimer_29.png");
	public static Image igametimer30 = new Image("file:rsc/game/gametimer/frames/gametimer_30.png");
	public static Image igametimer31 = new Image("file:rsc/game/gametimer/frames/gametimer_31.png");
	public static Image igametimer32 = new Image("file:rsc/game/gametimer/frames/gametimer_32.png");
	public static Image igametimer33 = new Image("file:rsc/game/gametimer/frames/gametimer_33.png");
	public static Image igametimer34 = new Image("file:rsc/game/gametimer/frames/gametimer_34.png");
	public static Image igametimer35 = new Image("file:rsc/game/gametimer/frames/gametimer_35.png");
	public static Image igametimer36 = new Image("file:rsc/game/gametimer/frames/gametimer_36.png");
	public static Image igametimer37 = new Image("file:rsc/game/gametimer/frames/gametimer_37.png");
	public static Image igametimer38 = new Image("file:rsc/game/gametimer/frames/gametimer_38.png");
	public static Image igametimer39 = new Image("file:rsc/game/gametimer/frames/gametimer_39.png");
	public static Image igametimer40 = new Image("file:rsc/game/gametimer/frames/gametimer_40.png");
	public static Image igametimer41 = new Image("file:rsc/game/gametimer/frames/gametimer_41.png");
	public static Image igametimer42 = new Image("file:rsc/game/gametimer/frames/gametimer_42.png");
	public static Image igametimer43 = new Image("file:rsc/game/gametimer/frames/gametimer_43.png");
	public static Image igametimer44 = new Image("file:rsc/game/gametimer/frames/gametimer_44.png");
	public static Image igametimer45 = new Image("file:rsc/game/gametimer/frames/gametimer_45.png");
	public static Image igametimer46 = new Image("file:rsc/game/gametimer/frames/gametimer_46.png");
	public static Image igametimer47 = new Image("file:rsc/game/gametimer/frames/gametimer_47.png");
	public static Image igametimer48 = new Image("file:rsc/game/gametimer/frames/gametimer_48.png");
	public static Image igametimer49 = new Image("file:rsc/game/gametimer/frames/gametimer_49.png");
	public static Image igametimer50 = new Image("file:rsc/game/gametimer/frames/gametimer_50.png");
	public static Image igametimer51 = new Image("file:rsc/game/gametimer/frames/gametimer_51.png");
	public static Image igametimer52 = new Image("file:rsc/game/gametimer/frames/gametimer_52.png");
	public static Image igametimer53 = new Image("file:rsc/game/gametimer/frames/gametimer_53.png");
	public static Image igametimer54 = new Image("file:rsc/game/gametimer/frames/gametimer_54.png");
	public static Image igametimer55 = new Image("file:rsc/game/gametimer/frames/gametimer_55.png");
	public static Image igametimer56 = new Image("file:rsc/game/gametimer/frames/gametimer_56.png");
	public static Image igametimer57 = new Image("file:rsc/game/gametimer/frames/gametimer_57.png");
	public static Image igametimer58 = new Image("file:rsc/game/gametimer/frames/gametimer_58.png");
	public static Image igametimer59 = new Image("file:rsc/game/gametimer/frames/gametimer_59.png");
	public static Image igametimer60 = new Image("file:rsc/game/gametimer/frames/gametimer_60.png");
	public static Image igametimer61 = new Image("file:rsc/game/gametimer/frames/gametimer_61.png");
	public static Image igametimer62 = new Image("file:rsc/game/gametimer/frames/gametimer_62.png");
	public static Image igametimer63 = new Image("file:rsc/game/gametimer/frames/gametimer_63.png");
	public static Image igametimer64 = new Image("file:rsc/game/gametimer/frames/gametimer_64.png");
	public static Image igametimer65 = new Image("file:rsc/game/gametimer/frames/gametimer_65.png");
	public static Image igametimer66 = new Image("file:rsc/game/gametimer/frames/gametimer_66.png");
	public static Image igametimer67 = new Image("file:rsc/game/gametimer/frames/gametimer_67.png");
	public static Image igametimer68 = new Image("file:rsc/game/gametimer/frames/gametimer_68.png");
	public static Image igametimer69 = new Image("file:rsc/game/gametimer/frames/gametimer_69.png");
	public static Image igametimer70 = new Image("file:rsc/game/gametimer/frames/gametimer_70.png");
	public static Image igametimer71 = new Image("file:rsc/game/gametimer/frames/gametimer_71.png");
	public static Image igametimer72 = new Image("file:rsc/game/gametimer/frames/gametimer_72.png");
	public static Image igametimer73 = new Image("file:rsc/game/gametimer/frames/gametimer_73.png");
	public static Image igametimer74 = new Image("file:rsc/game/gametimer/frames/gametimer_74.png");
	public static Image igametimer75 = new Image("file:rsc/game/gametimer/frames/gametimer_75.png");
	public static Image igametimer76 = new Image("file:rsc/game/gametimer/frames/gametimer_76.png");
	public static Image igametimer77 = new Image("file:rsc/game/gametimer/frames/gametimer_77.png");
	public static Image igametimer78 = new Image("file:rsc/game/gametimer/frames/gametimer_78.png");
	public static Image igametimer79 = new Image("file:rsc/game/gametimer/frames/gametimer_79.png");
	public static Image igametimer80 = new Image("file:rsc/game/gametimer/frames/gametimer_80.png");
	public static Image igametimer81 = new Image("file:rsc/game/gametimer/frames/gametimer_81.png");
	public static Image igametimer82 = new Image("file:rsc/game/gametimer/frames/gametimer_82.png");
	public static Image igametimer83 = new Image("file:rsc/game/gametimer/frames/gametimer_83.png");
	public static Image igametimer84 = new Image("file:rsc/game/gametimer/frames/gametimer_84.png");
	public static Image igametimer85 = new Image("file:rsc/game/gametimer/frames/gametimer_85.png");
	public static Image igametimer86 = new Image("file:rsc/game/gametimer/frames/gametimer_86.png");
	public static Image igametimer87 = new Image("file:rsc/game/gametimer/frames/gametimer_87.png");
	public static Image igametimer88 = new Image("file:rsc/game/gametimer/frames/gametimer_88.png");
	public static Image igametimer89 = new Image("file:rsc/game/gametimer/frames/gametimer_89.png");

}
