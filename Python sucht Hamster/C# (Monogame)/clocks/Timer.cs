using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Python3.clocks
{
    public class Timer
    {
        /*
         * instantiation props
         */
        long Delay; // time before executing for the first time, in milliseconds
        long Period; // intervals between executions, in milliseconds
        int Repetitions; // amount of repetitions to execute. infinite if set to a value <0
        Action Run; // the method to execute

        /*
         * public props
         */
        public bool Running = false; // was Start() already called?

        /*
         * internal vars
         */
        long startTime = 0; // time at which the timer starts/started (after waiting out the delay)
        bool hasStarted = false; // has Updater.cs already called UpdateTime() once?
        bool delayCompleted = false; // is the delay already over?

        long timeOfLastExecute = 0;
        long timeTillNextExecute = 0;

        public Timer(long delay, long period, Action run)
        {
            Init(delay, period, -1, run);
        }

        public Timer(long delay, long period, int repetitions, Action run)
        {
            Init(delay, period, repetitions, run);
        }

        private void Init(long delay, long period, int repetitions, Action run)
        {
            Delay = delay;
            Period = period;
            Repetitions = repetitions;
            Run = run;

            Updater.Timers.Add(this);
        }

        // this is only called by Updater.cs if running == true
        public void UpdateTime(long currentTimeMillis)
        {
            if (!hasStarted) // only execute this on first update after starting
            {
                startTime = currentTimeMillis + Delay;
                hasStarted = true;
            }

            if (!delayCompleted && currentTimeMillis >= startTime)
            {
                delayCompleted = true;
                timeOfLastExecute = currentTimeMillis;
                timeTillNextExecute = Period;
            }
            else if (delayCompleted)
            {
                Execute(currentTimeMillis);
            }

        }

        private void Execute(long currentTimeMillis)
        {
            if (Repetitions != 0)
            {
                timeTillNextExecute = timeOfLastExecute + Period - currentTimeMillis;
                if (timeTillNextExecute <= 0)
                {
                    timeOfLastExecute = currentTimeMillis;
                    timeTillNextExecute = Period;
                    Repetitions--;
                    Run();
                }
            }
            else
            {
                Stop();
            }
        }

        public void Start()
        {
            Running = true;
        }

        public void Pause()
        {
            Running = false;
        }

        public void Stop()
        {
            Running = false;
            Updater.Timers.Remove(this); // basically kill the timer
        }
    }
}
