#include<bits/stdc++.h>
using namespace std;

#define N 100001

vector<vector<int>> adj(N);
int visited[N], parent[N];
int n, m, a, b, cycleStart, cycleEnd;

bool dfs(int vertex, int par) {
    visited[vertex] = 1;
    parent[vertex] = par;
    for(auto u: adj[vertex]) {
        if(visited[u] == 0) {
            if(dfs(u, vertex)) {
                return true;
            }
        } else if(visited[u] == 1) {
                cycleStart = u;
                cycleEnd = vertex;
                return true;
        }
    }
    visited[vertex] = 2;
    return false;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> m;
    for(int i=0; i<m; ++i) {
        cin >> a >> b;
        adj[a].push_back(b);
    }

    // Find cycle
    bool cycle = false;
    for(int i=1; i<=n; ++i) {
        if(visited[i] == 0) {
            if(dfs(i, -1)) {
                cycle = true;
                break;
            }
        }
    }

    if(cycle) {
        vector<int> ans;
        int u = cycleEnd, cnt = 1;
        while(u != cycleStart) {
            ans.push_back(u);
            cnt++;
            u = parent[u];
        }
        ans.push_back(cycleStart);
        reverse(ans.begin(), ans.end());
        cout << cnt+1 << "\n";
        for(int i=0; i<ans.size(); ++i) {
            cout << ans[i] << " ";
        }
        cout << ans[0];
        cout << "\n";
    } else {
        cout << "IMPOSSIBLE\n";
    }

    return 0;
}