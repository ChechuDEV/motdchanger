package dev.chechu.motdchanger.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import dev.chechu.motdchanger.paper;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.print.DocFlavor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class packetPingListener {
    private ProtocolManager protocolManager;

    public packetPingListener(Plugin paper) {
        protocolManager = ProtocolLibrary.getProtocolManager();
        listener(paper);
    }

    private void listener(Plugin plugin) {
        protocolManager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL,  PacketType.Status.Server.SERVER_INFO){
            @Override
            public void onPacketSending(PacketEvent event) {
                if (event.getPacketType() == PacketType.Status.Server.SERVER_INFO) {
                    WrappedServerPing packet = event.getPacket().getServerPings().read(0);
                    packet.setMotD(getGradient("A longer test text for MotDChanger!","3C3C3B", "EBEBD3"));
                }
            }
        });
    }

    private List<Integer> hexToRGB(String hexColor) {
        int red = Integer.parseInt(hexColor.substring(0,2),16);
        int green = Integer.parseInt(hexColor.substring(2,4),16);
        int blue = Integer.parseInt(hexColor.substring(4,6),16);

        return Arrays.asList(red,green,blue);
    }

    private String RGBtoHex(int red, int green, int blue) {
        return "#" + Integer.toHexString(red).toUpperCase() + (red < 16 ? 0 : "") + // Add String
                Integer.toHexString(green).toUpperCase() + (green < 16 ? 0 : "") +
                Integer.toHexString(blue).toUpperCase() + (blue < 16 ? 0 : "");
    }

    private String getGradient(String text, String... hexColors) {
        int chars = text.length();
        int colors = hexColors.length;
        int stepLength = Math.floorDiv(chars, (colors-1));
        String[] characters = text.split("");
        StringBuilder gradient = new StringBuilder();
        List<Integer> rgb;
        List<Integer> rgb2;
        for(int i = 0; i < colors - 1; i++) {
            for(int j = 1; j < stepLength; j++) {
                rgb = hexToRGB(hexColors[i]);
                rgb2 = hexToRGB(hexColors[i+1]);
                int redStep = Math.floorDiv(rgb.get(0) - rgb2.get(0),stepLength);
                int greenStep = Math.floorDiv(rgb.get(1) - rgb2.get(1),stepLength);
                int blueStep = Math.floorDiv(rgb.get(2) - rgb2.get(2),stepLength);

                int step = stepLength * i + j - 1;

                int nextRed = redStep > 0 ? rgb.get(0)+(redStep*step) : rgb.get(0)-(redStep*step);
                int nextGreen = greenStep > 0 ? rgb.get(1)+(greenStep*step) : rgb.get(1)-(greenStep*step);
                int nextBlue = blueStep > 0 ? rgb.get(2)+(blueStep*step) : rgb.get(2)-(blueStep*step);

                System.out.println(nextRed);
                System.out.println(redStep);

                gradient.append(ChatColor.of(RGBtoHex(nextRed, nextGreen, nextBlue))).append(characters[step]);
            }
        }
        gradient.append(ChatColor.of("#"+hexColors[hexColors.length - 1])).append(characters[characters.length - 1]);
        return String.valueOf(gradient);
    }

    private final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");


}
