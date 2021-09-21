#include<bits/stdc++.h>
using namespace std;
 
#define ll long long
 
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
 
    ll n;
    cin >> n;
    cout << n << " ";
    while(n != 1) {
        if(n%2) n = n*3+1;
        else n/=2;
        cout << n << " ";
    }
    cout << "\n";
 
    return 0;
}