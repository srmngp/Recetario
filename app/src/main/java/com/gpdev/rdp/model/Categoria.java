package com.gpdev.rdp.model;

import com.gpdev.rdp.dao.CategoriaDao;
import com.gpdev.rdp.dao.DaoSession;
import com.gpdev.rdp.dao.RecetaDao;
import com.gpdev.rdp.view.adapter.ElementoListable;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;
import com.gpdev.rdp.dao.PlatoDao;

@Entity(nameInDb = "CATEGORIAS", createInDb = false)
public class Categoria implements ElementoListable {

    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    private Long id;

    @Property(nameInDb = "NOMBRE")
    private String nombre;

    @Property(nameInDb = "ID_PLATO")
    private Long idPlato;

    @ToMany(referencedJoinProperty = "idCategoria")
    @OrderBy("nombre ASC")
    private List<Receta> recetas;

    @ToOne(joinProperty = "idPlato")
    private Plato plato;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1170305099)
    private transient CategoriaDao myDao;

    @Generated(hash = 798145557)
    private transient Long plato__resolvedKey;

    @Generated(hash = 636314582)
    public Categoria(Long id, String nombre, Long idPlato) {
        this.id = id;
        this.nombre = nombre;
        this.idPlato = idPlato;
    }

    @Generated(hash = 577285458)
    public Categoria() {
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String getTitle() {
        return nombre;
    }

    @Override
    /* FIXME tiiiio implementa esto gracioso anda. Una opción puede ser crear una columna que defina si es padre pero eso not tiene mucho sentido pq todas las
        filas de tendrían ese valor igual. Mejor una tabla a modo de configuración? quizás con varios Enums. O quizás otra interfaz, pq tener los métodos isParent y getChild en Receta
        no tiene sentido
    */
    public boolean isParent() {
        return true;
    }

    @Override
    public List<Receta> getChilds() {
        return getRecetas();
    }

    @Override
    public RecetarioType getType() {
        return RecetarioType.CATEGORIA;
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

    public Long getIdPlato() {
        return this.idPlato;
    }

    public void setIdPlato(Long idPlato) {
        this.idPlato = idPlato;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 898358553)
    public List<Receta> getRecetas() {
        if (recetas == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RecetaDao targetDao = daoSession.getRecetaDao();
            List<Receta> recetasNew = targetDao._queryCategoria_Recetas(id);
            synchronized (this) {
                if (recetas == null) {
                    recetas = recetasNew;
                }
            }
        }
        return recetas;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1858407637)
    public synchronized void resetRecetas() {
        recetas = null;
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
    @Generated(hash = 829587735)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCategoriaDao() : null;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 932251344)
    public Plato getPlato() {
        Long __key = this.idPlato;
        if (plato__resolvedKey == null || !plato__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlatoDao targetDao = daoSession.getPlatoDao();
            Plato platoNew = targetDao.load(__key);
            synchronized (this) {
                plato = platoNew;
                plato__resolvedKey = __key;
            }
        }
        return plato;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1795268747)
    public void setPlato(Plato plato) {
        synchronized (this) {
            this.plato = plato;
            idPlato = plato == null ? null : plato.getId();
            plato__resolvedKey = idPlato;
        }
    }

}
