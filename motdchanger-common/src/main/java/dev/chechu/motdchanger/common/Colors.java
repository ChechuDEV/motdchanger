package dev.chechu.motdchanger.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Colors {
    public List<Integer> hexToRGB(String hex) {
        String hexColor = hex.replace("#","");
        int red = Integer.parseInt(hexColor.substring(0,2),16);
        int green = Integer.parseInt(hexColor.substring(2,4),16);
        int blue = Integer.parseInt(hexColor.substring(4,6),16);

        return Arrays.asList(red,green,blue);
    }

    public String RGBtoHex(int red, int green, int blue) {
        return String.format("#%02x%02x%02x", red, green, blue);
    }
}
