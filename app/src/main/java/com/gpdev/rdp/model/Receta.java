package com.gpdev.rdp.model;

import com.gpdev.rdp.dao.CategoriaDao;
import com.gpdev.rdp.dao.DaoSession;
import com.gpdev.rdp.dao.RecetaDao;
import com.gpdev.rdp.view.adapter.ElementoListable;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Collections;
import java.util.List;

@Entity(nameInDb = "RECETAS", createInDb = false)
public class Receta implements ElementoListable {

    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;

    @Property(nameInDb = "NOMBRE")
    private String nombre;

    @Property(nameInDb = "INGREDIENTES")
    private String ingredientes;

    @Property(nameInDb = "PREPARACION")
    private String preparacion;

    @Property(nameInDb = "ID_CATEGORIA")
    private Long idCategoria;

    @ToOne(joinProperty = "idCategoria")
    private Categoria categoria;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1571225324)
    private transient RecetaDao myDao;

    @Generated(hash = 1426606615)
    private transient Long categoria__resolvedKey;

    @Generated(hash = 500984161)
    public Receta(Long id, String nombre, String ingredientes, String preparacion,
                  Long idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.preparacion = preparacion;
        this.idCategoria = idCategoria;
    }

    @Generated(hash = 1263523195)
    public Receta() {
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
        return false;
    }

    @Override
    public List<Receta> getChilds() {
        return Collections.emptyList();
    }

    @Override
    public RecetarioType getType() {
        return RecetarioType.RECETA;
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

    public String getIngredientes() {
        return this.ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreparacion() {
        return this.preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    public Long getIdCategoria() {
        return this.idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 2004511963)
    public Categoria getCategoria() {
        Long __key = this.idCategoria;
        if (categoria__resolvedKey == null
                || !categoria__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CategoriaDao targetDao = daoSession.getCategoriaDao();
            Categoria categoriaNew = targetDao.load(__key);
            synchronized (this) {
                categoria = categoriaNew;
                categoria__resolvedKey = __key;
            }
        }
        return categoria;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 842589333)
    public void setCategoria(Categoria categoria) {
        synchronized (this) {
            this.categoria = categoria;
            idCategoria = categoria == null ? null : categoria.getId();
            categoria__resolvedKey = idCategoria;
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

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1619644599)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRecetaDao() : null;
    }
}
