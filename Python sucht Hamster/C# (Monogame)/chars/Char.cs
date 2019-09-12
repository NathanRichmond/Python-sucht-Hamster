using Python3.tiles;

namespace Python3.chars
{
    /// <summary>
    /// Abstract class both Enemy and Player inherit from. Contains basic properties.
    /// </summary>
    abstract class Char
    {
        public int X, Y; // coordinates within the grid
        public Tile Tile;
        public int XAfterMove, YAfterMove;
        public Tile TileAfterMove;
        public Dir FaceDirection;
    }

    /// <summary>
    /// Global face direction enum. Used for Player and Enemy.
    /// </summary>
    public enum Dir { North, East, South, West };
}
