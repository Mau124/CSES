#include<bits/stdc++.h>
using namespace std;

#define N 100001

int n, m, a, b;
vector<vector<int>> adj(N);
bool visited[N];
int dp[N], parent[N];
vector<int> topologicalSorting; 
bool start, last;

void dfs(int v, int startValue, int endValue) {
    if(v == startValue) start = true;
    if(v == endValue) last = true;
    visited[v] = true;
    for(auto u: adj[v]) {
        if(!visited[u]) {
            dfs(u, startValue, endValue);
        }
    }
    topologicalSorting.push_back(v);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> m;
    for(int i=0; i<m; ++i) {
        cin >> a >> b;
        adj[a].push_back(b);
    }

    // Topological Sort
    bool solution = false;
    for(int i=1; i<=n; ++i) {
        if(!visited[i]) {
            topologicalSorting.clear();
            start = false;
            last = false;
            dfs(i, 1, n);
            if(start && last) {
                solution = true;
                break;
            }
        }
    }
    reverse(topologicalSorting.begin(), topologicalSorting.end());

    dp[1] = 1;
    parent[1] = -1;
    for(int i=0; i<topologicalSorting.size(); ++i) {
        for(auto u: adj[topologicalSorting[i]]) {
            if(dp[topologicalSorting[i]]+1 > dp[u]) {
                dp[u] = dp[topologicalSorting[i]]+1;
                parent[u] = topologicalSorting[i];
            }
        }
    }

    if(!solution) {
        cout << "IMPOSSIBLE\n";
    } else {
        cout << dp[n] << "\n";
        vector<int> path;
        int u = n;
        while(parent[u] != -1) {
            path.push_back(u);
            u = parent[u];
        }
        path.push_back(1);
        reverse(path.begin(), path.end());
        for(int i=0; i<path.size(); ++i) {
            cout << path[i] << " ";
        }
        cout << "\n";
    }

    return 0;
}