package com.netcrackerpractice.startup_social_network.payload;

public class SearchStartupsRequest {
    private String startupNameContains;
    private String creator;
    private String sortBy;
    private String sortDirection;
    private String accountID;


    public String getStartupNameContains() {
        return startupNameContains;
    }

    public void setStartupNameContains(String startupNameContains) {
        this.startupNameContains = startupNameContains;
    }

    public String getSortBy() {  return sortBy; }

    public void setSortBy(String sortBy) {  this.sortBy = sortBy; }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getCreator() {  return creator; }

    public void setCreator(String creator) {  this.creator = creator;  }

    public String getAccountID() { return accountID;}

    public void setAccountID(String accountID) { this.accountID = accountID; }
}

