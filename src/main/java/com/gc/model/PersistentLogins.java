package com.gc.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persistent_logins")
public class PersistentLogins {
    @Id
    @Column(name="series", nullable = true)
    private String series;

    @Column(name="username", nullable = true)
    private String username;

    @Column(name="token", nullable = true)
    private String token;

    @Column(name="last_used", nullable = true)
    private Date lastUsed;

}
