import java.util.NoSuchElementException;

/**
 * Course: 2631-001. Instructor: Jason Heard. This file is implements
 * ImmutableSortedSet.
 *
 * @author Glenn Yeap
 *
 */
public class Tree implements ImmutableSortedSet {

    private final TreeNode rootnode;

    /**
     * A constructor for Tree that sets the node to the new node.
     *
     * @param root - root to initialize.
     */

    public Tree(TreeNode root) {
        this.rootnode = root;
    }

    /**
     * a constructor to set the root node to null.
     */

    public Tree() {
        this.rootnode = null;
    }

    @Override
    public ImmutableSortedSet add(String key) {
        if (contains(key)) {
            return Tree.this;
        } else {
            TreeNode newTreeNode = null;
            Tree newTree = new Tree(insert(rootnode, newTreeNode, key));
            return newTree;
        }
    }

    /**
     * This method is a helper function for add as it finds the spot to insert the
     * new node at and inserts the node to the correct spot with all other branches
     * included.
     *
     * @param oldNode - the old node.
     * @param newnode - the new node that we make changes based on the old node.
     * @param newdata - the new data for the new node.
     * @return the new node which is the new node with correct data.
     */

    public TreeNode insert(TreeNode oldNode, TreeNode newnode, String newdata) {

        if (newdata == null) {
            newnode = new TreeNode(new TreeNode(newdata), null, oldNode);
            return newnode;
        }
        if (oldNode == null) {
            newnode = new TreeNode(newdata);
            return newnode;
        } else {
            if (oldNode.getData() == null) {
                if (oldNode.getRight() == null) {
                    newnode = new TreeNode(oldNode, oldNode.getLeft(), new TreeNode(newdata));
                } else {
                    newnode = insert(oldNode.getRight(), newnode, newdata);
                    newnode = new TreeNode(oldNode, oldNode.getLeft(), newnode);
                }
            } else if (newdata.compareTo(oldNode.getData()) > 0) {
                if (oldNode.getRight() == null) {
                    newnode = new TreeNode(oldNode, oldNode.getLeft(), new TreeNode(newdata));
                } else {
                    newnode = insert(oldNode.getRight(), newnode, newdata);
                    newnode = new TreeNode(oldNode, oldNode.getLeft(), newnode);
                }
            } else if (newdata.compareTo(oldNode.getData()) < 0) {
                if (oldNode.getLeft() == null) {
                    newnode = new TreeNode(oldNode, new TreeNode(newdata), oldNode.getRight());
                } else {
                    newnode = insert(oldNode.getLeft(), newnode, newdata);
                    newnode = new TreeNode(oldNode, newnode, oldNode.getRight());
                }
            }
            return newnode;
        }
    }

    @Override
    public ImmutableSortedSet remove(String key) {
        if (!contains(key)) {
            return Tree.this;
        } else {
            Tree newSet = new Tree(removeNode(key, rootnode));
            return newSet;
        }
    }

    /**
     * This method is a helper method to delete which removes the the node we need
     * to remove.
     *
     * @param key     - the key.
     * @param newRoot - new root which means just a new root tree
     * @return the new tree node with the node removed and correct data.
     */

    public TreeNode removeNode(String key, TreeNode newRoot) {
        if (newRoot == null) {
            return null;
        } else if (compareTo(key, newRoot.getData()) < 0) {
            if (newRoot.getLeft() == null) {
                return null;
            } else {
                TreeNode removeNode = removeNode(key, newRoot.getLeft());
                newRoot = new TreeNode(newRoot, removeNode, newRoot.getRight());
                return newRoot;
            }
        } else if (compareTo(key, newRoot.getData()) > 0) {
            if (newRoot.getRight() == null) {
                return null;
            } else {
                newRoot = removeNode(key, newRoot.getRight());
                newRoot = new TreeNode(newRoot, newRoot.getLeft(), newRoot);
                return newRoot;
            }
        } else {
            if (newRoot.getLeft() == null && newRoot.getRight() == null) {
                return null;
            }
            if (newRoot.getLeft() == null) {
                return newRoot.getRight();
            } else if (newRoot.getRight() == null) {
                return newRoot.getLeft();
            } else {
                TreeNode tempMin = min(newRoot.getRight());
                newRoot = new TreeNode(tempMin, newRoot.getLeft(), removeNode(tempMin.getData(), newRoot.getRight()));
            }
        }
        return newRoot;
    }

