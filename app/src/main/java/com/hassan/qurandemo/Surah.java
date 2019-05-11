package com.hassan.qurandemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity
public class Surah {

    @Id(autoincrement = true)
    private Long id;

    private String surahName;
    private String revelationType;
    private int surahNumber;

    @ToMany(referencedJoinProperty = "surah_id")
    private List<Ayah> ayahs;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1408802846)
    private transient SurahDao myDao;

    @Generated(hash = 1160004581)
    public Surah(Long id, String surahName, String revelationType,
            int surahNumber) {
        this.id = id;
        this.surahName = surahName;
        this.revelationType = revelationType;
        this.surahNumber = surahNumber;
    }

    @Generated(hash = 539460711)
    public Surah() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurahName() {
        return this.surahName;
    }

    public void setSurahName(String surahName) {
        this.surahName = surahName;
    }

    public String getRevelationType() {
        return this.revelationType;
    }

    public void setRevelationType(String revelationType) {
        this.revelationType = revelationType;
    }

    public int getSurahNumber() {
        return this.surahNumber;
    }

    public void setSurahNumber(int surahNumber) {
        this.surahNumber = surahNumber;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 950723386)
    public List<Ayah> getAyahs() {
        if (ayahs == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AyahDao targetDao = daoSession.getAyahDao();
            List<Ayah> ayahsNew = targetDao._querySurah_Ayahs(id);
            synchronized (this) {
                if (ayahs == null) {
                    ayahs = ayahsNew;
                }
            }
        }
        return ayahs;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 772667438)
    public synchronized void resetAyahs() {
        ayahs = null;
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
    @Generated(hash = 1549248438)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSurahDao() : null;
    }
}
