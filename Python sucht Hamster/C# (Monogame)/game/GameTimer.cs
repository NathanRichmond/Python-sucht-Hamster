using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Python3.game
{
    class GameTimer
    {
        public static double GameDuration; // time in seconds until game is over
        public static int AmountImages = 89; // amount of different images for animation
        public static int GameTime; // image that is to be displayed currently
        public static bool IsModified;

        // When making a new GameTimer, set GameTime to 1 and IsModified to false
        public GameTimer()
        {
            GameTime = 1;
            IsModified = false;
        }
    }
}
