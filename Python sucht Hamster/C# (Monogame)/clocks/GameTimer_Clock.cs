using Python3;
using Python3.clocks;
using Python3.game;
using System;

namespace Python3.clocks
{
    public class GameTimer_Clock
    {

        /*
	    * Normal timer
	    */
        private Timer timer1;
        private int delay = 0;
        private double period; // is set in constructor, because GameTimer.gameDuration varies

        /*
         * For last two images (mirror crack):
         */
        private Timer timer2;
        private int delay2 = 200; // time between third to last and second to last GameTimer image
        private int period2 = 50; // time between second to last and last GameTimer image

        /*
         * Modified timer
         */
        private Timer ST_timer;
        private int ST_images;
        private int ST_imagecounter;
        public int NModify = 0; // number of queued modifications
        public bool ST_activatedByEnemy = false;

        public GameTimer_Clock()
        {
            /*
             * Interval between updates: Get total gameDuration (is in seconds --> multiply
             * with 1000 to have milliseconds), divide it by amount of GameTimer images in
             * order to switch to next GameTimer image at even intervals
             */
            period = GameTimer.GameDuration * 1000 / GameTimer.AmountImages;
        }

        public void Start()
        {
            timer1 = new Timer(delay, (long)period, Run1);
            timer1.Start();
        }

        private void Run1()
        {
            if (!GameTimer.IsModified)
            {

                if (GameTimer.GameTime < GameTimer.AmountImages - 2) // all images except last two
                {
                    if (GameMain.Gamestate == GameState.Ingame) GameTimer.GameTime += 1;
                }
                else // has reached third to last image
                {
                    timer1.Stop();
                    timer2 = new Timer(delay2, period2, 3, Run2);
                    timer2.Start(); // start second timer
                }

                if (GameMain.Gamestate != GameState.Ingame && GameMain.Gamestate != GameState.Pause)
                {
                    Stop();
                }
            }
        }

        private void Run2()
        {
            if (GameTimer.GameTime < GameTimer.AmountImages)
            {
                GameTimer.GameTime += 1;
            }
            else if (GameMain.Gamestate != GameState.Victory) // last image, end was reached. set to defeat, but only if it is not already victory
            {
                GameMain.Gamestate = GameState.Defeat;
            }
        }

        public void Modify_Start(double duration, double factor)
        {
            if (GameTimer.GameTime < GameTimer.AmountImages - 2) // no effect if last two images are already reached
            {
                long ST_period = (long)(period / factor);
                ST_images = (int)(duration / period);

                timer1.Pause();
                GameTimer.IsModified = true;

                ST_timer = new Timer(0, ST_period, ST_Run);
                ST_imagecounter = 0;
                ST_timer.Start();
            }
        }

        private void ST_Run()
        {
            if (GameMain.Gamestate != GameState.Ingame && GameMain.Gamestate != GameState.Pause)
            {
                Modify_Stop();
            }
            else
            {
                if (GameTimer.GameTime < GameTimer.AmountImages - 2) // all images except last two
                {
                    if (ST_imagecounter <= ST_images)
                    {
                        if (GameMain.Gamestate == GameState.Ingame) GameTimer.GameTime += 1;
                    }
                    else // cycle completed
                    {
                        NModify--;

                        if (NModify > 0) // more boosts queued?
                        {
                            ST_imagecounter = 0; // then restart
                        }
                        else // all done
                        {
                            Modify_Stop();
                        }
                    }
                }
                else // has reached third to last image
                {
                    Modify_Stop();
                }
            }
        }

        public void Modify_Stop()
        {
            ST_timer.Stop();
            GameTimer.IsModified = false;
            timer1.Start(); // resume regular gameTimer
        }

        public void Stop()
        {
            try
            {
                timer1.Stop();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }

            try
            {
                timer2.Stop();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }

            try
            {
                ST_timer.Stop();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }

            GameTimer.IsModified = false;
        }

    }
}