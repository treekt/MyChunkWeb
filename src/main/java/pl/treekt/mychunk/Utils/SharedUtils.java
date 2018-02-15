package pl.treekt.mychunk.Utils;

import java.util.Random;

public class SharedUtils {

    public static Long randomNegativeId() {
        Random rand = new Random();
        return -1 * ((long) rand.nextInt(1000));
    }
}
