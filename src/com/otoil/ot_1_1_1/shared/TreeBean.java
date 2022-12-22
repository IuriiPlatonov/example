package com.otoil.ot_1_1_1.shared;


import ru.ot.gwt.utils.client.bean.HasId;
import ru.ot.gwt.utils.client.bean.HasParentId;

import com.google.gwt.user.client.rpc.IsSerializable;


public interface TreeBean extends IsSerializable, HasId, HasParentId
{
    boolean hasChildren();
}
