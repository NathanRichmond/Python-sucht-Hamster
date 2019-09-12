using Python3.chars;

namespace Python3.tiles
{
    class Hammer : SpecialTile
    {
        public Hammer() : base() { }
        public Hammer(int gridX, int gridY) : base(gridX, gridY) { }

        public void Activate(Enemy e)
        {
            Alive = false;
            Tile.Content.Remove(this);

            int ex = e.X, ey = e.Y;

            // place walls behind enemy
            switch (e.FaceDirection)
            {
                case Dir.North:
                    Tiles.AddWallSafe(ex - 1, ey + 1);
                    Tiles.AddWallSafe(ex, ey + 1);
                    Tiles.AddWallSafe(ex + 1, ey + 1);
                    break;
                case Dir.East:
                    Tiles.AddWallSafe(ex - 1, ey - 1);
                    Tiles.AddWallSafe(ex - 1, ey);
                    Tiles.AddWallSafe(ex - 1, ey + 1);
                    break;
                case Dir.South:
                    Tiles.AddWallSafe(ex + 1, ey - 1);
                    Tiles.AddWallSafe(ex, ey - 1);
                    Tiles.AddWallSafe(ex - 1, ey - 1);
                    break;
                case Dir.West:
                    Tiles.AddWallSafe(ex + 1, ey + 1);
                    Tiles.AddWallSafe(ex + 1, ey);
                    Tiles.AddWallSafe(ex + 1, ey - 1);
                    break;
            }
        }

        public void Activate(Player p)
        {
            Alive = false;
            Tile.Content.Remove(this);

            int px = p.X, py = p.Y;

            // place walls in front of player
            switch (p.FaceDirection)
            {
                case Dir.North:
                    Tiles.AddWallSafe(px + 1, py - 1);
                    Tiles.AddWallSafe(px, py - 1);
                    Tiles.AddWallSafe(px - 1, py - 1);
                    break;
                case Dir.East:
                    Tiles.AddWallSafe(px + 1, py + 1);
                    Tiles.AddWallSafe(px + 1, py);
                    Tiles.AddWallSafe(px + 1, py - 1);
                    break;
                case Dir.South:
                    Tiles.AddWallSafe(px - 1, py + 1);
                    Tiles.AddWallSafe(px, py + 1);
                    Tiles.AddWallSafe(px + 1, py + 1);
                    break;
                case Dir.West:
                    Tiles.AddWallSafe(px - 1, py - 1);
                    Tiles.AddWallSafe(px - 1, py);
                    Tiles.AddWallSafe(px - 1, py + 1);
                    break;
            }
        }

    }

}
