using Python3.chars;
using Python3.clocks;
using Python3.game;

namespace Python3.tiles
{
    class Hourglass : SpecialTile
    {
        public Hourglass() : base() { }
        public Hourglass(int gridX, int gridY) : base(gridX, gridY) { }

        public void Activate(Enemy e)
        {
            Alive = false;
            Tile.Content.Remove(this);

            // increase game speed for limited period of time

            double duration = Level.HourglassEDuration; // duration in seconds
            double factor = Level.HourglassEFactor; // factor by which time is increased

            Game2.tc.NModify++; // queue a time modification

            if (!GameTimer.IsModified)
            {
                Game2.tc.ST_activatedByEnemy = true;
                Game2.tc.Modify_Start(duration, factor);
            }
            else // if time is currently modified
            {
                if (!Game2.tc.ST_activatedByEnemy) // if current time modifier was not activated by an enemy
                {
                    Game2.tc.ST_activatedByEnemy = true;
                    Game2.tc.Modify_Stop();
                    Game2.tc.Modify_Start(duration, factor);
                }
            }
        }

        public void Activate(Player p)
        {
            Alive = false;
            Tile.Content.Remove(this);

            // slow down game speed for limited period of time

            double duration = Level.HourglassPDuration; // duration in seconds
            double factor = Level.HourglassPFactor; // factor by which time is increased

            Game2.tc.NModify++; // queue a time modification

            if (!GameTimer.IsModified)
            {
                Game2.tc.Modify_Start(duration, factor);
                Game2.tc.ST_activatedByEnemy = false;
            }
            else // if time is currently modified
            {
                if (Game2.tc.ST_activatedByEnemy) // if current time modifier was activated by an enemy
                {
                    Game2.tc.Modify_Stop();
                    Game2.tc.Modify_Start(duration, factor);
                    Game2.tc.ST_activatedByEnemy = false;
                }
            }

        }
    }

}
