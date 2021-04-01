package chess.pieces;

public enum Alliance {
WHITE
 {
	@Override
	public int getDirection() {
		return -1;
	}

	@Override
	protected boolean isBlack() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isWhite() {
		// TODO Auto-generated method stub
		return true;
	}
},
 BLACK
 {
	@Override
	public int getDirection() {
		return 1;
	}

	@Override
	protected boolean isBlack() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected boolean isWhite() {
		// TODO Auto-generated method stub
		return false;
	}
};
public abstract int getDirection();

protected abstract boolean isBlack();

protected abstract boolean isWhite();
}
