package by.scodax.bird.helpers;

import com.badlogic.gdx.graphics.Color;

/**
 * User: Administrator
 * Date: 03.05.14
 * Time: 17:25
 */
public class ColorUtils {

    public static final Color COLOR_BACKGROUND = Color.valueOf("f7f7ef");
    public static final Color COLOR_RESTART_ACTIVE = Color.valueOf("ff7d00");
    public static final Color COLOR_RESTART_INACTIVE = Color.valueOf("b5aea5");
    public static final Color COLOR_TEXT_BUTTON = Color.valueOf("7b6d63");
    public static final Color COLOR_SCORE = Color.valueOf("f7f3ef");
    public static final Color COLOR_2 = Color.valueOf("efe3d6");
    public static final Color COLOR_4 = Color.valueOf("efdfc6");
    public static final Color COLOR_8 = Color.valueOf("f7ae7b");
    public static final Color COLOR_16 = Color.valueOf("f79663");
    public static final Color COLOR_32 = Color.valueOf("f77d5a");
    public static final Color COLOR_64 = Color.valueOf("f75d39");
    public static final Color COLOR_128 = Color.valueOf("efcf73");
    public static final Color COLOR_256 = Color.valueOf("efcf63");
    public static final Color COLOR_512 = Color.valueOf("efc752");
    public static final Color COLOR_1024 = Color.valueOf("ede136");
    public static final Color COLOR_2048 = Color.valueOf("efc331");
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
