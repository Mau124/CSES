#include<bits/stdc++.h>
using namespace std;

#define MAX 200000

class SegTree {
public:
    long long sum;
    int leftmost, rightmost;
    SegTree *lChild, *rChild;

    SegTree(int l, int r, int a[]) {
        leftmost = l;
        rightmost = r;

        if(leftmost == rightmost) {
            sum = a[leftmost];
        } else {
            int m = leftmost + (rightmost - leftmost)/2;
            lChild = new SegTree(l, m, a);
            rChild = new SegTree(m+1, r, a);
            recalc();
        }
    } 

    void update(int index, int newVal) {
        if(leftmost == rightmost)  {
            sum = newVal;
            return;
        }
                
        if(index <= lChild->rightmost)
            lChild->update(index, newVal);
        else
            rChild->update(index, newVal);

        recalc();
    }

    long long rangeSum(int l, int r) {
        // Case 1: interval of [l, r] does not overlaps with [leftmost, rightmost]
        if(l > rightmost || r < leftmost) return 0;

        // Case 2: interval of [l, r] covers completely [leftmost, rightmost]
        if(l <= leftmost && r >= rightmost) return sum;

        // Case 3: interval of [l, r] covers partially [leftmost, righmost]
        return lChild->rangeSum(l, r) + rChild->rangeSum(l, r);
    }

    void recalc() {
        if(leftmost == rightmost) 
            return;
        sum = lChild->sum + rChild->sum; 
    }
};

int n, q, i;
int arr[MAX];

int main() {
    cin >> n >> q;
    for(i=0; i<n; ++i) {    
        cin >> arr[i];
    }

    SegTree st = SegTree(0, n-1, arr);

    for(i=0; i<q; ++i) {
        int opc, a, b;
        cin >> opc >> a >> b;
        a--;
        if(opc == 1) { // Update
            st.update(a, b); 
        } else { // Range Sum
            b--;
            cout << st.rangeSum(a, b) << "\n";
        }     
    }
    return 0;
}
