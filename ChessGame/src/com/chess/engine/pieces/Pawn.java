package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.google.common.collect.ImmutableList;

public class Pawn extends Piece{
	private final static int[] CANDIDATE_MOVE_COORDINATE = {8,16,7,9};

	public Pawn(final int piecePosition,final Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		List<Move> legalMoves = new ArrayList<>();
		for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE)
		{
			final int candidateDestinationCoordinate = this.piecePosition + this.getPieceAlliance().getDirection()*currentCandidateOffset;
			if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
			{
				continue;
			}
			if(currentCandidateOffset==8 && !board.getTile(candidateDestinationCoordinate).isOccupiedTile())
			{
				legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
			}
			else if(currentCandidateOffset == 16 && this.isFirstMove() &&
					(BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
				(BoardUtils.SEVENTH_ROW[this.piecePosition])&&this.getPieceAlliance().isWhite())
			{
				final int behindCandidateDestinationCoordinate = this.piecePosition + 8*this.pieceAlliance.getDirection();
				if(!board.getTile(candidateDestinationCoordinate).isOccupiedTile()&&board.getTile(behindCandidateDestinationCoordinate).isOccupiedTile())
				{
					legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
				}
			}
			else if(currentCandidateOffset ==7 && !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()) ||
					(BoardUtils.FIRST_COLUMN[this.piecePosition]&&this.pieceAlliance.isBlack())))
			{
				if(board.getTile(candidateDestinationCoordinate).isOccupiedTile())
				{
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
					if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance())
					{
						//TODO 	
						legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
					}
				}
			}
			else if (currentCandidateOffset == 9 && 
					!((BoardUtils.EIGHTH_COLUMN[this.piecePosition]&&this.pieceAlliance.isBlack()) ||
						(BoardUtils.FIRST_COLUMN[this.piecePosition]&&this.pieceAlliance.isWhite())))
			{
				final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
				if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance())
				{
					//TODO 	
					legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}
	@Override
	public String toString()
	{
		return Piece.PieceType.PAWN.toString();
	}

}
