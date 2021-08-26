package dev.chechu.motdchanger.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import dev.chechu.motdchanger.MotD;
import dev.chechu.motdchanger.paper;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class packetPingListener {
    private ProtocolManager protocolManager;

    public packetPingListener(paper paper) {
        protocolManager = ProtocolLibrary.getProtocolManager();
        MotD motD = new MotD(paper);
        protocolManager.addPacketListener(new PacketAdapter(paper, ListenerPriority.NORMAL,  PacketType.Status.Server.SERVER_INFO){
            @Override
            public void onPacketSending(PacketEvent event) {
                if (event.getPacketType() == PacketType.Status.Server.SERVER_INFO) {
                    WrappedServerPing packet = event.getPacket().getServerPings().read(0);

                    packet.setMotD(motD.getMotD());
                    if(Objects.equals(motD.getProtocol(), "never")) packet.setVersionProtocol(protocolManager.getProtocolVersion(event.getPlayer()));
                    else if (Objects.equals(motD.getProtocol(), "yes")) packet.setVersionProtocol(-1);
                    packet.setVersionName(motD.getVersionName());
                    packet.setPlayersVisible(motD.hidePlayers());
                    // TODO: Set max numbers, custom playerlist, etc...
                }
            }
        });
    }
}
