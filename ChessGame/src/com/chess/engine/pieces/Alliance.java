package com.chess.engine.pieces;

public enum Alliance {
WHITE
 {
	@Override
	public int getDirection() {
		return -1;
	}

	@Override
	public boolean isBlack() {
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
	public boolean isBlack() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isWhite() {
		// TODO Auto-generated method stub
		return false;
	}
};
public abstract int getDirection();

public abstract boolean isBlack();

protected abstract boolean isWhite();
}
