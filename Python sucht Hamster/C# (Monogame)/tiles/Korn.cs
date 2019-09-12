using Python3.chars;
using Python3.game;

namespace Python3.tiles
{
    class Korn : SpecialTile
    {
        public Korn() : base() { }
        public Korn(int gridX, int gridY) : base(gridX, gridY) { }

        public void Activate(Enemy e)
        {
            Alive = false;
            Tile.Content.Remove(this);

            e.em.NSpeedBoost++; // queue a speed boost

            // increase Enemy speed for limited period of time
            if (!e.SpeedBoosted)
            {
                double duration = Level.KornDuration; // duration in seconds
                double speedBoost = Level.KornBoost; // factor by which base speed is increased
                e.em.SpeedBoost_Start(duration, speedBoost);
            }
        }
    }

}
