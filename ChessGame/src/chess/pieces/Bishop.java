package chess.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import chess.board.Board;
import chess.board.BoardUtils;
import chess.board.Move;
import chess.board.Tile;
import chess.board.Move.AttackMove;

public class Bishop extends Piece{
	
	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATE= {-9,-7,7,9};
	Bishop(int piecePosition, Alliance pieceAlliance) {
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
				candidateDestinationCoordinate+= candidateCoordinateOffset;
				if(isFirstColumnExclusive(candidateDestinationCoordinate, candidateCoordinateOffset)
					||isEightColumnExclusive(candidateDestinationCoordinate, candidateCoordinateOffset))
				{
					break;
				}
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
	public static boolean isFirstColumnExclusive(final int currentPosition, final int candidateCoordinateOffset)
	{
		return BoardUtils.FIRST_COLUMN[currentPosition]&&(candidateCoordinateOffset==7||candidateCoordinateOffset==-9);
	}
	public static boolean isEightColumnExclusive(final int currentPosition, final int candidateCoordinateOffset)
	{
		return BoardUtils.EIGHTH_COLUMN[currentPosition]&&(candidateCoordinateOffset==-7||candidateCoordinateOffset==9);
	}

}
