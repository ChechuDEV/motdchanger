package dev.chechu.motdchanger.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import dev.chechu.motdchanger.common.Colors;
import dev.chechu.motdchanger.gradient;
import dev.chechu.motdchanger.paper;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.print.DocFlavor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static dev.chechu.motdchanger.common.Colors.hexToRGB;

public class packetPingListener {
    private ProtocolManager protocolManager;

    public packetPingListener(Plugin paper, ChatColor color) {
        protocolManager = ProtocolLibrary.getProtocolManager();
        listener(paper);
    }

    private void listener(Plugin plugin) {
        protocolManager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL,  PacketType.Status.Server.SERVER_INFO){
            @Override
            public void onPacketSending(PacketEvent event) {
                if (event.getPacketType() == PacketType.Status.Server.SERVER_INFO) {
                    WrappedServerPing packet = event.getPacket().getServerPings().read(0);
                    packet.setMotD(gradient.getGradient("A longer test text for MotDChanger!","3C3C3B", "EBEBD3"));
                }
            }
        });
    }




}
