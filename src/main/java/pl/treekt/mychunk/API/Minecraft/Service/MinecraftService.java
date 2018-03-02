package pl.treekt.mychunk.API.Minecraft.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.treekt.mychunk.API.Minecraft.Entity.MinecraftResponse;
import pl.treekt.mychunk.API.Minecraft.JSONAPIManager;

@Service
public class MinecraftService implements IMinecraftService {

    @Autowired
    JSONAPIManager jsonapiManager;

    @Override
    public int getOnlinePlayers() {
        MinecraftResponse minecraftResponse = jsonapiManager.call("players.online.count", new String[0]);
        int onlinePlayers = 0;
        if (minecraftResponse.isSuccess()) {
            onlinePlayers = (Integer) minecraftResponse.getSuccessData();
        }
        return onlinePlayers;
    }

    @Override
    public int getMaxPlayers() {
        MinecraftResponse minecraftResponse = jsonapiManager.call("server.settings.max_players", new String[0]);
        int maxPlayers = 0;
        if (minecraftResponse.isSuccess()) {
            maxPlayers = (Integer) minecraftResponse.getSuccessData();
        }
        return maxPlayers;
    }

    @Override
    public boolean commandExecute(String command, String nickname) {
        String[] arguments = {command.replace("$player%", nickname)};
        MinecraftResponse minecraftResponse = jsonapiManager.call("server.run_command", arguments);
        if(minecraftResponse.isSuccess()){
            return true;
        }
        return false;
    }

    @Override
    public String getMOTD() {
        MinecraftResponse minecraftResponse = jsonapiManager.call("server.settings.motd", new String[0]);
        String motd = "";
        if(minecraftResponse.isSuccess()){
            motd = (String) minecraftResponse.getSuccessData();
        }
        return motd;
    }
}
