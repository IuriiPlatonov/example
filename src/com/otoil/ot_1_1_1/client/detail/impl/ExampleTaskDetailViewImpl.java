package com.otoil.ot_1_1_1.client.detail.impl;


import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.otoil.ot_1_1_1.client.detail.ExampleTaskDetailView;
import com.otoil.ot_1_1_1.client.dto.AttributeNameBean;
import com.otoil.ot_1_1_1.client.i18n.ExampleTaskConstant;

import ru.ot.wevelns.client.NSBlock;


public class ExampleTaskDetailViewImpl extends VerticalPanel
        implements ExampleTaskDetailView
{

    private final static ExampleTaskConstant CONSTANTS = ExampleTaskConstant.INSTANCE;
    private FlexTable detailTable = new FlexTable();

    @Override
    public Widget asWidget()
    {
        return this;
    }

    public ExampleTaskDetailViewImpl()
    {
        init();
    }

    public void init()
    {
        NSBlock block = new NSBlock(CONSTANTS.detail());

        block.setSize("50vw", "100vh");

        detailTable.addStyleName("detailsTable");
        detailTable.getColumnFormatter().addStyleName(0, "firstDetailColumn");
        detailTable.getColumnFormatter().addStyleName(1, "secondDetailColumn");
        detailTable.setWidth("100%");

        block.setWidget(detailTable);

        this.add(block);
    }

    @Override
    public void addDataToDetailTable(List<AttributeNameBean> list)
    {

        detailTable.removeAllRows();
        detailTable.setText(0, 0, CONSTANTS.attributesName());
        detailTable.setText(0, 1, CONSTANTS.values());

        for (int i = 0; i < list.size(); i++)
        {

            int row = detailTable.getRowCount();

            detailTable.setWidget(row, 0, new Label(list.get(i).getName()));
            detailTable.setWidget(row, 1,
                new Label(list.get(i).getValueString()));
        }

    }

}
