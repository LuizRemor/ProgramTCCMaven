module br.com.eng.laje {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens br.com.eng.laje to javafx.fxml;
    exports br.com.eng.laje;
}
