#include<bits/stdc++.h>
using namespace std;

#define N 200001

vector<vector<int>> adj(N);
vector<bool> visited(N);
int dp1[N]; // Genera sin subtree
int dp2[N]; // Genera de cada subtree
int n, i, j, a, b;

void dfs(int v, int p) {
    visited[v] = true;

    for(int u: adj[v]) {
        if(!visited[u])
            dfs(u, v);
    }

    int max1 = 0;
    int max2 = 0;
    for(int u: adj[v]) {
        if(dp1[u] > max1) {
            max2 = max1;
            max1 = dp1[u];
        } else if(dp1[u] > max2) {
            max2 = dp1[u];
        }
    }

    for(int u: adj[v]) {
        if(u != p)
            dp1[v] = max(dp1[v], dp1[u] + 1);
    }

    if(adj[v].size() > 1)
        dp2[v] = max1 + max2 + 2;
    else
        dp2[v] = dp1[v];
}

int main() {
    cin >> n;

    for(i=0; i<n-1; ++i) {
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    dfs(1, -1);
    
    /* 
    cout << "Dp1: " << endl;
    for(i=1; i<=n; ++i) {
        cout << dp1[i] << " ";
    }
    cout << "\nDp2: " << endl;
    for(i=1; i<=n; ++i) {
        cout << dp2[i] << " ";
    }
    */

    int ans = 0;
    for(i=1; i<=n; ++i)
        ans = max(ans, dp2[i]);

    cout << ans << "\n";
    return 0;
}
