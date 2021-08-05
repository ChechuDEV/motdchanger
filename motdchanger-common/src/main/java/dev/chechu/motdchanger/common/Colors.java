package dev.chechu.motdchanger.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Colors {
    public Colors() {
    }

    public List<Integer> hexToRGB(String hex) {
        String hexColor = hex.replace("#","");
        int red = Integer.parseInt(hexColor.substring(0,2),16);
        int green = Integer.parseInt(hexColor.substring(2,4),16);
        int blue = Integer.parseInt(hexColor.substring(4,6),16);

        return Arrays.asList(red,green,blue);
    }

    public String RGBtoHex(int red, int green, int blue) {
        return "#" + Integer.toHexString(red).toUpperCase() + (red < 16 ? 0 : "") + // Add String
                Integer.toHexString(green).toUpperCase() + (green < 16 ? 0 : "") +
                Integer.toHexString(blue).toUpperCase() + (blue < 16 ? 0 : "");
    }
}
