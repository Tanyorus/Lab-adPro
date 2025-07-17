package se233.chapter1exercise;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import se233.chapter1exercise.controller.GenCharacter;
import se233.chapter1exercise.controller.GenItemList;
import se233.chapter1exercise.model.character.BasedCharacter;
import se233.chapter1exercise.model.item.Armor;
import se233.chapter1exercise.model.item.BasedEquipment;
import se233.chapter1exercise.model.item.Weapon;
import se233.chapter1exercise.view.CharacterPane;
import se233.chapter1exercise.view.EquipPane;
import se233.chapter1exercise.view.InventoryPane;


import java.util.ArrayList;

public class Launcher extends Application {
    private static Scene mainScene;
    private static BasedCharacter mainCharacter = null;

    public static ArrayList<BasedEquipment> getAllEquipments() {
        return allEquipments;
    }

    public static void setAllEquipments(ArrayList<BasedEquipment> allEquipments) {
        Launcher.allEquipments = allEquipments;
    }

    private static ArrayList<BasedEquipment> allEquipments = null;

    public static Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public static void setEquippedWeapon(Weapon equippedWeapon) {
        Launcher.equippedWeapon = equippedWeapon;
    }

    private static Weapon equippedWeapon = null ;

    public static Armor getEquippedArmor() {
        return equippedArmor;
    }

    public static void setEquippedArmor(Armor equippedArmor) {
        Launcher.equippedArmor = equippedArmor;
    }

    public static void setInventoryPane(InventoryPane pane) {
        inventoryPane = pane;
    }

    public static InventoryPane getInventoryPane() {
        return inventoryPane;
    }

    private static Armor equippedArmor = null ;
    private static CharacterPane characterPane = null;
    private static EquipPane equipPane = null;
    private static InventoryPane inventoryPane = null;
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("chapter1exercise");
        primaryStage.setResizable(false);
        primaryStage.show();
        mainCharacter = GenCharacter.setUpCharacter();
        allEquipments = GenItemList.setUpItemList();
        Pane mainPane = getMainPane();
        mainScene = new Scene(mainPane);
        primaryStage.setScene(mainScene);
    }
    public Pane getMainPane() {
        BorderPane mainPane = new BorderPane();
        characterPane = new CharacterPane();
        equipPane = new EquipPane();
        inventoryPane = new InventoryPane();
        refreshPane();
        mainPane.setCenter(characterPane);
        mainPane.setLeft(equipPane);
        mainPane.setBottom(inventoryPane);
        return mainPane;
    }
    public static void refreshPane() {
         characterPane.drawPane(mainCharacter);
         equipPane.drawPane(equippedWeapon, equippedArmor);
         inventoryPane.drawPane(allEquipments);
    }
    public static BasedCharacter getMainCharacter() { return mainCharacter; }
    public static void setMainCharacter(BasedCharacter mainCharacter) {
        Launcher.mainCharacter = mainCharacter ;
    }
    public static void main(String[] args){
        launch(args);
    }
}