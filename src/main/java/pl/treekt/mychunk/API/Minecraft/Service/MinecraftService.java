package pl.treekt.mychunk.API.Minecraft.Service;

import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.to2mbn.jmccc.internal.org.json.JSONObject;
import org.to2mbn.jmccc.util.IOUtils;
import pl.treekt.mychunk.API.Minecraft.Entity.JSONDataObject;
import pl.treekt.mychunk.API.Minecraft.Entity.MinecraftResponse;
import pl.treekt.mychunk.API.Minecraft.JSONAPIManager;
import pl.treekt.mychunk.Utils.Enums.ServerType;

import java.io.IOException;
import java.net.URL;

@Service
public class MinecraftService implements IMinecraftService {


    @Value("${jsonapi.urlHub}")
    private String urlHub;
    @Value("${jsonapi.usernameHub}")
    private String usernameHub;
    @Value("${jsonapi.passwordHub}")
    private String passwordHub;
    @Value("${jsonapi.saltHub}")
    private String saltHub;

    @Value("${jsonapi.urlMain}")
    private String urlMain;
    @Value("${jsonapi.usernameMain}")
    private String usernameMain;
    @Value("${jsonapi.passwordMain}")
    private String passwordMain;
    @Value("${jsonapi.saltMain}")
    private String saltMain;

    @Autowired
    JSONAPIManager jsonapiManager;

    @Override
    public int getOnlinePlayers() {
        JSONDataObject dataObjectHub = new JSONDataObject(urlHub, usernameHub, passwordHub, saltHub);
        MinecraftResponse responseHub = jsonapiManager.call(dataObjectHub,"players.online.count", new String[0]);
        JSONDataObject dataObjectMain = new JSONDataObject(urlMain, usernameMain, passwordMain, saltMain);
        MinecraftResponse responseMain = jsonapiManager.call(dataObjectMain,"players.online.count", new String[0]);

        int onlinePlayers = 0;
        if (responseHub.isSuccess()) {
            onlinePlayers = (Integer) responseHub.getSuccessData();
        }
        if (responseMain.isSuccess()) {
            onlinePlayers += (Integer) responseMain.getSuccessData();
        }
        return onlinePlayers;
    }

    @Override
    public int getMaxPlayers() {
        JSONDataObject dataObjectMain = new JSONDataObject(urlMain, usernameMain, passwordMain, saltMain);
        MinecraftResponse responseMain = jsonapiManager.call(dataObjectMain,"server.settings.max_players", new String[0]);

        int maxPlayers = 0;
        if (responseMain.isSuccess()) {
            maxPlayers = (Integer) responseMain.getSuccessData();
        }
        return maxPlayers;
    }

    @Override
    public boolean commandExecute(String command, String nickname, ServerType serverType) {
        String[] arguments = {command.replace("$player$", nickname)};
        if(serverType == ServerType.Hub){
            JSONDataObject dataObjectHub = new JSONDataObject(urlHub, usernameHub, passwordHub, saltHub);
            MinecraftResponse minecraftResponse = jsonapiManager.call(dataObjectHub,"server.run_command", arguments);
            if(minecraftResponse.isSuccess()){
                return true;
            }
        }else{
            JSONDataObject dataObjectMain = new JSONDataObject(urlMain, usernameMain, passwordMain, saltMain);
            MinecraftResponse minecraftResponse = jsonapiManager.call(dataObjectMain,"server.run_command", arguments);
            if(minecraftResponse.isSuccess()){
                return true;
            }
        }
        return false;
    }

}
