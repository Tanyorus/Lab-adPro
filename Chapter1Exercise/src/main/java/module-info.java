module se233.chapter1exercise {
    requires javafx.controls;
    requires javafx.fxml;


    opens se233.chapter1exercise to javafx.fxml;
    exports se233.chapter1exercise;
}