package com.hassan.qurandemo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class Ayah {

    @Id(autoincrement = true)
    private Long id;

    private int ayahNumber;
    private String ayahAudio;
    private String ayahText;
    private int numberInSurah;

    private long surah_id;

    @ToOne(joinProperty = "surah_id")
    private Surah surah;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1444270850)
    private transient AyahDao myDao;

    @Generated(hash = 109086871)
    public Ayah(Long id, int ayahNumber, String ayahAudio, String ayahText,
            int numberInSurah, long surah_id) {
        this.id = id;
        this.ayahNumber = ayahNumber;
        this.ayahAudio = ayahAudio;
        this.ayahText = ayahText;
        this.numberInSurah = numberInSurah;
        this.surah_id = surah_id;
    }

    @Generated(hash = 830304122)
    public Ayah() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAyahNumber() {
        return this.ayahNumber;
    }

    public void setAyahNumber(int ayahNumber) {
        this.ayahNumber = ayahNumber;
    }

    public String getAyahAudio() {
        return this.ayahAudio;
    }

    public void setAyahAudio(String ayahAudio) {
        this.ayahAudio = ayahAudio;
    }

    public String getAyahText() {
        return this.ayahText;
    }

    public void setAyahText(String ayahText) {
        this.ayahText = ayahText;
    }

    public int getNumberInSurah() {
        return this.numberInSurah;
    }

    public void setNumberInSurah(int numberInSurah) {
        this.numberInSurah = numberInSurah;
    }

    public long getSurah_id() {
        return this.surah_id;
    }

    public void setSurah_id(long surah_id) {
        this.surah_id = surah_id;
    }

    @Generated(hash = 1546635340)
    private transient Long surah__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1455611538)
    public Surah getSurah() {
        long __key = this.surah_id;
        if (surah__resolvedKey == null || !surah__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SurahDao targetDao = daoSession.getSurahDao();
            Surah surahNew = targetDao.load(__key);
            synchronized (this) {
                surah = surahNew;
                surah__resolvedKey = __key;
            }
        }
        return surah;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 326898918)
    public void setSurah(@NotNull Surah surah) {
        if (surah == null) {
            throw new DaoException(
                    "To-one property 'surah_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.surah = surah;
            surah_id = surah.getId();
            surah__resolvedKey = surah_id;
        }
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
    @Generated(hash = 1788770284)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAyahDao() : null;
    }
}
