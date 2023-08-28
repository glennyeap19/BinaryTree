/**
 * Course: 2631-001. Instructor: Jason Heard. This is a TreeNode class that
 * holds all the tree node constructors.
 *
 * @author Glenn Yeap
 *
 */

public class TreeNode {

    private final String data;
    final int size;
    private final TreeNode left;
    private final TreeNode right;

    /**
     * A TreeNode constructor that initializes all variables, we also made our size
     * have different values on different occasions.
     */

    public TreeNode(String newdata, TreeNode newleft, TreeNode newright) {

        this.data = newdata;
        this.left = newleft;
        this.right = newright;

        if (right == null && left == null) {
            size = 1;
        } else if (left == null) {
            size = right.getsize() + 1;
        } else if (right == null) {
            size = left.getsize() + 1;
        } else {
            size = (left.getsize() + right.getsize() + 1);
        }
    }

    /**
     * A TreeNode constructor to update a new data to the new TreeNode.
     */

    public TreeNode(String newdata) {
        this.data = newdata;
        this.size = 1;
        this.left = null;
        this.right = null;
    }

    /**
     * A TreeNode constructor which is like a connector as we add or remove nodes we
     * need to connect the old node to the new one with this constructor.
     */

    public TreeNode(TreeNode prev, TreeNode left, TreeNode right) {
        this.data = prev.getData();
        this.left = left;
        this.right = right;

        if (right == null && left == null) {
            size = 1;
        } else if (left == null && right != null) {
            size = right.getsize() + 1;
        } else if (right == null && left != null) {
            size = left.getsize() + 1;
        } else {
            size = (left.getsize() + right.getsize() + 1);
        }
    }

    /**
     * gets the left of the node.
     *
     * @return left - the left node.
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * gets the right of the node.
     *
     * @return right - the right node.
     */

    public TreeNode getRight() {
        return right;
    }

    /**
     * gets the size of the node.
     *
     * @return size - gets the size of the node.
     */

    public int getsize() {
        return this.size;
    }

    /**
     * gets the data/value of the node.
     *
     * @return data - gets the data of the node.
     */
    public String getData() {
        return this.data;
    }
}
