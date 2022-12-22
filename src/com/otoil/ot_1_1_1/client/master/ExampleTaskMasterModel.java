package com.otoil.ot_1_1_1.client.master;


import java.util.List;

import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;

import io.reactivex.Single;
import ru.ot.gwt.utils.shared.tree.TreeNode;
import ru.ot.mvp.client.presenters.Model;


public interface ExampleTaskMasterModel extends Model
{
    Single<List<ResponseDocumentCardBean>> getDocumentsCard();

    Single<Boolean> saveDocumentCard(RequestDocumentCardBean request);

    Single<TreeNode<ResponseDocumentCardBean>> loadTree();
}
