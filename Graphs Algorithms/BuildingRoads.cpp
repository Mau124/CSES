#include<bits/stdc++.h>
using namespace std;

#define N 100001

int n, m, a, b, i, j;
vector<vector<int>> cities(N);
vector<bool> visited(N);
vector<int> ans;

void dfs(int v) {
    visited[v] = true;
    for(int u : cities[v]) {
        if(!visited[u]) {
            dfs(u);
        }
    }
}

int main() {
    int cc = 0;
    cin >> n >> m;
   
    for(i=0; i<m; ++i) {
        cin >> a >> b;
        cities[a].push_back(b);
        cities[b].push_back(a);
    }

    // Search for connected components
    
    for(i=1; i<=n; ++i) {
        if(!visited[i]) {
            ans.push_back(i); 
            dfs(i);
            cc++;
        }
    }
    
    cout << cc - 1 << "\n";
    for(i=0; i<ans.size()-1; ++i) {
        cout << ans[i] << " " << ans[i+1] << "\n";
    }
    return 0;
}
