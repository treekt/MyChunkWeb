package pl.treekt.mychunk.API.Bungee.Service;

import pl.treekt.mychunk.Utils.Enums.ServerType;

import java.util.ArrayList;

public interface IBungeeService {

    int getOnlineCount();

    int getPlayerLimit();

    ArrayList<String> getOnlinePlayers();

    boolean executeCommandOnServer(String player, String command, ServerType serverType);

    boolean sendMessageToPlayer(String nickname, String message);

    ArrayList<String> getServerList();

    boolean isPlayerLogged(String nickname);

}
