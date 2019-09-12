using Python3.chars;
using Python3.clocks;
using Python3.game;

namespace Python3.tiles
{
    class BabyhamsterTwo : SpecialTile
    {
        public BabyhamsterTwo() : base() { }
        public BabyhamsterTwo(int gridX, int gridY) : base(gridX, gridY) { }

        private Player p = Game2.p;
        private Timer timer;

        public void Activate()
        {
            Alive = false;
            Tile.Content.Remove(this);

            p.Move(p.FaceDirection); // one instant step, followed by the others, each after a delay

            timer = new Timer(30, 30, 1, Run);
            timer.Start();

        }

        private void Run()
        {
            p.Move(p.FaceDirection);
        }
    }

}
