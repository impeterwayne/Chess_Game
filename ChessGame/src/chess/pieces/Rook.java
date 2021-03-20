package chess.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import chess.board.Board;
import chess.board.BoardUtils;
import chess.board.Move;
import chess.board.Move.AttackMove;
import chess.board.Move.MajorMove;
import chess.board.Tile;

public class Rook extends Piece{
	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATE = {-8,-1,1,8};
	Rook(int piecePosition, Alliance pieceAlliance) {
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
				candidateDestinationCoordinate +=candidateCoordinateOffset;
				if(isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)||
						isEighthColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset))
				{
					break;
				}
				if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
				{
					final Tile candidateCoordinateTile = board.getTile(candidateDestinationCoordinate);
					if(!candidateCoordinateTile.isOccupiedTile())
					{
						legalMoves.add(new Move.MajorMove(board,this,candidateDestinationCoordinate));
					}
					else
					{
						final Piece pieceAtDestination = candidateCoordinateTile.getPiece();
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

}
