#include<bits/stdc++.h>
using namespace std;

class SegTree {
public:
    int minVal;
    int leftmost, rightmost;
    SegTree *lChild, *rChild;

    SegTree(int l, int r, int a[]) {
        leftmost = l;
        rightmost = r;

        if(leftmost == rightmost) {
            minVal = a[leftmost];
        } else {
            int m = leftmost + (rightmost - leftmost)/2;
            lChild = new SegTree(l, m, a);
            rChild = new SegTree(m+1, r, a);
            recalc();
        }
    }

    void update(int index, int newVal) {
        if(leftmost == rightmost) {
            minVal = newVal;
            return;
        }

        if(index <= lChild->rightmost) 
            lChild->update(index, newVal);
        else
            rChild->update(index, newVal);

        recalc();            
    }

    int minQuery(int l, int r) {
        // Case 1: Query [l, r] does not cover [leftmost, rightmost] at all
        if(l > rightmost || r < leftmost) return INT_MAX;

        // Case 2: Query [l, r] covers all [leftmost, rightmost]
        if(l <= leftmost && r >= rightmost) return minVal;

        // Case 3: Query [l, r] covers partially [leftmost, rightmost]
        return min(lChild->minQuery(l, r), rChild->minQuery(l, r));
    }

    void recalc() {
        if(leftmost == rightmost) 
            return;
        minVal = min(lChild->minVal, rChild->minVal); 
    }
};

int main() {
    int n, q;
    int arr[200000];
    cin >> n >> q;
    for(int i=0;i<n; ++i) {
        cin >> arr[i];
    }

    SegTree st = SegTree(0, n-1, arr);

    for(int i=0; i<q; ++i) {
        int opc, a, b;
        cin >> opc >> a >> b;
        a--;
        if(opc == 1) { // update
            st.update(a, b);            
        } else { // min Query
            b--;
            cout << st.minQuery(a, b) << "\n";
        }
    }
    return 0;
}
