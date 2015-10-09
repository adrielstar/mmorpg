package models;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adriel on 10/7/2015.
 */

@Entity
@Table(name = "servers")
@Proxy(lazy = false)
public class Server {

    @Id
    @Column(name = "address")
    private String sServerAddress;

    @Column(name = "name")
    private String sServerName;

    @Column(name = "location")
    private String sServerLocation;

    @Column(name = "max_users")
    private Integer sServerMaxUsers;

    @Column(name = "connected_users")
    private Integer sServerConnectedUsers;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "stores",
            joinColumns = {@JoinColumn(name = "address")},
            inverseJoinColumns = {@JoinColumn(name = "user_name")})
    private Set<User> mUsers = new HashSet<>();

    public Server() {
        super();
    }

    public Server(String serverAddress, String serverName, String serverLocation,
                  Integer serverMaxUsers, Integer serverConnectedUsers) {
        super();
        sServerAddress = serverAddress;
        sServerName = serverName;
        sServerLocation = serverLocation;
        sServerMaxUsers = serverMaxUsers;
        sServerConnectedUsers = serverConnectedUsers;
    }

    public String getServerAddress() {
        return sServerAddress;
    }

    public String getServerName() {
        return sServerName;
    }

    public String getServerLocation() {
        return sServerLocation;
    }

    public Integer getServerTotalUsers() {
        return sServerMaxUsers;
    }

    public Integer getServerJoinUsers() {
        return sServerConnectedUsers;
    }

    public void setServerJoinUsers(int serverConnectedUsers) {
        sServerConnectedUsers = serverConnectedUsers;
    }

    public Set<User> getUsers() {
        return mUsers;
    }

    public void setUsers(User user) {
        if (user != null) {
            mUsers.add(user);
        }
    }
}
