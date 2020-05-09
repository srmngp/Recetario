package com.gpdev.rdp.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.gpdev.rdp.model.Categoria;
import com.gpdev.rdp.model.Receta;

import com.gpdev.rdp.dao.CategoriaDao;
import com.gpdev.rdp.dao.RecetaDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig categoriaDaoConfig;
    private final DaoConfig recetaDaoConfig;

    private final CategoriaDao categoriaDao;
    private final RecetaDao recetaDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        categoriaDaoConfig = daoConfigMap.get(CategoriaDao.class).clone();
        categoriaDaoConfig.initIdentityScope(type);

        recetaDaoConfig = daoConfigMap.get(RecetaDao.class).clone();
        recetaDaoConfig.initIdentityScope(type);

        categoriaDao = new CategoriaDao(categoriaDaoConfig, this);
        recetaDao = new RecetaDao(recetaDaoConfig, this);

        registerDao(Categoria.class, categoriaDao);
        registerDao(Receta.class, recetaDao);
    }
    
    public void clear() {
        categoriaDaoConfig.clearIdentityScope();
        recetaDaoConfig.clearIdentityScope();
    }

    public CategoriaDao getCategoriaDao() {
        return categoriaDao;
    }

    public RecetaDao getRecetaDao() {
        return recetaDao;
    }

}
