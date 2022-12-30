#include<bits/stdc++.h>
using namespace std;

#define ll long long
const int N = 1e5+1;
const ll mod = 1e9+7;

int n, m, a, b;
vector<vector<int>> adj(N);
vector<int> tp;
bool visited[N];
ll dp[N];

void dfs(int v) {
    visited[v] = true;
    for(auto u: adj[v]) {
        if(!visited[u]) {
            dfs(u);
        }
    }
    tp.push_back(v);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> m;
    for(int i=0; i<m; ++i) {
        cin >> a >> b;
        adj[a].push_back(b);        
    }

    // Topological sorting
    dfs(1);
    reverse(tp.begin(), tp.end());

    // cout << "Topological sorting" << endl;
    // for(int i=0; i<tp.size(); ++i) {
    //     cout << tp[i] << " ";
    // }
    // cout << endl;
    dp[1] = 1;
    for(int i=0; i<tp.size(); ++i) {
        for(auto u: adj[tp[i]]) {
            dp[u] = (dp[tp[i]] + dp[u])%mod;
        }
    }

    cout << dp[n] << "\n";
    return 0;
}