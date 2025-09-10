package se233.chapter4.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import se233.chapter4.Launcher;
import se233.chapter4.view.GameStage;


public class GameCharacter extends Pane {
    private static final Logger logger = LogManager.getLogger(GameCharacter.class);
    public static final int CHARACTER_WIDTH = 32;
    public static final int CHARACTER_HEIGHT = 64;
    private Image gameCharacterImg;
    private AnimatedSprite imageView;
    private int x;
    private int y;


    public KeyCode getLeftKey() {
        return leftKey;
    }

    public void setLeftKey(KeyCode leftKey) {
        this.leftKey = leftKey;
    }

    public KeyCode getRightKey() {
        return rightKey;
    }

    public void setRightKey(KeyCode rightKey) {
        this.rightKey = rightKey;
    }

    public KeyCode getUpKey() {
        return upKey;
    }

    public void setUpKey(KeyCode upKey) {
        this.upKey = upKey;
    }

    public AnimatedSprite getImageView() {
        return imageView;
    }

    public void setImageView(AnimatedSprite imageView) {
        this.imageView = imageView;
    }

    private KeyCode leftKey;
    private KeyCode rightKey;
    private KeyCode upKey;
    int xVelocity   = 0;
    int yVelocity   = 0;
    boolean isMoveLeft = false;
    boolean isMoveRight = false;
    boolean isFalling = true;
    boolean canJump = false;
    boolean isJumping = false;
//  int highestJump = 100;
    int xAcceleration = 1;
    int yAcceleration = 1;
    int xMaxVelocity = 0;
    int yMaxVelocity = 0;


    public GameCharacter(int x, int y, int offsetX, int offsetY, KeyCode leftKey,
                         KeyCode rightKey, KeyCode upKey){
        this(x, y, "assets/MarioSheet.png", 4, 4, offsetX, offsetY, 16, 32,7,17, leftKey, rightKey, upKey);
    }

    public GameCharacter(int x, int y, String imagePath, int columns, int rows, int offsetX, int offsetY, int width, int height,int xMaxVelocity,int yMaxVelocity,KeyCode leftKey,
                         KeyCode rightKey, KeyCode upKey){
        this.x = x;
        this.y = y;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.gameCharacterImg = new Image(Launcher.class.getResourceAsStream(imagePath));
        this.imageView =  new AnimatedSprite(gameCharacterImg, columns, rows, 1, offsetX, offsetY, width, height);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.xMaxVelocity = xMaxVelocity;
        this.yMaxVelocity = yMaxVelocity;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.getChildren().addAll(this.imageView);
    }



    public void moveX() {
        setTranslateX(x);
        if (isMoveLeft) {
            xVelocity = xVelocity >= xMaxVelocity ? xMaxVelocity : xVelocity + xAcceleration;
            x = x - xVelocity;
        }
        if (isMoveRight) {
            xVelocity = xVelocity>= xMaxVelocity ? xMaxVelocity : xVelocity + xAcceleration;
            x = x + xVelocity;
        }

    }
    public void moveY(){
        setTranslateY(y);
        if(isFalling){
            yVelocity = yVelocity >= yMaxVelocity ? yMaxVelocity : yVelocity + yAcceleration;
            y = y + yVelocity;
        }
        else if(isJumping){
            yVelocity = yVelocity <= 0 ? 0 : yVelocity - yAcceleration;
            y = y - yVelocity;
        }
    }
    public void repaint(){
        moveX();
        moveY();
    }
    public void moveLeft() {
        setScaleX(-1);
        isMoveLeft = true;
        isMoveRight = false;
    }
    public void moveRight(){
        setScaleX(1);
        isMoveLeft = false;
        isMoveRight = true;
    }
//    public void go(){
//        xVelocity = 5;
//    }
    public void stop(){
        xVelocity = 0;
        isMoveLeft = false;
        isMoveRight = false;
    }
    public void checkReachGameWall(){
        if(x <= 0){
            logger.debug("Character collided with the left wall.");
            x = 0;

        } else if (x+getWidth() >= GameStage.WIDTH) {
            logger.debug("Character collided with the right wall.");
            x = GameStage.WIDTH-(int)getWidth();

        }
    }
    public void jump(){
        if(canJump){
            yVelocity = yMaxVelocity;
            canJump = false;
            isJumping = true;
            isFalling = false;
        }
    }
    public void checkReachHighest(){
        if(isJumping && yVelocity <= 0){
            logger.debug("Character collided with the ceiling.");
            isJumping = false;
            isFalling = true;
            yVelocity = 0;


        }
    }
    public void checkReachFloor(){
        if(isFalling && y >= GameStage.GROUND - CHARACTER_HEIGHT){
            logger.debug("Character collided with the floor.");
            isFalling = false;
            canJump = true;
            yVelocity = 0;
        }
    }
    public void trace(){
        logger.info("x:{} y:{} vx:{} vy:{}",x,y, xVelocity, yVelocity);
    }
}
