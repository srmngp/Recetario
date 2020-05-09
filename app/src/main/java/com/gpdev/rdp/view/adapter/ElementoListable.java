package com.gpdev.rdp.view.adapter;

import java.io.Serializable;
import java.util.List;

public interface ElementoListable extends Serializable {
    Long getId();

    String getTitle();

    boolean isParent();

    List getChilds();
}
