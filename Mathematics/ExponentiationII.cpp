#include<bits/stdc++.h>
using namespace std;

#define ll long long 
#define m 1'000'000'007

ll binary_exp(ll base, ll exp, ll mod) {
    ll ans = 1;
    while(exp >= 1) {
        if(exp & 1)
            ans = (ans * base) % mod;
        base = (base * base) % mod;
        exp/=2;
    }    
    return ans;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int n; 
    cin >> n; 
    while(n--) {
        ll base, exp1, exp2;
        cin >> base >> exp1 >> exp2;

        // cout << binary_exp(binary_exp(base, exp1), exp2) << "\n";
        cout << binary_exp(base, binary_exp(exp1, exp2, m-1), m) << "\n";
    }

    return 0;
}
