package chess.board;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import chess.pieces.Piece;

public abstract class Tile {
protected final int tileCoordinate;
Tile(int tileCoordinate)
{
	this.tileCoordinate = tileCoordinate;
}
/**
 * Create a map that contains all possible empty tiles
 * retrieve the value from the map rather use constructor to create a new one
 */
private static final Map<Integer, EmptyTile> EMTY_TILES_CACHE = createAllPossibleEmptyTiles();
private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles()
{
	final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
	for(int i=0; i<BoardUtils.NUM_TILES;i++)
	{
		emptyTileMap.put(i, new EmptyTile(i));
	}
	return ImmutableMap.copyOf(emptyTileMap);
}
/**
 * Create a tile
 * @param tileCoordinate	the coordinate of the tile
 * @param piece				the piece on the tile
 * @return					if piece != null -> create an Occupied tile, else retrieve an empty tile from the map
 */
public static Tile createTile(final int tileCoordinate, final Piece piece)
{
	return piece!= null ? new OccupiedTile(tileCoordinate, piece) : EMTY_TILES_CACHE.get(tileCoordinate);
}
public abstract boolean isOccupiedTile();
public abstract Piece getPiece();
/**
 * Inner class Empty tile
 * piece on tile is null
 * is occupied tile -> false
 *
 */
public static final class EmptyTile extends Tile
{
	EmptyTile(final int tileCoordinate)
	{
		super(tileCoordinate);
	}
	@Override
	public boolean isOccupiedTile()
	{
		return false;
	}
	@Override
	public Piece getPiece()
	{
		return null;
	}
}
/**
 * Inner class OccupiedTile
 * have a piece in this tile -> add an attribute pieceOnTile
 * isOccupiedTile -> true
 */
public static final class OccupiedTile extends Tile
{
	private final Piece pieceOnTile;
	OccupiedTile(final int tileCoordinate,final Piece pieceOnTile)
	{
		super(tileCoordinate);
		this.pieceOnTile = pieceOnTile;
	}

	@Override
	public boolean isOccupiedTile() {
		return true;
	}

	@Override
	public Piece getPiece() {
		return this.pieceOnTile;
	}
}
}
