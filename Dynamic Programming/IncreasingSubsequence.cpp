#include<bits/stdc++.h>
using namespace std;

#define INF INT_MAX

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;
    vector<int> arr(n), dp(n + 1, INF);

    for(int i=0; i<n; ++i)
        cin >> arr[i];

    dp[0] = -INF;
    for(int i=0; i<n; ++i) {
        int j = upper_bound(dp.begin(), dp.end(), arr[i]) - dp.begin();
        
        if(dp[j - 1] < arr[i] && arr[i] < dp[j])
            dp[j] = arr[i];
    }

    for(int i=n; i>0; --i) {
        if(dp[i] != INF) {
            cout << i << "\n";
            break;
        }
    }

    return 0;
}
