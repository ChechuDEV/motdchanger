package dev.chechu.motdchanger;

import dev.chechu.motdchanger.common.Colors;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MotD {

    final Pattern GRADIENT_TAG_PATTERN = Pattern.compile("<gradient #[a-fA-F0-9]{6} #[a-fA-F0-9]{6}>",Pattern.DOTALL);
    final Pattern GRADIENT_TEXT_PATTERN = Pattern.compile("<gradient #[a-fA-F0-9]{6} #[a-fA-F0-9]{6}>(.+?)</gradient>",Pattern.DOTALL);
    final Pattern HEX_PATTERN = Pattern.compile("#[a-fA-F0-9]{6}",Pattern.DOTALL);

    public MotD(Plugin plugin) {
    }

    public String getMotD() {
        String text = "<gradient #4aff98 #EBEB03>A longer test text </gradient>Test<gradient #EBEB03 #4aff98>For MotdChanger</gradient>";
        String[] others = text.split("<gradient (#[a-fA-F0-9]{6}).+>(.+?)</gradient>");
        StringBuilder string = new StringBuilder();
        Matcher textMatcher = GRADIENT_TEXT_PATTERN.matcher(text);
        while (textMatcher.find()){
            ArrayList<String> hexColors = new ArrayList<>();
            Matcher tagMatcher = GRADIENT_TAG_PATTERN.matcher(textMatcher.group(0));
            while(tagMatcher.find()) {
                Matcher hexMatcher = HEX_PATTERN.matcher(tagMatcher.group(0));
                while (hexMatcher.find()) {
                    hexColors.add(hexMatcher.group(0));
                }
            }
            string.append(getGradient(textMatcher.group(1), hexColors));
        }
        return string.toString();
    }

    public String getGradient(String text, ArrayList<String> hexColors) {
        Colors colorClass = new Colors();
        int chars = text.length();
        int colors = hexColors.size();
        int stepLength = Math.floorDiv(chars, (colors-1));
        String[] characters = text.split("");
        StringBuilder finalText = new StringBuilder();
        List<Integer> rgb;
        List<Integer> rgb2;
        for (int i = 0; i < colors - 1; i++) {
            rgb = colorClass.hexToRGB(hexColors.get(i));
            rgb2 = colorClass.hexToRGB(hexColors.get(i+1));
            for (int j = 1; j < stepLength; j++) {
                int step = stepLength * i + j - 1;

                double p = (double) step / stepLength;

                // Make class for Interpolation or use Java Gradient Methods https://stackoverflow.com/questions/27532/generating-gradients-programmatically

                int redNext = (int) Math.floor(rgb.get(0) * p + rgb2.get(0) * (1-p));
                int greenNext = (int) Math.floor(rgb.get(1) * p + rgb2.get(1) * (1-p));
                int blueNext = (int) Math.floor(rgb.get(2) * p + rgb2.get(2) * (1-p));

                finalText.append(ChatColor.of(colorClass.RGBtoHex(redNext,greenNext,blueNext))).append(characters[step]);
            }
        }
        return finalText.toString();
    }
}