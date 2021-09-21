#include<bits/stdc++.h>
using namespace std;
 
#define ll long long
 
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); 
 
    ll ans = 0;
    int n, last, actual;
    cin >> n >> last;
 
    for(int i=1; i<n; ++i) {
        cin >> actual;
        if(actual<last) ans += (last-actual);
        else last = actual;
    }
    cout << ans << "\n";
    return 0;
}