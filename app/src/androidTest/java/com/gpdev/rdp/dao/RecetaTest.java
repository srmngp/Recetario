package com.gpdev.rdp.dao;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

import com.gpdev.rdp.model.Receta;
import com.gpdev.rdp.dao.RecetaDao;

public class RecetaTest extends AbstractDaoTestLongPk<RecetaDao, Receta> {

    public RecetaTest() {
        super(RecetaDao.class);
    }

    @Override
    protected Receta createEntity(Long key) {
        Receta entity = new Receta();
        entity.setId(key);
        return entity;
    }



}
