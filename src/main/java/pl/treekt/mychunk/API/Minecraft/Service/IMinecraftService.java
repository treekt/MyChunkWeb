package pl.treekt.mychunk.API.Minecraft.Service;

public interface IMinecraftService {

   int getOnlinePlayers();
   int getMaxPlayers();
   String getMOTD();
   boolean commandExecute(String command, String nickname);

}
