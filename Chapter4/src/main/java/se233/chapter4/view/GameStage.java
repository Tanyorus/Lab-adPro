package se233.chapter4.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import se233.chapter4.Launcher;
import se233.chapter4.model.GameCharacter;
import se233.chapter4.model.Keys;

import java.util.ArrayList;
import java.util.List;

public class GameStage extends Pane {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static final int  GROUND = 300;
    private Image gameStageImg;
    private List<GameCharacter> gameCharacters;
    private Keys keys;
    public GameStage() {
        gameCharacters = new ArrayList<>();
        keys = new Keys();
        gameStageImg = new Image(Launcher.class.getResourceAsStream("assets/Background.png"));
        ImageView backgroundImg = new ImageView(gameStageImg);
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        GameCharacter gameCharacter1 = new GameCharacter(30, 30, 0, 0, KeyCode.A, KeyCode.D, KeyCode.W);
        GameCharacter gameCharacter2 = new GameCharacter(700,250,"assets/rockman.png",5,5,2,0,541,514,200,240,KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP);
        gameCharacters.add(gameCharacter1);
        gameCharacters.add(gameCharacter2);
        getChildren().addAll(backgroundImg, gameCharacter1, gameCharacter2);

    }
    public List<GameCharacter> getGameCharacter() {
        return gameCharacters;
    }



    public Keys getKeys() {
        return keys;
    }
}
