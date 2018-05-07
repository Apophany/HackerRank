"""
Insertion sort works by assuming a sorted
array exists between a[0]..a[i] for an
array of length k, where 0 <= i <= k. We then
sort the remaining a[i+1]..a[k] elements in
the array by performing pair-wise swaps to
insert the element a[j] into the correct
position within the sorted sub-array

Insertion sort is an in-place sorting algorithm
which runs in O(n^2) time complexity and requires
O(1) auxiliary space
"""


def sort(l):
    for i in xrange(1, len(l)):
        j = i
        while j > 0 and l[j - 1] > l[j]:
            temp_left = l[j - 1]
            temp_right = l[j]
            l[j - 1] = temp_right
            l[j] = temp_left
            j -= 1
    return l


if __name__ == "__main__":
    print sort([5, 4, 3, 2, 1])
