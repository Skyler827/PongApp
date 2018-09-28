package com.codingdojo.pongapp.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="matchHistories")
public class MatchHistory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private Long score;
    private Long opponentScore;
    private Long opponentId;
    private String opponentName;
    private Boolean hasWon;
    
    @Column(updatable=false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    User user;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public Long getOpponentScore() {
		return opponentScore;
	}
	public void setOpponentScore(Long opponentScore) {
		this.opponentScore = opponentScore;
	}
	public Long getOpponentId() {
		return opponentId;
	}
	public void setOpponentId(Long opponentId) {
		this.opponentId = opponentId;
	}
	public String getOpponentName() {
		return opponentName;
	}
	public void setOpponentName(String opponentName) {
		this.opponentName = opponentName;
	}
	public Boolean getHasWon() {
		return hasWon;
	}
	public void setHasWon(Boolean hasWon) {
		this.hasWon = hasWon;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
