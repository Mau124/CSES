#include<bits/stdc++.h>
using namespace std;
 
#define ll long long
 
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); 
 
    ll n, suma = 0, res;
    cin >> n;
 
    for(int i=0; i<n-1;++i) {
        int aux;
        cin >> aux;
        suma += aux;
    }
    res = ((n)*(n+1))/2 - suma;
    cout << res << "\n";
    return 0;
}