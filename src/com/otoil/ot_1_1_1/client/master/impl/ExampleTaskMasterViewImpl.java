package com.otoil.ot_1_1_1.client.master.impl;


import java.util.Date;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.client.i18n.ExampleTaskConstant;
import com.otoil.ot_1_1_1.client.master.ExampleTaskMasterView;

import io.reactivex.subjects.PublishSubject;
import ru.ot.gwt.utils.client.BeanProperty;
import ru.ot.wevelns.client.NSBlock;
import ru.ot.wevelns.client.NSTreeNodeDataGrid;
import ru.ot.wevelns.client.cell.datebox.DateboxCell;
import ru.ot.wevelns.client.cellview.columns.AbstractDateColumn;
import ru.ot.wevelns.client.cellview.columns.StringColumn;
import ru.ot.wevelns.client.datagrid.TreeNodeDataGrid;
import ru.ot.wevelns.client.tree.DefaultTreeNode;
import ru.ot.wevelns.client.tree.TreeDataDisplay;


public class ExampleTaskMasterViewImpl extends VerticalPanel
        implements ExampleTaskMasterView
{

    public static final BeanProperty<ResponseDocumentCardBean, String> name = BeanProperty
        .create(ResponseDocumentCardBean::getName,
            ResponseDocumentCardBean::setName);

    public static final BeanProperty<ResponseDocumentCardBean, String> orderedNumber = BeanProperty
        .create(ResponseDocumentCardBean::getOrderNumber,
            ResponseDocumentCardBean::setOrderNumber);

    public static final BeanProperty<ResponseDocumentCardBean, Date> changeDate = BeanProperty
        .create(ResponseDocumentCardBean::getChangeDate,
            ResponseDocumentCardBean::setChangeDate);

    public static final BeanProperty<ResponseDocumentCardBean, String> image = BeanProperty
        .create(ResponseDocumentCardBean::getBinaryData,
            ResponseDocumentCardBean::setBinaryData);

    private final static ExampleTaskConstant CONSTANTS = ExampleTaskConstant.INSTANCE;

    private NSTreeNodeDataGrid<ResponseDocumentCardBean> treeTable;

    private PublishSubject<RequestDocumentCardBean> treeSaveSubject = PublishSubject
        .create();

    public ExampleTaskMasterViewImpl()
    {
        init();
    }

    public void init()
    {
        initTreeNodeDataGrid();

        NSBlock block = new NSBlock(CONSTANTS.docCardTable());
        block.setSize("50vw", "100vh");
        block.setWidget(treeTable);

        this.add(block);

    }

    private void initTreeNodeDataGrid()
    {

        
        treeTable = new NSTreeNodeDataGrid<>();
        treeTable.setEditing(true);
        addStringColumn(name, CONSTANTS.name(), 200, true);
        addStringColumn(orderedNumber, CONSTANTS.orderedNumber(), 60, true);
        addDateColumn(changeDate, CONSTANTS.changeDate(), 100, true);
        addImageColumn(image, CONSTANTS.image(), 45, true);

    }

    private void addStringColumn(
        BeanProperty<ResponseDocumentCardBean, String> property, String title,
        int width, boolean visible)
    {
        Column<DefaultTreeNode<ResponseDocumentCardBean>, String> column = new StringColumn<DefaultTreeNode<ResponseDocumentCardBean>>()
        {
            @Override
            public String getValue(
                DefaultTreeNode<ResponseDocumentCardBean> object)
            {
                return property.get(object.getValue());
            }
        };
        column.setSortable(true);
        column.setFieldUpdater((index, node, value) -> {
            property.set(node.getValue(), value);
            treeSaveSubject.onNext(new RequestDocumentCardBean(
                node.getValue().getId(), node.getValue().getName(),
                node.getValue().getOrderNumber()));
        });

        treeTable.addColumn(column, title);
        treeTable.setColumnWidth(column, width + "px");
        treeTable.setColumnVisible(column, visible);

    }

    private void addImageColumn(
        BeanProperty<ResponseDocumentCardBean, String> property, String title,
        int width, boolean visible)
    {

        Column<DefaultTreeNode<ResponseDocumentCardBean>, String> column = new Column<DefaultTreeNode<ResponseDocumentCardBean>, String>(
            new ImageCell())
        {

            @Override
            public void render(Context context,
                DefaultTreeNode<ResponseDocumentCardBean> object,
                SafeHtmlBuilder sb)
            {
//                Image img = new Image(property.get(object.getValue()));
//
//                SafeHtml imageHtml = SafeHtmlUtils
//                    .fromTrustedString(img.toString());
//                sb.append(imageHtml);
//
//                sb.toSafeHtml();
            }

            @Override
            public String getValue(
                DefaultTreeNode<ResponseDocumentCardBean> object)
            {
                if (object == null)
                    return "";
                else
                    return property.get(object.getValue());
            }
        };
        column.setSortable(true);
        treeTable.addColumn(column, title);
        treeTable.setColumnWidth(column, width, Unit.PX);
        treeTable.setColumnVisible(column, visible);

    }

    private void addDateColumn(
        BeanProperty<ResponseDocumentCardBean, Date> property, String title,
        int width, boolean visible)
    {
        AbstractDateColumn<DefaultTreeNode<ResponseDocumentCardBean>, DateboxCell> column = new AbstractDateColumn<DefaultTreeNode<ResponseDocumentCardBean>, DateboxCell>(
            new DateboxCell())
        {
            @Override
            public Date getValue(
                DefaultTreeNode<ResponseDocumentCardBean> object)
            {
                return property.get(object.getValue());
            }
        };
        column.setSortable(true);
        treeTable.addColumn(column, title);
        treeTable.setColumnWidth(column, width + "px");
        treeTable.setColumnVisible(column, visible);
    }

    @Override
    public TreeDataDisplay<DefaultTreeNode<ResponseDocumentCardBean>> getTree()
    {
        return treeTable;
    }

    @Override
    public Widget asWidget()
    {
        return this;
    }

    @Override
    public PublishSubject<RequestDocumentCardBean> getTreeSaveSubject()
    {
        return treeSaveSubject;
    }

}
