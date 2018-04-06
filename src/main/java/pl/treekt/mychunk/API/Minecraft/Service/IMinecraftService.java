package pl.treekt.mychunk.API.Minecraft.Service;

import pl.treekt.mychunk.Utils.Enums.ServerType;

public interface IMinecraftService {

   int getOnlinePlayers();
   int getMaxPlayers();
   boolean commandExecute(String command, String nickname, ServerType serverType);

}
