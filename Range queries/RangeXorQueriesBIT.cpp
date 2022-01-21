// Xor queries using BIT

#include<bits/stdc++.h>
using namespace std;

using ll = long long;
const int MX = 2e5+5;

int n, q;
vector<ll> arr(MX), T(MX);

void update(int i, int add) {
    for(; i<=n; i+=i&(-i)) T[i] ^= add;
}

ll xor_(int i) {
    ll ans = 0;
    for(; i>0; i-=i&(-i)) ans ^= T[i];
    return ans;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    arr.assign(MX, 0);
    T.assign(MX, 0);

    cin >> n >> q;
    for(int i=1; i<=n; ++i) {
        cin >> arr[i];
        update(i, arr[i]);
    }

    // Make BIT
    //for(int i=1; i<=n; ++i)
        //cout << T[i] << " ";
    //cout << "\n";
    
    for(int qq=0; qq<q; ++qq) {
        int a, b;
        cin >> a >> b;
        cout << (xor_(b) ^ xor_(a-1)) << "\n"; 
    }
    return 0;
}
