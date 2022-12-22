package com.otoil.ot_1_1_1.client.master.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ru.ot.gwt.utils.shared.tree.Tree;
import ru.ot.gwt.utils.shared.tree.TreeNode;
import ru.ot.wevelns.client.tree.DefaultTreeNode;
import ru.ot.wevelns.client.tree.DefaultTreeNodeDataProvider;
import ru.ot.wevelns.client.tree.HasTreeData;


public class FetchedTreeProviderAdapter<T>
        extends DefaultTreeNodeDataProvider<T>
{
    private TreeNode<T> tree;

    private Map<T, TreeNode<T>> nodeMap = new HashMap<>();

    public void setTree(TreeNode<T> tree)
    {
        this.nodeMap.clear();
        this.tree = tree;
    }

    public TreeNode<T> getRawTree()
    {
        return tree;
    }

    @Override
    protected void loadChildrenImpl(T parent, boolean root,
        AsyncCallback<List<T>> callback)
    {
        if (tree == null)
        {
            callback.onSuccess(Collections.emptyList());
            return;
        }

        TreeNode<T> parentNode = parent == null ? tree : nodeMap.get(parent);

        if (parentNode == null)
        {
            callback.onSuccess(new ArrayList<>());
            return;
        }
        Objects.requireNonNull(parentNode);

        List<TreeNode<T>> children = parentNode.getChildren();
        children.forEach(node -> nodeMap.putIfAbsent(node.getValue(), node));

        callback.onSuccess(new ArrayList<>(children.stream()
            .map(TreeNode::getValue).collect(Collectors.toList())));
    }

    @Override
    protected boolean isLeaf(DefaultTreeNode<T> parentNode, T value)
    {
        if (tree == null)
        {
            return super.isLeaf(parentNode, value);
        }

        TreeNode<T> node = nodeMap.get(value);
        if (node == null)
        {
            node = Tree.search(tree, value::equals);
            if (node != null)
            {
                nodeMap.put(node.getValue(), node);
            }
        }

        return node == null
            || Objects.requireNonNull(node).getChildren().isEmpty();
    }

    @Override
    public void removeDataDisplay(HasTreeData<DefaultTreeNode<T>> display)
    {
        super.removeDataDisplay(display);

        if (getDataDisplays().isEmpty())
        {
            this.nodeMap.clear();
            this.tree = null;
        }
    }
}
