package com.otoil.ot_1_1_1.client.master.impl;


import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.client.master.ExampleTaskMasterModel;
import com.otoil.ot_1_1_1.shared.ExampleTaskService;

import io.reactivex.Single;
import ru.ot.gwt.utils.client.rest.RestServiceAdapter;
import ru.ot.gwt.utils.shared.tree.TreeNode;


public class ExampleTaskMasterModelImpl implements ExampleTaskMasterModel
{

    private RestServiceAdapter<ExampleTaskService> service = RestServiceAdapter
        .get(GWT.create(ExampleTaskService.class));

    @Override
    public Single<List<ResponseDocumentCardBean>> getDocumentsCard()
    {
        return service.toSingle(ExampleTaskService::getDocumentCard);
    }

    @Override
    public Single<Boolean> saveDocumentCard(RequestDocumentCardBean request)
    {
        return service.toSingle(rs -> rs.saveDocumentCard(request));
    }

    @Override
    public Single<TreeNode<ResponseDocumentCardBean>> loadTree()
    {
        return service.toSingle(rs -> rs.loadTree());
    }
}
