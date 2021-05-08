import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

class SortAlgos {
    void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp; 
                }
            }
        }
    }
    void mergeSort(int[] arr, int l, int r) {
        if (r - l <= 1) return;
        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid, r);
        int[] subArr = new int[r - l];
        for (int i = l, j = mid; i < mid || j < r;) {
            if (i < mid && (j >= r || arr[i] <= arr[j])) subArr[i - l + j - mid] = arr[i++];
            else subArr[i - l + j - mid] = arr[j++];
        }
        for (int k = l; k < r; k++) arr[k] = subArr[k - l];
    }
    int partition(int arr[], int l, int r) {
        int pivot = arr[r - 1];
        // arr[l...i] < pivot && arr[i + 1...j - 1] >= pivot
        int i = l - 1;
        for (int j = l; j < r - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
            }
        }
        //System.out.println(i);
        int tmp = arr[i + 1]; arr[i + 1] = arr[r - 1]; arr[r - 1] = tmp;
        return i + 1;
    }
    void quickSort(int arr[], int l, int r) {
        if (r - l <= 1) return;
        int p = partition(arr, l, r);
        quickSort(arr, l, p); quickSort(arr, p + 1, r);
    }
    void countingSort(int[] arr, int maxVal) {
        int[] cnt = new int[maxVal + 1];
        for (int x: arr) cnt[x]++;
        for (int k = 1; k <= maxVal; k++) cnt[k] += cnt[k - 1];
        int[] res = new int[arr.length];
        for (int x: arr) {
            res[cnt[x] - 1] = x;
            cnt[x]--;
        } 
        for (int i = 0; i < arr.length; i++) arr[i] = res[i];
    }
    void defaultSort(int[] arr) {
        Arrays.sort(arr);
    }
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter size of array to test: ");
        int n = inp.nextInt();
        int[] arr = new int[n];
        //for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        Random rand = new Random();
        final int maxVal = (int)1e6;
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(maxVal);
        inp.close();
        SortAlgos obj = new SortAlgos();
        int[] arr_copy = new int[n];
        
        System.out.println("Time of execution for each method:");
        for (int i = 0; i < n; i++) arr_copy[i] = arr[i];
        long startTime = System.nanoTime();
        obj.defaultSort(arr_copy);
        System.out.format("defaultSort: %d\n", System.nanoTime() - startTime);

        for (int i = 0; i < n; i++) arr_copy[i] = arr[i];
        startTime = System.nanoTime();
        obj.bubbleSort(arr_copy);
        System.out.format("bubbleSort: %d\n", System.nanoTime() - startTime);

        for (int i = 0; i < n; i++) arr_copy[i] = arr[i];
        startTime = System.nanoTime();
        obj.mergeSort(arr_copy, 0, n);
        System.out.format("mergeSort: %d\n", System.nanoTime() - startTime);

        for (int i = 0; i < n; i++) arr_copy[i] = arr[i];
        startTime = System.nanoTime();
        obj.quickSort(arr_copy, 0, n);
        System.out.format("quickSort: %d\n", System.nanoTime() - startTime);

        for (int i = 0; i < n; i++) arr_copy[i] = arr[i];
        startTime = System.nanoTime();
        obj.countingSort(arr_copy,  maxVal);
        System.out.format("countingSort: %d\n", System.nanoTime() - startTime);
        

        //obj.quickSort(arr, 0, n);
        //System.out.println(Arrays.toString(arr));
    }
}