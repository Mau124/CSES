#include<bits/stdc++.h>
using namespace std;
 
#define INF INT_MAX
 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    map<int, long long> dp;
    int n;
    cin >> n;
    vector<tuple<long long, long long, long long>> projects(2*n);
    for(int i=0; i<2*n; i+=2) {
        long long start, end, reward;
        cin >> start >> end >> reward;
        projects[i] = {start, end, reward};
        projects[i+1] = {end+1, -1, 0};
    }
    sort(projects.begin(), projects.end());

    dp[-1] = 0;
    long long ans = 0;
    for(int i=projects.size()-1; i>=0; --i) {
        long long start, end, reward;
        tie(start, end, reward) = projects[i];

        long long currentValue = dp[start];
        long long updatedValue = reward + dp[end+1];

        dp[start] = max(ans, max(currentValue, updatedValue));

        ans = max(ans, dp[start]);
    }

    cout << ans;
 
    return 0;
}
