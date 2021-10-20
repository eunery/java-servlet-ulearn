package letscode.DataBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "email", unique = true, updatable = false)
    private String email;

    @Column(name = "pass", unique = false, updatable = false)
    private String pass;

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(String login) {
        this.id = -1;
        this.login = login;
        this.pass = login;
        this.email = login;
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(String login, String pass, String email) {
        this.id = -1;
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    @SuppressWarnings("UnusedDeclaration")
    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPass() {
        return pass;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getEmail() {
        return email;
    }
}
