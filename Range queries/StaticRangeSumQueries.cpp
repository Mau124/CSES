#include<bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int i, l, r, n, q;
    cin >> n >> q;

    int arr[n+1];
    vector<long long> prefix(n+1);

    cin >> arr[1];
    prefix[1] = arr[1];
    
    for(i=2; i<=n; ++i) {
        cin >> arr[i];
        prefix[i] = prefix[i-1] + arr[i];
    }

    for(i=0; i<q; ++i) {
        cin >> l >> r;
        cout << prefix[r] - prefix[l-1] << "\n";
    }

    return 0;
}
