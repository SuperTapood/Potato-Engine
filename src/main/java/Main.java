import com.mlomb.freetypejni.FreeType;
import com.mlomb.freetypejni.Library;

import potato.nightly.Window;

public class Main {

    public static void main(String[] args) {
        Library library = FreeType.newLibrary();
        System.out.println("Version " + library.getVersion());
        Window window = new Window();
        window.init();
        window.run();
    }
}
