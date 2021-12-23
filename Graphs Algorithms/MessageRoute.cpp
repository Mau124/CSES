#include<bits/stdc++.h>
using namespace std;

#define N 100001

vector<vector<int>> adj(N);
vector<int> parent(N);
vector<bool> visited(N);
queue<int> q;
int n, m, i, a, b;

int main() {
    vector<int> ans;
    int vertices = 1;
    cin >> n >> m;
    for(i=0; i<m; ++i) {
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    parent[1] = -1;   
    visited[1] = true;
    q.push(1);

    while(!q.empty()) {
        int v = q.front();
        q.pop();

        for(int u: adj[v]) {
            if(!visited[u]) {
                visited[u] = true;
                parent[u] = v;
                q.push(u);
            }
        }
    }
    
    if(visited[n]) {
        i = n;
        while(parent[i] != -1) {
            ans.push_back(i);
            i = parent[i];
            vertices++;
        }
        ans.push_back(1);

        reverse(ans.begin(), ans.end());
        cout << vertices << "\n";
        for(int n: ans)
            cout << n << " ";
        cout << "\n";

    } else {
        cout << "IMPOSSIBLE\n";
    }

    return 0;
}
