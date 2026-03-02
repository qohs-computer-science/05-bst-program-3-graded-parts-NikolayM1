import java.lang.Comparable;


public class BST implements BSTInterface
{
    private TreeNode root;       // root of the tree
    private int nodeCount;       // number of nodes in the tree

    /**
     * Construct an empty BST.
     */
    public BST()
    {
        root = null;
        nodeCount = 0;
    }

    /**
     * @return current number of nodes in the tree
     */
    public int size()
    {
        return nodeCount;
    }

    /**
     * @return true if the tree contains no nodes
     */
    public boolean isEmpty()
    {
        return root == null;
    }

    /**
     * Add a value to the tree.  Duplicates are allowed and placed on the left
     * side (<= relationship).
     * @param newVal value to insert
     */
    public void add(Comparable newVal)
    {
        root = addHelper(root, newVal);
    }

    /**
     * Recursively insert into subtree rooted at current.
     * @param current current subtree root
     * @param newVal value to insert
     * @return new subtree root after insertion
     */
    private TreeNode addHelper(TreeNode current, Comparable newVal)
    {
        if (current == null)
        {
            nodeCount++;
            return new TreeNode(newVal);
        }
        if (newVal.compareTo(current.getValue()) <= 0)
        {
            current.setLeft(addHelper(current.getLeft(), newVal));
        }
        else
        {
            current.setRight(addHelper(current.getRight(), newVal));
        }
        return current;
    }

    /**
     * Search for a value in the tree.
     * @param toFind value to look for
     * @return true if found, false otherwise
     */
    public boolean find(Comparable toFind)
    {
        return findHelper(root, toFind);
    }

    private boolean findHelper(TreeNode current, Comparable toFind)
    {
        if (current == null)
            return false;
        int cmp = toFind.compareTo(current.getValue());
        if (cmp == 0)
            return true;
        else if (cmp < 0)
            return findHelper(current.getLeft(), toFind);
        else
            return findHelper(current.getRight(), toFind);
    }

    /**
     * Replace old value with toAdd.  If old exists, delete it then insert toAdd,
     * returning true.  If old not found, insert toAdd and return false.
     */
    public boolean replace(Comparable old, Comparable toAdd)
    {
        boolean existed = delete(old);
        add(toAdd);
        return existed;
    }

    /**
     * Delete a value from the tree.  Returns true if deletion occurred.
     */
    public boolean delete(Comparable old)
    {
        int before = nodeCount;
        root = deleteHelper(root, old);
        return nodeCount < before;
    }

    /**
     * Recursively delete a node from subtree and return new root.
     */
    private TreeNode deleteHelper(TreeNode current, Comparable target)
    {
        if (current == null)
            return null;
        int cmp = target.compareTo(current.getValue());
        if (cmp < 0)
        {
            current.setLeft(deleteHelper(current.getLeft(), target));
        }
        else if (cmp > 0)
        {
            current.setRight(deleteHelper(current.getRight(), target));
        }
        else
        {
            // found node to delete
            nodeCount--;
            // cases
            if (current.getLeft() == null)
            {
                return current.getRight();
            }
            else if (current.getRight() == null)
            {
                return current.getLeft();
            }
            else
            {
                // two children: replace with inorder successor
                TreeNode successor = findMin(current.getRight());
                current.setValue(successor.getValue());
                current.setRight(deleteHelper(current.getRight(), successor.getValue()));
            }
        }
        return current;
    }

    private TreeNode findMin(TreeNode current)
    {
        while (current.getLeft() != null)
            current = current.getLeft();
        return current;
    }

    /**
     * Print in-order traversal.
     */
    public void printInOrder()
    {
        System.out.println("InOrder:");
        inOrderHelper(root);
        System.out.println();
    }

    private void inOrderHelper(TreeNode current)
    {
        if (current == null) return;
        inOrderHelper(current.getLeft());
        System.out.print(current.getValue() + " ");
        inOrderHelper(current.getRight());
    }

    /**
     * Print pre-order traversal.
     */
    public void printPreOrder()
    {
        System.out.println("PreOrder:");
        preOrderHelper(root);
        System.out.println();
    }

    private void preOrderHelper(TreeNode current)
    {
        if (current == null) return;
        System.out.print(current.getValue() + " ");
        preOrderHelper(current.getLeft());
        preOrderHelper(current.getRight());
    }

    /**
     * Print post-order traversal.
     */
    public void printPostOrder()
    {
        System.out.println("PostOrder:");
        postOrderHelper(root);
        System.out.println();
    }

    private void postOrderHelper(TreeNode current)
    {
        if (current == null) return;
        postOrderHelper(current.getLeft());
        postOrderHelper(current.getRight());
        System.out.print(current.getValue() + " ");
    }
}
