package se233.chapter1exercise.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import se233.chapter1exercise.Launcher;
import se233.chapter1exercise.model.character.BasedCharacter;
import se233.chapter1exercise.model.item.Armor;
import se233.chapter1exercise.model.item.BasedEquipment;
import se233.chapter1exercise.model.item.Weapon;

import java.util.ArrayList;

public class AllCustomHandler{
    public static class GenCharacterHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event){
            Launcher.setMainCharacter(GenCharacter.setUpCharacter());
            Launcher.refreshPane();
        }

        public static void onDragDetected(MouseEvent event, BasedEquipment equipment, ImageView imgView) {
            Dragboard db = imgView.startDragAndDrop(TransferMode.ANY);
            db.setDragView(imgView.getImage());
            ClipboardContent content = new ClipboardContent();
            content.put(equipment.DATA_FORMAT, equipment);
            db.setContent(content);
            event.consume();
        }
    }
        public static void onDragOver(DragEvent event, String type){
        Dragboard dragboard = event.getDragboard();
        BasedCharacter character = Launcher.getMainCharacter();
        BasedEquipment retrievedEquipment = (BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);

//        if(dragboard.hasContent(BasedEquipment.DATA_FORMAT) && retrievedEquipment.getClass().getSimpleName().equals(type)) && retrievedEquipment.getClass().getTypeName().equals(damageType);
//            event.acceptTransferModes(TransferMode.MOVE);

            if(retrievedEquipment.getClass().getSimpleName().equals(type)){
                if(retrievedEquipment instanceof Weapon weapon){
                    if (character.getType().equals(weapon.getDamageType())){
                        event.acceptTransferModes(TransferMode.MOVE);
                    }else {event.consume();}
                }
                else if (retrievedEquipment instanceof Armor){
                        if (character.getName().equals("Battlemage"))
                        {
                            event.consume();
                        }
                    else event.acceptTransferModes(TransferMode.MOVE);
                }

            }
    }

        public static void onDragDropped(DragEvent event, Label lbl, StackPane imgGroup){
        boolean dragCompleted = false;
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEquipment> allEquipments = Launcher.getAllEquipments();
            if (dragboard.hasContent(BasedEquipment.DATA_FORMAT)) {
                BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);
                BasedCharacter character = Launcher.getMainCharacter();
                if (retrievedEquipment.getClass().getSimpleName().equals("Weapon")) {

                        if (Launcher.getEquippedWeapon() != null )
                            allEquipments.add(Launcher.getEquippedWeapon());
                        Launcher.setEquippedWeapon((Weapon) retrievedEquipment);
                        character.equipWeapon((Weapon) retrievedEquipment);
                    } else {
                        if (Launcher.getEquippedArmor() != null)
                            allEquipments.add(Launcher.getEquippedArmor());
                        Launcher.setEquippedArmor((Armor) retrievedEquipment);
                        character.equipArmor((Armor) retrievedEquipment);
                    }
                    Launcher.setMainCharacter(character);
                    Launcher.setAllEquipments(allEquipments);
                    Launcher.refreshPane();
                    ImageView imgView = new ImageView();
                    if (imgGroup.getChildren().size() != 1) {
                        imgGroup.getChildren().remove(1);
                        Launcher.refreshPane();
                    }
                    lbl.setText(retrievedEquipment.getClass().getSimpleName() + ":\n" + retrievedEquipment.getName());
                    imgView.setImage(new Image(Launcher.class.getResource(retrievedEquipment.getImagepath()).toString()));
                    imgGroup.getChildren().add(imgView);
                    dragCompleted = true;

                }
            event.setDropCompleted(dragCompleted);

        }
        public static void onEquipDone(DragEvent event){
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEquipment> allEquipments = Launcher.getAllEquipments();
        BasedEquipment retrievedEquipments = (BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);
            if (event.isAccepted()) {
                int pos = -1;
                for (int i = 0; i < allEquipments.size(); i++) {
                    if (allEquipments.get(i).getName().equals(retrievedEquipments.getName())) {
                        pos = i;
                        break;

                    }
                }
                if (pos != -1) {
                    allEquipments.remove(pos);
                }
            }
        Launcher.setAllEquipments(allEquipments);
        Launcher.refreshPane();

        }


}