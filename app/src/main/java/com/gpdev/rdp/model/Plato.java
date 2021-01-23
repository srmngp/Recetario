package com.gpdev.rdp.model;

import com.gpdev.rdp.view.adapter.ElementoListable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.gpdev.rdp.dao.DaoSession;
import com.gpdev.rdp.dao.CategoriaDao;
import com.gpdev.rdp.dao.PlatoDao;

@Entity(nameInDb = "PLATOS", createInDb = false)
public class Plato implements ElementoListable {

    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;

    @Property(nameInDb = "NOMBRE")
    private String nombre;

    @ToMany(referencedJoinProperty = "idPlato")
    @OrderBy("nombre ASC")
    private List<Categoria> categorias;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 787521487)
    private transient PlatoDao myDao;

    @Generated(hash = 769893402)
    public Plato(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Generated(hash = 1527140625)
    public Plato() {
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String getTitle() {
        return nombre;
    }

    @Override
    public boolean isParent() {
        return true;
    }

    @Override
    public List<Categoria> getChilds() {
        return getCategorias();
    }

    @Override
    public RecetarioType getType() {
        return RecetarioType.PLATO;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1369051173)
    public List<Categoria> getCategorias() {
        if (categorias == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CategoriaDao targetDao = daoSession.getCategoriaDao();
            List<Categoria> categoriasNew = targetDao._queryPlato_Categorias(id);
            synchronized (this) {
                if (categorias == null) {
                    categorias = categoriasNew;
                }
            }
        }
        return categorias;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1090732649)
    public synchronized void resetCategorias() {
        categorias = null;
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
    @Generated(hash = 1564694326)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlatoDao() : null;
    }

}
