package core.tiles;

import core.characters.GameElement;

public abstract class ActionDecorator extends TileDecorator {

    private static final long serialVersionUID = -2300606344978587014L;
    private boolean rightAction, leftAction, topAction, bottomAction;

    public ActionDecorator(Tile decoratedPlatform) {
        super(decoratedPlatform);
    }

    public abstract void doAction();

    public void setRightAction(boolean active) {
        rightAction = active;
    }

    public void setLeftAction(boolean active) {
        leftAction = active;
    }

    public void setTopAction(boolean active) {
        topAction = active;
    }

    public void setBottomAction(boolean active) {
        bottomAction = active;
    }

    @Override
    public void afterHitFromBottomBy(GameElement e, String tag) {
        super.afterHitFromBottomBy(e, tag);
        if (bottomAction) {
            doAction();
        }
    }

    @Override
    public void afterHitFromTopBy(GameElement e, String tag) {
        super.afterHitFromTopBy(e, tag);
        if (topAction) {
            doAction();
        }
    }

    @Override
    public void afterHitFromRightBy(GameElement e, String tag) {
        super.afterHitFromRightBy(e, tag);
        if (rightAction) {
            doAction();
        }
    }

    @Override
    public void afterHitFromLeftBy(GameElement e, String tag) {
        super.afterHitFromLeftBy(e, tag);
        if (leftAction) {
            doAction();
        }
    }

}
