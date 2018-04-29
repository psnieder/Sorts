package sorts;

import java.util.Comparator;

/**
 * Quick Sort.
 * @author Paul Snieder
 * @param <E> generic
 */
public class QuickSort<E> implements Sort<E> {
  
  /**
   * Sorts the array using quick sort algorithm.
   * @param data the data
   * @param comp comparator
   */
  public void sort(E[] data, Comparator<E> comp) {
    quickSortHelper(data, 0, data.length - 1, comp);
  }
  
  /** Recursive method for quickSort. */
  private void quickSortHelper(E[] data, int first, int last, Comparator<E> comp) {
    if (first < last) {
      int pivot = partition(data, first, last, comp);
      quickSortHelper(data, first, pivot - 1, comp);
      quickSortHelper(data, pivot + 1, last, comp);
    }
  }
  
  /** Partition method for quickSort. */
  private int partition(E[] data, int first, int last, Comparator<E> comp) {
    findPivot(data, first, last, comp);
    E pivot = data[first];
    int up = first;
    int down = last;
    do {
      while (up < last && comp.compare(pivot, data[up]) >= 0) {
        up++;
      }
      while (comp.compare(pivot, data[down]) < 0) {
        down--;
      }
      if (up < down) {
        swap(data, up, down);
      }
    } while (up < down);
    swap(data, first, down);
    return down;
  }
  
  /** Method to find a good pivot. */
  private void findPivot(E[] data, int first, int last, Comparator<E> comp) {
    int mid = (first + last) / 2;
    if (comp.compare(data[first], data[mid]) < 0 && comp.compare(data[mid], data[last]) <= 0) {
      swap(data, first, mid);
    }
    if (comp.compare(data[first], data[last]) < 0 && comp.compare(data[last], data[mid]) <= 0) {
      swap(data, first, last);
    }
  }
  
  /**
   * Sorts the data and returns the time it took, in seconds.
   * @param data the data
   * @param comp comparator
   * @return time it took to run the sort, in seconds
   */
  public double timedSort(E[] data, Comparator<E> comp) {
    long start = System.nanoTime();
    sort(data, comp);
    long end = System.nanoTime();
    return (end - start) / 1000000000.0;
  }
  
  /** Swaps 2 elements in an array. */
  private void swap(E[] data, int i, int j) {
    E temp = data[i];
    data[i] = data[j];
    data[j] = temp;
  }

}
