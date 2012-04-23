package core.characters;

import core.configuration.key.KeyAnnotation;

public class PlayerControls extends Character {
    private static final long serialVersionUID = 1293093288749871823L;
    protected double strengthUp, strengthDown, strengthLeft, strengthRight;

    protected void giveStrengthUp() {
	this.addAcceleration(0, strengthUp * this.getGravitationalAcceleration());
    }

    @KeyAnnotation(action = "sequence")
    public void sequenceKey() {
	this.setImages(myGame.getImages("resources/Mushroom.png", 1, 1));
    }

    @KeyAnnotation(action = "up")
    public void keyUpPressed() {
	this.giveStrengthUp();
    }

    @KeyAnnotation(action = "down")
    public void keyDownPressed() {
	this.addAcceleration(0, strengthDown * -this.getGravitationalAcceleration());
    }

    @KeyAnnotation(action = "left")
    public void keyLeftPressed() {
	this.addAcceleration(strengthLeft * -this.getGravitationalAcceleration(), 0);
    }

    @KeyAnnotation(action = "right")
    public void keyRightPressed() {
	this.addAcceleration(strengthRight * this.getGravitationalAcceleration(), 0);
    }

    // @KeyAnnotation(action = "space")
    // public Weapon keySpacePressed() {
    // return Weapon.useWeapon();
    // }

    public void keyAPressed() {
	shoot();
    }

    public void keyBPressed() {
	specialSkill();
    }

    public void shoot() {
    }

    public void specialSkill() {
    }

    public void setStrengthUp(double s) {
	strengthUp = s;
    }

    public void setStrengthDown(double s) {
	strengthDown = s;
    }

    public void setStrengthLeft(double s) {
	strengthLeft = s;
    }

    public void setStrengthRight(double s) {
	strengthRight = s;
    }

    public void setStrength(double s) {
	this.setStrengthDown(s);
	this.setStrengthLeft(s);
	this.setStrengthRight(s);
	this.setStrengthUp(s);
    }

}
