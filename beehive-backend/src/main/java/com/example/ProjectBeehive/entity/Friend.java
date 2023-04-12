package com.example.ProjectBeehive.entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "FRIENDS")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private BigInteger ID;

    @Column(nullable = false, name = "U_ID")
    private BigInteger U_ID;

    @Column(nullable = false, name = "F_ID")
    private BigInteger F_ID;

    public Friend(){}

    public Friend(BigInteger ID, BigInteger U_ID, BigInteger F_ID){
        this.ID = ID;
        this.F_ID = F_ID;
        this.U_ID = U_ID;
    }


    public BigInteger getFriendId() {
        return F_ID;
    }

    public void setFriendId(BigInteger F_ID) {
        this.F_ID = F_ID;
    }

    public BigInteger getUserId() {
        return U_ID;
    }

    public void setUserId(BigInteger U_ID) {
        this.U_ID = U_ID;
    }

    public BigInteger getId() {
        return ID;
    }

    public void setId(BigInteger ID) {
        this.ID = ID;
    }
}
