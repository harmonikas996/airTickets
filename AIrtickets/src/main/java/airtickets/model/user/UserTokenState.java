package airtickets.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class UserTokenState {
	
    private String accessToken;
    private Long expiresIn;
    private String username;
    private Long userId;
    private List<String> authorities;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
        this.userId = null;
        this.authorities = new ArrayList<>();
    }

    public UserTokenState(String accessToken, long expiresIn, String username, long userId, Collection<? extends GrantedAuthority> authorities) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.username = username;
        this.userId = userId;
        this.authorities = new ArrayList<>();
        for(GrantedAuthority a : authorities)
        	this.authorities.add(a.getAuthority());
        
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}