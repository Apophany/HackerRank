import time

import matplotlib.pyplot as plt
import numpy as np
import numpy.random as rnd

import sorting.merge_sort as merge
import sorting.quick_sort as quick

MAX_SIZE = 1000
STEP = 1
NUM_ITERATIONS = 10


def timer(task):
    start = time.clock()
    task()
    end = time.clock()
    return end - start


def test_merge_sort(merge_func):
    res = []
    for list_size in xrange(1, MAX_SIZE, STEP):
        inner_res = []
        for iteration in xrange(NUM_ITERATIONS):
            list_to_sort = [rnd.randint(0, list_size) for r in xrange(list_size)]
            inner_res.append(timer(lambda: merge_func(list_to_sort)))
        res.append(np.mean(inner_res))
    return res


def plot_results(results_one, results_two=None):
    x = [i for i in xrange(len(results_one))]
    plt.plot(x, results_one, label='results_one')
    plt.plot(x, results_two, label='results_two')
    plt.legend(loc='upper right')
    plt.show()


if __name__ == "__main__":
    time_results = test_merge_sort(lambda x: merge.merge_sort(x))
    time_results_opt = test_merge_sort(lambda x: quick.lomuto_sort(x, 0, len(x) - 1))

    plot_results(time_results, time_results_opt)
