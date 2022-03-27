#include<bits/stdc++.h>
using namespace std;

#define ll long long

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    vector<ll> v(n);
    stack<pair<ll, ll>> s;

    for(int i=0; i<n; ++i) {
       cin >> v[i]; 
    }

    // Create stack with values in ascending order
    ll ans = 0;
    s.push({v[0], 1});
    for(int i=1; i<n; ++i) {
        if(v[i] > s.top().first) {
            s.push({v[i], 1});
        } else {
            ll total = 0;
            while(!s.empty() && v[i] <= s.top().first) {
                total += s.top().second; 

                ans = max(ans, total*s.top().first);
                s.pop();
            }

            s.push({v[i], total+1});
        }
    }

    
    ll total = 0;
    while(!s.empty()) {
        total += s.top().second;

        ans = max(ans, total*s.top().first);
        s.pop();
    }
     

    cout << ans << "\n";

    return 0;
}
