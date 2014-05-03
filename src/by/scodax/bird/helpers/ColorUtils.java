package by.scodax.bird.helpers;

import com.badlogic.gdx.graphics.Color;

/**
 * User: Administrator
 * Date: 03.05.14
 * Time: 17:25
 */
public class ColorUtils {

    public static final Color COLOR_BACKGROUND = Color.valueOf("faf8ef");
    public static final Color COLOR_2 = Color.valueOf("eee4da");
    public static final Color COLOR_4 = Color.valueOf("ede0c8");
    public static final Color COLOR_8 = Color.valueOf("f2b179");
    public static final Color COLOR_16 = Color.valueOf("f59563");
    public static final Color COLOR_32 = Color.valueOf("f67c60");
    public static final Color COLOR_64 = Color.valueOf("f65e3c");
    public static final Color COLOR_128 = Color.valueOf("edce72");
    public static final Color COLOR_256 = Color.valueOf("edcc61");
    public static final Color COLOR_512 = Color.valueOf("ebc852");
    public static final Color COLOR_1024 = Color.valueOf("ede136");
    public static final Color COLOR_2048 = Color.valueOf("edc22e");
    public static final Color COLOR_4096 = Color.valueOf("b784ab");

    public static Color getFishkaColor(Integer displayedValue) {
        Color color = null;
        switch (displayedValue) {
            case 2:
                color = COLOR_2;
                break;
            case 4:
                color = COLOR_4;
                break;
            case 8:
                color = COLOR_8;
                break;
            case 16:
                color = COLOR_16;
                break;
            case 32:
                color = COLOR_32;
                break;
            case 64:
                color = COLOR_64;
                break;
            case 128:
                color = COLOR_128;
                break;
            case 256:
                color = COLOR_256;
                break;
            case 512:
                color = COLOR_512;
                break;
            case 1024:
                color = COLOR_1024;
                break;
            case 2048:
                color = COLOR_2048;
                break;
            default:
                color = COLOR_4096;
                break;
        }
        return color;
    }
}
