package com.dgg.hdforeman.mvp.model.been;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;


/**
 * Created by Rex on 2016/10/19.
 */
@Entity(active = true,nameInDb = "HD_USERS")
public class User {

    @Id
    private Long usid ;
    @NotNull
    private String usname ;//用户名

    private String uspassword;

    private String usxname ;//昵称

    private String ussex ;

    private String usborth ;

    private String usmoble ;

    private String ustel ;

    private String ustext ;

    private String usrole ;

    private String usimage;

    private float grade;

    private String taken ;

    private String bank;

    private String bankno;

    private boolean online ;

    private String servicetel;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;

    @Generated(hash = 46419964)
    public User(Long usid, @NotNull String usname, String uspassword,
            String usxname, String ussex, String usborth, String usmoble,
            String ustel, String ustext, String usrole, String usimage, float grade,
            String taken, String bank, String bankno, boolean online,
            String servicetel) {
        this.usid = usid;
        this.usname = usname;
        this.uspassword = uspassword;
        this.usxname = usxname;
        this.ussex = ussex;
        this.usborth = usborth;
        this.usmoble = usmoble;
        this.ustel = ustel;
        this.ustext = ustext;
        this.usrole = usrole;
        this.usimage = usimage;
        this.grade = grade;
        this.taken = taken;
        this.bank = bank;
        this.bankno = bankno;
        this.online = online;
        this.servicetel = servicetel;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getUsid() {
        return this.usid;
    }

    public void setUsid(Long usid) {
        this.usid = usid;
    }

    public String getUsname() {
        return this.usname;
    }

    public void setUsname(String usname) {
        this.usname = usname;
    }

    public String getUspassword() {
        return this.uspassword;
    }

    public void setUspassword(String uspassword) {
        this.uspassword = uspassword;
    }

    public String getUsxname() {
        return this.usxname;
    }

    public void setUsxname(String usxname) {
        this.usxname = usxname;
    }

    public String getUssex() {
        return this.ussex;
    }

    public void setUssex(String ussex) {
        this.ussex = ussex;
    }

    public String getUsborth() {
        return this.usborth;
    }

    public void setUsborth(String usborth) {
        this.usborth = usborth;
    }

    public String getUsmoble() {
        return this.usmoble;
    }

    public void setUsmoble(String usmoble) {
        this.usmoble = usmoble;
    }

    public String getUstel() {
        return this.ustel;
    }

    public void setUstel(String ustel) {
        this.ustel = ustel;
    }

    public String getUstext() {
        return this.ustext;
    }

    public void setUstext(String ustext) {
        this.ustext = ustext;
    }

    public String getUsrole() {
        return this.usrole;
    }

    public void setUsrole(String usrole) {
        this.usrole = usrole;
    }

    public String getUsimage() {
        return this.usimage;
    }

    public void setUsimage(String usimage) {
        this.usimage = usimage;
    }

    public float getGrade() {
        return this.grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getTaken() {
        return this.taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

    public String getBank() {
        return this.bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankno() {
        return this.bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno;
    }

    public boolean getOnline() {
        return this.online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getServicetel() {
        return this.servicetel;
    }

    public void setServicetel(String servicetel) {
        this.servicetel = servicetel;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }

    
}
