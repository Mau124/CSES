#include<bits/stdc++.h>
using namespace std; 

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    int arr[200000];

    cin >> n;
    for(int i=0; i<n; ++i) {
        cin >> arr[i];
    }

    sort(arr, arr+n);
    
    int aux = arr[0], i = 0, cnt = 0;
    while(i < n) {
        while(arr[i] == aux && i < n) 
            i++;
        cnt++;
        aux = arr[i];
    }
    
    cout << cnt << "\n";

    return 0;
}
