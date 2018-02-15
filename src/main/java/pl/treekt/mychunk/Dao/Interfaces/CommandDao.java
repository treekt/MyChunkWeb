package pl.treekt.mychunk.Dao.Interfaces;

import org.springframework.stereotype.Repository;
import pl.treekt.mychunk.Entity.Web.Command;
import pl.treekt.mychunk.Entity.Web.Position;
import pl.treekt.mychunk.Entity.Web.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class CommandDao implements ICommandDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Command> getAllCommands(Position position) {
        String query = "FROM Command WHERE position_id = " + position.getId();
        return (List<Command>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public Command getCommandByContent(String content) {
        String query = "FROM Command WHERE content = '" + content + "'";
        Command command;
        try{
            command = (Command)entityManager.createQuery(query).getSingleResult();
        }catch (Exception e){
            command = null;
        }
        return command;
    }

    @Override
    public void addCommand(Command command) {
        entityManager.persist(command);
    }

    @Override
    public void updateCommand(Command command) {
        Command newCommand = new Command();
        newCommand.setId(command.getId());
        newCommand.setContent(command.getContent());
        newCommand.setPosition(command.getPosition());
        entityManager.flush();
    }

    @Override
    public void deleteCommand(Command command) {
        entityManager.remove(command);
    }

    @Override
    public boolean commandExists(String content) {
        return getCommandByContent(content) != null;
    }
}
