
package com.otoil.ot_1_1_1.client.master;


import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.otoil.ot_1_1_1.client.ExampleTaskClientFactory;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.client.event.GetDetailIdEvent;
import com.otoil.ot_1_1_1.client.master.impl.FetchedTreeProviderAdapter;

import lombok.Setter;
import ru.ot.mvp.client.presenters.AbstractPresenter;

import ru.ot.wevelns.client.tree.DefaultTreeNode;


@Setter
public class ExampleTaskMasterPresenter
        extends AbstractPresenter<ExampleTaskMasterModel, ExampleTaskMasterView>
{

    private FetchedTreeProviderAdapter<ResponseDocumentCardBean> provider = new FetchedTreeProviderAdapter<>();
    private NoSelectionModel<DefaultTreeNode<ResponseDocumentCardBean>> selectionModel = new NoSelectionModel<>();

    public ExampleTaskMasterPresenter(ExampleTaskClientFactory factory)
    {
        super(factory.getMasterModel(), factory.getMasterView());
        provider.addDataDisplay(view.getTree());
        view.getTree().setSelectionModel(selectionModel);

    }

    @Override
    public void bind()
    {
       

        model.loadTree().subscribe((treeData) -> {
            provider.setTree(treeData);
            provider.refresh();

        });
        selectionModel.addSelectionChangeHandler(e -> createGetDetailIdEvent(
            selectionModel.getLastSelectedObject().getValue().getId()));
        view.getTreeSaveSubject().subscribe(this::saveDocument);
    }

    private void saveDocument(RequestDocumentCardBean cardBean)
    {
        model.saveDocumentCard(cardBean).subscribe();
    }

    private void createGetDetailIdEvent(String id)
    {
        eventBus.fireEvent(new GetDetailIdEvent(id));
    }

}
