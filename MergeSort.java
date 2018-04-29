package sorts;

import java.util.Comparator;

/**
 * Merge Sort.
 * @author Paul Snieder
 * @param <E> generic
 */
public class MergeSort<E> implements Sort<E> {
  
  /**
   * Sorts the array using merge sort algorithm.
   * @param data the data
   * @param comp comparator
   */
  @SuppressWarnings("unchecked")
  public void sort(E[] data, Comparator<E> comp) {
    if (data.length > 1) {
      int half = data.length / 2;
      E[] leftData = (E[]) new Object[half];
      System.arraycopy(data, 0, leftData, 0, half);
      E[] rightData = (E[]) new Object[data.length - half];
      System.arraycopy(data, half, rightData, 0, data.length - half);
      sort(leftData, comp);
      sort(rightData, comp);
      merge(data, leftData, rightData, comp);
    }
  }
  
  /** Merge method for mergeSort. */
  private void merge(E[] out, E[] left, E[] right, Comparator<E> comp) {
    int i = 0; //left index
    int j = 0; //right index
    int k = 0; //out index
    
    while (i < left.length && j < right.length) {
      if (comp.compare(left[i], right[j]) < 0) {
        out[k++] = left[i++];
      } else {
        out[k++] = right[j++];
      }
    }
    while (i < left.length) { 
      out[k++] = left[i++]; 
    }
    while (j < right.length) {
      out[k++] = right[j++];
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

}
