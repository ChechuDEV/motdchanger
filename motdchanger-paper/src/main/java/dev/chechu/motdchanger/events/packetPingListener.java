package dev.chechu.motdchanger.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import dev.chechu.motdchanger.MotD;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.Plugin;

public class packetPingListener {
    private ProtocolManager protocolManager;

    public packetPingListener(Plugin paper) {
        protocolManager = ProtocolLibrary.getProtocolManager();
        MotD motD = new MotD(paper);
        protocolManager.addPacketListener(new PacketAdapter(paper, ListenerPriority.NORMAL,  PacketType.Status.Server.SERVER_INFO){
            @Override
            public void onPacketSending(PacketEvent event) {
                if (event.getPacketType() == PacketType.Status.Server.SERVER_INFO) {
                    WrappedServerPing packet = event.getPacket().getServerPings().read(0);

                    packet.setMotD(motD.getMotD());
                            //getGradient("{gradient #3C3C3B #EBEB03} A longer test text for MotDChanger!","3C3C3B", "EBEBD3")
                }
            }
        });
    }
}
