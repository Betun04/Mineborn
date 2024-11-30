package me.betun.mineborn.listeners;

import com.github.rumsfield.konquest.api.KonquestAPI;
import com.github.rumsfield.konquest.api.model.KonquestKingdom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class PlayerListener implements Listener {


    private final KonquestAPI api;

    public PlayerListener(KonquestAPI api) {
        this.api = api;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(e.getFrom().getY() < Objects.requireNonNull(e.getTo()).getY()){

            Player p = e.getPlayer();

            KonquestKingdom kingdom = api.getPlayerManager().getPlayer(p).getKingdom();

            p.sendMessage("Reino: "+kingdom.getName());

        }
    }

}
