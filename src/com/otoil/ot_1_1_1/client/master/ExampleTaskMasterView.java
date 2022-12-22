package com.otoil.ot_1_1_1.client.master;


import com.google.gwt.user.client.ui.IsWidget;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;

import io.reactivex.subjects.PublishSubject;
import ru.ot.wevelns.client.tree.DefaultTreeNode;
import ru.ot.wevelns.client.tree.TreeDataDisplay;


public interface ExampleTaskMasterView extends IsWidget
{
    TreeDataDisplay<DefaultTreeNode<ResponseDocumentCardBean>> getTree();

    PublishSubject<RequestDocumentCardBean> getTreeSaveSubject();
}
