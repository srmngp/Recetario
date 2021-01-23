package com.gpdev.rdp.dao;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

import com.gpdev.rdp.model.Categoria;

public class CategoriaTest extends AbstractDaoTestLongPk<CategoriaDao, Categoria> {

    public CategoriaTest() {
        super(CategoriaDao.class);
    }

    @Override
    protected Categoria createEntity(Long key) {
        Categoria entity = new Categoria();
        entity.setId(key);
        return entity;
    }

}
