#include<bits/stdc++.h>
using namespace std;

#define MAX 200000
#define LOG 18

//  sp is for sparse_table
int sp[MAX][LOG];

int arr[MAX];
int n, q, i, j, l, r;

int query(int l, int r) {
    // Get size of subarray
    int diff = r-l+1;
    int k = 0;
    // Get log of two closest to that diff
    while((1<<(k+1)) <= diff) 
        k++;

    return min(sp[l][k], sp[r-(1<<k)+1][k]);
}

int main() {
    // Read data
    cin >> n >> q;
    for(i=0; i<n; ++i) {
        cin >> arr[i];
        sp[i][0] = arr[i];
    }

    // Construct sparse table
    for(j=1; j < LOG; ++j) {
        for(i=0; i < n-(1<<j)+1; ++i) {
            sp[i][j] = min(sp[i][j-1], sp[i+(1<<(j-1))][j-1]);
        }
    }

    // Answer queries 
    for(i=0; i<q; ++i) {
        cin >> l >> r;   
        l--; r--;
        cout << query(l, r) << "\n";
    }  

    return 0;
}
