package service;

/**
 * Created by Adriel on 10/8/2015.
 */
import models.Server;

import java.util.List;

public interface ServerService {
    boolean addServer(Server server);
    List<Server> ServerList();
    void deleteServer(String serverName);
    void updateServer(Server user);
}
