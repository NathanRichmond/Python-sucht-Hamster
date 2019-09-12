using Python3.chars;
using Python3.data;
using Python3.game;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Python3.clocks
{
    class Enemy_Movement
    {

        private Enemy e;

        public Timer Timer;
        public Timer SB_Timer;

        private long delay;
        private long period; // Interval: 1 / e.Speed (Enemy moves within one >second<) * 1000 (as period is in >milliseconds<)

        private int SB_moves;
        private int SB_movecounter = 0;
        public int NSpeedBoost = 0; // number of queued speed boosts

        private int interval = 5; // interval between hamsterinflation spawns
        private int i = 0; // internal int for hamsterinflation

        public Enemy_Movement(Enemy enemy)
        {
            e = enemy;
            double enemyspeed = e.Speed;

            /*
             * Convert e.Speed to timer period
             */
            if (enemyspeed == 0) enemyspeed = 4.656612875245796924105750827168e-7; // smallest number possible to create the maximum long

            period = (long)(1000 / enemyspeed); // time until next move; see top

            /*
             * Generate random delay in time margin of one move. If Enemy is not supposed to move (speed==0), make delay maximum
             */
            delay = enemyspeed == 0 ? period : DataUtils.r.Next((int)period);
        }

        public void Start()
        {
            Timer = new Timer(delay, period, Run);
            e.ai = new EnemyAI(e);
            Timer.Start();
        }

        private void Run()
        {
            if (GameMain.Gamestate == GameState.Ingame)
            {
                e.ai.AI_Move(); // make a move

                if (Level.Hamsterinflation) Hamsterinflation();

                // Stop timer if Enemy is dead or game is quit
                if (!e.Alive || (GameMain.Gamestate != GameState.Ingame && GameMain.Gamestate != GameState.Pause))
                {
                    Timer.Stop();
                    i = 0;
                }
            }
        }

        public void SpeedBoost_Start(double duration, double boost)
        {
            long SB_period = (long)(period / boost); // boosted speed, converted to timer period
            SB_moves = (int)((duration * 1000) / SB_period);

            Timer.Pause();
            e.SpeedBoosted = true;

            SB_Timer = new Timer(0, SB_period, SB_Run);
            SB_movecounter = 0;
            SB_Timer.Start();
        }

        private void SB_Run()
        {
            if (GameMain.Gamestate == GameState.Ingame)
            {
                Console.WriteLine("movecounter: {0}\tmoves: {1}", SB_movecounter, SB_moves); // debug
                if (SB_movecounter <= SB_moves)
                {
                    e.ai.AI_Move(); // make a move

                    if (Level.Hamsterinflation) Hamsterinflation();

                    // Stop timer if Enemy is dead or game is quit
                    if (!e.Alive || (GameMain.Gamestate != GameState.Ingame && GameMain.Gamestate != GameState.Pause))
                    {
                        SB_Timer.Stop();
                        Timer.Stop();
                    }

                    SB_movecounter++;
                }
                else // cycle completed
                {
                    Console.WriteLine("Cycle completed. Reduce NSpeedBoost by 1 from {0} to {1}.", NSpeedBoost, NSpeedBoost-1); // debug
                    NSpeedBoost--;

                    Console.WriteLine("{0} boosts queued.", NSpeedBoost); // debug
                    if (NSpeedBoost > 0)
                    { // more boosts queued?
                        SB_movecounter = 0; // then restart
                    }
                    else
                    { // all done
                        SpeedBoost_Stop();
                    }
                }
            }
        }

        private void SpeedBoost_Stop()
        {
            SB_Timer.Stop();
            e.SpeedBoosted = false;
            Timer.Start(); // resume regular movement
        }

        private void Hamsterinflation()
        {
            if (i == interval)
            { // every i moves
                Console.WriteLine("Enemy {0} called Hamsterinflation().", e.Index); // debug
                Game2.Hamsterinflation();
                i = 0;
            }
            i++;

        }

    }
}
