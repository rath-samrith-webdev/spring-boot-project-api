package com.springdev.rentalApp.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserUtilityId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "utility_id")
    private Long utilityId;

    public UserUtilityId() {}

    public UserUtilityId(Long userId, Long utilityId) {
        this.userId = userId;
        this.utilityId = utilityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUtilityId() {
        return utilityId;
    }

    public void setUtilityId(Long utilityId) {
        this.utilityId = utilityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUtilityId that = (UserUtilityId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(utilityId, that.utilityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, utilityId);
    }
}
