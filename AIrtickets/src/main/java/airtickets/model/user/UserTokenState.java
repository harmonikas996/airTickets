package airtickets.model.user;

import java.util.ArrayList;
import java.util.List;

public class UserTokenState {
	
    private String accessToken;
    private Long expiresIn;
    //private List<String> authorities;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
        //this.authorities = new ArrayList<>();
    }

    public UserTokenState(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        //for(String s : authorities)
        	//this.authorities.add(s);
        
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
}