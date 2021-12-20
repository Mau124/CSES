#include<bits/stdc++.h>
using namespace std;

#define N 100001

int n, m, i, a, b;
vector<vector<int>> adj(N);
vector<int> visited(N, -1);

bool dfs(int vertex, int color) {
    visited[vertex] = color;

    for(int u: adj[vertex]) {
        if(visited[u] == -1) 
            if(!dfs(u, color^1))
                return false;
        if(visited[u] == color) 
            return false;
    }
    return true;
}

int main() {
    bool ans = true;
    cin >> n >> m;
    for(i=0; i<m; ++i) {
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    for(i=1; i<=n; ++i) {
        if(visited[i] == -1) {
            if (!dfs(i, 0)) {
                ans = false;
                break;
            }
        } 
    }

    if(ans) {
        for(i=1; i<=n; ++i) 
            cout << visited[i] + 1 << " ";
        cout << "\n";
    } else {
        cout << "IMPOSSIBLE\n";
    }

    return 0;
}
