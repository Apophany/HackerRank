"""
Merge Sort is a divide and conquer sorting algorithm
which aims to decompose a list of length n into a set
of n sublists. Each set is then merged against its
neighbour set into a new sorted sublist. This process
is repeated recursively until a sorted sublist of length
n is returned.

Average performance: O(n*log(n))
Worst case performance: O(n*log(n))
"""
import sys


def merge_sort(x):
    """
    Optimised implementation of merge sort

    :param x: Integer list to sort
    :return: Sorted list
    """
    result = []

    if len(x) < 2:
        return x

    mid = int(len(x) / 2)
    y = merge_sort(x[:mid])
    z = merge_sort(x[mid:])

    i = 0
    j = 0

    while i < len(y) and j < len(z):
        if y[i] > z[j]:
            result.append(z[j])
            j += 1
        else:
            result.append(y[i])
            i += 1

    result += y[i:]
    result += z[j:]
    return result


def naive_sort(list_to_sort):
    """
    Naive implementation of merge sort using
    a more verbose implementation
    """
    split_list = list(__split__(list_to_sort))
    return __sort__(split_list)


def __sort__(split_list):
    if len(split_list) == 0:
        return split_list
    if len(split_list) == 1:
        return split_list[0]

    res = []
    for i in xrange(0, len(split_list), 2):
        left = split_list[i]

        if i + 1 < len(split_list):
            right = split_list[i + 1]
            res.append(__merge__(left, right))
        else:
            res.append(left)

    return __sort__(res)


def __split__(list_to_sort):
    for i in xrange(0, len(list_to_sort)):
        yield list_to_sort[i:i + 1]


def __merge__(left, right):
    res = []
    left_pointer = 0
    right_pointer = 0

    while left_pointer < len(left) or right_pointer < len(right):
        left_entry = sys.maxsize
        right_entry = sys.maxsize

        if left_pointer < len(left):
            left_entry = left[left_pointer]
        if right_pointer < len(right):
            right_entry = right[right_pointer]

        if left_entry < right_entry:
            res.append(left_entry)
            left_pointer += 1
        elif left_entry > right_entry:
            res.append(right_entry)
            right_pointer += 1
        elif left_entry == right_entry:
            res.append(left_entry)
            res.append(right_entry)
            left_pointer += 1
            right_pointer += 1

    return res


if __name__ == "__main__":
    a = [4, 5, 9, 36, 4, 14, 8, 9, 11, 1, 9, 3, 6]
    print naive_sort(a)
