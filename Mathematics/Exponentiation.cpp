#include<bits/stdc++.h>
using namespace std;

#define ll long long
#define mod 1'000'000'007


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n ;
    while(n--) {
        ll base, exp, ans = 1;
        cin >> base >> exp;

        while(exp >= 1) {
            if(exp & 1) 
                ans = ans * base % mod;

            base = base * base % mod;
            exp/=2;
        } 

        cout << ans << "\n";
    }

    return 0;
}
