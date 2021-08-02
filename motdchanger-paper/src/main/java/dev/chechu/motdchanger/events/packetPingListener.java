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
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;

public class packetPingListener {
    private ProtocolManager protocolManager;

    public packetPingListener(Plugin paper) {
        protocolManager = ProtocolLibrary.getProtocolManager();
        packetPingListener(paper);
    }

    private void packetPingListener(Plugin plugin) {
        protocolManager.addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL,  PacketType.Status.Server.SERVER_INFO){
            @Override
            public void onPacketSending(PacketEvent event) {
                if (event.getPacketType() == PacketType.Status.Server.SERVER_INFO) {
                    WrappedServerPing packet = event.getPacket().getServerPings().read(0);
                    packet.setMotD("§3Test\n§2Test2");
                    packet.setPlayersVisible(false);
                    packet.setVersionProtocol(-1);
                    packet.setVersionName("§4MAINTENANCE");
                }
            }
        });
    }
}
