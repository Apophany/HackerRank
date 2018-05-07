class BinarySearchTree:
    def __init__(self):
        self.__root = None

    def insert(self, val):
        if self.__root is None:
            self.__root = Node(val=val)
        else:
            self.__insert(val, self.__root)

    def max(self):
        if self.__root is None:
            return None

        node = self.__root
        while node.right is not None:
            node = node.right
        return node.val

    def min(self):
        if self.__root is None:
            return None

        node = self.__root
        while node.left is not None:
            node = node.left
        return node.val

    def __insert(self, val, node):
        if val < node.val:
            if node.left is None:
                node.left = Node(val)
            else:
                self.__insert(val, node.left)
        if val > node.val:
            if node.right is None:
                node.right = Node(val)
            else:
                self.__insert(val, node.right)


class Node:
    def __init__(self, val=None, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
