package com.netcrackerpractice.startup_social_network.entity;

import java.util.Arrays;

public class SearchObject {
    private String[] roles;
    private String[] skills;
    private String searchString;

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public String toString() {
        return "SearchObject{" +
                "roles=" + Arrays.toString(roles) +
                ", skills=" + Arrays.toString(skills) +
                ", searchString='" + searchString + '\'' +
                '}';
    }
}
