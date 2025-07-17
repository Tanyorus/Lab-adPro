package se233.chapter1.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import se233.chapter1.Launcher;
import se233.chapter1.model.item.BasedEquipment;

import java.util.ArrayList;

import static se233.chapter1.controller.AllCustomHandler.GenCharacterHandler.onDragDetected;
import static se233.chapter1.controller.AllCustomHandler.onEquipDone;

public class InventoryPane extends ScrollPane {
    private ArrayList<BasedEquipment> equipmentArray;

    public InventoryPane() {  }
    private Pane getDetailsPane() {
        Pane inventoryInfoPane = new HBox(10);
        inventoryInfoPane.setBorder(null);
        inventoryInfoPane.setPadding(new Insets(25,25,25,25));
        if (equipmentArray != null) {
            ImageView[] imageViewsList = new ImageView[equipmentArray.size()];
            for (int i = 0 ; i < equipmentArray.size() ; i++){
                imageViewsList[i] = new ImageView();
                imageViewsList[i].setImage(new Image(Launcher.class.getResource(
                        equipmentArray.get(i).getImagepath()).toString()));
                int finalI = i;
                imageViewsList[i].setOnDragDetected(new EventHandler<MouseEvent>(){
                    public void handle(MouseEvent event) {
                        onDragDetected(event, equipmentArray.get(finalI), imageViewsList[
                                finalI]);
                    }
                });
                imageViewsList[i].setOnDragDone(new EventHandler<DragEvent>(){
                    @Override
                    public void handle(DragEvent event) {
                        onEquipDone(event);
                    }
                });
            }
            inventoryInfoPane.getChildren().addAll(imageViewsList);
        }
        return inventoryInfoPane;
    }
    public void drawPane(ArrayList<BasedEquipment> equipmentArray) {
        this.equipmentArray = equipmentArray;
        Pane invetoryInfo = getDetailsPane();
        this.setStyle("-fx-background-color:Red");
        this.setContent(invetoryInfo);
    }
}