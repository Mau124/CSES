#include<bits/stdc++.h>
using namespace std;

const int N = 1e5+1;

int n, m, a, b;
vector<vector<int>> adj(N), reverse_adj(N);
vector<bool> visited(N);
vector<int> order;
int ans[N];

void dfs1(int node) {
    visited[node] = true;
    for(auto u: adj[node]) {
        if(!visited[u]) {
            dfs1(u);
        }
    }

    order.push_back(node);
}

void dfs2(int node, int color) {
    visited[node] = true;
    ans[node] = color;
    for(auto u: reverse_adj[node]) {
        if(!visited[u]) {
            dfs2(u, color);
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> m;
    for(int i=0; i<m; ++i) {
        cin >> a >> b;
        adj[a].push_back(b);
        reverse_adj[b].push_back(a);
    }

    for(int i=1; i<=n; ++i) {
        if(!visited[i]) {
            dfs1(i);
        }
    }

    reverse(order.begin(), order.end());
    visited.assign(N, false);
    int color = 1;
    for(int i=0; i<order.size(); ++i) {
        if(!visited[order[i]]) {
            dfs2(order[i], color);
            color++;
        }
    }

    cout << color-1 << "\n";
    for(int i=1; i<=n;++i) {
        cout << ans[i] << " ";
    }
    cout << "\n";

    return 0;
}