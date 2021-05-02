package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public abstract class Move {
	final Board board;
	final Piece movedPiece;
	final int destinationCoordinate;
	/**
	 * Initiate a move
	 * @param board							the board
	 * @param movedPiece					the piece which was moved
	 * @param destinationCoordinate			the destination coordinate of the moved piece
	 */
	Move(final Board board, 
		final Piece movedPiece,
		final int destinationCoordinate)
	{
		this.board = board;
		this.movedPiece = movedPiece;
		this.destinationCoordinate = destinationCoordinate;
	}
	/**
	 *  Inner class Major Move
	 * 	no change
	 */
	public static final class MajorMove extends Move
	{
		public MajorMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
			super(board, movedPiece, destinationCoordinate);
		} 
	}
	/**
	 * Inner class Attack Move
	 *	add a new attribute : attackedPiece <-> the piece which was attacked
	 */
	public static final class AttackMove extends Move
	{
		final Piece attackedPiece;
		public AttackMove(final Board board, final Piece movedPiece,final int destinationCoordinate,final Piece attackedPiece) {
			super(board, movedPiece, destinationCoordinate);
			this.attackedPiece = attackedPiece;
		}
	}
}
