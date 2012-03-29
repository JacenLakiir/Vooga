package setting;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;

public abstract class PlatformDecorator extends Platform {
	
	protected final Platform decoratedPlatform;
	
	public PlatformDecorator(Platform decoratedPlatform) {
		this.decoratedPlatform = decoratedPlatform;
	}
	
	/*
	 * The following methods allow the PlatformDecorator to act as a ConcretePlatform
	 * (Sprite) by allowing access to the inner sprites methods.
	 */
	
	public void addHorizontalSpeed(long elapsedTime, double accel, double maxSpeed){
		decoratedPlatform.addHorizontalSpeed(elapsedTime, accel, maxSpeed);
	}
	
	public void addVerticalSpeed(long elapsedTime, double accel, double maxSpeed){
		decoratedPlatform.addVerticalSpeed(elapsedTime, accel, maxSpeed);
	}
	
	public void forceX(double xs){
		decoratedPlatform.forceX(xs);
	}
	
	public void forceY(double ys){
		decoratedPlatform.forceY(ys);
	}
	
	public Background getBackground(){
		return decoratedPlatform.getBackground();
	}
	
	public Object getDataId(){
		return decoratedPlatform.getDataID();
	}
	
	public double getDistance(Sprite other){
		return decoratedPlatform.getDistance(other);
	}
	
	public int getHeight(){
		return decoratedPlatform.getHeight();
	}
	
	public double getHorizontalSpeed(){
		return decoratedPlatform.getHorizontalSpeed();
	}
	
	public int getID(){
		return decoratedPlatform.getID();
	}
	
	public BufferedImage getImage(){
		return decoratedPlatform.getImage();
	}
	
	public int getLayer(){
		return decoratedPlatform.getLayer();
	}
	
	public double getOldX(){
		return decoratedPlatform.getOldX();
	}
	
	public double getOldY(){
		return decoratedPlatform.getOldY();
	}
	
	public double getScreenX(){
		return decoratedPlatform.getScreenX();
	}
	
	public double getScreenY(){
		return decoratedPlatform.getScreenY();
	}
	
	public double getVerticalSpeed(){
		return decoratedPlatform.getVerticalSpeed();
	}
	
	public int getWidth(){
		return decoratedPlatform.getWidth();
	}
	
	public double getX(){
		return decoratedPlatform.getX();
	}
	
	public double getY(){
		return decoratedPlatform.getY();
	}
	
	public boolean isActive(){
		return decoratedPlatform.isActive();
	}
	
	public boolean isImmutable(){
		return decoratedPlatform.isImmutable();
	}
	
	public boolean isOnScreen(){
		return decoratedPlatform.isOnScreen();
	}
	
	public boolean isOnScreen(int leftOffset, int topOffset, int rightOffset, int bottomOffset){
		return decoratedPlatform.isOnScreen(leftOffset, topOffset, rightOffset, bottomOffset);
	}
	
	public void move(double dx, double dy){
		decoratedPlatform.move(dx, dy);
	}
	
	public boolean moveTo(long elapsedTime, double xs, double ys, double speed){
		return decoratedPlatform.moveTo(elapsedTime, xs, ys, speed);
	}
	
	public void moveX(double dx){
		decoratedPlatform.moveX(dx);
	}
	
	public void moveY(double dy){
		decoratedPlatform.moveY(dy);
	}
	
	public void render(Graphics2D g){
		decoratedPlatform.render(g);
	}
	
	public void render(Graphics2D g, int x, int y){
		decoratedPlatform.render(g, x, y);
	}
	
	public void setActive(boolean b){
		decoratedPlatform.setActive(b);
	}
	
	public void setBackground(Background backgr){
		decoratedPlatform.setBackground(backgr);
	}
	
	public void setDataId(Object dataID){
		decoratedPlatform.setDataID(dataID);
	}
	
	public void setHorizontalSpeed(double vx){
		decoratedPlatform.setHorizontalSpeed(vx);
	}
	
	public void setID(int id){
		decoratedPlatform.setID(id);
	}
	
	public void setImage(BufferedImage image){
		decoratedPlatform.setImage(image);
	}
	
	public void setImmutable(boolean b){
		decoratedPlatform.setImmutable(b);
	}
	
	public void setLayer(int i){
		decoratedPlatform.setLayer(i);
	}
	
	public void setLocation(double xs, double ys){
		decoratedPlatform.setLocation(xs, ys);
	}
	
	public void setMovement(double speed, double angleDir){
		decoratedPlatform.setMovement(speed, angleDir);
	}
	
	public void setSpeed(double vx, double vy){
		decoratedPlatform.setSpeed(vx, vy);
	}
	
	public void setVerticalSpeed(double vy){
		decoratedPlatform.setVerticalSpeed(vy);
	}
	
	public void setX(double xs){
		decoratedPlatform.setX(xs);
	}
	
	public void setY(double ys){
		decoratedPlatform.setY(ys);
	}
	
	public void update(long elapsedTime){
		decoratedPlatform.update(elapsedTime);
	}
	
}
