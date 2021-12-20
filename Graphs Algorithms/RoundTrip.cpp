#include<bits/stdc++.h>
using namespace std;

#define N 100001

vector<vector<int>> adj(N);
vector<int> parent(N);
vector<bool> visited(N);

int n, m, i, j, a, b, cycle_start, cycle_end;

bool dfs(int vertex, int p) {
    visited[vertex] = true;
    parent[vertex] = p;

    for(int u: adj[vertex]) {
        if(!visited[u]) {
            if(dfs(u, vertex)) 
                return true;
        } else {
            if(u != p) {
                cycle_start = u;
                cycle_end = vertex;
                return true;
            }
        }
    }
    return false;
}

int main() {
    bool ans = false;
    cin >> n >> m;

    for(i=0; i<m; ++i) {
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    for(i=1; i<=n; ++i) {
        if(!visited[i]) {
            if(dfs(i, -1)) {
                ans = true;
                break;
            }
        }
    } 

    if(ans) {
        vector<int> cities{cycle_start};
        while(cycle_end != cycle_start) {
            cities.push_back(cycle_end);
            cycle_end = parent[cycle_end];
        }
        cities.push_back(cycle_start);
        cout << cities.size() << "\n";
        for(int u: cities)
            cout << u << " ";
        cout << "\n";
    } else {
        cout << "IMPOSSIBLE\n";
    }

    return 0;
}
