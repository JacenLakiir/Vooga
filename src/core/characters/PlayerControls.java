package core.characters;

import core.keyconfiguration.KeyAnnotation;

@SuppressWarnings("serial")
public class PlayerControls extends Character {

    protected double strengthUp, strengthDown, strengthLeft, strengthRight;

    protected void giveStrengthUp() {
	this.addAcceleration(0, strengthUp * stdGravity);
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
	this.addAcceleration(0, strengthDown * -stdGravity);
    }

    @KeyAnnotation(action = "left")
    public void keyLeftPressed() {
	this.addAcceleration(strengthLeft * -stdGravity, 0);
    }

    @KeyAnnotation(action = "right")
    public void keyRightPressed() {
	this.addAcceleration(strengthRight * stdGravity, 0);
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
