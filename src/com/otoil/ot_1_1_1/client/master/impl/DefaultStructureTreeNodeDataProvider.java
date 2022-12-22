package com.otoil.ot_1_1_1.client.master.impl;


import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.ot.gwt.exception.client.DefaultAsyncCallback;
import ru.ot.wevelns.client.tree.DefaultTreeNode;
import ru.ot.wevelns.client.tree.DefaultTreeNodeDataProvider;


public class DefaultStructureTreeNodeDataProvider<T>
        extends DefaultTreeNodeDataProvider<T>
{

    @Override
    protected void loadChildrenImpl(T parentNode, boolean root,
        AsyncCallback<List<T>> callback)
    {
    }

    public void refreshAndExpandFirst(final DefaultAsyncCallback<T> callback)
    {
        refresh();
        fetch(null, true, new DefaultAsyncCallback<List<DefaultTreeNode<T>>>()
        {
            @Override
            public void onSuccess(List<DefaultTreeNode<T>> result)
            {
                if (getTree().getChildCount(getTree().getRoot()) > 0)
                {
                    DefaultTreeNode<T> child = (DefaultTreeNode<T>) getTree()
                        .getChild(getTree().getRoot(), 0);
                    callback.onSuccess(child.getValue());
                }
                else
                {
                    callback.onSuccess(null);
                }
            }
        });
    }

    public void refreshAndExpandAll(final AsyncCallback<Void> callback)
    {
        refresh();
        Scheduler.get().scheduleFinally(new ScheduledCommand()
        {
            @Override
            public void execute()
            {
                fetchAll(callback);
            }
        });
    }

    public void toggleExpandTemplate(T bean, TreeNode currentNode)
    {
        DefaultTreeNode<T> findNode = findNode(bean);
        Object[] path = getTree().getPathToRoot(findNode);
        for (int i = 1; i < path.length; i++)
        {
            for (int j = 0; j < currentNode.getChildCount(); j++)
            {
                if (currentNode.getChildValue(j).equals(path[i]))
                {
                    currentNode.setChildOpen(j, true, true);
                    break;
                }
            }
        }
    }

}
