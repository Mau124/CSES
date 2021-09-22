#include<bits/stdc++.h>
using namespace std;

#define ll long long

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    ll n, x;
    cin >> n >> x;
    vector<ll> v(n);

    for(int i=0; i<n; ++i) cin >> v[i];
    sort(v.begin(), v.end());

    ll ptr1 = 0, ptr2 = v.size()-1, ans = 0;

    while(ptr1 <= ptr2) {
        if(v[ptr1] + v[ptr2] <= x) {
            ptr1++;
            ptr2--;
            ans++;
        } else {
            ptr2--;
            ans++;
        }
    }

    cout << ans << "\n";
    
    return 0;
}
