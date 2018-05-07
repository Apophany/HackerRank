"""
You are given an array of length l containing
an ascending ordered sequence from the full set of integers.

The array may contain duplicate values from the set.

Given an integer, find a pair from the array,
which when summed add to the given integer
"""


def pair_find_opt(arr, sum):
    """
    Solution in O(n).

    Maintains pointers at the beginning and end
    of the array to provide a bound for the sum.

    If the current sum is too small, we increment
    the lowest value to the next highest. If the
    current sum is too large, then we decrement
    the highest value to the next smallest
    """
    lo = 0
    high = len(arr) - 1

    while lo < high:
        current_sum = arr[lo] + arr[high]

        if current_sum == sum:
            return arr[lo], arr[high]
        elif current_sum < sum:
            lo += 1
        else:
            high -= 1


def pair_find(arr, sum):
    """
    Naive solution running in something
    like O(n log(n))
    """

    did_reach_max = False

    for i in range(len(arr)):
        if did_reach_max:
            for j in range(i):
                current_sum = arr[i] + arr[j]

                if current_sum == sum:
                    return arr[i], arr[j]

                if current_sum > sum:
                    break
        else:
            current_sum = arr[i] + arr[i + 1]

            if current_sum == sum:
                return arr[i], arr[i + 1]

            if current_sum > sum:
                did_reach_max = True


if __name__ == "__main__":
    arr = []
    sum = 8

    print (pair_find_opt(arr, sum))
