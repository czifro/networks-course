import java.util.ArrayList;

/**
 * @author Will Czifro
 */
public class ValuesController {

    // simulate persisting data source
    // only persist during lifespan of application
    // resets upon shutdown/startup
    private static ArrayList<String> values;

    static {
        values = new ArrayList<>();
        values.add("Hello World");
    }

    public ArrayList<String> get() {
        return values;
    }

    public void put(String value) {
        values.add(value);
    }

    public void delete(int index) {
        values.remove(index);
    }
}
