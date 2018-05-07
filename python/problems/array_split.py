"""
An array contains N integers.
We have to break this array into 3 parts at breakpoints P and Q, where: 0 < P < Q < N-1.
P and Q cannot be adjacent, that is: Q-P > 1.

The cost of this breaking is arr[P]+arr[Q].
An array can be broken in many ways using various legal combinations of P and Q, each having its own cost. Find the minimal cost.

For example, an array

arr = [5,2,4,6,3,7]
we can break as follows:

P, Q -----> Cost
1, 3 -----> 2 + 6 = 8
1, 4 -----> 2 + 3 = 5
2, 4 -----> 4 + 3 = 7

The minimum cost is = 5.
"""
import sys


def minimum_cost(array):
    p = sys.maxint
    q = sys.maxint
    smallest_p = sys.maxint
    smallest_q = sys.maxint

    for i, j in enumerate(array):
        current_p_value = array[i]

        if current_p_value < smallest_p:
            p = i
            q = sys.maxint

            smallest_p = current_p_value
            smallest_q = sys.maxint

        if j + 2 < len(array) - 1:
            current_j_value = array[j + 2]
            if current_j_value < smallest_q:
                q = j + 2
                smallest_q = current_j_value

    return array[p] + array[q]


if __name__ == "__main__":
    array = [5, 2, 4, 6, 3, 7]
    cost = minimum_cost(array)

    print cost
