#include<bits/stdc++.h>
using namespace std;

#define N 100001

vector<vector<int>> adj(N);
vector<int> ans, parent(N);
int checkCycle[N];
bool visited[N];

bool dfsCycle(int vertex) {
    checkCycle[vertex] = 1;

    for(int u: adj[vertex]) {
        if(checkCycle[u] == 0) {
            if(dfsCycle(u))
                return true;
        } else if(checkCycle[u] == 1) {
            return true;
        }
    }
    checkCycle[vertex] = 2;
    return false;
}

void dfs(int vertex) {
    visited[vertex] = true;
    for(auto u: adj[vertex]) {
        if(!visited[u]) {
            dfs(u);
        }
    }
    ans.push_back(vertex); 
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m, a, b;
    cin >> n >> m;

    for(int i=0; i<m; ++i) {
        cin >> a >> b;
        adj[a].push_back(b);
    }

    // Check for cycles in the graph
    bool cycle = false;
    for(int i=1; i<=n; ++i) {
        if(checkCycle[i] == 0) {
            if(dfsCycle(i)) {
                cycle = true;
                break;
            }
        }
    } 

    if(cycle) {
        cout << "IMPOSSIBLE\n";
        return 0;
    }

    for(int i=1; i<=n; ++i) {
        if(!visited[i]) {
            dfs(i);
        }
    }

    reverse(ans.begin(), ans.end());
    for(int i=0; i<n; ++i) {
        cout << ans[i] << " ";
    }
    cout << "\n";
    return 0;
}