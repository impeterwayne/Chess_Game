package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;

public class King extends Piece{

	private final static int[] CANDIDATE_MOVE_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};
	public King(final int piecePosition,final Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		final List<Move> legalMoves = new ArrayList<>();
		for(int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE)
		{
			final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
			if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
			{
				if(isFirstColumnExclusive(this.piecePosition, currentCandidateOffset)||
					isEightColumnExclusive(this.piecePosition, currentCandidateOffset))
				{
					continue;
				}
				final Tile candidateDestinationTile  = board.getTile(candidateDestinationCoordinate);
				if(!candidateDestinationTile.isOccupiedTile())
				{
					legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
				}
				else
				{
					final Piece pieceAtDestiPiece = candidateDestinationTile.getPiece();
					if(this.pieceAlliance != pieceAtDestiPiece.getPieceAlliance())
					{
						legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate,pieceAtDestiPiece));
					}
				}
			}
		}
		return null;
	}
	private static boolean isFirstColumnExclusive(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset ==-1||candidateOffset==-9||candidateOffset==7);
	}
	private static boolean isEightColumnExclusive(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset ==-7||candidateOffset==1||candidateOffset==9);
	}
	@Override
	public String toString()
	{
		return Piece.PieceType.KING.toString();
	}

}
