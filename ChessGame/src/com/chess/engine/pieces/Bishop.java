package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.board.Move.AttackMove;
import com.google.common.collect.ImmutableList;

public class Bishop extends Piece{
	
	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATE= {-9,-7,7,9};
	public Bishop(final int piecePosition, final Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		final List<Move> legalMoves = new ArrayList<Move>();
		for(final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATE)
		{
			int candidateDestinationCoordinate = this.piecePosition;
			while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
			{
				if(isFirstColumnExclusive(candidateDestinationCoordinate, candidateCoordinateOffset)
					||isEightColumnExclusive(candidateDestinationCoordinate, candidateCoordinateOffset))
				{
					break;
				}
				candidateDestinationCoordinate+= candidateCoordinateOffset;
				if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
				{
					final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
					if(!candidateDestinationTile.isOccupiedTile())
					{
						legalMoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));
					}else
					{
						final Piece pieceAtDestination = candidateDestinationTile.getPiece();
						Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
						if(pieceAlliance!=this.pieceAlliance)
						{
							legalMoves.add(new Move.AttackMove(board, this, candidateCoordinateOffset, pieceAtDestination));
						}
						break;
					}
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}
	public static boolean isFirstColumnExclusive(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.FIRST_COLUMN[currentPosition]&&(candidateOffset==7||candidateOffset==-9);
	}
	public static boolean isEightColumnExclusive(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.EIGHTH_COLUMN[currentPosition]&&(candidateOffset==-7||candidateOffset==9);
	}
	@Override
	public String toString()
	{
		return Piece.PieceType.BISHOP.toString();
	}

}
