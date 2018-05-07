class Heap:
    """
    A heap is an array representation of
    a partial binary tree. Heaps consist of
    min heaps and max heaps. The invariant
    for a min/max heap is that given a node n,
    with value v, all nodes i in the subtree below
    n, must have v_i <= v for a min heap, or v_i
    >= v for a max heap

    The heap is represented by an array where
    the root of the tree is at index 1, and for
    any arbitrary node index i, its left and
    right children can be found at index 2i
    and 2i + 1 respectively
    """
    MIN = "min"
    MAX = "max"

    def __init__(self, heap_type=MAX, data=None):
        if heap_type == Heap.MAX:
            self.__invariant = self.__max_func
        if heap_type == Heap.MIN:
            self.__invariant = self.__min_func

        if data is None:
            self.__root = "root"
            self.__data = [self.__root]
        else:
            self.__data = data
            self.__build_heap()

    def insert(self, x):
        self.__data.append(x)
        self.__shift_up(len(self.__data) - 1)

    def peek(self):
        """
        Returns the root of the heap. Depending
        if this is a max/min heap, this is the
        max/min element in the heap
        :return: the root (max/min) element
        """
        return self.__data[1]

    def pop(self):
        """
        Returns and removes the root of the heap.
        Depending on the invariant this is the
        min/max element
        :return: The root (max/min) element
        """
        if len(self.__data) == 1:
            return
        if len(self.__data) == 2:
            return self.__data.pop()
        root = self.__data[1]
        self.__data[1] = self.__data.pop()
        self.__heapify(1)
        return root

    def sort(self):
        a = []
        self.__sort(a)
        return a

    def __build_heap(self):
        """
        Builds a heap satisfying the heap invariant
        using a bottom up approach
        :return: a heap satisfying the invariant
        """
        for i in xrange(len(self.__data) / 2, 0, -1):
            self.__heapify(i)

    def __heapify(self, i):
        """
        Corrects a single violation of the heap invariant.
        Assumes the subtrees of the children of this root
        already satisfy the heap invariant
        :param arr: array heap with a single violation
        :param i: index of the violation
        :return: a heap satisfying the heap invariant
        """
        # Leaf node
        if i > len(self.__data) / 2:
            return

        curr = self.__data[i]
        l = self.__left(i)
        r = self.__right(i)

        is_invariant = True
        if l is not None and not self.__invariant(curr, self.__data[l]):
            is_invariant = False
        if r is not None and not self.__invariant(curr, self.__data[r]):
            is_invariant = False

        if is_invariant:
            return self.__data

        if l is not None and not self.__invariant(curr, self.__data[l]):
            largest = l
        else:
            largest = i

        if r is not None and not self.__invariant(self.__data[largest], self.__data[r]):
            largest = r

        if largest is not i:
            self.__data[i] = self.__data[largest]
            self.__data[largest] = curr
            self.__heapify(largest)

    def __shift_up(self, x_i):
        if x_i == 1:
            return

        parent_i = self.__parent(x_i)
        parent_val = self.__data[parent_i]
        x_val = self.__data[x_i]

        if not self.__invariant(parent_val, x_val):
            self.__data[parent_i] = x_val
            self.__data[x_i] = parent_val
            self.__shift_up(parent_i)

    def __sort(self, a):
        a.append(self.pop())
        if len(self.__data) == 1:
            return
        self.__sort(a)

    def __parent(self, i):
        if i / 2 >= len(self.__data):
            return None
        return i / 2

    def __left(self, i):
        if 2 * i >= len(self.__data):
            return None
        return 2 * i

    def __right(self, i):
        if 2 * i + 1 >= len(self.__data):
            return None
        return 2 * i + 1

    @staticmethod
    def __max_func(x, y):
        if x is None:
            return y
        if y is None:
            return x
        return x >= y

    @staticmethod
    def __min_func(x, y):
        if x is None:
            return y
        if y is None:
            return x
        return x <= y
