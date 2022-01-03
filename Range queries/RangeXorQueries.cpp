#include<bits/stdc++.h>
using namespace std;

#define N 200001

class SegTree {
public:
    int val;
    int leftmost, rightmost;
    SegTree *lChild, *rChild;

    SegTree(int l, int r, int a[]) {
        leftmost = l;
        rightmost = r;

        if(leftmost == rightmost) {
            val = a[leftmost];
        } else {
            int m = leftmost + (rightmost - leftmost)/2;
            lChild = new SegTree(l, m, a);
            rChild = new SegTree(m+1, r, a);

            val = lChild->val ^ rChild->val;
        }
    }

    void print() {
        cout << val << endl;

        if(leftmost == rightmost) return;

        lChild->print();
        rChild->print();
    }

    int xorRange(int l, int r) {
        // Case 1: interval [l, r] does not covers at all [leftmost, rightmost]
        if(l > rightmost || r < leftmost) 
            return -1;

        // Case 2: interval [l, r] covers completelly [leftmost, rightmost]
        if(l <= leftmost && r >= rightmost)
            return val;

        // Case 3: interval [l, r] covers partially [leftmost, rightmost]
        int lVal = lChild->xorRange(l, r);
        int rVal = rChild->xorRange(l, r);
        
        if(lVal == -1) 
            return rVal;

        if(rVal == -1) 
            return lVal;

        return lVal ^ rVal;
    }
};

int arr[N];
int n, q;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> q;
    for(int i=0; i<n; ++i) {
        cin >> arr[i];
    }

    SegTree st = SegTree(0, n-1, arr);
    for(int i=0; i<q; ++i) {
        int l, r;
        cin >> l >> r;
        l--; r--;

        cout << st.xorRange(l, r) << "\n";
    }
     
    // cout << "Printing segment tree" << endl;
    // st.print();
    return 0;
}
