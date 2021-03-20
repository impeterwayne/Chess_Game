package chess.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import chess.board.Board;
import chess.board.BoardUtils;
import chess.board.Move;
import chess.board.Tile;

public class Knight extends Piece{

	// candidate moves that a knight can take from current location
	public final static int[] CANDIDATE_MOVES_COORDINATE = {-17, -15,-10,-6,6,10,15,17};
	/**
	 * Create a Knight
	 * @param piecePosition		Knight's position
	 * @param pieceAlliance		Knight's color
	 */
	Knight(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
	}
 
	@Override
	public Collection<Move> calculateLegalMoves(Board board) {

		List<Move> legalMoves = new ArrayList<>();
		for(int currentCandidateOffset: CANDIDATE_MOVES_COORDINATE)
		{
			int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
			if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
			{
				if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset)
					||isSecondColumnExclusion(this.piecePosition, currentCandidateOffset)
					||isSeventhColmnExclusion(this.piecePosition, currentCandidateOffset)
					||isEighthColumnExclusion(this.piecePosition, currentCandidateOffset))
				{
					continue;
				}
				final Tile candidateDestinationTile =board.getTile(candidateDestinationCoordinate);
				if(!candidateDestinationTile.isOccupiedTile())
				{
					legalMoves.add(new Move());
				}
				else
				{
					Piece pieceAtDestination = candidateDestinationTile.getPiece();
					Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					if(this.pieceAlliance != pieceAlliance)
					{
						legalMoves.add(new Move());
					}
				}
			}
		}
		
		return ImmutableList.copyOf(legalMoves);
	}
	public static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.FIRST_COLUMN[currentPosition]&&(candidateOffset==-17||candidateOffset==-10||candidateOffset==6||candidateOffset==15);
	}
	public static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.SECOND_COLUMN[currentPosition]&&(candidateOffset==-10||candidateOffset==6);
	}
	public static boolean isSeventhColmnExclusion(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.SEVENTH_COLUMN[currentPosition]&&(candidateOffset==-6||candidateOffset==10);
	}
	public static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset)
	{
		return BoardUtils.EIGHTH_COLUMN[currentPosition]&&(candidateOffset==-6||candidateOffset==-15||candidateOffset==10||candidateOffset==17);
	}

}