    /**
     * This is a helper method to help us compare the nodes we are looking at each
     * comparison makes a certain value to allow us to know which direction to move
     * to.
     *
     * @param key  - key one
     * @param key2 - key two
     * @return - we return a integer for the result of the comparison.
     */

    public int compareTo(String key, String key2) {
        if (key == null && key2 == null) {
            return 0;
        } else if (key == null && key2 != null) {
            return -1;
        } else if (key != null && key2 == null) {
            return 1;
        } else {
            return key.compareTo(key2);
        }
    }

    /**
     * This is a helper function to help us find the minimum value of the tree left.
     *
     * @param root - the root we are checking the minimum for.
     * @return - the root or the minimum value of the trees left side.
     */

    private TreeNode min(TreeNode root) {
        if (root.getLeft() == null) {
            return root;
        } else {
            return min(root.getLeft());
        }
    }

    @Override
    public boolean contains(String key) {
        return find(key, rootnode);
    }

    /**
     * a helper method that finds the node we are looking for and returns a boolean
     * indicating if the method has found it or not. We also used the other helper
     * function compareTo to compare both keys.
     *
     * @param key  - the data of the node
     * @param root - the tree node
     * @return - the boolean value indicating if the found the given key is in the
     *         set.
     */

    public boolean find(String key, TreeNode root) {
        if (root == null) {
            return false;
        } else if (compareTo(key, root.getData()) > 0) {
            return find(key, root.getRight());
        } else if (compareTo(key, root.getData()) < 0) {
            return find(key, root.getLeft());
        } else {
            return true;
        }
    }

    @Override
    public String getAtIndex(int index) throws NoSuchElementException {
        if (rootnode == null) {
            throw new NoSuchElementException();
        } else if (index < 0 || index >= sizenode(rootnode)) {
            throw new NoSuchElementException();
        } else {
            return getAtIndex(index, rootnode);
        }
    }

    /**
     * Returns the number of elements in the set.
     *
     * @param key The key to be added to the set
     * @return true if the key was added to the set, false if the key was already in
     *         the set
     * @throws NoSuchElementException if the key is null
     */

    private String getAtIndex(int index, TreeNode root) {
        if (index < sizenode(root.getLeft())) {
            return getAtIndex(index, root.getLeft());
        } else if (index == sizenode(root.getLeft())) {
            return root.getData();
        } else {
            return getAtIndex(index - sizenode(root.getLeft()) - 1, root.getRight());
        }
    }

    @Override
    public int getIndex(String key) throws NoSuchElementException {
        if (key == null) {
            throw new NoSuchElementException();
        } else {
            if (!contains(key)) {
                throw new NoSuchElementException();
            } else {
                return getIndex(key, rootnode);
            }
        }
    }

    /**
     * Returns the number of elements in the set.
     *
     * @param key  The key to be added to the set
     * @param root The root of the tree
     * @return The index of the key
     */

    private int getIndex(String key, TreeNode root) {
        if (root == null) {
            return -1;
        } else if (compareTo(key, root.getData()) < 0) {
            return getIndex(key, root.getLeft());
        } else if (compareTo(key, root.getData()) > 0) {
            return 1 + root.getLeft().getsize() + getIndex(key, root.getRight());
        } else {
            if (root.getLeft() == null) {
                return 0;
            }
            return root.getLeft().getsize();
        }
    }

    @Override
    public int size() {
        return sizenode(rootnode);
    }

    /**
     * We get the size of the given node we need. As this is a helper method for
     * size.
     *
     * @param root - the node we need the size at.
     * @return - the value of the node.
     */

    public int sizenode(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return root.getsize();
        }
    }

    @Override
    public String[] keys() {
        String[] keys = new String[size()];
        getKeys(keys, rootnode, 0);
        return keys;
    }

    /**
    * Returns a new set with the same elements as this set, but with the given
    * element added. The new set must not contain the given element if it was
    * already in the set.
    *
    * @param keys  the array that holds the elements from the tree in order
    * @param index The index to add the element at
    * @param root  The root of the tree
    * @return A new set with the desired change
    * @throws NullPointerException if key is null
    */

    private int getKeys(String[] keys, TreeNode root, int index) {
        if (root != null) {
            index = getKeys(keys, root.getLeft(), index);
            keys[index] = root.getData();
            index++;
            index = getKeys(keys, root.getRight(), index);
        }
        return index;
    }
}
