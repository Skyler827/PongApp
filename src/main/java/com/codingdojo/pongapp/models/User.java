package com.codingdojo.pongapp.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String username;
    @Email
    @NotBlank
    private String email;
    @Size(min=8)
    private String password;
    @Transient
    private String passwordConfirmation;
    @Column(updatable=false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy="user")
    List<MatchHistory> matchHistory;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    
    @OneToOne
    @JoinColumn(name="player1_id")
    private GameRoom gameRoom_player1;
    
    @OneToOne
    @JoinColumn(name="player2_id")
    private GameRoom gameRoom_player2;
    
    @ManyToOne
    @JoinColumn(name="spectator_id")
    private GameRoom gameRoom_spectators;
    
    public User() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }
    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<MatchHistory> getMatchHistory() {
		return matchHistory;
	}
	public void setMatchHistory(List<MatchHistory> matchHistory) {
		this.matchHistory = matchHistory;
	}
	public GameRoom getGameRoom_player1() {
		return gameRoom_player1;
	}
	public void setGameRoom_player1(GameRoom gameRoom_player1) {
		this.gameRoom_player1 = gameRoom_player1;
	}
	public GameRoom getGameRoom_player2() {
		return gameRoom_player2;
	}
	public void setGameRoom_player2(GameRoom gameRoom_player2) {
		this.gameRoom_player2 = gameRoom_player2;
	}
	public GameRoom getGameRoom_spectators() {
		return gameRoom_spectators;
	}
	public void setGameRoom_spectators(GameRoom gameRoom_spectators) {
		this.gameRoom_spectators = gameRoom_spectators;
	}
}