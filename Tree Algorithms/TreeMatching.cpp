#include<bits/stdc++.h>
using namespace std;

#define N 200001

vector<vector<int>> adj(N);
vector<bool> visited(N);
vector<int> dp1(N), dp2(N), p(N);
int n, i, a, b;

void dfs(int v, int p) {
    visited[v] = true;

    for(int u: adj[v]) {
        if(!visited[u]) {
            dfs(u, v);
        }
    }

    int sum = 0;
    for(int u: adj[v]) 
        sum += max(dp1[u], dp2[u]);

    dp2[v] = sum;
    for(int u: adj[v]) {
        if(u != p) {
            dp1[v] = max(dp1[v], dp2[u] + 1 + dp2[v] - max(dp2[u], dp1[u]));
        }
    }
}

int main() {
    cin >> n;

    for(i=0; i<n-1; ++i) {
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    dfs(1, 0);
    /*
    for(i=1; i<=n; ++i)
        cout << dp1[i] << " ";
    cout << "\n";
    for(i=1; i<=n; ++i) 
        cout << dp2[i] << " ";
    cout << "\n";
    */

    cout << max(dp1[1], dp2[1]) << "\n";
    return 0;
}
