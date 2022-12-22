package com.otoil.ot_1_1_1.client.detail.impl;


import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.otoil.ot_1_1_1.client.detail.ExampleTaskDetailModel;
import com.otoil.ot_1_1_1.client.dto.AttributeNameBean;
import com.otoil.ot_1_1_1.shared.ExampleTaskService;

import io.reactivex.Single;
import ru.ot.gwt.utils.client.rest.RestServiceAdapter;


public class ExampleTaskDetailModelImpl implements ExampleTaskDetailModel
{

    private RestServiceAdapter<ExampleTaskService> service = RestServiceAdapter
        .get(GWT.create(ExampleTaskService.class));

    @Override
    public Single<List<AttributeNameBean>> getObjectAttribute(String id)
    {
        return service.toSingle(rs -> rs.getObjectAttribute(id));
    }

}
