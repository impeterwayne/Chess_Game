package com.chess.engine.pieces;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;


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
	Piece(final int piecePosition, final Alliance pieceAlliance)
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
	public Integer getPiecePosition() {
		// TODO Auto-generated method stub
		return this.piecePosition;
	}
	public enum PieceType
	{
		PAWN("P"),
		ROOK("R"),
		KNIGHT("N"),
		BISHOP("B"),
		QUEEN("Q"),
		KING("K");
		private String pieceName;
		private PieceType(final String pieceName)
		{
			this.pieceName = pieceName;
		}
		@Override
		public String toString()
		{
			return this.pieceName;
		}
	}
}
