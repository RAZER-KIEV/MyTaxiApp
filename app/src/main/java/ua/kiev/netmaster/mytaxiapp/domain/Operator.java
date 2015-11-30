package ua.kiev.netmaster.mytaxiapp.domain;


import java.util.Date;

/**
 * Created by ПК on 14.07.2015.
 */

public class Operator {

    private Long id;
    private String login;
    private String inn;
    private String password;
    private Date passDate;
    private Boolean isBlocked;
    private Boolean isSuperAdmin;
    private Integer wrongPass;

    public Operator(){}
    public Operator(String login, String password, String inn){
        this.login = login;
        this.password=password;
        this.inn=inn;
        passDate = new Date();
        isBlocked=false;
        wrongPass=0;
        isSuperAdmin = false;

    }

    public Boolean getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(Boolean isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getPassDate() {
        return passDate;
    }

    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Integer getWrongPass() {
        return wrongPass;
    }

    public void setWrongPass(Integer wrongPass) {
        this.wrongPass = wrongPass;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", inn='" + inn + '\'' +
                ", password='" + password + '\'' +
                ", passDate=" + passDate +
                ", isBlocked=" + isBlocked +
                ", isSuperAdmin=" + isSuperAdmin +
                ", wrongPass=" + wrongPass +
                '}';
    }
}
