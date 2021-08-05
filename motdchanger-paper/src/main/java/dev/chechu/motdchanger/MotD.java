package dev.chechu.motdchanger;

import dev.chechu.motdchanger.common.Colors;
import net.md_5.bungee.api.ChatColor;

import java.util.List;

public class MotD {
    public String getGradient(String text, String... hexColors) {
        Colors colorClass = new Colors();
        int chars = text.length();
        int colors = hexColors.length;
        int stepLength = Math.floorDiv(chars, (colors-1));
        String[] characters = text.split("");
        StringBuilder finalText = new StringBuilder();
        List<Integer> rgb;
        List<Integer> rgb2;
        for (int i = 0; i < colors - 1; i++) {
            rgb = colorClass.hexToRGB(hexColors[i]);
            rgb2 = colorClass.hexToRGB(hexColors[i+1]);
            for (int j = 1; j < stepLength; j++) {
                int redStep = Math.floorDiv(rgb.get(0) - rgb2.get(0), stepLength);
                int greenStep = Math.floorDiv(rgb.get(1) - rgb2.get(1), stepLength);
                int blueStep = Math.floorDiv(rgb.get(2) - rgb2.get(2), stepLength);

                int step = stepLength * i + j - 1;

                int redNext = redStep > 0 ? rgb.get(0)+(redStep*step) : rgb.get(0)-redStep*step;
                int greenNext = greenStep > 0 ? rgb.get(1)+(greenStep*step) : rgb.get(1)-greenStep*step;
                int blueNext = blueStep > 0 ? rgb.get(2)+(blueStep*step) : rgb.get(2)-blueStep*step;

                finalText.append(ChatColor.of(colorClass.RGBtoHex(redNext,greenNext,blueNext))).append(characters[step]);
            }
        }
        return finalText.toString();
    }
}
