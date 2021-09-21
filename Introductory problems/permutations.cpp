#include<bits/stdc++.h>
using namespace std;
 
#define ll long long
#define mod 1000000007
 
ll mpow(ll base, ll exp, ll m) {
    ll ans = 1;
    base%=m;
    while(exp >= 1) {
        if (exp%2) ans = (ans*base) % m;
        base = (base*base) % m;
        exp/=2;
    }
    return ans;
}
 
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
 
    int n;
    cin >> n;
 
    if(n<=3 && n!=1) cout << "NO SOLUTION\n";
    else {
        for(int i=n-1;i>=1;i-=2) cout << i << " ";
        for(int i=n;i>=1;i-=2) cout << i << " ";
        cout << "\n";
    }
 
    return 0;
}