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
import com.google.common.collect.ImmutableList;

public class Rook extends Piece{
	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATE = {-8,-1,1,8};
	public Rook(final int piecePosition, final Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		List<Move> legalMoves = new ArrayList<>();
		int candidateDestinationCoordinate = this.piecePosition;
		for(int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATE)
		{
			while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
			{
				if(isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)||
						isEighthColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset))
				{
					break;
				}
				candidateDestinationCoordinate +=candidateCoordinateOffset;
				if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
				{
					final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
					if(!candidateDestinationTile.isOccupiedTile())
					{
						legalMoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));
					}
					else
					{
						final Piece pieceAtDestination = candidateDestinationTile.getPiece();
						final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
						if(this.pieceAlliance!=pieceAlliance)
						{
							legalMoves.add(new Move.AttackMove(board,this, candidateDestinationCoordinate,pieceAtDestination));
						}
						break;
					}
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.FIRST_COLUMN[currentPosition]&&candidateOffset ==-1;
	}
	private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.EIGHTH_COLUMN[currentPosition]&&candidateOffset ==1;
	}
	@Override
	public String toString()
	{
		return Piece.PieceType.ROOK.toString();
	}

}
