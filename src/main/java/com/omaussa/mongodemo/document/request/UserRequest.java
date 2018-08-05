package com.omaussa.mongodemo.document.request;

public class UserRequest {

    private String name;
    private String teamName;
    private Long salary;

    public UserRequest() {
    }

    public UserRequest(String name, String teamName, Long salary) {
        this.name = name;
        this.teamName = teamName;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
