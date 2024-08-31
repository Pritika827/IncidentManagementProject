package com.transline.entities;


import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class UserId implements Serializable {

    private String userId;
    private String cmpCd;
    private String offCd;

    // Constructor with parameters
    public UserId(String userId, String cmpCd, String offCd) {
        this.userId = userId;
        this.cmpCd = cmpCd;
        this.offCd = offCd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId1 = (UserId) o;
        return userId.equals(userId1.userId) &&
               cmpCd.equals(userId1.cmpCd) &&
               offCd.equals(userId1.offCd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, cmpCd, offCd);
    }
}
