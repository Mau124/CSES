#include<bits/stdc++.h>
using namespace std;

#define N 200001

vector<vector<int>> adj(N);
vector<bool> visited(N);
vector<int> subs(N);
int n, i, e;

void dfs(int v) {
    int sub = 0;
    visited[v] = true;

    for(int u: adj[v]) {
        if(!visited[u]) {
            dfs(u);
            sub += subs[u];
        }
    }
    subs[v] = sub + 1;
}

int main() {
    cin >> n;
    for(i=2; i<=n; ++i) {
        cin >> e;
        adj[e].push_back(i);   
    }

    dfs(1);
    for(i=1; i<=n; ++i) 
        cout << subs[i] - 1 << " ";
    cout << "\n";
    return 0;
}
