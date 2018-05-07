import numpy.random as rnd


def naive_sort(x):
    if len(x) < 2:
        return x

    pivot = mid_pivot(x)

    left = naive_sort(x[pivot:])
    right = naive_sort(x[:pivot])

    left_tmp = []
    right_tmp = []

    for i in xrange(len(left)):
        if left[i] < x[pivot]:
            left_tmp.append(left[i])
        else:
            right_tmp.append(left[i])

    for i in xrange(len(right)):
        if right[i] > x[pivot]:
            right_tmp.append(right[i])
        else:
            left_tmp.append(right[i])

    return left_tmp + right_tmp


def mid_pivot(x):
    return int(len(x) / 2)


def lomuto_sort(x, low, high):
    if low < high:
        p = partition(x, low, high)
        lomuto_sort(x, low, p - 1)
        lomuto_sort(x, p + 1, high)


def partition(x, low, high):
    pivot = x[high]
    i = low

    for j in xrange(low, high):
        if x[j] <= pivot:
            left = x[i]
            right = x[j]
            x[j] = left
            x[i] = right
            i += 1
    left = x[i]
    right = x[high]
    x[high] = left
    x[i] = right

    return i


if __name__ == "__main__":
    list_to_sort = [rnd.randint(0, 10) for r in xrange(10)]
    lomuto_sort(list_to_sort, 0, len(list_to_sort) - 1)
    print list_to_sort
