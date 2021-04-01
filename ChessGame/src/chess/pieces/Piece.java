package chess.pieces;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import chess.board.Board;
import chess.board.Move;


public abstract class Piece {
	/**
	 * every piece has its position
	 */
	protected final int piecePosition;
	/**
	 * Black and White
	 */
	protected final Alliance pieceAlliance;
	protected final boolean isFirstMove;
	/**
	 * Create a piece
	 * @param piecePosition		the piece's position
	 * @param pieceAlliance		the piece's color (black and white)
	 */
	Piece(int piecePosition, Alliance pieceAlliance)
	{
		this.pieceAlliance = pieceAlliance;
		this.piecePosition = piecePosition;
		// TODO more work here!!
		this.isFirstMove = false;
	}
	/**
	 * Calculate all legal move of the piece and save it into a list
	 * @param board		the board has 64 tiles
	 * @return
	 */
	public abstract Collection<Move> calculateLegalMoves(final Board board);
	public Alliance getPieceAlliance()
	{
		return this.pieceAlliance;
	};
	public boolean isFirstMove()
	{
		return this.isFirstMove;
	}
}
