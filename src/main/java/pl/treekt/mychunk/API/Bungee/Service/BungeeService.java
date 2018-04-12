package pl.treekt.mychunk.API.Bungee.Service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.treekt.mychunk.API.Bungee.BungeeManager;
import pl.treekt.mychunk.Utils.Enums.ServerType;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class BungeeService implements IBungeeService {

    @Value("${bungee.key}")
    private String key;

    @Autowired
    private BungeeManager manager;

    @Override
    public int getOnlineCount() {
        HashMap<String, String> params = prepareParamsIfAuthRequired(false);
        JsonNode response = manager.call("player_count", params);
        return response.get("current_players").asInt();
    }

    @Override
    public int getPlayerLimit() {
        HashMap<String, String> params = prepareParamsIfAuthRequired(false);
        JsonNode response = manager.call("player_count", params);
        return response.get("max_players").asInt();
    }

    @Override
    public ArrayList<String> getOnlinePlayers() {
        HashMap<String, String> params = prepareParamsIfAuthRequired(false);
        JsonNode response = manager.call("players_online", params);

        ArrayList<String> players = new ArrayList<>();
        if (response.get("players").isArray()) {
            for (JsonNode playerNode : response.get("players")) {
                players.add(playerNode.asText());
            }
        }
        return players;
    }

    @Override
    public boolean executeCommandOnServer(String player, String command, ServerType serverType) {
        command = command.replace("$player$", player);
        if(isPlayerLogged(player)){
            for(String serverName : getServerList()){
                if(serverName.toLowerCase().contains(serverType.toString().toLowerCase())){
                    HashMap<String, String> params = prepareParamsIfAuthRequired(true);
                    params.put("command", command);
                    params.put("server", serverName);
                    manager.call("invoke_command_on_server", params);
                }
            }
            return true;
        }
        return false;
    }


    @Override
    public boolean sendMessageToPlayer(String nickname, String message) {
        HashMap<String, String> params = prepareParamsIfAuthRequired(true);
        params.put("player", nickname);
        params.put("message", message);
        JsonNode response = manager.call("send_message", params);
        return response.get("status").asText().equals("OK");
    }

    @Override
    public boolean isPlayerLogged(String nickname) {
        return getOnlinePlayers().contains(nickname);
    }

    @Override
    public ArrayList<String> getServerList() {
        HashMap<String, String> params = prepareParamsIfAuthRequired(false);
        JsonNode response = manager.call("server_list", params);

        ArrayList<String> servers = new ArrayList<>();
        if (response.get("servers").isArray()) {
            for (JsonNode serverNode : response.get("servers")) {
                servers.add(serverNode.asText());
            }
        }

        return servers;
    }

    private HashMap<String, String> prepareParamsIfAuthRequired(boolean requiresAuth) {
        HashMap<String, String> params = new HashMap<>();
        if (requiresAuth) {
            params.put("key", key);
        }
        return params;
    }
}
