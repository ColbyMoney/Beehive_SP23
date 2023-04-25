/*
package com.example.ProjectBeehive.entity;

import com.example.ProjectBeehive.enums.FriendStatus;
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
    private BigInteger userId;

    @Column(nullable = false, name = "F_ID")
    private BigInteger friendId;

    @Column(nullable = false, name = "STATUS")
    @Enumerated(EnumType.STRING)
    private FriendStatus status;

    public Friend(){}

    public Friend(BigInteger ID, BigInteger userId, BigInteger friendId){
        this.ID = ID;
        this.friendId = friendId;
        this.userId = userId;
    }


    public BigInteger getFriendId() {
        return friendId;
    }

    public void setFriendId(BigInteger F_ID) {
        this.friendId = friendId;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger U_ID) {
        this.userId = userId;
    }

    public BigInteger getId() {
        return ID;
    }

    public void setId(BigInteger ID) {
        this.ID = ID;
    }

    public FriendStatus getStatus() {
        return status;
    }

    public void setStatus(FriendStatus status) {
        this.status = status;
    }
}
*/
