package dev.chechu.motdchanger.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import dev.chechu.motdchanger.Configuration;
import dev.chechu.motdchanger.MotD;
import dev.chechu.motdchanger.Main;

import java.util.Objects;

public class packetPingListener {
    private final ProtocolManager protocolManager;
    private final Configuration config;

    public packetPingListener(Configuration config) {
        this.config = config;
        protocolManager = ProtocolLibrary.getProtocolManager();
        MotD motD = new MotD(config);
        protocolManager.addPacketListener(new PacketAdapter(config.getPlugin(), ListenerPriority.NORMAL,  PacketType.Status.Server.SERVER_INFO){
            @Override
            public void onPacketSending(PacketEvent event) {
                if (event.getPacketType() == PacketType.Status.Server.SERVER_INFO) {
                    WrappedServerPing packet = event.getPacket().getServerPings().read(0);

                    packet.setMotD(motD.getMotD());
                    if(Objects.equals(config.getBlockProtocolID(), 1)) packet.setVersionProtocol(protocolManager.getProtocolVersion(event.getPlayer()));
                    else if (Objects.equals(config.getBlockProtocolID(), 2)) packet.setVersionProtocol(-1);
                    packet.setVersionName(config.getVersionText());
                    packet.setPlayersVisible(config.isHidePlayersEnabled());
                    // TODO: Set max numbers, custom playerlist, etc...
                }
            }
        });
    }
}
