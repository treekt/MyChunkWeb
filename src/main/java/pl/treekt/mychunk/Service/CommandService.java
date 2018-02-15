package pl.treekt.mychunk.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.treekt.mychunk.Dao.Interfaces.ICommandDao;
import pl.treekt.mychunk.Entity.Web.Command;
import pl.treekt.mychunk.Entity.Web.Position;
import pl.treekt.mychunk.Service.Interfaces.ICommandService;

import java.util.List;

@Service
public class CommandService implements ICommandService {

    @Autowired
    private ICommandDao commandDao;

    @Override
    public List<Command> getAllCommands(Position position) {
        return commandDao.getAllCommands(position);
    }

    @Override
    public Command getCommandByContent(String content) {
        return commandDao.getCommandByContent(content);
    }

    @Override
    public Boolean addCommand(Command command) {
        if(commandDao.commandExists(command.getContent())){
            return false;
        }else{
            commandDao.addCommand(command);
            return true;
        }
    }

    @Override
    public void updateCommand(Command command) {
        commandDao.updateCommand(command);
    }

    @Override
    public void deleteCommand(String content) {
        commandDao.deleteCommand(getCommandByContent(content));
    }
}
